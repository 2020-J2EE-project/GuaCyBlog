<template>
  <!--面包屑和头部按钮  -->
<!--侧边栏-->
    <el-container>
      <div>
        <el-aside :width="isCollapseA?'0px':'200px'" class="my-article-aside">
          <el-menu :collapse="isCollapseA"
                   :collapse-transition="true" :router="true">
            <el-card style="min-height: 468px;" shadow="never" id="directoryDiv">
              <div slot="header" style="font-size: 15px"><b>{{article.articleTitle}}</b></div>
              <div v-for="(item, index) in aData">
                <el-button
                    type="text"
                    size="small"
                    text-align="left"
                    @click="url(item.id)">
                {{item.name}}
                </el-button>
              </div>
            </el-card>
          </el-menu>
        </el-aside>
      </div>
      <el-main>
        <!--文章显示-->
        <mavon-editor class="md"
                      :value="article.articleText"
                      :subfield = "prop.subfield"
                      :defaultOpen = "prop.defaultOpen"
                      :toolbarsFlag = "prop.toolbarsFlag"
                      :editable="prop.editable"
                      :scrollStyle="prop.scrollStyle"
        >
        </mavon-editor>
        <!--评论区-->
        <el-container direction="vertical">
          <div v-for="(comment,index) in commentList" :key="comment">
            <el-card class="box-card">
              <div>
                <el-avatar class="header-img" :size="40">
                  <img v-if="commentImg[index]" :src="require('../static/img/'+commentImg[index])"   />
                </el-avatar>

                <div class="author-info">
                  <span class="author-name">{{comment.reviewerName}}</span>
                  <span class="author-time">{{comment.commentTime}}</span>
                </div>
              </div>
              <el-divider></el-divider>
              <div class="my-comment">{{comment.commentText}}</div>
              <div class="my-button" align="right"
                   v-if="comment.reviewerId==user.id||user.id==visitorId">
                <el-button type="text" @click="deleteComment">
                  <i class="el-icon-delete">
                  </i>
                </el-button>
              </div>
            </el-card>
          </div>
          <!--发布评论-->
          <el-form class="comment-container" :model="commentForm" ref="commentForm"
                   :rules="commentRules" label-position="left">
            <h4 class="comment-title" align="left">发布评论</h4>
            <el-form-item prop="commentText">
              <el-input type="textarea" :rows="3" v-model="commentForm.commentText" auto-complete="off" placeholder="评论内容"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button class="comment-button" type="primary" @click="submitComment">发布</el-button>
            </el-form-item>
          </el-form>
        </el-container>
      </el-main>
    </el-container>
</template>

<script>
import 'github-markdown-css/github-markdown.css'
import 'element-ui/lib/theme-chalk/base.css';
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';
import Vue from 'vue'
Vue.component(CollapseTransition.name, CollapseTransition)
export default {
  name : 'showArticle',
  watch: {
    commentList: {
      handler : (val,olVal) =>{
      },
      deep: true
    },
    isCollapseA: {
      handler : (val,olVal) =>{
      },
      deep: true
    },
    $route(to, from) {
      this.isCollapseA = this.$route.query.isCollapseA;
    },
  },

  data() {
    return {
      isCollapseA:true,
      visitorId:window.sessionStorage.getItem("visitingId"),
      user: {
        id:window.sessionStorage.getItem("userId"),
        username:window.sessionStorage.getItem("username"),
      },
      // article:this.$route.query.article,
      article:{
          articleId:1,
          articleText:'',
          articleTitle:'',
          articleInfo:'',
          userId:'',
      },
      aData:[],
      commentImg:[],
      commentList:[],
      // commentImg:this.$route.query.commentImg,
      // commentList :this.$route.query.commentList,

      //评论格式
      commentForm: {
        commentId:'',
        reviewerId:'' ,
        articleId:'',
        commentText:'',
        commentTime:''
      },
      //信息格式
      infoForm: {
        postUserId:window.sessionStorage.getItem("userId"),
        receiveUserId:'',
        type:'',
        articleId:'',
        isRead:0
      },
      //评论规则
      commentRules: {
        commentText: [
          { required: true, message: "评论内容不得为空", trigger: "blur" },
          { max: 100, message: "长度在 100 个字符以内", trigger: "blur" }
        ],
      },
    }
  },
  mounted() {
    this.listA();
    window.addEventListener("scroll", this.directory);
  },
  created() {
    this.getArticleInfo();
    this.getCommendInfo();
  },
  methods: {
    async getArticleInfo() {
      this.article.articleId = window.sessionStorage.getItem("articleId");
      console.log(this.article.articleId);
      const {data: res2} = await this.$http.post("article/findById", this.article);
      console.log(res2.data);
      this.article = res2.data;
    },
    async getCommendInfo() {
      const {data: res} = await this.$http.post("article/allComment", this.article);
      this.commentImg = res.commentImg;
      this.commentList = res.comments;
      this.commentForm.commentId = res.commentId;
      console.log(this.commentForm);
    },
    url(url) {
      document.querySelector('#' + url).scrollIntoView(true);
    },
    async deleteComment() {
      const {data: res4} = await this.$http.post("article/deleteComment", this.commentForm);
      const {data: res2} = await this.$http.post("article/allComment", this.article);
      this.commentList = res2.comments;
      },
    listA() { //找到所有标签为a的内容,但是数字不可以
      this.$nextTick(function () {
        let k = document.getElementsByTagName("a").length;
        let t = k-8;
        let m = 0;
        for(let i=0; i<document.getElementsByTagName("a").length; i++) {
          //console.log(i,document.getElementsByTagName("a")[i].id," ",document.getElementsByTagName("a")[i].parentNode.innerText);
          if (document.getElementsByTagName("a")[i].id !== '') {
            m = m+1;
            let aDat = {
              'id' : document.getElementsByTagName("a")[i].id,  // 获取a标签的id
              'name' : document.getElementsByTagName("a")[i].parentNode.innerText//获取这个a标签父节点的文字（即我们的标题）
            };
            this.aData.splice(i, 1, aDat);
            if(m>=(t)/2)
              break;
          }
        }
      })
    },
    async submitComment() {
      //处理评论
      this.commentForm.articleId = this.article.articleId;
      this.commentForm.reviewerId = this.article.userId;
      const {data: res1} = await this.$http.post("article/addComment", this.commentForm);
      //更新评论
      const {data: res2} = await this.$http.post("article/allComment", this.article);
      this.commentList = res2.comments;
      this.commentImg = res2.commentImg;
      this.commentForm.commentId = res2.commentId;
      //处理通知
      this.infoForm.receiveUserId = this.article.userId;
      this.infoForm.type = '2';
      this.infoForm.articleId = this.article.articleId;
      const {data: res3} = await this.$http.post("info/addInfo", this.infoForm);
      this.$message_success(res3);
    },
    directory() {
      var top=document.documentElement.scrollTop||document.body.scrollTop;
      var directoryDiv=document.getElementById("directoryDiv");
      if(top>= 164){
        directoryDiv.style.position = "fixed";
        directoryDiv.style.top = "50px";
        directoryDiv.style.width = "229.313px";
      }else{
        directoryDiv.style.position = "absolute";
        directoryDiv.style.top = "0px";
        directoryDiv.style.width = "229.313px";
      }
    }
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
  destroyed() {
    window.removeEventListener("scroll", this.directory);
  }
}
</script>

<style>
.my-header{
  background-color:#eaedf1;
}
.my-article-aside{
  margin-top: 20px;
  line-height: 50px;
  margin-left: 10px;
  min-height: 242px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple {
  background: #d3dce6;
  font-size:10px;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 10px;
  min-height: 18px;
  line-height: 30px;
  margin-bottom: -5px;
  font-size: 15px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.time{
  font-size:9px;
  text-align: left;
}
.clearfix {
  font-size:15px;
  text-align: left;
  margin-top:8px;
}
.box-card {
  text-align: left;
  padding:0px;
  margin: 20px;
}
.author-info {
  font-size:20px;
  display: inline;
}
.header-img {
  display: inline;
}
.author-time {
  font-size:12px;
  display: flex;
  margin-bottom:-20px;
}
</style>