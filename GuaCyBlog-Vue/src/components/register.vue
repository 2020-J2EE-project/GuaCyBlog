<template>
  <div class="register_container">
    <div class="register_box">
      <div class="avatar_box">
        <!--头像-->
        <img src="../assets/logo.png" alt />
      </div>
      <!--添加表单-->
      <el-form
          ref="registerFormRef"
          :model="registerForm"
          :rules="registerRules"
          class="register_form"
          label-width="0px"
      >
        <el-form-item prop="username">
          <el-input v-model="registerForm.username" prefix-icon="iconfont icondenglu"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerForm.password" prefix-icon="iconfont iconmima" type="password"></el-input>
        </el-form-item>

        <el-form-item class="btns">
          <el-button type='primary' @click='register'>注册</el-button>
          <el-button type='info' @click='returnback'>返回登陆界面</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      registerForm: {
        username: "",
        password: "",
      },
      registerRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 20, message: "长度在 5 到 20 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "密码为 6~8 位", trigger: "blur" }
        ],

      }
    };
  },
  methods: {

    register() {
      this.$refs.registerFormRef.validate(async valid => {
        if (!valid) return;
        // 调用get请求
        const {data :res} = await this.$http.post("user/register", this.registerForm);
        if (res == "success!" ) {
          // window.sessionStorage.setItem('flag','ok'); // session 放置
          this.$message.success(res);
          this.$router.push({ path: "/login"});
        }else{
          this.$message.error(res);
          this.$router.push({ path: "/register"});
        }
      });
    },
    returnback(){
      this.$router.push({path:"/login"});
    }
  }
};
</script>

<style lang="less" scoped>

// 跟节点样式
.register_container {
  background-color: #2b4b6b;
  height: 100%;
}

// 登录框
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
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

<style lang="less" scoped>

// 跟节点样式
.register_container {
  background-color: #2b4b6b;
  height: 100%;
}

// 登录框
.register_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
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
  .register_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;// 设置边框
  }
}
</style>