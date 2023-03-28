package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CellMessage {
    private String unitCode;
    private Integer startFloor;
    private Integer stopFloor;
    private Integer startCellId;
    private Integer stopCellId;
}
