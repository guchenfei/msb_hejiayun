package com.example.mapper;

import com.example.bean.ZhCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 业主信息表 Mapper 接口
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
public interface ZhCustomerMapper extends BaseMapper<ZhCustomer> {
    List<ZhCustomer> selectAll();
}
