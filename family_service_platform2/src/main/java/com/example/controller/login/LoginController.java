package com.example.controller.login;

import com.example.bean.TblUserRecord;
import com.example.result.Permissions;
import com.example.result.R;
import com.example.result.Result;
import com.example.result.Role;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Permission;
import java.util.ArrayList;
import java.util.LinkedList;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/login")
    public R login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession){
        System.out.println("username"+username + "password" + password);
        final TblUserRecord tblUserRecord = loginService.login(username, password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        final R r = new R(200,"ok",tblUserRecord);
        httpSession.setAttribute("userRecord",tblUserRecord);
        System.out.println(r);
        return r;
    }

    @RequestMapping("/user/info")
    public R getInfo(HttpSession httpSession){
        final TblUserRecord userRecord = (TblUserRecord)httpSession.getAttribute("userRecord");
        final R r = new R();
        r.setCode(200);
        r.setMessage("success");
        final Result result = new Result();
        result.setName(userRecord.getUserName());
        final Role role = new Role();
        final ArrayList<Permissions> permissions = new ArrayList<>();
        final String[] rolePrivileges = userRecord.getTblRole().getRolePrivileges().split("-");
        for (String rolePrivilege : rolePrivileges) {
            permissions.add(new Permissions(rolePrivilege));
        }
        role.setPermissions(permissions);
        result.setRole(role);
        r.setResult(result);
        return r;
    }

    @RequestMapping("/auth/logout")
    public void logOut(HttpSession session){
        System.out.println("Logout");
        //将session设置为失效
        session.invalidate();
    }

    @RequestMapping("/auth/2step-code")
    public boolean step2code(){
        System.out.println("此请求是前端框架默认请求");
        return true;
    }
}
