package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.entity.Follow;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.mapper.UserMapper;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("/userService")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    @Override
    public int findIDByName(String username){
        return userMapper.findByUsername(username).getId();
    }

    @Override
    public List<User> findFollowers(int id){
        int[] a = userMapper.findFollowers(id);
        List<User> followers=new ArrayList<>();
        for(int i=0;i<a.length;i++){
            User user = userMapper.findById(a[i]);
            followers.add(user);
        }
        return followers;
    }

    @Override
    public void addUser(User user){
        userMapper.addUser(user);
    }

    @Override
    public List<User> findFollowings(int id){
        int[] a = userMapper.findFollowings(id);
        List<User> followers=new ArrayList<>();
        for(int i=0;i<a.length;i++){
            User user = userMapper.findById(a[i]);
            followers.add(user);
        }
        return followers;
    }
    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }


    @Override
    public void addFollow(Follow follow){
        userMapper.addFollow(follow);
    }
    @Override
    public void removeFollow(Follow follow){
        userMapper.removeFollow(follow);
    }

    @Override
    public void updateArticleNum(User user){userMapper.updateArticleNum(user);}

    @Override
    public void updateFollowerNum(User user){userMapper.updateFollowerNum(user);}

    @Override
    public void updateFollowingNum(User user){userMapper.updateFollowingNum(user);}

}
