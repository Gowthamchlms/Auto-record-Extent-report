package org.scripts.excel;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.scripts.trainings.UtilityClass;

import pom.pojo.locators.LoginPageAdactin;

public class ExcelScripts extends UtilityClass {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
	    LoginPageAdactin login ;
		browserLaunch();
		url("http://www.adactin.com/HotelApp/index.php", 10);
		
		for (int i = 1; i < 10; i++) {
			login = new LoginPageAdactin();		
			fillFromWorkbook(login.getUsername(), i, 1);
			fillFromWorkbook(login.getPassword(), i, 2);
			click(login.getLogin());
			login.getLogin().clear();
			login.getPassword().clear();
			
			
		}
		
	}

}
