<template>
  <el-container>
    <el-header>
      <!--回到首页-->
      <router-link to="/welcome">
        <el-button type="text"s>首页</el-button>
      </router-link>

      <!--个人中心下拉框-->
      <el-col :span="8">
        <el-dropdown @command="handleCommand">
        <el-button type="text" class="el-dropdown-link">
          个人中心
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-plus" command="/user" @click="handleCommand">信息修改</el-dropdown-item>
            <el-dropdown-item icon="el-icon-s-promotion" command="/mail" @click="handleCommand">消息中心</el-dropdown-item>
            <el-dropdown-item icon="el-icon-user" command="/friend" @click="handleCommand">我的好友</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>

      <!--文章列表跳转-->
      <router-link to="/article">
        <el-button type="text">文章列表</el-button>
      </router-link>
      <router-link to="/editor">
        <el-button type="text">新建文章</el-button>
      </router-link>
      <router-link to="/github">
        <el-button type="text">关于我们</el-button>
      </router-link>
      <router-link to="/article">
        <el-button type="text" @click="logout">退出登录</el-button>
      </router-link>

    </el-header>
    <el-container>
      <el-main>
        <!--显示主页-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  methods: {
    handleCommand(command) {
      this.$router.push(command);
    },
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
  }
}
</script>

<style>
.el-container{
  height:100%
}
.el-header {
  background-color: #fff;
  display: flex;
  justify-content: space-between;
  padding-left: 0%;
  align-items: center;
  color: #fff;
  font-size: 10 px;
  > div {
    display: flex;
      align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
.el-menu{
  border-right: none;
}
.el-main {
  background-color: #eaedf1;
  height:100%;
}
.home-container {
  height: 100%;
}
</style>