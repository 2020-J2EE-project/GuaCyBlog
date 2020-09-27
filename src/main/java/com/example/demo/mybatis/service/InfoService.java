package com.example.demo.mybatis.service;

import com.example.demo.mybatis.entity.Info;

import java.util.List;

public interface InfoService {
    public List<Info> findInfoById(int receiveUserId);
    void addInfo(Info info);
    void deleteInfoById(int infoId);
}
