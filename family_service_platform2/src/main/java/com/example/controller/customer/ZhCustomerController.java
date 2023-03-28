package com.example.controller.customer;


import com.example.bean.ZhCustomer;
import com.example.bean.ZhCustomerEstate;
import com.example.result.R;
import com.example.service.ZhCustomerService;
import com.example.utils.ExcelUtil;
import com.example.vo.CustomerMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业主信息表 前端控制器
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
@RestController
@RequestMapping("/zhCustomer")
public class ZhCustomerController {
    @Autowired
    private ZhCustomerService zhCustomerService;

    @PostMapping("/insertCustomer")
    public R insertCustomer(ZhCustomer zhCustomer) {
        System.out.println("insertCustomer");
        final Integer result = zhCustomerService.insertCustomer(zhCustomer);
        if (result == 1) {
            return new R(200, "success", "插入成功");
        }
        return new R(200, "success", "存在相同业主.");
    }

    @PostMapping("/selectCustomer")
    public R selectAllCustomer(@RequestBody CustomerMessage customerMessage) {
        final List<ZhCustomer> zhCustomers = zhCustomerService.selectAllCustomer(customerMessage);
        return new R(200, "success", zhCustomers);
    }

    @PostMapping("/UpdateCustomerStatusByCustomerCode")
    public R UpdateCustomerStatusByCustomerCode(@RequestBody Map<String,String> map){
        System.out.println("UpdateCustomerStatusByCustomerCode");
        final String customerCodes = map.get("customerCodes");
        final String status = map.get("status");
        final Integer result = zhCustomerService.updateCustomerStatusByCustomerCode(customerCodes, status);
        if (result == 1){
            return new R(200,"success","更新成功");
        }
        return new R(200,"success","更新失败");
    }

    @PostMapping("/uploadExcel")
    public R uploadExcel(MultipartFile file,String company) throws Exception {
        System.out.println("uploadExcel");
        if (file!=null&&file.getSize()>0){
            final List<ZhCustomer> zhCustomers = ExcelUtil.readExcel((FileInputStream) file.getInputStream(), ZhCustomer.class);
            int result = zhCustomerService.insertAll(zhCustomers, company);
            return new R(200,"success","批量插入成功");
        }
        return new R(200,"success","文件存在问题.");
    }

    @PostMapping("/insertCustomerOrEstate")
    public R insertCustomerOrEstate(@RequestBody ZhCustomerEstate zhCustomerEstate){
        System.out.println("insertCustomerOrEstate");
        final Integer result = zhCustomerService.insertCustomerOrEstate(zhCustomerEstate);
        if (result == 1){
            return new R(200,"success","1");
        }else {
            return new R(200,"success","0");
        }
    }
}

