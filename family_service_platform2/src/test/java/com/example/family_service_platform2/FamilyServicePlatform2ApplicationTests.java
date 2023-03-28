package com.example.family_service_platform2;

import com.example.bean.TblCompany;
import com.example.bean.TblUserRecord;
import com.example.mapper.TblCompanyMapper;
import com.example.mapper.TblUserRecordMapper;
import com.example.service.LoginService;
import com.example.service.base.TblCompanyService;
import com.example.service.impl.EstateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FamilyServicePlatform2ApplicationTests {
    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;

    @Autowired
    private LoginService loginService;

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private EstateServiceImpl estateService;

    @Test
    void contextLoads() {
//        final TblUserRecord tblUserRecord = tblUserRecordMapper.login("admin", "c4ca4238a0b923820dcc509a6f75849b");
//        final TblUserRecord tblUserRecord = loginService.login("admin", "c4ca4238a0b923820dcc509a6f75849b");
//        System.out.println(tblUserRecord);
//        final List<TblCompany> tblCompanies = tblCompanyMapper.selectCompany();
        final List<TblCompany> tblCompanies = estateService.selectCompany();
        tblCompanies.forEach(System.out::println);
    }

}
