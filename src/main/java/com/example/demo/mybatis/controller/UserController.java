package com.example.demo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.entity.Follow;
import com.example.demo.mybatis.entity.Info;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.InfoService;
import com.example.demo.mybatis.service.UserService;
import com.example.demo.mybatis.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
//RequestMapping用来映射请求,指定控制器可以处理的URL请求
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")         //列出所有的user
    public String users() {
        List<User> users = userService.findAll();
        HashMap<String,Object> data = new HashMap<>();
        data.put("userlist",users);
        String users_json=JSON.toJSONString(data);
        return users_json;
    }

    @CrossOrigin
    @RequestMapping("/login")    //登录
    public String login(@RequestBody User user){
        HashMap<String,Object> data = new HashMap<>();
        String message;
        String username=user.getUsername();
        String password=user.getPassword();
        String userimg = user.getUserimg();
        System.out.println("User:"+user);
        User user1 = userService.findByUsername(username);
        System.out.println("User1:"+user1);
        if(user1==null){
            message="no such user";
        }else if(!user1.getPassword().equals(password)){
            message="wrong pass";
        }else{
            data.put("userid",user1.getId());
            System.out.println(user1.getId());
            data.put("username",user1.getUsername());
            data.put("userimg",userimg);
            message="success";
        }
        data.put("msg",message);
        String login_json = JSON.toJSONString(data);
        return login_json;
    }

    @CrossOrigin
    @RequestMapping("/register")
    public String register(@RequestBody User user){
        String msg="您的用户名已被使用，请更换用户名";
        User haveuser=userService.findByUsername(user.getUsername());
        if(haveuser==null){
            userService.addUser(user);
            msg="success!";
        }
        return msg;
    }

    @CrossOrigin
    @RequestMapping("/follower")
    public String follower(@RequestBody User user){


        HashMap<String,Object> data = new HashMap<>();
        List<User> followers=userService.findFollowers(user.getId());
        data.put("followers",followers);
        String follower_json= JSON.toJSONString(data);
        return follower_json;
    }
    @CrossOrigin
    @RequestMapping("/following")
    public String following(@RequestBody User user){


        HashMap<String,Object> data = new HashMap<>();
        List<User> followings=userService.findFollowings(user.getId());
        data.put("followings",followings);
        String following_json= JSON.toJSONString(data);
        return following_json;
    }

    @CrossOrigin
    @RequestMapping("/findById")
    public String findById(@RequestBody User user) {
        User userr = userService.findById(user.getId());
        HashMap<String,Object> res = new HashMap<>();
        res.put("data",userr);
        System.out.println(userr);
        String users_json = JSON.toJSONString(res);
        return users_json;
    }

    @CrossOrigin
    @RequestMapping("/updateById")
    public String updateById(@RequestBody User user) {
        userService.updateById(user);
        System.out.println("update:"+user);
        return "haha";
    }

    @Autowired
    private InfoService infoService;

    @CrossOrigin
    @RequestMapping("/addfollow")
    public String addfollow(@RequestBody Follow follow){
        userService.addFollow(follow);
        System.out.println(JSON.toJSONString(follow));
        //改变被关注者粉丝数
        User followingUser=userService.findById(follow.getFollowingId());
        followingUser.setFollowerNum(followingUser.getFollowerNum()+1);
        //改变关注者被关注数
        User followerUser=userService.findById(follow.getFollowerId());
        followerUser.setFollowingNum(followerUser.getFollowingNum()+1);
        //更新一下
        userService.updateFollowerNum(followingUser);
        userService.updateFollowingNum(followerUser);
        //发送通知
        Info info = new Info();
        info.setPostUserId(follow.getFollowerId());
        info.setReceiveUserId(follow.getFollowingId());
        info.setType(1);
        info.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        infoService.addInfo(info);

        return "ok";

    }

    @CrossOrigin
    @RequestMapping("/removefollow")
    public String removefollow(@RequestBody Follow follow){
        userService.removeFollow(follow);
        //改变被关注者粉丝数
        User followingUser=userService.findById(follow.getFollowingId());
        followingUser.setFollowerNum(followingUser.getFollowerNum()-1);
        //改变关注者被关注数
        User followerUser=userService.findById(follow.getFollowerId());
        followerUser.setFollowingNum(followerUser.getFollowingNum()-1);
        //更新一下
        userService.updateFollowerNum(followingUser);
        userService.updateFollowingNum(followerUser);

        return "ok";
    }

    @CrossOrigin
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        // replaceAll 用来替换windows中的\\ 为 /
        System.out.println("访问");
        String s= FileUploadUtil.upload(multipartFile).replaceAll("\\\\", "/");
        System.out.println(s);
        return s;
    }
}


