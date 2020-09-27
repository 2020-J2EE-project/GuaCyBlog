 <template >
    <el-container >
      <el-container>
        <!--侧边栏-->
        <!--文章主题-->
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/article' }">文章列表</el-breadcrumb-item>
         <el-breadcrumb-item :to="{ }">{{tagName}}</el-breadcrumb-item>
          </el-breadcrumb>
        <div style="height:100%;width:100%;width:2000px">        
         <el-row gutter="40">
             <el-col span="11" class="text">
               <el-table :data="articleList" border stripe>
               <el-table-column type="index"></el-table-column>
               <el-table-column label="标题"
                           prop="articleTitle"
                           >
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
             </el-col>

           <el-col span="8" style="line-height:40px">
             <div class="tagbox" >
               <div style="margin-top:-30px;">
                 <h4>我的TAG</h4>
               </div>
               <div style="margin-top:-30px;">
                 <tag />
               </div>
             </div>
           </el-col>
      </el-row>
    </div>
    </el-main>
  </el-container>
</el-container>
</template>
 


<script>
import tag from '../components/admin/tag.vue'
export default {
  watch: {
    '$route' (to, from) {
        this.articleList=this.$route.query.articleList;
        this.tagName=this.$route.query.tagName;
    }
},
  data() {
    return{
      articleList:this.$route.query.articleList,
      tagName:this.$route.query.tagName,
      commentList:[],
      total: 0,
  
  
    }
  },
  components: {
      tag,
    },

  methods:{
    async show(x) {
        const {data:res}=await this.$http.post("article/allComment",x);
        this.commentList=res.comments;
        this.$message_success(this.commentList);
        this.$router.push({path:'/show',query:{article:x ,commentList:this.commentList}})
    }
  }
}
</script>

<style>
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
  line-height: 120px;
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

.text{
  width:400px
}
.tagbox {
  margin-top: 0px;
  text-align:center;
  padding: 20px;
  border-radius: 10px 10px 10px 10px;
  background-color:white;

}
</style>