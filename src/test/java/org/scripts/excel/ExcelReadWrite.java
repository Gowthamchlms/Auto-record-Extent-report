package org.scripts.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ExcelReadWrite {
public static void main(String[] args) throws IOException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\GOWTHAM\\eclipse-workspace\\AutomationScripts\\Drivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("http://www.adactin.com/HotelApp/index.php");
	File location = new File("C:\\Users\\GOWTHAM\\eclipse-workspace\\AutomationScripts\\Workbook\\Testdatas with username and password and results.xlsx");
	FileInputStream stream = new FileInputStream(location);
	Workbook book = new XSSFWorkbook(stream);
	Sheet page = book.getSheet("Sheet1");
	for (int i = 1; i < page.getPhysicalNumberOfRows(); i++) {
	Row rowData = page.getRow(i);
	for (int j = 1; j < rowData.getPhysicalNumberOfCells(); j++) {
	Cell cellData = rowData.getCell(j);	
	//System.out.println(cellData);
	int cellType = cellData.getCellType();
	if (cellType==1) {
		String stringCellValue = cellData.getStringCellValue();
		System.out.println(stringCellValue);
	}
	else if (cellType == 0) {
		if (DateUtil.isCellDateFormatted(cellData)) {
			Date dateCellValue = cellData.getDateCellValue();
			SimpleDateFormat simple = new SimpleDateFormat("dd-mm-yyyy");
			String format = simple.format(dateCellValue);
			System.out.println(format);
		}
		else {
			double numericCellValue = cellData.getNumericCellValue();
			long numericValue = (long) numericCellValue;
			String numValue = String.valueOf(numericValue);
			System.out.println(numValue);
		
		}
	
	}
	
	}
	
 }
	
}

}
