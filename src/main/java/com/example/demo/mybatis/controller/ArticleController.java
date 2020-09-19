package com.example.demo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.entity.Article;
import com.example.demo.mybatis.service.ArticleService;
import com.example.demo.mybatis.service.TagService;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

   @RequestMapping("/markdown")
    public ModelAndView article() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showArticle");
        return mv;
    }

    @RequestMapping("/show")
    public ModelAndView showArticle() {
        Article article = articleService.findText(1);
        System.out.println(article.getArticleText());
        ModelAndView mv = new ModelAndView();
        mv.addObject("article",article);
        mv.setViewName("showArticle");
        return mv;
    }

    @CrossOrigin
    @RequestMapping("/addText")
    public String addText(@RequestBody Article article){
        int userId=article.getUserId();
        String title=article.getArticleTitle();
        String text=article.getArticleText();
        System.out.println(userId+"  "+title+"  "+text);
        articleService.addText(article);
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
        System.out.println("numbers:"+numbers);
        String users_json = JSON.toJSONString(res);
        return users_json;
    }

}
