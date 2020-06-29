package com.bitc.practiceProgress.util;

import java.io.FileInputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bitc.practiceProgress.model.PracticeTable;

public class Excel {
	public static List<PracticeTable> getList(String excelFile) throws Exception{
		// 파일을 읽기위해 엑셀파일을 가져온다
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowindex = 0;
		int columnindex = 0;
		// 시트 수 (첫번째에만 존재하므로 0을 준다)
		// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 행의 수
		int rows = sheet.getPhysicalNumberOfRows();
		
		List<PracticeTable> practiceTables = new ArrayList<>();

		for (rowindex = 1; rowindex < rows; rowindex++) {
			// 행을 읽는다
			XSSFRow row = sheet.getRow(rowindex);
			if (row != null) {
				// 셀의 수
				int cells = row.getPhysicalNumberOfCells();
				
				PracticeTable practiceTable = PracticeTable.builder().build();
				
				for (columnindex = 0; columnindex <= cells; columnindex++) {
					
					String colData = "";
					
					// 셀값을 읽는다
					XSSFCell cell = row.getCell(columnindex);
					// 셀이 빈값일경우를 위한 널체크
					if (cell == null || cell.toString().equals("")) {
						continue;
					}
					
					
//					if(columnindex == 1) { // 날짜를 yyyy-MM-dd 형식으로 바꾸어서 넣는다
//						
//						Calendar cal = Calendar.getInstance();
//						SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//						colData = formater.format(cell.getDateCellValue());
//
//					} else if(columnindex == 2) { // 데이터가 [월] 과 같은 일반데이터와 [=C11]과 같은 레퍼런스데이터가 섞여있을 경우 아래와같이 불러온다
//						
//						colData = cell.getStringCellValue();
//						
//					} else if(columnindex == 0 || columnindex == 6 || columnindex == 7 || columnindex == 8) { // 문자열의 경우 아래와같이
//						
//						colData = cell.toString();
//						
//					} else { // 숫자는 내용그대로 가져오기위해서 아래와 같이
//						
//						colData = cell.getRawValue().toString();
//						
//					}
					
					
					if(columnindex == 1) { // 날짜를 yyyy-MM-dd 형식으로 바꾸어서 넣는다
						
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
						colData = formater.format(cell.getDateCellValue());

					} else if(columnindex == 3 || columnindex == 4 || columnindex == 5 || columnindex == 9) { // 숫자의 경우
						
						colData = cell.getRawValue();
						
					} else { // 문자열
						
						colData = cell.getStringCellValue();
						
					}
					

					if (columnindex == 0) {
						practiceTable.setClassName(colData);
					} else if (columnindex == 1) {
						practiceTable.setClassDate(colData);
					} else if (columnindex == 2) {
						practiceTable.setDayWeek(colData);
					} else if (columnindex == 3) {
						practiceTable.setClassTime(Integer.parseInt(colData));
					} else if (columnindex == 4) {
						practiceTable.setStartTime(colData);
					} else if (columnindex == 5) {
						practiceTable.setEndTime(colData);
					} else if (columnindex == 6) {
						practiceTable.setSubject1(colData);
					} else if (columnindex == 7) {
						practiceTable.setSubject2(colData);
					} else if (columnindex == 8) {
						practiceTable.setProf(colData);
					} else if (columnindex == 9) {
						practiceTable.setRoom(Integer.parseInt(colData));
					}
					
				}
				practiceTables.add(practiceTable);
			}
		}
		return practiceTables;
	}
}
