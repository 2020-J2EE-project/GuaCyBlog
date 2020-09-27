<template>
  <el-container direction="vertical">
    <!--侧边栏-->
    <el-container>
      <div>
        <el-aside :width="isCollapse?'0px':'201px'" class="my-aside">
          <MyMenu></MyMenu>
        </el-aside>
      </div>
      <el-main class="my-editor">
        <!--资料卡-->
        <el-container direction="horizontal">
          <el-col span="200" class="my-col">
            <div class="editor_container">
              <div class="editor_box">
                <!--添加表单-->
                <el-form
                    ref="editorFormRef"
                    :model="editorForm"
                    :rules="editorRules"
                    label-width="55px"
                >
                  <el-form-item label="标题" prop="articleTitle">
                    <el-input v-model="editorForm.articleTitle" clearable></el-input>
                  </el-form-item>
                  <el-form-item prop="tag" label="标签">
                    <template>
                      <TagInput ref="tagInput" align="left" v-model='editorForm.tags'/>
                    </template>
                  </el-form-item>
                  <el-form-item label="摘要" prop="articleInfo">
                    <el-input type="textarea" v-model="editorForm.articleInfo"></el-input>
                  </el-form-item>
                </el-form>
                <el-form>
                  <el-form-item prop="articleText">
                    <mavon-editor v-model="editorForm.articleText"/>
                  </el-form-item>
                  <el-form-item class="btns">
                    <el-button type="primary" @click="addArticle">发布</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </div>
          </el-col>
        </el-container>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import TagInput from '../components/TagInput.vue';
import MyMenu from "../components/MyMenu.vue";
export default {
  components :{
    TagInput,MyMenu
  },
  created() {
    window.sessionStorage.setItem("visitingPage","editor");
    //this.$router.push({path:"/page",visitingPage:"editor"});
  },
  data() {
    return {
      isCollapse:true,
      editorForm:{
        userId : window.sessionStorage.getItem("userId"),
        articleTitle:'',
        articleText: '',
        tags:[]
        //defaultData: "preview"
      },
      editorRules: {
        articleTitle: [
          { required: true, message: "文章标题不可为空", trigger: "blur" },
          {  max: 100, message: "长度在不能大于100个字符", trigger: "blur" }
        ],
        articleText : [
          { required: true, message: "文章内容不能为空", trigger: "blur" },
        ]
      }

    };
  },
  methods:{
    addArticle(){
      this.$refs.editorFormRef.validate(async valid => {
        if (!valid) return  this.$message.success("dd"); ;
        // 调用get请求
        this.editorForm.tags=this.$refs.tagInput.dynamicTags;
        this.$message.success(this.editorForm.tags);
        const {data :res} = await this.$http.post("article/addText", this.editorForm);
        this.$message.success(res);
        this.$router.push(
            {  path: '/article'});
      });
    },
    asideChange() {
      this.isCollapse=!this.isCollapse;
      this.$message_success(this.isCollapse);
    }
  }
};
</script>

<style>
.my-aside{
  margin-top: 20px;
  line-height: 50px;
  margin-left: -5px;
  margin-right: -5px;
}
.my-editor{
  margin-left: -40px;
}
</style>