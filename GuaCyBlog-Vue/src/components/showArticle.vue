<style>
.markdown-body {
  box-sizing:border-box;
  min-width: 200px;
  max-width: 98%;
  margin:0 auto;
  box-shadow:2px 4px 6px gray;
  padding-left: 20px;
  padding-right: 15px;
  padding-top: 40px;
  padding-bottom: 45px;
  margin-bottom:100px;
}

</style>

<template>
  <article class="markdown-body" v-html="articleText"></article>
</template>

<script>
import 'github-markdown-css/github-markdown.css'
export default{
  name : 'showArticle',
  //props:['articleText'],
  // articleText : '# 你好',
  created() {
    //this.$message.success("登陆成功！！！");
  },
  data(){
    return{
      articleText : this.$route.query.articleText,
    };
  },


}
</script>