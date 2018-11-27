package com.example.jpademo.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportUtils {

    public Object exportInfo(HttpServletResponse response, List<Object> list,String title,String[] headers) throws IOException {

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("信息表");
        //  List<code> byCreatetimeIsLessThanEqual = userJPA.findByCreatetimeIsLessThanEqual(4);
        //List<Object> testseclect = crm_employerbaseinfoJPA.testseclect();
        String fileNmae=title + ".xls";
        int rowNum=1;
      //  String[] headers={"姓名","性别","电话","密码","父母电话"};
        HSSFRow row=sheet.createRow(0);
        for (int i=0;i<headers.length;i++){
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (int i = 0; i <list.size() ; i++) {
            Object[] obj = (Object[])list.get(i);
            HSSFRow row1=sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(obj[0].toString());
            row1.createCell(1).setCellValue(obj[1].toString());
            row1.createCell(2).setCellValue(obj[2].toString());
            row1.createCell(3).setCellValue(obj[3].toString());
            row1.createCell(4).setCellValue(obj[4].toString());

            rowNum++;
        }


        response.setHeader("content-Type","application/vnd/ms-excel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=" +new String(fileNmae.getBytes("UTF-8"),"iso-8859-1"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        return "成功";


    }


}
