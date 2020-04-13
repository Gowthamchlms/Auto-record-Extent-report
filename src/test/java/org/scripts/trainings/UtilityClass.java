package org.scripts.trainings;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.naming.RefAddr;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//utility method to pass the values

public class UtilityClass {
public static WebDriver driver;
	
	// To launchbthe browser
	
	public static void browserLaunch() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\GOWTHAM\\eclipse-workspace\\AutomationScripts\\Drivers\\chromedriver.exe");
		ChromeOptions popup = new ChromeOptions();
		ChromeOptions disable = popup.addArguments("-disable--notifications");
		driver = new ChromeDriver(disable);
		driver.manage().window().maximize();
	}
	
	// To enter the URL
	
	public static void url(String url, int time) {
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}
	
    // To pass the values to any field
	
	public static void fill(WebElement e, String data ) {
		e.sendKeys(data);
	}
	
	// To click any kind of buttons 
	
	public static void click(WebElement e) {
		e.click();
	}
	
	// To take Screenshots
	
	public static void screenshots(String location) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		System.out.println("The source folder location is :" + source);
		File destination = new File(location);
		System.out.println("The destination folder location is :" + destination);
		FileUtils.copyFile(source, destination);
	}
	
	// To do Actions 
	// To do right click
	public static void rightClick(WebElement e) {
		Actions act = new Actions(driver);
		act.contextClick(e).perform();
	}
	
	// To do double click
	public static void doubleClick(WebElement e) {
			Actions act = new Actions(driver);
			act.doubleClick(e).perform();
		}
		

	// To do double click
	public static  void dragAndDropk(WebElement e, WebElement  source, WebElement target) {
			Actions act = new Actions(driver);
			act.dragAndDrop(source, target);
		}
			
	// To perform more than one actions
		
	public static void buildPerform(WebElement doubleClick, WebElement rightClick) {
			Actions act = new Actions(driver);
			act.doubleClick(doubleClick).contextClick(rightClick).build().perform();
		}
	
	// To do keyboard actions 
	
	public static void keys(int position) throws AWTException {
		Robot keys = new Robot();
		for (int i = 0; i <= position; i++) {
			keys.keyPress(KeyEvent.VK_UP);
			keys.keyRelease(KeyEvent.VK_UP);
		}
		keys.keyPress(KeyEvent.VK_ENTER);
		keys.keyRelease(KeyEvent.VK_ENTER);		
	}
	
	// To do Windows handling
	
	public static void windowsHandleByIteration() {
		String superId = driver.getWindowHandle();
		Set<String> subIds = driver.getWindowHandles();
		for (String allSubIds : subIds) {
			if (!(allSubIds.equals(superId))) {
				driver.switchTo().window(allSubIds);
			}
		}
	}
	
	// To do windows handle by index position
	
	public static void windowsHandleByIndex(int index) {
		String superId = driver.getWindowHandle();
		Set<String> subIds = driver.getWindowHandles();
		List<String> listOfIds = new ArrayList<String>();
		listOfIds.addAll(subIds);
		String id = listOfIds.get(index);
		driver.switchTo().window(id);
	}
	
	// To handle Frames 
	
	public static void framesHandle(WebElement e) {
		driver.switchTo().frame(e);		
	}
	
	// To count no of frames & transfer control to all frames 
	
	public static void handleNumberOfFrames() {
		List<WebElement> all = driver.findElements(By.tagName("iframe"));
		int noOfFrames = all.size();
		System.out.println(noOfFrames);
		for (int i = 0; i < noOfFrames; i++) {
			driver.switchTo().frame(i);
		}
	}
	
	// To handle a particular frames
	
	public static void handleAParticularFrames(int index) {
		List<WebElement> all = driver.findElements(By.tagName("iframe"));
		int noOfFrames = all.size();
		System.out.println(noOfFrames);
		driver.switchTo().frame(index);
	}
	
	// To handle alerts
	// To handle a simple alert
	
	public static void simpleAlert() {
		Alert popups = driver.switchTo().alert();
		popups.accept();
	}
	
	// To handle a Confirm alert
	
		public static void confirmAlert() {
			Alert popups = driver.switchTo().alert();
			popups.accept();
			//popups.dismiss();
		}
		
	// To handle a Confirm alert
		
		public static void promptAlert(String data) {
			Alert popups = driver.switchTo().alert();
			popups.sendKeys(data);
			popups.accept();
		}
			
	// To handle dropdowns
		// by visible text
		
		public static void dropDownByVisibletext(WebElement dropdown, String text) {
			Select dropdowns = new Select(dropdown);
			dropdowns.selectByVisibleText(text);
		}
		
	// To handle dropdowns 
		// by index position
		
		public static void dropDownByIndexPosition(WebElement dropdown, int index) {
			Select dropdowns = new Select(dropdown);
			dropdowns.selectByIndex(index);
		}
	// To handle dropdowns 
		// by index value
				
		public static void dropDownByValue(WebElement dropdown, String value) {
			Select dropdowns = new Select(dropdown);
			dropdowns.selectByValue(value);
		}
	// To select multiple options in a dropdown
		
		public static void selectMultipleOptions(WebElement dropdown) {
			Select dropdowns = new Select(dropdown);	
			boolean multipleOptions = dropdowns.isMultiple();
			List<WebElement> allOptions = dropdowns.getOptions();
			//int sizeOfDropdown = allOptions.size();
			for (int i = 0; i < allOptions.size(); i++) {
				dropdowns.selectByIndex(i);
			}
		}
		
		// To read the data from Workbook 
		
		public static String readDataFromWorkbook(int row, int column) throws IOException {
			String value = null;
			File location = new File("C:\\Users\\GOWTHAM\\eclipse-workspace\\AutomationScripts\\Workbook\\Testdatas with username and password and results.xlsx");
			FileInputStream input = new FileInputStream(location);
			Workbook book = new XSSFWorkbook(input);
			Sheet page = book.getSheet("Sheet1");
			Row rows = page.getRow(row);
		//	for (int i = 1; i < rows.getPhysicalNumberOfCells(); i++) {
				Cell cells = rows.getCell(column);
				int cellType = cells.getCellType();
				if (cellType == 1) {
					value = cells.getStringCellValue();
				}
				else if (cellType == 0) {
					if (DateUtil.isCellDateFormatted(cells)) {
						Date dateCellValue = cells.getDateCellValue();
						SimpleDateFormat simple = new SimpleDateFormat("dd-mm-yyyy");
						value = simple.format(dateCellValue);
					}
					else {
						double numericCellValue = cells.getNumericCellValue();
						long numValue = (long) numericCellValue;
						value = String.valueOf(numValue);
					}
				}
				
		//	}			
			
			return value;
		}
		
		// To feed the data from excel sheet
		
		public static void fillFromWorkbook(WebElement e, int row, int column) throws IOException {
			e.sendKeys(readDataFromWorkbook(row, column));
		}
		
		
	}
