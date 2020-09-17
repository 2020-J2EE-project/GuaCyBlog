package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.entity.Tag;
import com.example.demo.mybatis.service.TagService;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping("/allTag")
    public ModelAndView allTag() {

        ModelAndView mv = new ModelAndView();
        List<Tag> tags = tagService.findAllTags();
        Tag tag1=new Tag();
        tag1.setTag_id(4);
        tag1.setTag_name("tag4");
        List<Tag> tags1=new ArrayList<>();
        tags1.add(tag1);
        System.out.println(tags.get(0));
        System.out.println(tags.size());
        mv.addObject("tags", tags1);
        mv.setViewName("tags");
        return mv;
    }

}

