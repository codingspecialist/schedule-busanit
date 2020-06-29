package com.bitc.practiceProgress.test;

import java.io.FileInputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcel {
	public static void main(String[] args) {
		// 파일을 읽기위해 엑셀파일을 가져온다
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream("c://utils/inputtest.xlsx");
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rowindex = 0;
		int columnindex = 0;
		// 시트 수 (첫번째에만 존재하므로 0을 준다)
		// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 행의 수
		int rows = sheet.getPhysicalNumberOfRows();
		for (rowindex = 1; rowindex < rows; rowindex++) {
			// 행을 읽는다
			XSSFRow row = sheet.getRow(rowindex);
			if (row != null) {
				// 셀의 수
				int cells = row.getPhysicalNumberOfCells();
				//class_name는 안쓰기때문에 1번컬럼부터
				for (columnindex = 0; columnindex <= cells; columnindex++) {
					// 셀값을 읽는다
					XSSFCell cell = row.getCell(columnindex);
					// 셀이 빈값일경우를 위한 널체크
					if (cell == null || cell.toString().equals("")) {
						System.out.println("빈값");
						continue;
					}
					if(columnindex == 1) {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
						String day = formater.format(cell.getDateCellValue());
						System.out.println(columnindex + "셀 내용 : " + day);

					} else if(columnindex == 0|| columnindex == 6|| columnindex == 7|| columnindex == 8) {
						System.out.println(columnindex + "셀 내용 : " + cell.toString());
					} else {
						System.out.println(columnindex + "셀 내용 : " + cell.getRawValue().toString());
					}
				}
			}
		}
	}
}
