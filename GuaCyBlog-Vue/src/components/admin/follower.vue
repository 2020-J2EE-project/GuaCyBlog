<template>
  <div class="friend">
    <el-main>
      <el-input placeholder="搜索粉丝" v-model="value">
        <el-button slot="append" icon="el-icon-search" @click="Search"></el-button>
      </el-input>
      <el-table
        :data="sfollowers.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        border
        stripe
      >
        <el-table-column width="30" type="index"></el-table-column>
        <el-table-column width="80" label="用户名" prop="username"></el-table-column>
        <el-table-column width="200" label="个人简介" prop="intro"></el-table-column>
        <div v-if="userid==user.id">
          <el-table-column width="80" label="操作" prop="id">
            <template slot-scope="scope">
              <el-button type="text" size="small" prop="id" @click="removefollower(scope.row)">
                <p>移除粉丝</p>
              </el-button>
            </template>
          </el-table-column>
        </div>
        <el-table-column width label="to" prop="id">
          <template slot-scope="scope">
            <el-button type="text" size="small" align="left" prop="id" @click="visitor(scope.row)">
              <p>访问主页</p>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 40]"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="sfollowers.length"
      ></el-pagination>
    </el-main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //粉丝、关注、搜索用户
      currentPage: 1,
      pagesize: 5,
      currentPage1: 1,
      pagesize1: 5,
      followers: [],
      sfollowers: [],
      value: "",
      v: "",
      user: {
        id: window.sessionStorage.getItem("visitingId"),
      },
      userid: window.sessionStorage.getItem("userId"),

      //添加follow关系
      follow: {
        followingId: "",
        followerId: "",
      },
    };
  },
  watch: {
    sfollowers: {
      handler: (val, olVal) => {},
      deep: true,
    },
  },
  created() {
    this.getFList1();
    this.getUserList();
  },
  methods: {
    async getFList1() {
      const { data: res } = await this.$http.post("user/follower", this.user);
      this.followers = res.followers;
      this.sfollowers = res.followers;
    },
    async getUserList() {
      const { data: res } = await this.$http.post("user/users");
      this.userlist = res.userlist;
      //this.$message_error(this.userlist);
    },
    Search() {
      // search 是 v-model="search" 的 search
      var value = this.value;
      this.sfollowers = this.followers.filter(function (product) {
        return Object.keys(product).some(function (key) {
          return String(product[key]).toLowerCase().indexOf(value) > -1;
        });
      });
    },
    visitor(x) {
      this.$message_success("Hello!!!");
      console.log("try try:", x.id);
      window.sessionStorage.setItem("visitingId", x.id);
      this.$router.go(0);
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
      this.getFList1();
    },

    async removefollower(x) {
      this.follow.followingId = window.sessionStorage.getItem("userId");
      this.follow.followerId = x.id;
      const { data: res } = await this.$http.post(
        "user/removefollow",
        this.follow
      );
      this.getFList1();
    },

    handleSizeChange: function (size) {
      this.pagesize = size;
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
    },
  },
};
</script>
<style scoped>
.el-card {
  line-height: 30px;
}
.el-main {
  padding: 0px;
  line-height: 30px;
}
.el-table {
  margin-top: 0px;
  line-height: 30px;
}
.img {
  width: 130px;
  height: 130px;
  border: 1px solid #eee;
  border-radius: 50%;

  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
}
.friend {
  margin-top: 0px;
  line-height: 30px;
  width: 500px;
}
</style>