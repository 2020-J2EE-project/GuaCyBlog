<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>个人中心</el-breadcrumb-item>
    <el-breadcrumb-item>信息修改</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区 -->
    <el-card>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名:">
          <el-input v-model="User.username">
            <i class="el-icon-edit el-input__icon" slot="suffix"></i>
          </el-input>
        </el-form-item>

        <el-form-item label="密码:">
          <el-input v-model="User.password">
            <i class="el-icon-edit el-input__icon" slot="suffix"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input type="textarea" v-model="UserInfo.intro"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交修改</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user:{
        id:window.sessionStorage.getItem("userId")
      },
      User:[],
      UserInfo:[],
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    async getUser() {
        this.$message_success(this.user.id);
        const{data:res} = await this.$http.post("user/findById",this.user);
        const{cap:res2} = await this.$http.post("userInfo/getById",this.user);
        this.User = res.data;
        this.UserInfo = res2.data;
    }
  }
}
</script>

<style>
.el-form-item{
  font-weight: ;
}
</style>