<template>
  <el-container>
    <el-container>
      <!--侧边栏-->
      <!--文章主题-->
      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/article' }">文章列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-table :data="articleList" border stripe>
          <el-table-column type="index"></el-table-column>
          <el-table-column label="标题"
                           prop="articleTitle">
            <template slot-scope="scope">
              <el-button
                  type="text"
                  size="small"
                  prop="articleTitle"
                  @click="show(scope.row)">
                <p>{{scope.row.articleTitle}}</p>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  data() {
    return{
      articleList:[],
      total: 0
    }
  },
  created() {
      this.getArticleList();
  },
  methods:{
    async getArticleList() {
      // 调用post请求
      const {data: res} = await this.$http.get("article/findAllArticle");
      this.articleList = res.data; // 将返回数据赋值
      this.total = res.numbers; // 总个数
    },
    show(x) {
      this.$router.push({path:'/show',query:{article:x}})
    }
  }
}
</script>

<style>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
body > .el-container {
  margin-bottom: 40px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>