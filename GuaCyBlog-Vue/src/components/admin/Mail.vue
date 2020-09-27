<template>
  <div>
    <!-- 卡片视图区 -->
<!--    <div class="mail" style="margin-top:-30px;">-->
      <el-card>
        <!-- 用户列表 -->
        <el-table :data="infolist.slice((currentPage-1)*pagesize,currentPage*pagesize)" border stripe>
          <el-table-column type="index"></el-table-column>
          <el-table-column label="发信人" prop="postUser"></el-table-column>
          <el-table-column label="内容" prop="text"></el-table-column>
          <el-table-column label="发信时间" prop="time"></el-table-column>
          <el-table-column label="click here" prop="articleId">
            <template slot-scope="scope">
              <el-button
                type="text"
                size="small"
                prop="articleId"
                @click="show(scope.row.articleId)"
              >
                <p>点击跳转</p>
              </el-button>
            </template>
          </el-table-column>
          <el-table-column label="删除" prop="infoId">
            <template slot-scope="scope">
              <el-button type="text" size="small" prop="infoId" @click="deleteInfo(scope.row)">
                <p>删除</p>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="infolist.length"
        ></el-pagination>
      </el-card>
    </div>
</template>

<script>
export default {
  data() {
    return {
      // 请求数据
      currentPage: 1,
      pagesize: 5,
      infolist: [], // 消息列表
      user: {
        id: window.sessionStorage.getItem("userId"),
      },
      article: {
        articleId: "",
      },
      total: 0, // 最大数据记录
    };
  },
  created() {
    this.getInfoList();
  },
  methods: {
    async getInfoList() {
      this.$message.success(this.user.id);
      // 调用post请求
      const { data: res } = await this.$http.post("info/findById", this.user);
      this.infolist = res.data; // 将返回数据赋值
      this.total = res.numbers; // 总个数
    },
    async show(x) {
      this.article.articleId = x;
      const { data: res } = await this.$http.post(
        "article/findById",
        this.article
      );
      if (res.msg == "fail") this.$message_error("您想跳转的链接已被作者删除");
      else {
        this.$message_success("恭喜您成功跳转");
        this.$router.push({ path: "/show", query: { article: res.data } });
      }
    },
    async deleteInfo(x) {
      const { data: res } = await this.$http.post("info/deleteInfo", x);
      this.getInfoList();
      this.$$message_success("ok delete!");
    },
    handleSizeChange: function (size) {
      this.pagesize = size;
       //每页下拉显示数据
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
      //点击第几页
    },
  },
};
</script>
<style lang="less" scoped>
.mail {
  margin-top: 0px;
  padding: 80px;
}
</style>
<style lang='stylus'>
.el-table__header tr, .el-table__header th {
  padding: 0;
  height: 40px;
  line-height: 40px;
}

.el-table__body tr, .el-table__body td {
  padding: 0;
  height: 30px;
  line-height: 30px;
}
</style>