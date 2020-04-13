package org.scripts.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.scripts.trainings.UtilityClass;

public class AutoReadWriteFromWorkbook extends UtilityClass {

	public static void main(String[] args) throws IOException {
		
		browserLaunch();
		url("http://www.adactin.com/HotelApp/index.php", 10);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		File location = new File("C:\\Users\\GOWTHAM\\eclipse-workspace\\AutomationScripts\\Workbook\\Testdatas with username and password and results.xlsx");
		FileInputStream stream = new FileInputStream(location);
		Workbook book = new XSSFWorkbook(stream);
		Sheet page = book.getSheet("Sheet1");
		for (int i = 0; i < page.getPhysicalNumberOfRows(); i++) {
			Row rows = page.getRow(i);
			for (int j = 0; j <rows.getPhysicalNumberOfCells(); j++) {
				Cell cells = rows.getCell(j);
				int cellType = cells.getCellType();
					if (cellType == 1) {
					String stringCellValue = cells.getStringCellValue();
					System.out.println(stringCellValue);
					if (j == 0) {
						driver.findElement(By.id("username")).sendKeys(stringCellValue);	
					} else {
						driver.findElement(By.id("password")).sendKeys(stringCellValue);
					}
					
				}
				else if (cellType == 0) {
					if (DateUtil.isCellDateFormatted(cells)) {
						Date dateCellValue = cells.getDateCellValue();
						SimpleDateFormat simple = new SimpleDateFormat("dd-mm-yyyy");
						String date = simple.format(dateCellValue);
						System.out.println(date);
						if (j == 0) {
							driver.findElement(By.id("username")).sendKeys(date);	
						} else {
							driver.findElement(By.id("password")).sendKeys(date);
						}
					}
					else {
						double numericCellValue = cells.getNumericCellValue();
						long numValue = (long) numericCellValue;
						String numeric = String.valueOf(numValue);
						System.out.println(numeric);
						if (j == 0) {
							driver.findElement(By.id("username")).sendKeys(numeric);	
						} else {
							driver.findElement(By.id("password")).sendKeys(numeric);
						}
					}
				}
			}
			driver.findElement(By.id("login")).click();
			
			// Locating the invalid login message and write it on new cell  
			WebElement errorMessage = driver.findElement(By.xpath("//b[text()='Invalid Login details or Your Password might have expired. ']"));
			String error = errorMessage.getText();
			Cell message = rows.createCell(2);
			message.setCellValue(error);
			FileOutputStream outStream = new FileOutputStream(location);
			book.write(outStream);				
		}
		
	}
}
