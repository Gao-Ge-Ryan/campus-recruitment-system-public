<template>
  <div class="main">
    <el-form ref="infoForm" :model="infoForm" label-width="130px" class="demo-ruleForm">
      <el-form-item label="统一社会信用代码" prop="companyNumber">
        <el-input v-model="infoForm.companyNumber" />
      </el-form-item>
      <el-form-item label="公司名称" prop="name">
        <el-input v-model="infoForm.name" />
      </el-form-item>
      <el-form-item label="公司logo" prop="name">
        <el-upload class="avatar-uploader" action="http://82.157.42.25:5050/file/upload" :show-file-list="false" :on-success="handleAvatarSuccess">
          <img v-if="infoForm.companyLogo" :src="infoForm.companyLogo" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item label="公司人数" prop="number">
        <el-input v-model="infoForm.number" type="number" />
      </el-form-item>
      <el-form-item label="公司类别" prop="category">
        <el-select v-model="infoForm.category" placeholder="请选择">
          <el-option v-for="item in categoryList" :key="item.enumCode" :label="item.msg" :value="item.enumCode" />
        </el-select>
      </el-form-item>
      <el-form-item label="公司性质" prop="nature">
        <el-select v-model="infoForm.nature" placeholder="请选择">
          <el-option v-for="item in natureList" :key="item.enumCode" :label="item.msg" :value="item.enumCode" />
        </el-select>
      </el-form-item>
      <el-form-item label="企业介绍" prop="description">
        <el-input v-model="infoForm.description" type="textarea" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="updateCompany">
          提交审核
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { getCompanyInfo } from '@/api/login'
export default {
  data () {
    return {
      infoForm: {
        companyNumber: '',
        name: '',
        description: '',
        number: '',
        category: '',
        nature: '',
        companyLogo: ''
      },
      natureList: [{ enumCode: 'Private_Enterprises', msg: '私企' }, { enumCode: 'State_Owned_Enterprise', msg: '国企' }, { enumCode: 'Listed_Company', msg: '上市公司' }],
      categoryList: [{ enumCode: 'Internet', msg: '互联网' }, { enumCode: 'Financial', msg: '金融' }, { enumCode: 'Education', msg: '教育' }]
    }
  },
  mounted () {
    this.getCompanyInfo()
  },
  methods: {
    getCompanyInfo () {
      getCompanyInfo().then(res => {
        let company = ''
        company = JSON.stringify(res)
        localStorage.setItem('company', company)
        this.infoForm = Object.assign({}, res)
        this.infoForm.status = res.status.enumCode
        this.infoForm.nature = res.nature.enumCode
        this.infoForm.category = res.category.enumCode
        this.info.status = res.status.enumCode
      })
    },
    handleAvatarSuccess (el) {
      console.log('高歌')
      console.log(el)
      this.infoForm.companyLogo = el.data.accessAddress
      console.log(el.data.accessAddresscc)
    },
    updateCompany () {
      this.$store.dispatch('updateCompany', this.infoForm).then(res => {
        this.$message.success('操作成功')
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.main {
  padding: 30px;

  .avatar-uploader {
    width: 180px;
    height: 180px;
    border: 1px dashed #ccc;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader:hover {
    border-color: #409eff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
}
</style>
