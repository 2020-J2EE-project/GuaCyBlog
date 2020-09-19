<template>
  <el-container>
    <el-aside>haha</el-aside>
    <el-main>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/article' }">文章列表</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/show' }">{{article.articleTitle}}</el-breadcrumb-item>
      </el-breadcrumb>
      
      <mavon-editor
          class="md"
          :value="article.articleText"
      :subfield = "prop.subfield"
      :defaultOpen = "prop.defaultOpen"
      :toolbarsFlag = "prop.toolbarsFlag"
      :editable="prop.editable"
      :scrollStyle="prop.scrollStyle"
      ></mavon-editor>

    </el-main>
  </el-container>
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
      article:this.$route.query.article,
      articleText :[]
    };
  },
  computed: {
    prop () {
      let data = {
        subfield: false,// 单双栏模式
        defaultOpen: 'preview',//edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: false,
        toolbarsFlag: false,
        scrollStyle: true
      }
      return data
    }
  },
}
</script>

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
