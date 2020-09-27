package com.example.demo.mybatis.service;

import com.example.demo.mybatis.entity.Follow;
import com.example.demo.mybatis.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    int findIDByName(String username);
    List<User> findFollowers(int id);
    List<User> findFollowings(int id);
    void addUser(User user);
    User findById(int id);
    void updateById(User user);


    void updateArticleNum(User user);
    void updateFollowerNum(User user);
    void updateFollowingNum(User user);

    void addFollow(Follow follow);
    void removeFollow(Follow follow);
}
