<template>
  <el-container>
    <el-header>
      <!--前进后退-->
      <div>
        <el-button type="text" @click="goback(x)">
          <i class="el-icon-arrow-left"></i>
        </el-button>
        <el-button type="text" @click="goback(1)">
          <i class="el-icon-arrow-right"></i>
        </el-button>
        <router-link to="/welcome">
          <el-button type="text" @click="goHome">首页</el-button>
        </router-link>
      </div>
      <div>
      <router-link to="/github">
        <el-button type="text">关于我们</el-button>
      </router-link>
      <router-link to="/article">
        <el-button type="text" @click="logout">退出登录</el-button>
      </router-link>
      </div>
      <!--文章列表跳转-->
    </el-header>
    <el-main>
      <div>
        <el-row :gutter="15">
          <el-col :span="2"><div class="grid-content bg-purple" @click="asideChange()">|||</div></el-col>
          <div v-if="visitingPage=='Article'">
            <el-col :span="2">
              <div class="grid-content bg-purple" @click="asideChangeA()">
                |||
              </div></el-col>
          </div>
        </el-row>
      </div>
      <el-container>
        <div v-if="visitingPage=='editor'||visitingPage=='Article'">
          <el-aside :width="isCollapse?'0px':'201px'" class="my-aside">
            <MyMenu></MyMenu>
          </el-aside>
        </div>
        <div v-else style="width: 210px">
          <el-aside  class="my-aside">
            <div v-if="isCollapse">
              <MyMenu></MyMenu>
            </div>
          </el-aside>
        </div>
        <el-container>
          <router-view></router-view>
        </el-container>
      </el-container>
    </el-main>
  </el-container>
</template>
<script>
import MyMenu from "@/components/MyMenu";
export default {
  watch: {
    // visitingPage: {
    //   handler : (val,olVal) =>{
    //   },
    //   deep: true
    // },
    $route(to, from) {
      this.visitingPage = window.sessionStorage.getItem("visitingPage");
      console.log("now-page:",this.visitingPage);
    },
  },
  data(){
    return{
      isCollapse:true,
      isCollapseA:true,
      visitingPage:window.sessionStorage.getItem("visitingPage"),
    }
  },
  components:{
    MyMenu
  },
  created() {
    window.addEventListener('setItem', ()=> {
      this.newVal = sessionStorage.getItem('visitingPage');
    })
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    goback(x) {
      this.$router.go(x);
    },
    goHome() {
      let id = window.sessionStorage.getItem("userId");
      window.sessionStorage.setItem("visitingId", id);
      window.sessionStorage.setItem("visitingPage", "articleList");
      this.$router.push('/welcome');
    },
    asideChange() {
      this.isCollapse = !this.isCollapse;
      this.$message_success(this.isCollapse);
    },
    asideChangeA:
        function () {
          this.isCollapseA = !this.isCollapseA;
          this.$message_success(this.isCollapseA);
          this.$router.push({path: '/show', query: {isCollapseA: this.isCollapseA}});
      }
    }
}
</script>

<style>
.el-container{
  height:100%;
  line-height: 50px;
}
.el-header {
  background-color: #fff;
  display: flex;
  justify-content: space-between;
  padding-left: 0%;
  align-items: center;
  color: #cccccc;
  font-size: 10px;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  /*import*/
  text-align: center;
  line-height: 50px;
}
.my-aside{
  margin-top: 20px;
  line-height: 50px;
  margin-left: -5px;
  margin-right: -40px;
}
</style>