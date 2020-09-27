<template>
  <div>
    <!--无文章-->
    <div v-if="articleList.length==0">
      <el-card class="article-card">
        <div align="middle">
          <h2>暂无文章</h2>
        </div>
        <div align="middle" v-if="userId==user.id">
          <el-button type="text" @click="goto('/editor')">新建文章</el-button>
        </div>
      </el-card>
    </div>
    <!--有文章-->
    <div v-for="article in articleList" :key="article.articleTitle">
      <el-card class="article-card">
        <div slot="header" align="left">
          <el-button
              type="text"
              size="small" @click="show(article)">
            {{article.articleTitle}}
          </el-button>
          <el-button
              type="text"
              size="small" @click="deleteText(article)">
            <i class="el-icon-delete" float="right"></i></el-button>
        </div>
        <div style="font-size:10px;" align="left">
          {{article.articleInfo}}
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  watch: {
    $route(to, from) {
      this.articleList = this.$route.query.articleList;
    },
  },
  data() {
    return{
      articleList:[],
      commentList:[],
      commentImg:[],
      total: 0,
      userId:window.sessionStorage.getItem("visitingId"),
      user:{
        id:window.sessionStorage.getItem("visitingId")
      }
    }
  },
  created() {
      this.getArticleList();
  },
  methods:{
    async getArticleList() {
      const {data: res} = await this.$http.post("article/findArticleById",this.user);
      this.articleList = res.data;
      await this.$router.push({path: '/welcome', query: {articleList: this.articleList}});

    },
    async show(x) {
       window.sessionStorage.setItem("visitingPage","Article");
       window.sessionStorage.setItem("articleId",x.articleId);
       this.$router.push({path:'/show'});
       // await this.$router.push({path: '/show', query: {article: x, commentList: this.commentList,commentImg:this.commentImg}})
    },
    async deleteText(x) {
      this.$confirm('确认删除文章?', {
        confirmButtonClass: '确定',
        cancelButtonText: '取消',
      }).then(()=>{
        const {data:res}=this.$http.post("article/delete",x);
        this.getArticleList();
      }).catch(()=>{
      });
    },
    goto(x) {
      this.$router.push({path:x});
    }
  }
}
</script>
<style>
</style>