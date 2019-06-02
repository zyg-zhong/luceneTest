package com.cms.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 测试地址数据.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    /**
     * id.
     */
    @Excel(name = "id")
    private int id;
    /**
     * 姓名.
     */
    @Excel(name = "姓名")
    private String name;
    /**
     * 时间.
     */
    @Excel(name = "时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
    private Date time;


}
