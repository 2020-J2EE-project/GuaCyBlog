<template>
  <div class="login_container">
    <div class="login_box">
      <!--添加表单-->
      <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginRules"
          class="login_form"
          label-width="0px"
      >
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont icondenglu" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="iconfont iconmima" type="password" clearable></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="register">注册</el-button>
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        username: "guagua",
        password: "123456"
      },
      loginRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 20, message: "长度在 1 到 20 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 1, max: 8, message: "密码为 1~8 位", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    resetLoginForm() {
      // 直接调用refs里的方法
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      //因为这个ref="loginFormRef"
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        // 调用get请求
        const {data :res} = await this.$http.post("user/login", this.loginForm);
        if (res.msg == "success" ) {
          window.sessionStorage.setItem('flag','ok'); // session 放置
          window.sessionStorage.setItem('userId',res.userid);
          window.sessionStorage.setItem('visitingId',res.userid);
          window.sessionStorage.setItem('username',res.username);
          window.sessionStorage.setItem('userimg',res.userimg);
          window.sessionStorage.setItem('visitingPage',"articleList");
          //visitingPage:articleList,showArticle,userSetting,mailBox,following,follower,search
          await this.$router.push({path: "/welcome"});
        }else{
          this.$message_error(res);
        }
      });
    },
    register() {
      this.$router.push("/register")
    }

  }
};
</script>

<style lang="less" scoped>

// 跟节点样式
.login_container {
  background-color: #b3d4fc;
  height: 100%;
}

// 登录框
.login_box {
  width: 450px;
  height: 300px;
  background-color: #FFF;
  border-radius: 3px;// 圆角
  position: absolute;// 绝对定位
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);// 根据自己位置 以自己为长度位移
  // 头像框
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%; // 加圆角
    padding: 10px;
    box-shadow: 0 0 10px #ddd;// 盒子阴影
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%; // 加圆角
      background-color: #eee;
    }
  }
  .btns {
    display: flex;// 弹性布局
    justify-content: flex-end; // 尾部对齐
  }
  .login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;// 设置边框
  }
}
</style>