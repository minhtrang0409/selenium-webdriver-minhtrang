package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper02_Manage_Cart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest(alwaysRun = true)
	public void initBrower() {
		System.out.println("--------Open brower and driver ---------");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

	}
	@Test(groups = {"admin", "cart"})
	public void Cart_01_Create_Product() {
		
	}
	@Test(groups = {"admin", "cart"})
	public void Cart_02_View_Product() {
		
	}
	@Test(groups = {"admin", "cart"})
	public void Cart_03_Update_Product() {
		
	}
	@Test(groups = {"admin", "cart"})
	public void Cart_04_Delete_Product() {
		
	}
	@AfterTest(alwaysRun = true)
	public void cleanBrowser() {
		System.out.println("------Clean browser and driver ---------");
		driver.quit();
	}
}
