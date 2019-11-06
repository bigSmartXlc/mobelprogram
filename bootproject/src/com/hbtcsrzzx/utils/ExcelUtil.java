package com.hbtcsrzzx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


public class ExcelUtil {
	public static List<List<String[]>> getExcelData(File file) {
		HSSFWorkbook workbook;
		List<List<String[]>> dataList = new ArrayList<List<String[]>>();
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet;
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				sheet = workbook.getSheetAt(i);
				List<String[]> rows = new ArrayList<String[]>();
				int colsnum = 0;
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					HSSFRow row = sheet.getRow(j);
					if (row != null) {
						colsnum = sheet.getRow(1).getPhysicalNumberOfCells();
						String[] cols = new String[colsnum];
						for (int k = 0; k < colsnum; k++) {
							Cell cell = row.getCell(k);
							if (cell != null) {
								switch (cell.getCellTypeEnum()) {
								case NUMERIC:
									if (DateUtil.isCellDateFormatted(cell)) {
										Date date = cell.getDateCellValue();
										DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
										cols[k] = formater.format(date);
									} else if (String.valueOf(cell.getNumericCellValue()).contains(".")) {
										DecimalFormat df = new DecimalFormat("#");
										cols[k] = df.format(cell.getNumericCellValue());
									} else {
										cols[k] = (cell + "").trim();
									}
									continue;
								default:
									cols[k] = (cell + "").trim();
									continue;
								}
							} else {
								cols[k] = "";
							}
						}
						rows.add(cols);
					}
				}
				dataList.add(rows);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;

	}

	
	   

	public static Map<String, String> getExcelDataMap(File file) {
		List<List<String[]>> list = getExcelData(file);
		int i = 0;
		Map<String, String> map = new LinkedHashMap<>();
		for (List<String[]> list2 : list) {

			//System.out.println("list2"+list2);
			for (String[] strings : list2) {
				map.put("name", strings[0]);
				map.put("addr", strings[1]);
				map.put("category", strings[2]);
				map.put("subject", strings[3]);
				map.put("phone", strings[4]);
				map.put("email", strings[5]);
				map.put("introduction", strings[6]);
			}
		}
		
		//test02();
		return map;
	}
	
	
	
}
