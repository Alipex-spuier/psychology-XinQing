<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
           class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="adminName">
      <el-input type="text" v-model="ruleForm2.adminName" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="adminPassword">
      <el-input type="password" v-model="ruleForm2.adminPassword" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">登录
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { requestLogin } from '../api/api'
export default {
  data () {
    return {
      logining: false,
      ruleForm2: {
        adminName: 'admin1',
        adminPassword: '123'
      },
      rules2: {
        adminName: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        adminPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      checked: true
    }
  },
  methods: {
    handleReset2 () {
      this.$refs.ruleForm2.resetFields()
    },
    handleSubmit2 (ev) {
      this.$refs.ruleForm2.validate((valid) => {
        if (valid) {
          this.logining = true
          var loginParams = { adminName: this.ruleForm2.adminName, adminPassword: this.ruleForm2.adminPassword }
          requestLogin(loginParams).then(res => {
            console.log(res)
            this.logining = false
            if (res.data.code !== 200) {
              this.$message({
                message: res.data.msg,
                type: 'error'
              })
            } else {
              sessionStorage.setItem('user', JSON.stringify(res.data.data))
              sessionStorage.setItem('auth', JSON.stringify(res.headers.authorization))
              this.$router.push({ path: '/user' })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}

</script>

<style lang="scss" scoped>
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;

    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }

    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>
