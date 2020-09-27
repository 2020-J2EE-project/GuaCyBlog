package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.entity.Follow;
import com.example.demo.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    User findByUsername(String username);
    User findById(int id);
    int[] findFollowers(int id);
    int[] findFollowings(int id);
    void addUser(User user);
    void updateById(User user);


    void addFollow(Follow follow);
    void removeFollow(Follow follow);

    void updateArticleNum(User user);
    void updateFollowerNum(User user);
    void updateFollowingNum(User user);

}
