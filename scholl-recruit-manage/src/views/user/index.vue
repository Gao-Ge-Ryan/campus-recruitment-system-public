<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.userName" placeholder="请输入用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleQuery" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleQuery">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="addUser">
        Add
      </el-button>
    </div>
    <el-table v-loading="listLoading" style="margin-top:20px" :data="userList" element-loading-text="Loading" border fit highlight-current-row max-height="470">
      <el-table-column align="center" type="index" />
      <el-table-column align="center" label="头像" prop="avatar">
        <template>
          <div>
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
          </div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" prop="userName" />
      <el-table-column align="center" label="昵称" prop="nickName">
        <template slot-scope="{row}">
          <span>
            {{ row.nickName || '--' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机号" prop="phone">
        <template slot-scope="{row}">
          <span>
            {{ row.phone || '--' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="身份证号" prop="identityNum">
        <template slot-scope="{row}">
          <span>
            {{ row.identityNum || '--' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="居住地址" prop="address">
        <template slot-scope="{row}">
          <span>
            {{ row.address || '--' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="角色" prop="role.msg" />
      <el-table-column align="center" label="积分" prop="integral" />
      <el-table-column align="center" label="信誉" prop="credit" />
      <el-table-column align="center" label="创建时间" prop="createTime">
        <template slot-scope="{row}">
          <span>{{ row.createTime | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="修改时间" prop="updateTime">
        <template slot-scope="{row}">
          <span>{{ row.updateTime | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="操作" width="200">
        <template slot-scope="row">
          <!-- <el-button type="primary" size="mini" @click="handleUpdate(row)">
            修改
          </el-button> -->
          <el-popconfirm title="确定要删除此条数据吗？" @confirm="handleDelete(row)">
            <el-button slot="reference" size="mini" type="danger">
              删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getUserList" />

    <!-- 弹框 -->
    <el-dialog :visible.sync="dialogFormVisible" :title="type === 'add'? '新增数据' : '修改数据'">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="temp.userName" :disabled="type === 'add' ? false : true" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item v-if="type === 'add'" label="密码" prop="password">
          <el-input v-model="temp.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item prop="role" label="角色">
          <el-select v-model="temp.role" class="filter-item" placeholder="请选择角色">
            <el-option v-for="item in roleList" :key="item.enumCode" :label="item.msg" :value="item.enumCode" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?addData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import { parseTime } from '@/utils/time'

export default {
  components: { Pagination },
  filters: {
    parseTime (val) {
      return parseTime(val, '{y}-{m}-{d} {h}:{i}') || '--'
    }
  },
  data () {
    return {
      type: 'add',
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        userName: ''
      },
      listLoading: true,
      userName: '',
      dialogFormVisible: false,
      dialogStatus: '',
      temp: {
        password: '',
        role: '',
        userName: ''
      },
      visible: false,
      total: 0,
      rules: {
        userName: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }

        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '长度不小于6 个字符', trigger: 'blur' }
        ],
        role: [
          { required: true, message: '请选择角色', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapState({
      userList: state => state.user.userList,
      roleList: state => state.enumList.roleList
    })
  },
  mounted () {
    this.getUserList()
    this.getRoleList()
  },
  methods: {
    // 获取用户角色枚举
    getRoleList () {
      this.$store.dispatch('getUserRoleList', 'UserRoleEnum')
    },

    // 获取用户列表
    getUserList () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize
      }
      this.$store.dispatch('getUserList', params).then(res => {
        this.total = res.total
        this.listLoading = false
      }).catch(err => {
        console.log(err)
      })
    },

    // 清空form表单
    resetrForm () {
      this.temp = {
        password: '',
        role: '',
        userName: ''
      }
    },

    // 添加用户弹窗
    addUser () {
      this.resetrForm()
      this.type = 'add'
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
    },
    // 修改用户弹窗
    handleUpdate (row) {
      this.type = ''
      this.temp = Object.assign({}, row.row)
      this.temp.role = row.row.role.enumCode
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
    },

    // 删除用户
    handleDelete (row) {
      this.$store.dispatch('deleteUser', row.row.id).then(res => {
        this.$notify({
          title: 'Success',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
        this.getUserList()
      }).catch(err => {
        this.$message.error(err)
      })
    },

    // 添加用户
    addData () {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('addUser', this.temp).then(res => {
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: '添加成功',
              type: 'success',
              duration: 2000
            })
            this.resetrForm()
            this.getUserList()
          }).catch(err => {
            this.$message.error(err)
            this.$message.error('error')
          })
        } else {
          return false
        }
      })
    },
    // 修改用户
    updateData () {
      this.$store.dispatch('updateUser', this.temp).then(res => {
        this.$notify({
          title: 'Success',
          message: '修改成功',
          type: 'success',
          duration: 2000
        })
        this.dialogFormVisible = false
        this.getUserList()
      }).catch(err => {
        this.$message.error(err)
      })
    },

    // 按条件查询用户
    handleQuery () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        condition: { userName: this.listQuery.userName }
      }
      this.$store.dispatch('queryUser', params).then(res => {
        this.total = res.total
      })
    }
  }

}
</script>
