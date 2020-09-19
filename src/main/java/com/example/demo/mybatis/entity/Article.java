package com.example.demo.mybatis.entity;
/**
 * Created by 20372 on 2020/9/11.
 */
public class Article {

    private int articleId;
    private String articleTitle;
    private int userId;
    private String articleText;
    private String tag;

    public String getArticleText() {
        return articleText;
    }
    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getArticleId() {
        return articleId;
    }
    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

}

