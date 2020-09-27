<template>
<div>  
  <el-tag
   :key="tag"
   v-for="tag in dynamicTags"
   closable
   :disable-transitions="false"
   @close="handleClose(tag)">
   {{tag}}
  </el-tag>
  <el-col>
  <el-input
   class="input-new-tag"
   v-if="inputVisible"
   v-model="inputValue"
   ref="saveTagInput"
   size="small"
   @keyup.enter.native="handleInputConfirm"
   @blur="handleInputConfirm"
   style="width:90px"
  >
   </el-input>
     <el-button v-else class="button-new-tag" size="small" @click="showInput">
       <i class="el-icon-folder-add"></i>
    </el-button>
  </el-col>
</div>
</template>

<style>
 .el-tag + .el-tag {
  margin-left: 10px;
 }
 .button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
 }
 .input-new-tag {
  width: 0px;
  margin-left: 10px;
 }
</style>
 
<script>
 export default {
  data() {
   return {
    dynamicTags: [],
    inputVisible: false,
    inputValue: ''
   };
  },
  methods: {
   handleClose(tag) {
    this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
   },
 
   showInput() {
    this.inputVisible = true;
    this.$nextTick(_ => {
     this.$refs.saveTagInput.$refs.input.focus();
    });
   },
 
   handleInputConfirm() {
    let inputValue = this.inputValue;
    if (inputValue) {
     this.dynamicTags.push(inputValue);
    }
    this.inputVisible = false;
    this.inputValue = '';
   }
  }
 }
</script>