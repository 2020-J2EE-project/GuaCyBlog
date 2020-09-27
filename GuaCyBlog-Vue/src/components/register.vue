<template>
  <div class="register_container">
    <div class="register_box">
      <div class="avatar_box">
        <img v-if="registerForm.userimg" :src="require('../static/img/'+registerForm.userimg)"   />
      </div>

      <!--添加表单-->
      <el-form
        ref="registerFormRef"
        :model="registerForm"
        :rules="registerRules"
        class="register_form"
        label-width="0px"
        
      >
        <el-button type="text" @click="dialogVisible = true">上传头像</el-button>
        <el-form-item prop="username">
          <el-input v-model="registerForm.username" prefix-icon="iconfont icondenglu" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerForm.password" prefix-icon="iconfont iconmima" placeholder="密码" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="intro">
          <el-input v-model="registerForm.intro" placeholder="个人简介"></el-input>
        </el-form-item>

        <el-form-item class="btns">
          <el-button type="primary" @click="register">注册</el-button>
          <el-button type="info" @click="returnback">返回登陆界面</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-dialog
      title="头像一旦上传无法修改，切记！"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
      style="margin-top:-100px;height=300px"
    >
      <span>
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8890/user/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="registerForm.userimg" :src="require('../static/img/'+registerForm.userimg)" width="200" height="200" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  watch: {
    registerForm : {
      handler: (val, olVal) => {},
      deep: true,
    },
  },
  data() {
    return {
      registerForm: {
        username: "",
        password: "",
        intro:'',
        userimg: "",
      },
      dialogVisible: false,
      registerRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 5,
            max: 20,
            message: "长度在 5 到 20 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "密码为 6~8 位", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.$message_success(res);
      this.registerForm.userimg = res;
      console.log("dd"+this.registerForm.userimg);
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
     // const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
    //  if (!isLt2M) {
      //  this.$message.error("上传头像图片大小不能超过 2MB!");
     // }
      //开始上传
     // if (isJPG && isLT2M == true) {
  //      let fd = new FormData();
    //    fd.append("file", file);
      //  console.log(fd);
    //  }

      return isJPG;
    },

    register() {
      this.$refs.registerFormRef.validate(async (valid) => {
        if (!valid) return;
        // 调用get请求
        const { data: res } = await this.$http.post(
          "user/register",
          this.registerForm
        );
        if (res == "success!") {
          // window.sessionStorage.setItem('flag','ok'); // session 放置
          this.$message.success(res);
          this.$router.push({ path: "/login" });
        } else {
          this.$message.error(res);
          this.$router.push({ path: "/register" });
        }
      });
    },
    returnback() {
      this.$router.push({ path: "/login" });
    },
  },
};
</script>
<style lang="less" scoped>
// 跟节点样式
.register_container {
  background-color: #b3d4fc;
  height: 100%;
}

// 登录框
.register_box {
  width: 450px;
  height: 350px;
  background-color: #fff;
  border-radius: 3px; // 圆角
  position: absolute; // 绝对定位
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%); // 根据自己位置 以自己为长度位移
  // 头像框
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%; // 加圆角
  
    box-shadow: 0 0 10px #ddd; // 盒子阴影
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%; // 加圆角
      background-color: #eee;
    }
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }
  .avatar-uploader-icon {
    transform: translate(-13%, -13%);
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    display: block;
  }
  .btns {
    display: flex; // 弹性布局
    justify-content: flex-end; // 尾部对齐
  }
  .register_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box; // 设置边框
  }
}
</style>