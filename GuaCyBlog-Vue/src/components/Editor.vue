<template>
  
  <div class="editir_container">
    <div class="editor_box">
      <!--添加表单-->
      <el-form
        ref="editorFormRef"
        :model="editorForm"
        :rules="editorRules"
        label-width="0px"
      >
        <el-form-item prop="articleTitle">
          <el-input v-model="editorForm.articleTitle" ></el-input>
        </el-form-item>
        <el-form-item prop="tag">
          <el-input v-model="editorForm.tag" ></el-input>
        </el-form-item>
        <el-form-item prop="articleText">
         <mavon-editor v-model="editorForm.articleText"/>
        </el-form-item>
          
        <el-form-item class="btns">
          <el-button type="primary" @click="addArticle">发布</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
           editorForm:{
            userId : window.sessionStorage.getItem("userId"),
            articleTitle:'文章标题',
            tag:'',
            articleText: '',
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
              if (!valid) return ;
              // 调用get请求
              const {data :res} = await this.$http.post("article/addText", this.editorForm);
              this.$message.success(res);
              this.$router.push( 
                   {
                     path: '/showArticle',
                     query: {
                     articleText: '# hello!'
                  }
     });
              //if (res == "successfu" ) {
              //window.sessionStorage.setItem('flag','ok'); // session 放置
              //this.$message.success("登陆成功！！！");
              //this.$router.push({ path: "/home"});
         //}else{
          //this.$message.error(res);
         //}
      });
        }
    }
};
</script>