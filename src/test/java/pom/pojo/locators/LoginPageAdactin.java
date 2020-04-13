package pom.pojo.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.scripts.trainings.UtilityClass;

public class LoginPageAdactin extends UtilityClass {

	public LoginPageAdactin() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id = "username")
	private WebElement username;
	
	@FindBy (id = "password")
	private WebElement password;
	
	@FindBy (id = "login")
	private WebElement login;

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLogin() {
		return login;
	}
	
}
