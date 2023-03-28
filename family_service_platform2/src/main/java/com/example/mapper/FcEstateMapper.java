package com.example.mapper;

import com.example.bean.FcEstate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 楼盘信息 Mapper 接口
 * </p>
 *
 * @author GuYinTai
 * @since 2023-03-23
 */
public interface FcEstateMapper extends BaseMapper<FcEstate> {

    List<FcEstate> selectAllEstate();
}
