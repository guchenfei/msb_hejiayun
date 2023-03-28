package com.example.mapper;

import com.example.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
public interface TblCompanyMapper extends BaseMapper<TblCompany> {
   @Select("select id,company_full_name from tbl_company")
    List<TblCompany> selectCompany();
}
