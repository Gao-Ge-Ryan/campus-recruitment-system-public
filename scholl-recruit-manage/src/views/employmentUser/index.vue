<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="请输入姓名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleQuery" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleQuery">
        查询
      </el-button>
    </div>
    <el-table v-loading="listLoading" style="margin-top:20px" :data="employmentUserList" element-loading-text="Loading" border fit highlight-current-row>
      <!-- <el-table-column align="center" label="职位id" prop="employmentId" />
      <el-table-column align="center" label="用户id" prop="userId" /> -->
      <el-table-column align="center" label="姓名" prop="name" />
      <el-table-column align="center" label="手机" prop="phone" />
      <el-table-column align="center" label="职位" prop="employmentModel.title" />
      <!-- <el-table-column align="center" label="" prop="introduce" /> -->
      <el-table-column align="center" label="简历" prop="resume" width="160">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="preview(row.userResume)">
            预览
          </el-button>
          <el-button type="primary" size="mini" @click="down(row.userResume)">
            下载
          </el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime">
        <template slot-scope="{row}">
          <span>
            {{ row.createTime | parseTime }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="回复时间" prop="replyTime">
        <template slot-scope="{row}">
          <span>
            {{ row.replyTime | parseTime }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="回复人" prop="replyUser" />
      <el-table-column align="center" label="求职人状态" prop="userStatus.msg" />
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="row">
          <el-button v-if="row.row.replyStatus.enumCode === 'Wait_For_Reply'" type="primary" size="mini" @click="handleUpdate(row)">
            发offer
          </el-button>
          <el-tag v-if="row.row.replyStatus.enumCode === 'Agree_With_Induction'">
            已发offer
          </el-tag>

          <!-- <el-popconfirm title="确定要删除此条数据吗？" @confirm="handleDelete(row)">
            <el-button slot="reference" size="mini" type="danger">
              删除
            </el-button>
          </el-popconfirm> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getEmploymentUserList" />

    <!-- 弹框 -->
    <el-dialog :visible.sync="dialogFormVisible" title="发送offer">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:50px;">
        <el-form-item label="联系人" prop="replyUser">
          <el-input v-model="temp.replyUser" />
        </el-form-item>
        <el-form-item label="联系方式" prop="introduce">
          <el-input v-model="temp.introduce" maxlength="11" />
        </el-form-item>
        <el-form-item label="备注" prop="replyContent">
          <el-input v-model="temp.replyContent" type="textarea" />
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
import FileSave from 'file-saver'
export default {
  components: { Pagination },
  filters: {
    parseTime (val) {
      return parseTime(val, '{y}-{m}-{d} {h}:{i}') || '--'
    }
  },
  data () {
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        title: ''
      },
      listLoading: true,
      ExamName: '',
      dialogFormVisible: false,
      dialogStatus: '',
      temp: {
        replyStatus: 'Agree_With_Induction',
        replyUser: '',
        introduce: '',
        replyContent: ''
      },
      visible: false,
      total: 0,
      rules: {
        replyUser: [
          { required: true, message: '请输入联系人', trigger: 'blur' },
          { min: 2, max: 6, message: '长度在2-6个字符', trigger: 'blur' }

        ],
        introduce: [
          { required: true, message: '请输入联系方式', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapState({
      employmentUserList: state => state.employmentUser.employmentUserList,
      employmentReplyStatus: state => state.enumList.employmentReplyStatus,
      employmentUserStatus: state => state.enumList.employmentUserStatus
    })
  },
  mounted () {
    this.getEmploymentUserList()
    this.$store.dispatch('getEmploymentReplyStatus', 'EmploymentReplyStatus')
    this.$store.dispatch('getEmploymentUserStatus', 'EmploymentUserStatus')
    // this.getRoleList()
  },
  methods: {
    // 获取用户角色枚举
    // getRoleList() {
    //   this.$store.dispatch('getExamRoleList', 'ExamRoleEnum')
    // },

    // 获取用户列表
    getEmploymentUserList () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        params: {
          companyId: JSON.parse(localStorage.getItem('company')).id
        }
      }
      this.$store.dispatch('getEmploymentUserList', params).then(res => {
        this.total = res.total
        this.listLoading = false
      }).catch(err => {
        console.log(err)
      })
    },

    // 清空form表单
    resetrForm () {
      this.temp = {
        replyStatus: 'Agree_With_Induction',
        replyUser: '',
        introduce: '',
        replyContent: ''
      }
    },

    // 添加用户弹窗
    addExam () {
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改用户弹窗
    handleUpdate (row) {
      this.dialogFormVisible = true
      this.temp = Object.assign({}, row.row)
      this.temp.replyStatus = 'Agree_With_Induction'
      this.temp.userStatus = row.row.userStatus.enumCode
      this.dialogStatus = 'update'
    },

    // 删除用户
    handleDelete (row) {
      this.$store.dispatch('deleteExamUser', row.row.id).then(res => {
        this.getEmploymentUserList()
        this.$notify({
          title: 'Success',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
      }).catch(err => {
        this.$message.error(err)
      })
    },

    // 添加用户
    addData () {
      this.$store.dispatch('addExamUser', this.temp).then(res => {
        this.dialogFormVisible = false
        this.resetrForm()
        this.getEmploymentUserList()
        this.$notify({
          title: 'Success',
          message: '添加成功',
          type: 'success',
          duration: 2000
        })
      }).catch(err => {
        this.$message.error(err)
      })
    },
    // 修改用户
    updateData () {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('updateEmploymentUser', this.temp).then(res => {
            this.dialogFormVisible = false
            this.getEmploymentUserList()
            this.$notify({
              title: 'Success',
              message: '修改成功',
              type: 'success',
              duration: 2000
            })
          }).catch(err => {
            this.$message.error(err)
          })
        } else {
          return false
        }
      })
    },

    // 按条件查询用户
    handleQuery () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        condition: { title: this.listQuery.title }
      }
      this.$store.dispatch('queryExamUser', params)
    },
    down (item) {
      if (item.extension === '.pdf') {
        var oReq = new XMLHttpRequest()
        var URLToPDF = item.resumeUrl
        oReq.open('GET', URLToPDF, true)
        oReq.responseType = 'blob'
        oReq.onload = function () {
          var file = new Blob([oReq.response], {
            type: 'application/pdf'
          })
          // 保存文件设置文件名
          FileSave(file, item.resumeName)
        }
        oReq.send()
      }
      if (item.extension === '.doc' || item.extension === '.docx') {
        const x = new XMLHttpRequest()
        x.open('GET', item.type, true)
        x.responseType = 'blob'
        x.onload = function () {
          const url = window.URL.createObjectURL(x.response)
          const a = document.createElement('a')
          a.href = url
          a.download = item.resumeName // 名字可自定义
          a.click()
        }
        x.send()
      }
    },
    preview (item) {
      /* TODO */
      if (item.extension === '.doc' || item.extension === '.docx') {
        const href = 'http://localhost:3030/' + item.resumeUrl
        window.open(href, '_blank')
      }
      if (item.extension === '.pdf') {
        window.open(item.resumeUrl, '_blank')
      }
    }
  }

}
</script>
