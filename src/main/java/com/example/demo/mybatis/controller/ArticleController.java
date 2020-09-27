package com.example.demo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.entity.*;
import com.example.demo.mybatis.service.ArticleService;
import com.example.demo.mybatis.service.TagService;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 20372 on 2020/9/13.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;
    private UserService userService;
    private TagService tagService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setUserService(UserService userService){ this.userService=userService; }

    @Autowired
    public void setTagService(TagService tagService){this.tagService=tagService;}

    @CrossOrigin
    @RequestMapping("/addText")
    public String addText(@RequestBody Article article){
        int userId=article.getUserId();
        User user=userService.findById(userId);
        String[] tags=article.getTags();
        String title=article.getArticleTitle();
        String text=article.getArticleText();
        System.out.println(JSON.toJSONString(article));
        articleService.addText(article);
        for(int i=0;i<tags.length;i++) {
            String tagName = tags[i];
            //创建tag
            if (!tagService.findTag(tagName)) {
                Tag newtag = new Tag();
                newtag.setTagName(tagName);
                tagService.addTag(newtag);
            }
            //创建链接
            Connect connect=new Connect();
            connect.setArticleId(articleService.findArticleIdByTitle(title));
            connect.setUserId(article.getUserId());
            System.out.println(articleService.findArticleIdByTitle(title));
            connect.setTagName(tagName);
            tagService.addConnect(connect);

        }
        //更新userinfo
        int articleNum=user.getArticleNum()+1;
        user.setArticleNum(articleNum);
        userService.updateArticleNum(user);
        return "successful add!";
    }

    @CrossOrigin
    @RequestMapping("/findAllArticle")
    public String findAllArticle() {
        List<Article> articleList = articleService.findAllArticle();
        int numbers = articleList.size();
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",articleList);
  //      System.out.println("numbers:"+numbers);
        String users_json = JSON.toJSONString(res);
        return users_json;
    }

    @CrossOrigin
    @RequestMapping("/findArticleById")
    public String findArticleById(@RequestBody User user) {
        List<Article> articleList = articleService.findArticleById(user.getId());
        int numbers = articleList.size();
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",articleList);
//        System.out.println("numbers:"+numbers);
        String users_json = JSON.toJSONString(res);
        return users_json;
    }

    @CrossOrigin
    @RequestMapping("/findByIdAndTag")
    public String findArticleByIdAndTag(@RequestBody Connect connect) {
        List<Article> articleList = articleService.findArticleByTagAndId(connect);
        String articles_json1 = JSON.toJSONString(articleList);
        int numbers = articleList.size();
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",articleList);
        String articles_json = JSON.toJSONString(res);
        System.out.println(articles_json);
        return articles_json;
    }

    @CrossOrigin
    @RequestMapping("/findById")
    public String findById(@RequestBody Article article) {
        Article a = articleService.findText(article.getArticleId());
        HashMap<String,Object> res = new HashMap<>();
        if(a!=null){
            res.put("msg","success");
            res.put("data",a);
        }
        else {
            res.put("msg","fail");
        }
       // System.out.println("articleId="+a.getArticleId());
        String article_json = JSON.toJSONString(res);
        return article_json;
    }

    @CrossOrigin
    @RequestMapping("/addComment")
    public String addComment(@RequestBody Comment comment){

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        comment.setCommentTime(now);
  //      System.out.println("aricle:"+comment.getArticleId()+" text:"+comment.getCommentText()+" time:"+comment.getCommentTime()+" commentPerson"+comment.getReviewerId());
        articleService.addComment(comment);
        return "ok";
    }

    @CrossOrigin
    @RequestMapping("/allComment")
    public String allComment(@RequestBody Article article){

        List<Comment> comments=articleService.findCommentsByArticleId(article.getArticleId());
        HashMap<String,Object> data = new HashMap<>();
        List<String> imgs = new ArrayList<>();
        int commentId = 0;
        for(int i=0;i<comments.size();i++) {
            String rn = userService.findById(comments.get(i).getReviewerId()).getUsername();
            comments.get(i).setReviewerName(rn);
            imgs.add(i,userService.findById(comments.get(i).getReviewerId()).getUserimg());
            commentId = comments.get(i).getCommentId();
        }
        data.put("commentImg",imgs);
        data.put("commentId",commentId);
        System.out.println("commentId:"+commentId);
        data.put("comments",comments);
        String commments_json = JSON.toJSONString(data);
        System.out.println(commments_json);
        return commments_json;
    }
    @CrossOrigin
    @RequestMapping("/delete")
    public String deleteText(@RequestBody Article article) {
        String msg = "success";
        Connect connect = new Connect();
        int id = article.getArticleId();
        connect.setArticleId(id);
        tagService.deleteConnect(connect);
        articleService.deleteText(id);
        User user = userService.findById(article.getUserId());
        System.out.println(user);
        user.setArticleNum(user.getArticleNum()-1);
        userService.updateArticleNum(user);
        return msg;
    }

    @CrossOrigin
    @RequestMapping("/deleteComment")
    public String deleteComment(@RequestBody Comment comment) {
        String msg = "success";
        System.out.println(comment.getCommentId());
        articleService.deleteComment(comment.getCommentId());
        return msg;
    }

}
