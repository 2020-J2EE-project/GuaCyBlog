<template>
  <div>
    <!-- 卡片视图区 -->
    <el-card>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item>
        </el-form-item>
        <el-form-item label="用户名:">
          <el-input v-model="User2.username">
            <i class="el-icon-edit el-input__icon" slot="suffix"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="密码:">
          <el-input v-model="User2.password" span="4">
            <i class="el-icon-edit el-input__icon" slot="suffix"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input aria-placeholder="User.intro" type="textarea" v-model="User2.intro">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交修改</el-button>
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
      User2:{
        id:'',
        username:'',
        password:'',
        intro:''
      },
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      fileList: []
    }
  },
  created() {
    this.getUser()
  },
  inject:['reload'],
  methods: {
    onSubmit: async function () {
      console.log("hidpi");
      const {data: res2} = await this.$http.post("user/updateById", this.User2);
      const {data: res} = await this.$http.post("user/findById", this.user);
      this.User2 = res.data;
      this.reload();
      this.$message_success("用户信息修改成功!");
    },
    async getUser() {
        const{data:res} = await this.$http.post("user/findById",this.user);
        this.User2 = res.data;
    },
  }
}
</script>

