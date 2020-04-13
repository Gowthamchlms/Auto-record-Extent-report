package org.extent.reports;

import java.awt.AWTException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.scripts.trainings.UtilityClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;


public class ExtentReportsDemo extends UtilityClass {
public static void main(String[] args) throws InterruptedException, AWTException, ATUTestRecorderException {
	
	// To record the Script
	DateFormat dat = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
	Date date = new Date();
	
	// ATU Test Recorder
	ATUTestRecorder record = new ATUTestRecorder("D:\\Automation Testing Profile\\Scripts Recording", "ExtentReportInGooglesearch"+dat.format(date), false);
	record.start();
	
	// EXTENT Report
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
	
	// Create Extent Reports & attach reporters
	
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(htmlReporter);
	
	// Create a toggle for a given test and adds all log events under it
	
	ExtentTest test = extent.createTest("My first test case on google site","Test is on Search Functionality" );
	
	browserLaunch();
	

	test.log(Status.INFO, "Starts my test case on Google site");
	url("https://www.google.co.in/", 5);
	test.pass("Navigated to the Google site");
	
	// Locators
	WebElement searchBox = driver.findElement(By.name("q"));
	
	
	// Actions to perform
	fill(searchBox, "Automation");
	test.pass("Text entered in the search box");
	keys(1);
	test.pass("The Enter key is pressed");
	Thread.sleep(3000);
	driver.close();
	test.pass("The tab gets closed");
	
	// Calling Flush writes everything on the reports file
	extent.flush();
	
	record.start();
}	
}
