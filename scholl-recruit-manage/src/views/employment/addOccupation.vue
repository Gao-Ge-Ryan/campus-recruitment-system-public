<template>
  <div class="main">
    <div class="title">
      发布职位
    </div>
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="right" label-width="100px" style="width:800px; margin-left:50px;">
      <el-form-item label="职位名称：" prop="title">
        <el-input v-model="temp.title" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="职位描述：" prop="description">
        <el-input v-model="temp.description" type="textarea" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="学历：" prop="education">
        <el-select v-model="temp.education" class="filter-item" placeholder="请选择">
          <el-option v-for="item in educationList" :key="item.enumCode" :label="item.msg" :value="item.enumCode" />
        </el-select>
      </el-form-item>
      <el-form-item label="地址：" prop="position">
        <el-input v-model="temp.position" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="工资：" required="">
        <el-col :span="10">
          <el-form-item prop="minSalary">
            <el-input v-model="temp.minSalary" type="number" maxlength="2" placeholder="最低工资" />
          </el-form-item>
        </el-col>
        <el-col :span="1" style="text-align: center;">
          至
        </el-col>
        <el-col :span="10">
          <el-form-item prop="maxSalary">
            <el-input v-model="temp.maxSalary" type="number" maxlength="3" placeholder="最高工资" />
          </el-form-item>
        </el-col>
        <el-col :span="1" style="text-align: center;">
          K
        </el-col>
      </el-form-item>
      <el-form-item label="待遇：" prop="treatment">
        <el-input v-model="temp.treatment" placeholder="请输入" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">
        重置
      </el-button>
      <el-button type="primary" @click="type==='add'?addData():updateData()">
        发布
      </el-button>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      temp: {
        title: '',
        description: '',
        education: '',
        position: '',
        minSalary: '',
        maxSalary: '',
        // status: '',
        treatment: '',
        companyId: JSON.parse(localStorage.getItem('company')).id
      },
      type: 'add',
      educationList: [{ enumCode: 'Bachelor_Or_Above_Degree', msg: '本科及以上学历' }, { enumCode: 'College_Degree_Or_Above', msg: '专科及以上学历' }, { enumCode: 'Master_Degree_Or_Above', msg: '研究生及以上学历' }],
      rules: {
        title: [
          { required: true, message: '请输入职位名称', trigger: 'blur' },
          { min: 2, max: 12, message: '长度在2-12个字符', trigger: 'blur' }

        ],
        description: [
          { required: true, message: '请输入职位描述', trigger: 'blur' },
          { min: 2, message: '长度不小于2个字符', trigger: 'blur' }
        ],
        education: [
          { required: true, message: '请选择学历', trigger: 'blur' }
        ],
        position: [
          { required: true, message: '请输入地址', trigger: 'blur' },
          { min: 2, message: '长度不小于2个字符', trigger: 'blur' }
        ],
        minSalary: [
          { required: true, message: '请输入最低工资', trigger: 'blur' }
        ],
        maxSalary: [
          { required: true, message: '请输入最高工资', trigger: 'blur' }
        ],
        treatment: [
          { required: true, message: '待遇', trigger: 'blur' },
          { min: 2, max: 100, message: '长度在2-100个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    resetTemp () {
      this.temp = {
        title: '',
        description: '',
        education: '',
        position: '',
        minSalary: '',
        maxSalary: '',
        // status: '',
        treatment: '',
        companyId: JSON.parse(localStorage.getItem('company')).id
      }
    },
    // 发布职位
    addData () {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('addEmployment', this.temp).then(res => {
            this.dialogFormVisible = false
            this.resetTemp()
            this.$notify({
              title: 'Success',
              message: '添加成功',
              type: 'success',
              duration: 2000
            })
          }).catch(err => {
            this.$message.error(err)
          })
        } else { return false }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.main {
  padding: 30px;
}
</style>
