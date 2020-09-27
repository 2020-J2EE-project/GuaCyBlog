<template>
  <div class="tags-wrapper card-box">
    <div class="tags">
      <template v-for="(item, index) in tagList" >
        <el-button  :class="isActive==index?'tagbtn':'tagbtn2'"
                    type="text" size="small" @click="click(item.tagName,index)"
                    >
          {{item.tagName}}
        </el-button>
    </template>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tagBgColor:['#c4dae6','#a9ddf9'],
      fontStyleList:[],
      tagStyleList: [],
      tagList:[],
      clicked:[],
      allList:[],
      myTagName:"All Blogs",
      isActive:-1,
      articleList:[],
      connect:{
          userId:window.sessionStorage.getItem("visitingId"),
          tagName:''
      },
      user:{
        id:window.sessionStorage.getItem("visitingId"),
      }
      
    }
  },
  created() {
    this.getTags();
    this.getArticleList();
  },
  methods: {
    async getArticleList() {
      const {data: res} = await this.$http.post("article/findArticleById",this.user);
      this.articleList = res.data;
      this.allList = res.data;
      this.myTagName = "All Blogs";
    },
    async getTags(){
        const { data: res } = await this.$http.post("tag/allTag", this.user);
        let ind = 0;
        for(let i=0;i<res.tags.length;i++) {
          this.connect.tagName=res.tags[i].tagName;
          console.log(this.connect.tagName,i);
          const {data: res2} = await this.$http.post("article/findByIdAndTag",this.connect);
          if(res2.numbers>0) {
            this.tagList.splice(ind,1,res.tags[i]);
            ind++;
          }
        }
        //this.tagList=res.tags;
        for (let i = 0, tagH = this.tagList.length; i < tagH; i++) {
          this.tagStyleList.push(this.getTagStyle())
        }
    },
    getTagStyle() {
      const tagBgColor = this.tagBgColor
      const randomColor = tagBgColor[Math.floor(Math.random() * tagBgColor.length)]
      return `background: ${randomColor};--randomColor:${randomColor};`
    },
    async click(x,y){
      this.connect.tagName=x;
      if(this.isActive==y) {
        this.isActive=-1
        this.myTagName="All Blogs"
        this.articleList = this.allList;
      }
      else{
        this.isActive=y;
        this.myTagName=x;
        this.connect.tagName=x;
        const {data: res} = await this.$http.post("article/findByIdAndTag",this.connect);
        this.articleList = res.data;
      }
      await this.$router.push({path: '/welcome', query: {myTagName: this.myTagName, articleList: this.articleList}});
    }
  }
}
</script>

<style lang='stylus'>
.tags-wrapper 
  .title
    color var(--textColor)
    opacity 0.9
    font-size 1.2rem
    margin:auto
  .tags
    text-align justify
    padding .8rem .5rem .5rem .5rem
    margin:auto
    a
      opacity .8
      display inline-block
      padding .2rem .4rem
      transition all .4s
      background-color var(--textColor)
      color var(--mainBg)
      border-radius 3px
      margin 0 .3rem .5rem 0
      min-width 2rem
      height 1rem
      line-height 1rem
      font-size .8rem
      margin:auto
      text-align center
      @media (max-width: $MQMobile) 
        font-weight 400
      &:hover
        opacity 1
        margin:auto
        transform scale(1.1)
      &.active
        box-shadow: 0 5px 10px -5px var(--randomColor, rgba(0,0,0,0.15));
        transform scale(1.22)
        opacity 1
        &:hover
          text-decoration none
    .tagbtn
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
        margin:6px 6px 6px 6px;
        size: auto;
        font-weight : bold;
        color: #ffffff
        background: #a9ddf9;
      --randomColor:#a9ddf9;
    .tagbtn2
      height: 32px;
      line-height: 30px;
      padding-top: 0;
      padding-bottom: 0;
      margin:6px 6px 6px 6px;
      size: auto;
      font-weight : bold;
      color: #ffffff;
      background: #c4dae6;
      --randomColor:#c4dae6;


</style>