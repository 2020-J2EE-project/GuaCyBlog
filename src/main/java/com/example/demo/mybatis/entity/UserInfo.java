package com.example.demo.mybatis.entity;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.mapper.ArticleMapper;
import com.example.demo.mybatis.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/userInfo")
public class UserInfo {
    int id;
    int followerNum;
    int followingNum;
    int articleNum;
    String intro;

    public String getIntro() { return intro; }

    public void setIntro(String intro) { this.intro = intro; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowerNum() {
        return followerNum;
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

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @CrossOrigin
    @RequestMapping("/getById")
    public String getById(@RequestBody User user){
        //UserInfo userInfo = userInfoMapper.getById(user.getId());
        int articleNum1 = articleMapper.cntArticle(user.getId());
        UserInfo userInfo = new UserInfo();
        userInfo.articleNum = articleNum1;
        HashMap<String,Object> res = new HashMap<>();
        res.put("data",userInfo);
        System.out.println("userInfo:"+userInfo);
        String users_json = JSON.toJSONString(res);
        return users_json;
    }
}
