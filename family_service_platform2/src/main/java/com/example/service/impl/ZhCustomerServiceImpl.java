package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.bean.ZhCustomer;
import com.example.bean.ZhCustomerEstate;
import com.example.mapper.ZhCustomerEstateMapper;
import com.example.mapper.ZhCustomerMapper;
import com.example.service.ZhCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vo.CustomerMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业主信息表 服务实现类
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
@Service
public class ZhCustomerServiceImpl extends ServiceImpl<ZhCustomerMapper, ZhCustomer> implements ZhCustomerService {

    @Autowired
    private ZhCustomerMapper zhCustomerMapper;

    @Autowired
    private ZhCustomerEstateMapper zhCustomerEstateMapper;

    @Override
    public Integer insertCustomer(ZhCustomer zhCustomer) {
        Integer result = 0;
        final QueryWrapper<ZhCustomer> zhCustomerQueryWrapper = new QueryWrapper<>();
        zhCustomerQueryWrapper.eq("customer_code", zhCustomer.getCustomerCode());
        final ZhCustomer customer = zhCustomerMapper.selectOne(zhCustomerQueryWrapper);
        if (customer == null) {
            result = zhCustomerMapper.insert(zhCustomer);
        }
        return result;
    }

    @Override
    public List<ZhCustomer> selectAllCustomer() {
        final List<ZhCustomer> zhCustomers = zhCustomerMapper.selectAll();
        return zhCustomers;
    }

    @Override
    public List<ZhCustomer> selectCustomerByColumnAndValue(String column, String value) {
        final QueryWrapper<ZhCustomer> zhCustomerQueryWrapper = new QueryWrapper<>();
        zhCustomerQueryWrapper.eq(column, value);
        final List<ZhCustomer> zhCustomers = zhCustomerMapper.selectList(zhCustomerQueryWrapper);
        return zhCustomers;
    }

    @Override
    public List<ZhCustomer> selectCustomerByCustomerType(String customerType) {
        final QueryWrapper<ZhCustomer> zhCustomerQueryWrapper = new QueryWrapper<>();
        zhCustomerQueryWrapper.eq("customer_type", customerType);
        final List<ZhCustomer> zhCustomers = zhCustomerMapper.selectList(zhCustomerQueryWrapper);
        return zhCustomers;
    }

    @Override
    public Integer updateCustomerStatusByCustomerCode(String customerCodes, String status) {
        Integer result = 0;
        if (customerCodes.contains("|")) {
            final String[] customerCodesArray = customerCodes.split("[|]");
            for (String customerCode : customerCodesArray) {
                final UpdateWrapper<ZhCustomer> zhCustomerUpdateWrapper = new UpdateWrapper<>();
                zhCustomerUpdateWrapper.set("customer_status", status).eq("customer_code", customerCode);
                result = zhCustomerMapper.update(null, zhCustomerUpdateWrapper);
            }
            return result;
        } else {
            final UpdateWrapper<ZhCustomer> zhCustomerUpdateWrapper = new UpdateWrapper<>();
            zhCustomerUpdateWrapper.set("customer_status", status).eq("customer_code", customerCodes);
            return zhCustomerMapper.update(null, zhCustomerUpdateWrapper);
        }
    }

    @Override
    public List<ZhCustomer> selectAllCustomer(CustomerMessage customerMessage) {
        final QueryWrapper<ZhCustomer> zhCustomerQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(customerMessage.getColumn()) && !StringUtils.isEmpty(customerMessage.getValue())){
            zhCustomerQueryWrapper.eq(customerMessage.getColumn(),customerMessage.getValue());
        }
        if (!StringUtils.isEmpty(customerMessage.getCustomerType())){
            zhCustomerQueryWrapper.eq("customer_type",customerMessage.getCustomerType());
        }
        return zhCustomerMapper.selectList(zhCustomerQueryWrapper);
    }

    @Override
    public Integer insertAll(List<ZhCustomer> customers, String company) {
        Integer result = 0;
        for (ZhCustomer customer : customers) {
            customer.setCompany(company);
            result = zhCustomerMapper.insert(customer);
        }
        return result;
    }

    @Override
    public Integer insertCustomerOrEstate(ZhCustomerEstate zhCustomerEstate) {
        Integer result = 0;
        if (zhCustomerEstate != null){
          result =   zhCustomerEstateMapper.insert(zhCustomerEstate);
          return result;
        }
        return result;
    }
}
