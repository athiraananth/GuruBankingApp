package webPages_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	@FindBy(xpath="//input[@name='uid']")
	private WebElement userId;
	@FindBy(xpath="//input[@name='password']")
	private WebElement password;
	@FindBy(css="input[name='btnLogin'")
	private WebElement login;
	@FindBy(css="input[name='btnReset'")
	private WebElement reset;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUser_Id(String username) {
		userId.sendKeys(username);
	}
	
	public void setPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void click_Login() {
		login.click();
	}
	
	public void click_Reset() {
		reset.click();
	}
}
