package com.example.service.impl;

import com.example.bean.TblUserRecord;
import com.example.mapper.TblUserRecordMapper;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;

    @Override
    public TblUserRecord login(String username, String password) {
        final TblUserRecord tblUserRecord = tblUserRecordMapper.login(username, password);
        return tblUserRecord;
    }
}
