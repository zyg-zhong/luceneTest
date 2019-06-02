package com.cms.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * 测试打印文档到Excel.
 */
public class Testpoi {
    /**
     * main.
     * @param args .
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        //创建要打印的文件数据
        List<Address> list = new ArrayList<Address>();
        list.add(new Address(1, "北京", new Date()));
        list.add(new Address(2, "成都", new Date()));
        list.add(new Address(3, "深圳", new Date()));
        //设置excel表格的表头，工作表，放入对应的数据
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("地址表", "地址"),
                Address.class, list);
        //设置输出流---输出的文件的具体地址
        FileOutputStream fos = new FileOutputStream("E:/test.xls");
        //打印
        workbook.write(fos);
        //关流
        workbook.close();

    }

}
