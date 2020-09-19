package com.example.demo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
//RequestMapping用来映射请求,指定控制器可以处理的URL请求
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")         //列出所有的user
    public ModelAndView users() {
        List<User> users = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("users",users);
        mv.setViewName("users");
        return mv;
    }

    @CrossOrigin
    @RequestMapping("/login")    //登录
    public String login(@RequestBody User user){
        HashMap<String,Object> data = new HashMap<>();
        String message;
        String username=user.getUsername();
        String password=user.getPassword();
        System.out.println("User:"+user);
        User user1 = userService.findByUsername(username);
        System.out.println("User1:"+user1);
        if(user1==null){
            message="no such user";
        }else if(!user1.getPassword().equals(password)){
            message="wrong pass";
        }else{
            data.put("userid",user1.getId());
            data.put("username",user1.getUsername());
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

    @RequestMapping("/follower")
    public ModelAndView follower(HttpServletRequest request, HttpSession session){
        ModelAndView mv=new ModelAndView();
        String username=request.getParameter("username");
        User user = userService.findByUsername(username);
        List<User> followers=userService.findFollowers(user.getId());
        mv.addObject("followers",followers);
        mv.setViewName("users");
        return mv;
    }

    @RequestMapping("/following")
    public ModelAndView following(HttpServletRequest request, HttpSession session){
        ModelAndView mv=new ModelAndView();
        String username=request.getParameter("username");
        User user = userService.findByUsername(username);
        List<User> followings=userService.findFollowers(user.getId());
        mv.addObject("followings",followings);
        mv.setViewName("users");
        return mv;
    }

    @CrossOrigin
    @RequestMapping("/findById")
    public String findById(int id) {
        User user = userService.findById(id);
        HashMap<String,Object> res = new HashMap<>();
        res.put("data",user);
        String users_json = JSON.toJSONString(res);
        return users_json;
    }
}


