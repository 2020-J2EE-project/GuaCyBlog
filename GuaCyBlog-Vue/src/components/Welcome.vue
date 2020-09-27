<template>
  <!--头部按钮  -->
    <el-container>
      <el-main class="my-main">
        <!--资料卡-->
        <el-container direction="horizontal">
          <el-col span="40" class="my-col">
            <el-card class="my-card">
              <el-avatar>
                <img v-if="this.user.userimg" :src="require('../static/img/'+this.user.userimg)"   />
              </el-avatar>
              <div>{{this.user.username}}</div>
              <div class="intro-text">{{this.user.intro}}</div>
              <el-divider></el-divider>
              <div>
                <el-button type="text" @click="goto('/article')">
                  文章数 {{this.user.articleNum}}
                </el-button>
                <el-divider direction="vertical"></el-divider>
                <el-button type="text" @click="goto('/friend')">
                  粉丝数 {{this.user.followerNum}}
                </el-button>
                <el-divider direction="vertical"></el-divider>
                <el-button type="text" @click="goto('/friend')">
                  关注数 {{this.user.followingNum}}
                </el-button>
              </div>
              <el-divider>
              </el-divider>
              <!--标签展示-->
              <el-col span="40" style="line-height:40px">
                <div style="margin-top: -30px;">
                  <tag ref="guaguatag"></tag>
                </div>
              </el-col>
            </el-card>
          </el-col>
          <!--文章显示-->
          <el-col span="100" class="my-col2">
            <el-card class="my-card">
              <div v-if="visitingPage=='following'">
                <div>正在关注</div>
                <following></following>
              </div>
              <div v-else-if="visitingPage=='follower'">
                <div>粉丝</div>
                <follower></follower>
              </div>
              <div v-else-if="visitingPage=='mail'">
                <div>消息中心</div>
                <mail></mail>
              </div>
              <div v-else-if="visitingPage=='userinfo'">
                <div>信息修改</div>
                <User></User>
              </div>
              <div v-else>
                  {{myTagName}}
                <Article></Article>
              </div>
            </el-card>
          </el-col>
        </el-container>
      </el-main>
    </el-container>
</template>
<script>
import tag from '../components/admin/tag.vue';
import MyMenu from "@/components/MyMenu";
import Article from '@/components/Article';
import follower from "@/components/admin/follower";
import following from "@/components/admin/following";
import mail from "@/components/admin/Mail"
import User from "@/components/admin/User"
export default {
  watch: {
    $route(to, from) {
      this.myTagName = this.$refs.guaguatag.myTagName;
      this.articleList = this.$route.query.articleList;
      this.visitingPage = this.$route.query.visitingPage;
    },
  },
  data() {
    return {
      articleList:[],
      allList:[],
      visitingPage:window.sessionStorage.getItem("visitingPage"),
      user:{
        username:'',
        intro:'',
        id:window.sessionStorage.getItem("visitingId"),
        userimg:window.sessionStorage.getItem("userimg"),
      },
      connect:{
        userId:window.sessionStorage.getItem("visitingId"),
        tagName:''
      },
      myTagName:'All Blogs',
      commentList:[],
    }
  },
  components :{
    MyMenu, tag, Article,
    follower, following,
    mail,User
  },
  inject: ['reload'],
  created() {
    this.getUser();
    this.getTags();
    this.getArticleList();
  },
  mounted() {
    this.myTagName = this.$refs.guaguatag.myTagName;
  },
  methods: {
    async getUser() {
      const{data:res} = await this.$http.post("user/findById",this.user);
      this.user = res.data;
      console.log("user:",this.user);
    },
    goto(x) {
      this.$router.push({path:x});
    },
  }
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-button{
  width: 64px;
}
.el-card{
  line-height: 30px;
}
.el-main{
  font-family:Consolas;
}
.intro-text{
  font-size: 10px;
}

.my-aside{
  margin-top: 20px;
  line-height: 50px;
  margin-left: -5px;
  margin-right: -5px;
}
.my-card{
  alignment: center;
  min-width: 300px;
  min-height: 288px;
}
.article-card{
  alignment: center;
  min-width: 500px;
}
.my-col{
  float: unset;
  margin-left: 50px;
  margin-right: 50px;
}
.my-col2{
  margin-left:0px;
}
</style>
