package com.example.demo.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mybatis.entity.Tag;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @CrossOrigin
    @RequestMapping("/allTag")
    public String allTag(@RequestBody User user) {
        List<Tag> tags = tagService.findAllTags(user.getId());
        List<Tag> tags2 = new ArrayList<>();
        HashMap<String,Object> data = new HashMap<>();
        Set<String> s = new HashSet<String>();
        for(int i=0;i<tags.size();i++) {
            s.add(tags.get(i).getTagName());
        }
        Iterator it = s.iterator();
        int id = 0;
        while (it.hasNext()) {
            Tag tag = new Tag();
            tag.setTagName((String)it.next());
            tag.setTagId(id);
            id++;
            tags2.add(tag);
        }
        data.put("tags",tags2);
        String tags_json= JSON.toJSONString(data);
        return tags_json;
    }
}

