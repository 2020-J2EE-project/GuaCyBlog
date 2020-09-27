<template>
  <div class="friend">
    <el-main>
          <el-input placeholder="输入用户名" v-model="v">
            <el-button slot="append" icon="el-icon-search" type="text" @click="getlist"></el-button>
          </el-input>
          <!--个人资料卡-->
          <div v-for="(user,index) in suserlist" :key="index">
            <el-card>
              <el-button
                style="float: right; padding: 3px 0;"
                type="text"
                @click="addfollow(user.id)"
              >关注</el-button>
              <el-avatar>
                <img v-if="user.userimg" :src="user.userimg"   />
              </el-avatar>
              <div>{{user.username}}</div>
              <div class="intro-text">{{user.intro}}</div>
              <el-divider></el-divider>
              <div>
                <el-button type="text">文章数:{{user.articleNum}}</el-button>
                <el-divider direction="vertical"></el-divider>
                <el-button type="text">粉丝数:{{user.followerNum}}</el-button>
                <el-divider direction="vertical"></el-divider>
                <el-button type="text">关注数:{{user.followingNum}}</el-button>
                <el-divider direction="vertical"></el-divider>
              </div>
            </el-card>
          </div>
    </el-main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //粉丝、关注、搜索用户
     
      userlist: [],
      suserlist: [],
      value: "",
      v: "",
      activeName: "first",
      props:['page'],
      user: {
        id: window.sessionStorage.getItem("userId"),
      },

      //添加follow关系
      follow: {
        followingId: "",
        followerId: "",
      },
    };
  },
  watch: {
    sfollowings: {
      handler : (val,olVal) =>{
           
      },
      deep: true
    }
  },
  created() {
    this.getUserList();
  },
  methods: {

    async getUserList() {
      const { data: res } = await this.$http.post("user/users");
      this.userlist = res.userlist;
    },
    visitor(x) {
      window.sessionStorage.setItem("visitingId",x.id);
      this.$router.push('/welcome');
    },
    async addfollow(x) {
      this.follow.followerId = window.sessionStorage.getItem("userId");
      this.follow.followingId = x;
      this.$message_success(x);
      const { data: res } = await this.$http.post(
        "user/addfollow",
        this.follow
      );
      this.$message_success(res);
    },
    async getlist() {
      const { data: res } = await this.$http.post("user/users");
      this.userlist = res.userlist;
      var v = this.v;
      this.$message_success("enter " + v);
      if (v) {
        this.suserlist = this.userlist.filter(function (product) {
          return Object.keys(product).some(function (key) {
            return String(product[key]).toLowerCase().indexOf(v) > -1;
          });
        });
      }
    },
   
 
  },
};
</script>
<style scoped>
.el-card {
  line-height: 30px;
}
.el-main {
  padding: 80px;
  line-height: 40px;
}
.el-table {
  line-height: 40px;
}
.el-tabs {
  line-height: 40px;
}
.friend {
  line-height: 40px;
}
</style>