package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.entity.Info;
import com.example.demo.mybatis.mapper.InfoMapper;
import com.example.demo.mybatis.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("/infoService")
public class InfoServiceImpl implements InfoService {
    private InfoMapper infoMapper;

    @Autowired
    public void setInfoMapper(InfoMapper infoMapper) {
        this.infoMapper = infoMapper;
    }

    public List<Info> findInfoById(int receiveUserId){
        return infoMapper.findInfoById(receiveUserId);
    }

    public void addInfo(Info info){infoMapper.addInfo(info);}

    public void deleteInfoById(int infoId){
        infoMapper.deleteInfoById(infoId);
    }
}
