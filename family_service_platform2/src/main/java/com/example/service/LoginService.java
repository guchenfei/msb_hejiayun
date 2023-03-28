package com.example.service;

import com.example.bean.TblUserRecord;

public interface LoginService {
    TblUserRecord login(String username, String password);
}
