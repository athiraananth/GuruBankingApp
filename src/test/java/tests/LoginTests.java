package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPages_POM.LoginPage;

public class LoginTests {
	
private static WebDriver driver;
	
	@BeforeTest
	public static void initDriver() {
		System.setProperty("webdriver.chrome.driver", "F:\\Athira\\Selenium\\Selenium 3.141.59\\Driver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get(" http://www.demo.guru99.com/V4/");
		driver.manage().window().maximize();
	}
	
	
	@Test
	public static void LoginTest() {
		LoginPage lPage= new LoginPage(driver);
		lPage.setUser_Id("mngr240444");
		lPage.setPassword("zyjAgUn");
		lPage.click_Login();
		String expectedHPage="http://www.demo.guru99.com/V4/manager/Managerhomepage.php";
		Assert.assertEquals(driver.getCurrentUrl(), expectedHPage);
	}

}
