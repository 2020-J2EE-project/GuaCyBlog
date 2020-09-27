<template>
  <el-menu default-active="1-1" class="el-menu-vertical-demo"
           :collapse="isCollapse">
    <el-submenu index="1" style="margin-left: -50px;">
      <template slot="title">
        <i class="el-icon-user" />
        <span slog="title" style="margin-left: -3px">个人中心</span>
      </template>
      <el-menu-item-group>
        <template slot="title">my center</template>
        <el-menu-item index="1-1">
          <el-button
              type="text"
              @click="goto('/welcome','userinfo')">信息修改</el-button>
        </el-menu-item>
        <el-menu-item index="1-2">
          <el-button
              type="text"
              @click="goto('/welcome','following')">我的关注</el-button></el-menu-item>
        <el-menu-item index="1-3">
          <el-button
              type="text"
              @click="goto('/welcome','follower')">我的粉丝</el-button></el-menu-item>
        <el-menu-item index="1-4">
          <el-button
              type="text"
              @click="goto('/welcome','mail')">消息中心</el-button></el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="1" style="margin-left: -50px">
      <template slot="title">
        <i class="el-icon-document" />
        <span slog="title" style="margin-left: -3px">文章中心</span>
      </template>
      <el-menu-item-group>
        <template slot="title">my article</template>
        <el-menu-item index="2-1"><span slot="title"><el-button
            type="text"
            @click="goto('/welcome','articleList')">文章列表</el-button></span></el-menu-item>
        <el-menu-item index="2-2"><span slot="title"><el-button
            type="text"
            @click="goto('/editor','editor')">新建文章</el-button></span></el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  data:{
    inject: ['reload'],
    presentCard:'',
  },
  methods:{
    async goto(x, y) {
      window.sessionStorage.setItem("visitingPage", y);
      await this.$router.push({path: x, query: {visitingPage: y}});
    },
    async goto2(x, y) {
      window.sessionStorage.setItem("visitingPage", y);
      await this.$router.push({path: x, visitingPage: y});

    }
  }
}
</script>