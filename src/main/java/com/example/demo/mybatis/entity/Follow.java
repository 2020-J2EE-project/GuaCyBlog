package com.example.demo.mybatis.entity;

public class Follow {
    int followlistId;
    int followingId;  //被关注者
    int followerId;   //关注者
    String followerName;
    String followingName;
    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public String getFollowingName() {
        return followingName;
    }

    public void setFollowingName(String followingName) {
        this.followingName = followingName;
    }

    public int getFollowlistId() {
        return followlistId;
    }

    public void setFollowlistId(int followId) {
        this.followlistId = followId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }


}
