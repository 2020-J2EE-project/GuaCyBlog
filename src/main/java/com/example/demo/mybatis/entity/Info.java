package com.example.demo.mybatis.entity;

public class Info {
   int infoId;
   int postUserId;
   int receiveUserId;
   int type;
   int articleId; //可以为空
   int isRead;//0未读1已读
   String postUser;
   String time;
   public String getTime() {
      return time;
   }
   public void setTime(String time) {
      this.time = time;
   }
   public String getText() {
      return text;
   }
   public void setText(String text) {
      this.text = text;
   }
   String text;
   //1:xx关注了你！
   //2:xx在你的文章xxx下给您留言了 clickhere
   //3：xx对你在XX文章下的留言回复进行了回复 clickhere:
   public int getInfoId() {
      return infoId;
   }
   public void setInfoId(int infoId) {
      this.infoId = infoId;
   }
   public int getPostUserId() {
      return postUserId;
   }
   public void setPostUserId(int postUserId) {
      this.postUserId = postUserId;
   }
   public int getReceiveUserId() {
      return receiveUserId;
   }
   public void setReceiveUserId(int receiveUserId) {
      this.receiveUserId = receiveUserId;
   }
   public int getType() {
      return type;
   }
   public void setType(int type) {
      this.type = type;
   }
   public int getArticleId() {
      return articleId;
   }
   public void setArticleId(int articleId) {
      this.articleId = articleId;
   }
   public int getIsRead() {
      return isRead;
   }
   public void setIsRead(int isRead) {
      this.isRead = isRead;
   }
   public String getPostUser() {
      return postUser;
   }
   public void setPostUser(String postUser) {
      this.postUser = postUser;
   }
}
