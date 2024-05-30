<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="请输入职位名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleQuery" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleQuery">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="addOccupation">
        新建职位
      </el-button>
    </div>
    <el-table v-loading="listLoading" style="margin-top:20px" :data="singleEmploymentList" element-loading-text="Loading" border fit highlight-current-row :expand-row-keys="expands" :row-key="getRowKeys" @expand-change="expandChange">
      <el-table-column type="expand">
        <template slot-scope="{row}">
          <div class="open-diag">
            <div class="content-item">
              <div class="title">
                地点：
              </div>
              <div class="words">
                {{ row.position }}
              </div>
            </div>
            <div class="content-item">
              <div class="title">
                工资范围：
              </div>
              <div class="words">
                {{ row.minSalary }}-{{ row.maxSalary }}k
              </div>
            </div>
            <div class="content-item">
              <div class="title">
                待遇：
              </div>
              <div class="words">
                {{ row.treatment }}
              </div>
            </div>
            <div class="content-item">
              <div class="title">
                职位描述：
              </div>
              <div class="words">
                {{ row.description }}
              </div>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" prop="title" />
      <el-table-column align="center" label="学历" prop="education.msg" />
      <el-table-column align="center" label="提交时间" prop="createTime">
        <template slot-scope="{row}">
          <span>
            {{ row.createTime |parseTime }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="审核状态" prop="salary">
        <template slot-scope="{row}">
          <el-tag v-if="row.status === 0">
            待审核
          </el-tag>
          <el-tag v-if="row.status === 1" type="success">
            审核通过
          </el-tag>
          <el-tag v-if="row.status === 2" type="warning">
            驳回
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="所属公司" prop="companyId">
        <template slot-scope="{row}">
          <span>
            {{ row.companyModel && row.companyModel.name }}
          </span>
        </template>
      </el-table-column>>
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="{row}">
          <el-popconfirm title="确定要删除此条数据吗？" @confirm="handleDelete(row)">
            <el-button slot="reference" size="mini" type="danger">
              删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getSingleEmploymentList" />

    <!-- 弹框 -->
    <el-dialog :visible.sync="dialogFormVisible" :title="type === 'add'? '新增数据' : '修改数据'">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="标题" prop="title">
          <el-input v-model="temp.title" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="temp.description" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-select v-model="temp.education" class="filter-item" placeholder="请选择">
            <el-option v-for="item in employmentEducationEnum" :key="item.enumCode" :label="item.msg" :value="item.enumCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="temp.position" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="工资" prop="salary">
          <el-input v-model="temp.salary" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="待遇" prop="treatment">
          <el-input v-model="temp.treatment" placeholder="请输入" />
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
      expands: [],
      type: 'add',
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
        title: '',
        description: '',
        education: '',
        position: '',
        salary: '',
        // status: '',
        treatment: ''
      },
      visible: false,
      total: 0
    }
  },
  computed: {
    ...mapState({
      singleEmploymentList: state => state.employment.singleEmploymentList,
      employmentEducationEnum: state => state.enumList.employmentEducationEnum,
      employmentReplyStatus: state => state.enumList.employmentReplyStatus
      // employmentUserStatus: state => state.enumList.employmentUserStatus
    })
  },
  mounted () {
    this.getSingleEmploymentList()
    // this.getRoleList()
    this.$store.dispatch('getEmploymentEducationEnum', 'EmploymentEducationEnum')
    this.$store.dispatch('getEmploymentReplyStatus', 'EmploymentReplyStatus')
    // this.$store.dispatch('getEmploymentUserStatus', 'EmploymentUserStatus')
  },
  methods: {
    getRowKeys (row) {
      return row.id
    },
    expandChange (row, expandedRows) {
      const that = this
      console.log(expandedRows)
      if (expandedRows.length) { // 说明展开了
        that.expands = []
        if (row) {
          that.expands.push(row.id)// 只展开当前行id
        }
      } else { // 说明收起了
        that.expands = []
      }
    },
    // 获取用户角色枚举
    // getRoleList() {
    //   this.$store.dispatch('getExamRoleList', 'ExamRoleEnum')
    // },

    // 获取用户列表
    getSingleEmploymentList () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize
      }
      this.$store.dispatch('getSingleEmploymentList', params).then(res => {
        console.log(this.singleEmploymentList)
        this.total = res.total
        this.listLoading = false
      }).catch(err => {
        console.log(err)
      })
    },

    // 清空form表单
    resetrForm () {
      this.temp = {
        title: '',
        description: '',
        education: '',
        position: '',
        salary: '',
        // status: '',
        treatment: ''
      }
    },

    // 添加用户弹窗
    addExam () {
      this.type = 'add'
      this.resetrForm()
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改用户弹窗
    handleUpdate (row) {
      this.type = ''
      this.temp = Object.assign({}, row)
      this.temp.education = row.education.enumCode
      this.temp.status = 1
      // this.dialogFormVisible = true
      this.dialogStatus = 'update'
      this.updateData()
    },

    // 删除用户
    handleDelete (row) {
      console.log(123)
      this.$store.dispatch('deleteEmployment', row.id).then(res => {
        this.getSingleEmploymentList()
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
      this.$store.dispatch('addEmployment', this.temp).then(res => {
        this.dialogFormVisible = false
        this.getSingleEmploymentList()
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
      this.$store.dispatch('updateEmployment', this.temp).then(res => {
        this.getSingleEmploymentList()
        this.$notify({
          title: 'Success',
          message: '修改成功',
          type: 'success',
          duration: 2000
        })
        this.dialogFormVisible = false
      }).catch(err => {
        this.$message.error(err)
      })
    },

    // 按条件查询用户
    handleQuery () {
      console.log(this.examIntroductionList)
      this.listQuery.pageNum = 1
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        params: { title: this.listQuery.title }
      }
      this.$store.dispatch('getSingleEmploymentList', params).then(res => {
        this.total = res.total
      })
    },
    /* 新建职位 */
    addOccupation () {
      this.$router.push({ path: '/occupation/add' })
    }
  }

}
</script>
<style lang="scss" scoped>
.content-item {
  display: flex;
  margin-bottom: 20px;
  .title {
    width: 100px;
    margin-right: 6 px;
    font-weight: 600;
    font-size: 18px;
    text-align: right;
  }
  .words {
    flex: 1;
    // display: flex;
    // align-items: center;
    font-weight: 500;
    font-style: 16px;
    line-height: 1.5rem;
  }
}
</style>
