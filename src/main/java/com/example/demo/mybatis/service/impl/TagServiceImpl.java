package com.example.demo.mybatis.service.impl;
import com.example.demo.mybatis.entity.Connect;
import com.example.demo.mybatis.entity.Tag;
import com.example.demo.mybatis.mapper.TagMapper;
import com.example.demo.mybatis.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("/tagService")
public class TagServiceImpl implements TagService{
    private TagMapper tagMapper;
    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }
    @Override
    public List<Tag> findAllTags(int id){
        return tagMapper.findAllTags(id);
    }
    @Override
    public boolean findTag(String tagName){
        if(tagMapper.findTag(tagName)==null)
            return false;

        return true;
    }
    @Override
    public void addTag(Tag tag){
        tagMapper.addTag(tag);
    }

    @Override
    public void addConnect(Connect connect){tagMapper.addConnect(connect);}

    @Override
    public void deleteConnect(Connect connect) {
        tagMapper.deleteConnect(connect);
    }

}
