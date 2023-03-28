package com.example.controller.estate;

import com.example.bean.*;
import com.example.result.R;
import com.example.service.EstateService;
import com.example.vo.CellMessage;
import com.example.vo.UnitMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EstateController {

    @Autowired
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public R selectCompany() {
        System.out.println("selectCompany");
        final List<TblCompany> tblCompanies = estateService.selectCompany();
        final R r = new R();
        r.setResult(tblCompanies);
        r.setMessage("success");
        r.setCode(200);
        return r;
    }


    @RequestMapping("/estate/insertEstate")
    public R insertEstate(FcEstate fcEstate) {
        System.out.println("insertEstate");
        final Integer result = estateService.insertEstate(fcEstate);
        if (result == 1) {
            return new R(200, result.toString(), "插入成功.");
        }
        return new R(200, result.toString(), "房产编码已存在.");
    }

    @RequestMapping("/estate/selectBuilding")
    public R selectBuilding(Integer buildingNumber, String estateCode) {
        System.out.println("selectBuilding");
        final List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        return new R(200, "success", fcBuildings);
    }

    @RequestMapping("/estate/updateBuilding")
    public R updateBuilding(FcBuilding fcBuilding) {
        System.out.println("updateBuilding");
        final Integer integer = estateService.updateBuilding(fcBuilding);
        if (integer == 1) {
            return new R(200, "", "更新数据成功.");
        } else {
            return new R(200, "", "更新数据失败.");
        }
    }

    @RequestMapping("/estate/selectUnit")
    public R selectUnit(@RequestBody UnitMessage[] unitMessages) {
        System.out.println("selectUnit");
        final ArrayList<FcUnit> fcUnits = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            fcUnits.addAll(estateService.selectUnit(unitMessage));
        }
        return new R(200, "success", fcUnits);
    }

    @RequestMapping("/estate/updateUnit")
    public R updateUnit(FcUnit fcUnit) {
        System.out.println("updateUnit");
        final Integer result = estateService.updateUnit(fcUnit);
        if (result == 1) {
            return new R(200, "success", "更新成功.");
        }
        return new R(200, "success", "更新失败.");
    }

    @RequestMapping("/estate/insertCell")
    public R insertCell(@RequestBody CellMessage[] cellMessages){
        System.out.println("insertCell");
        final ArrayList<FcCell> fcCells = new ArrayList<>();
        for (CellMessage cellMessage : cellMessages) {
            fcCells.addAll(estateService.insertCell(cellMessage));
        }

        return new R(200,"success",fcCells);
    }

    @RequestMapping("/estate/selectBuildingByEstate")
    public R selectBuildingByEstate(String estateCode){
        System.out.println("selectBuildingByEstate");
        final List<FcBuilding> fcBuildings = estateService.selectBuildingByEstate(estateCode);
        return new R(200,"success",fcBuildings);
    }

    @RequestMapping("/estate/selectUnitByBuildingCode")
    public R selectUnitByBuildingCode(String buildingCode){
        System.out.println("selectUnitByBuildingCode");
        final List<FcUnit> fcUnits = estateService.selectUnitByBuildingCode(buildingCode);
        return new R(200,"success",fcUnits);
    }

    @RequestMapping("/estate/selectCell")
    public R selectCell(String unitCode){
        System.out.println("selectCell");
        final List<FcCell> fcCells = estateService.selectCell(unitCode);
        return new R(200,"success",fcCells);
    }

    @RequestMapping("/estate/selectEstate")
    public R selectEstate(String company){
        System.out.println("selectEstate");
        final List<FcEstate> fcEstates = estateService.selectEstate(company);
        return new R(200,"success",fcEstates);
    }

    @RequestMapping("/estate/selectAllEstate")
    public R selectAllEstate(){
        System.out.println("selectAllEstate");
        final List<FcEstate> fcEstates = estateService.selectAllEstate();
       return new R(200,"success",fcEstates);
    }

    @RequestMapping("/estate/selectBuildingByEstateCode")
    public R selectBuildingByEstateCode(String estateCode){
        System.out.println("selectBuildingByEstateCode");
        final List<FcBuilding> fcBuildings = estateService.selectBuildingByEstateCode(estateCode);
        return new R(200,"success",fcBuildings);
    }


    @RequestMapping("/estate/selectBuildingByEstateCodeAndBuildingCode")
    public R selectBuildingByEstateCodeAndBuildingCode(@Param("estateCode") String estateCode, @Param("buildingCode") String buildingCode){
        System.out.println("selectBuildingByEstateCodeAndBuildingCode");
        final FcBuilding fcBuilding = estateService.selectBuildingByEstateCodeAndBuildingCode(estateCode, buildingCode);
        return new R(200,"success",fcBuilding);
    }

}
