package com.example.demo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.entity.Info;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.ArticleService;
import com.example.demo.mybatis.service.InfoService;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    private InfoService infoService;
    private UserService userService;
    private ArticleService articleService;

    @Autowired
    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    @Autowired
    public void setUserService(UserService userService){this.userService=userService;}

    @Autowired
    public void setArticleService(ArticleService articleService){this.articleService=articleService;}

    @CrossOrigin
    @RequestMapping("/addInfo")
    public String addInfo(@RequestBody Info info){
      //  System.out.println("info:"+info.getPostUserId()+" infotype"+info.getReceiveUserId());
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        info.setTime(now);
        infoService.addInfo(info);
        return "ok info";
    }
    @CrossOrigin
    @RequestMapping("/findById")
    public String findById(@RequestBody User user){
        System.out.println("userid:"+user.getId());
        List<Info> infoList = infoService.findInfoById(user.getId());
        for (int i = 0; i < infoList.size(); i++) {
            Info info = infoList.get(i);

            int postId= info.getPostUserId();
            String postUser =userService.findById(postId).getUsername();
            info.setPostUser(postUser);

            int type=info.getType();
            if(type==1)info.setText("你好，"+postUser+"关注了你");
            else if(type==2)
                info.setText(postUser+"在你的文章'"+articleService.findText(info.getArticleId()).getArticleTitle()+"'下给您留言了");
            else if(type==3)
                info.setText(postUser+"回复了你在'"+articleService.findText(info.getArticleId()).getArticleTitle()+"'文章下的留言");
            System.out.println("artcleId="+info.getArticleId());
        }
        int numbers = infoList.size();
        Collections.reverse(infoList);
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",infoList);
        System.out.println("numbers:"+numbers);
        String infos_json = JSON.toJSONString(res);
        return infos_json;
    }

    @CrossOrigin
    @RequestMapping("/deleteInfo")
    public String deleteInfo(@RequestBody Info info){
        System.out.println("info delete:"+info.getInfoId());
        infoService.deleteInfoById(info.getInfoId());
        return "ok delete";
    }
}
