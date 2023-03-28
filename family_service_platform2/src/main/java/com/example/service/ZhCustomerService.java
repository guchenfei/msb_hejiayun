package com.example.service;

import com.example.bean.ZhCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bean.ZhCustomerEstate;
import com.example.vo.CustomerMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 业主信息表 服务类
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
public interface ZhCustomerService extends IService<ZhCustomer> {
    Integer insertCustomer(ZhCustomer zhCustomer);

   List<ZhCustomer> selectAllCustomer();

   List<ZhCustomer> selectCustomerByColumnAndValue(String column, String value);

   List<ZhCustomer> selectCustomerByCustomerType(String customerType);

   Integer updateCustomerStatusByCustomerCode(String customerCodes,String status);

    List<ZhCustomer> selectAllCustomer(CustomerMessage customerMessage);

    Integer insertAll(List<ZhCustomer> customers,String company);

    Integer insertCustomerOrEstate(ZhCustomerEstate zhCustomerEstate);
}
