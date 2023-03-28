package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bean.*;
import com.example.mapper.*;
import com.example.service.EstateService;
import com.example.vo.CellMessage;
import com.example.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstateServiceImpl implements EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    @Autowired
    private FcUnitMapper fcUnitMapper;

    @Autowired
    private FcCellMapper fcCellMapper;

    @Override
    public List<TblCompany> selectCompany() {
        return tblCompanyMapper.selectCompany();
    }

    @Override
    public Integer insertEstate(FcEstate fcEstate) {
        int result = 0;
        final QueryWrapper<FcEstate> fcEstateQueryWrapper = new QueryWrapper<>();
        fcEstateQueryWrapper.eq("estate_code", fcEstate.getEstateCode());
        final FcEstate fcEstate1 = fcEstateMapper.selectOne(fcEstateQueryWrapper);
        if (fcEstate1 == null) {
            result = fcEstateMapper.insert(fcEstate);
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        final ArrayList<FcBuilding> fcBuildings = new ArrayList<>();
        for (int i = 0; i < buildingNumber; i++) {
            final FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode(estateCode + "B" + (i + 1));
            fcBuilding.setBuildingName("第" + (i + 1) + "号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    @Override
    public Integer updateBuilding(FcBuilding fcBuilding) {
        return fcBuildingMapper.updateById(fcBuilding);
    }

    @Override
    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        final ArrayList<FcUnit> fcUnits = new ArrayList<>();
        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            final FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode(unitMessage.getBuildingCode() + "U" + (i + 1));
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    @Override
    public Integer updateUnit(FcUnit fcUnit) {
        return fcUnitMapper.updateById(fcUnit);
    }

    @Override
    public List<FcCell> insertCell(CellMessage cellMessage) {
        final ArrayList<FcCell> fcCells = new ArrayList<>();
        for (int i = 0; i < cellMessage.getStopFloor(); i++) {
            for (int j = 0; j < cellMessage.getStopCellId(); j++) {
                final FcCell fcCell = new FcCell();
                fcCell.setUnitCode(cellMessage.getUnitCode());
                fcCell.setCellCode(cellMessage.getUnitCode() + "C" + (i + 1) + "0" + (j + 1));
                fcCell.setCellName((i + 1) + "0" + (j + 1));
                fcCell.setFloorNumber(cellMessage.getStopFloor());
                fcCellMapper.insert(fcCell);
                fcCells.add(fcCell);
            }
        }
        return fcCells;
    }

    @Override
    public List<FcBuilding> selectBuildingByEstate(String estateCode) {
        final QueryWrapper<FcBuilding> fcBuildingQueryWrapper = new QueryWrapper<>();
        fcBuildingQueryWrapper.eq("estate_code", estateCode);
        fcBuildingQueryWrapper.select("building_code", "building_name");
        final List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(fcBuildingQueryWrapper);
        return fcBuildings;
    }

    @Override
    public List<FcUnit> selectUnitByBuildingCode(String buildingCode) {
        QueryWrapper<FcUnit> fcUnitQueryWrapper = new QueryWrapper<>();
        List<FcUnit> fcUnits;
        if (buildingCode.equals("")) {
            fcUnitQueryWrapper.select("*");
            fcUnits = fcUnitMapper.selectList(fcUnitQueryWrapper);
        } else {
            fcUnitQueryWrapper.eq("building_code", buildingCode);
            fcUnits = fcUnitMapper.selectList(fcUnitQueryWrapper);
        }
        return fcUnits;
    }

    @Override
    public List<FcCell> selectCell(String unitCode) {
        final QueryWrapper<FcCell> fcCellQueryWrapper = new QueryWrapper<>();
        List<FcCell> fcCells;
        if (unitCode.equals("")) {
            fcCellQueryWrapper.select("*");
            fcCells = fcCellMapper.selectList(fcCellQueryWrapper);
        } else {
            fcCellQueryWrapper.eq("unit_code", unitCode);
            fcCells = fcCellMapper.selectList(fcCellQueryWrapper);
        }
        return fcCells;
    }

    @Override
    public List<FcEstate> selectEstate(String company) {
        final QueryWrapper<FcEstate> fcEstateQueryWrapper = new QueryWrapper<>();
        fcEstateQueryWrapper.eq("company", company);
        final List<FcEstate> fcEstates = fcEstateMapper.selectList(fcEstateQueryWrapper);
        return fcEstates;
    }

    @Override
    public List<FcEstate> selectAllEstate() {
        final List<FcEstate> fcEstates = fcEstateMapper.selectAllEstate();
        return fcEstates;
    }

    @Override
    public List<FcBuilding> selectBuildingByEstateCode(String estateCode) {
        final List<FcBuilding> fcBuildings;
        final QueryWrapper<FcBuilding> fcBuildingQueryWrapper = new QueryWrapper<>();
        if (estateCode.equals("")) {
            fcBuildingQueryWrapper.select("*");
            fcBuildings = fcBuildingMapper.selectList(fcBuildingQueryWrapper);
        } else {
            fcBuildingQueryWrapper.eq("estate_code", estateCode);
            fcBuildings = fcBuildingMapper.selectList(fcBuildingQueryWrapper);
        }
        return fcBuildings;
    }

    @Override
    public FcBuilding selectBuildingByEstateCodeAndBuildingCode(String estateCode, String buildingCode) {
        final QueryWrapper<FcBuilding> fcBuildingQueryWrapper = new QueryWrapper<>();
        fcBuildingQueryWrapper.eq("estate_code",estateCode);
        fcBuildingQueryWrapper.eq("building_code",buildingCode);
       return fcBuildingMapper.selectOne(fcBuildingQueryWrapper);
    }
}
