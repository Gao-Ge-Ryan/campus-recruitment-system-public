<template>
  <div class="main">
    <div class="left">
      <el-form ref="infoForm" :rules="rules" :model="info" label-width="80px" class="demo-ruleForm">
        <el-form-item label="头像">
          <el-upload class="avatar-uploader" action="http://82.157.42.25:5050/file/upload" :show-file-list="false" :on-success="handleAvatarSuccess">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="info.userName" disabled />
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="info.nickName" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="info.phone" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateInfo">
            修改
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="right">
      <div class="inputBox">
        <el-form ref="passwordForm" :rules="passwordRules" :model="passwordForm">
          <el-form-item prop="password">
            <el-input v-model="passwordForm.password" clearable type="text" placeholder="新密码" style="width:300px;margin-bottom:30px" />
          </el-form-item>
          <br>
          <el-form-item prop="authCode">
            <el-input v-model="passwordForm.authCode" clearable type="text" placeholder="验证码" style="width:300px;margin-bottom:30px" />
          </el-form-item>
        </el-form>
      </div>
      <div v-if=" isCheck" class="check" @click="getAuthCode">
        点击获取验证码
      </div>
      <div v-else class="check-false">
        重新获取({{ time }})
      </div>
      <el-button type="primary" @click="updatePassword">
        修改密码
      </el-button>
    </div>
  </div>
</template>
<script>
import { mapState } from 'vuex'
export default {
  data () {
    return {
      info: {},

      isCheck: true, // 显示获取验证码
      time: 60,
      imageUrl: '',
      passwordForm: {
        password: '',
        authCode: ''
      },
      rules: {
        nickName: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
        ]
      },
      passwordRules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '长度不小于6 个字符', trigger: 'blur' }
        ],
        authCode: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.getInfo()
  },
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    handleAvatarSuccess (el) {
      console.log(el)
      this.imageUrl = el.data.accessAddress
      this.$message.success('头像上传成功，点击修改保存')
    },
    getInfo () {
      this.$store.dispatch('getInfo').then(res => {
        console.log(this.userInfo)
        this.info = this.userInfo
        this.imageUrl = this.userInfo.avatar
      })
    },
    updateInfo () {
      this.$refs.infoForm.validate(valid => {
        const params = {
          nickName: this.info.nickName,
          phone: this.info.phone,
          identityNum: this.info.identityNum,
          address: this.info.address,
          avatar: this.imageUrl,
          id: this.userInfo.id
        }
        if (valid) {
          this.$store.dispatch('updateUser', params).then(res => {
            console.log(11111111111)
            this.$message.success('修改成功')
            this.getInfo()
          })
        } else {
          return false
        }
      })
    },

    /* 获取验证码 */
    getAuthCode () {
      /* 获取验证码 */
      this.isCheck = false
      const timer = setInterval(() => {
        if (this.time > 0) {
          this.time--
        }
        if (this.time === 0) {
          this.isCheck = true
          this.time = 60
          clearInterval(timer)
        }
      }, 1000)
      const params = {
        receiver: JSON.parse(localStorage.getItem('userInfo')).sub
      }
      this.$store.dispatch('getAuthCode', params)
    },
    updatePassword () {
      this.$refs.passwordForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('updatePassword', { password: this.passwordForm.password, inputCode: this.passwordForm.authCode }).then(res => {
            this.$message.success('密码修改成功')
          }).catch(err => {
            console.log(err)
            this.$message.error(err)
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.word {
  font-weight: 600;
  font-size: 18px;
}
.main {
  // margin: 0 auto;
  // width: 1200px;
  min-height: calc(100vh - 240px);
  background-color: #fff;
  padding: 60px 30px 30px 100px;
  text-align: left;
  display: flex;
  .left {
    // padding-right: 50px;
    flex: 1;
    border-right: 1px solid #ccc;

    display: flex;
    margin-bottom: 40px;
    .avatar-uploader {
      width: 180px;
      border: 1px dashed #d9d9d9;
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
    .label {
      margin-right: 20px;
    }
    .avator {
      width: 160px;
      height: 160px;
    }
    .el-input {
      width: 300px;
    }

    .username {
      margin-top: 20px;
    }
  }
  .right {
    flex: 1;
    padding-left: 50px;
    .check-false {
      margin-bottom: 30px;
    }
    .check {
      margin-bottom: 30px;
    }
  }
}
</style>
