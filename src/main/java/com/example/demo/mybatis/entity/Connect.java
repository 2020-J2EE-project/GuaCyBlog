package com.example.demo.mybatis.entity;

public class Connect {
    int connectId;
    int userId;
    String tagName;
    int articleId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getConnectId() {
        return connectId;
    }

    public void setConnectId(int connectId) {
        this.connectId = connectId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


}
