package com.example.demo.mybatis.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    public int getFollowerNum() {
        return followerNum;
    }
    int followerNum;
    int followingNum;
    int articleNum;
    private String userimg;


    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public void setFollowerNum(int followerNum) {
        this.followerNum = followerNum;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }

    public int getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    private String intro;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", followerNum=" + followerNum +
                ", followingNum=" + followingNum +
                ", articleNum=" + articleNum +
                ", intro='" + intro + '\'' +
                '}';
    }
}


