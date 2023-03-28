package com.example.mapper;

import com.example.bean.TblRole;
import com.example.bean.TblUserRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户档案 Mapper 接口
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
public interface TblUserRecordMapper extends BaseMapper<TblUserRecord> {
    TblUserRecord login(@Param("username") String username, @Param("password") String password);
}
