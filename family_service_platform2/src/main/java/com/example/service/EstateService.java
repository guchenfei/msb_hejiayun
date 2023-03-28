package com.example.service;

import com.example.bean.*;
import com.example.result.R;
import com.example.vo.CellMessage;
import com.example.vo.UnitMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EstateService {
    List<TblCompany> selectCompany();

    Integer insertEstate(FcEstate fcEstate);

    List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode);

    Integer updateBuilding(FcBuilding fcBuilding);

    List<FcUnit> selectUnit(UnitMessage unitMessage);

    Integer updateUnit(FcUnit fcUnit);

    List<FcCell> insertCell(CellMessage cellMessage);

    List<FcBuilding> selectBuildingByEstate(String estateCode);

    List<FcUnit> selectUnitByBuildingCode(String buildingCode);

    List<FcCell> selectCell(String unitCode);

    List<FcEstate> selectEstate(String company);

    List<FcEstate> selectAllEstate();

    List<FcBuilding> selectBuildingByEstateCode(String estateCode);
   FcBuilding selectBuildingByEstateCodeAndBuildingCode(String estateCode,String buildingCode);
}
