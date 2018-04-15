package com.zyp.erp.homework1.excelTool;

import com.zyp.erp.homework1.mps.Time;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author ZYP
 */
public class excelTool {
  private String excelPath;
  private ArrayList<String> nameList;

  public excelTool(String excelPath) {
    this.excelPath = excelPath;
    nameList = new ArrayList<String>();
  }

  public ArrayList<HashMap<String,Double>> getInfo() {
    ArrayList<HashMap<String, Double>> info = new ArrayList<HashMap<String, Double>>(50);
    File excelFile = null;
    InputStream is = null;
    HashMap<String, Double> column = null;
    try {
      excelFile = new File(excelPath);
      is = new FileInputStream(excelFile);
      HSSFWorkbook workbook = new HSSFWorkbook(is);
      HSSFSheet sheet= workbook.getSheetAt(0);
      HSSFRow headRow =  sheet.getRow(0);
      int length = headRow.getLastCellNum();
      for (int i = 1; i < length; i++) {
        column = new HashMap<String,Double>(50);
        info.add(column);
      }
      Iterator it = sheet.rowIterator();
      it.next();
      while (it.hasNext()) {
        HSSFRow row = (HSSFRow)it.next();
        length = row.getLastCellNum();
        String name = row.getCell(0).getStringCellValue();
        nameList.add(name);
        for (int i = 1; i < length; i++) {
          info.get(i-1).put(name,row.getCell(i).getNumericCellValue());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return info;
  }

  public ArrayList<String> getNameList() {
    return nameList;
  }

  public void createExcel(ArrayList<HashMap<String,Double>> timeList) {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet();
    HSSFRow headRow = sheet.createRow(0);
    HSSFCell cell;
    HSSFRow row;
    cell = headRow.createCell(0);
    cell.setCellValue("时段");
    for (int i = 0; i < nameList.size(); i++) {
      cell = headRow.createCell(i + 1);
      cell.setCellValue(nameList.get(i));
    }
    for (int i = 0; i < timeList.size(); i++) {
      row = sheet.createRow(i + 1);
      cell = row.createCell(0);
      cell.setCellValue(i+1);
      for (int j = 0; j < nameList.size(); j++) {
        cell = row.createCell(j + 1);
        cell.setCellValue(timeList.get(i).get(nameList.get(j)));
      }
    }
    try {
      FileOutputStream outputStream = new FileOutputStream("C:\\Users\\ZYP\\Desktop\\test4.xls");
      workbook.write(outputStream);
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /*public static void main(String[] args) {
    excelTool tool = new excelTool("C:\\Users\\ZYP\\Desktop\\test.xls");
    ArrayList<HashMap<String,Double>> info = tool.getInfo();
    System.out.println( info.get(1).get("我去"));
  }*/
}
