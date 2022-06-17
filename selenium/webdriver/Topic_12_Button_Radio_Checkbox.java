package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(5);
		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		//Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		driver.findElement(By.id("login_username")).sendKeys("minhtrang.123@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		//Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		//Verify background color
		String loginButtonBackgroundColor = Color.fromString(driver.findElement(loginButtonBy).getCssValue("background-color")).asHex().toUpperCase();
		System.out.println(loginButtonBackgroundColor);
		Assert.assertEquals(loginButtonBackgroundColor, "#C92127");
		
	}

	@Test
	public void TC_02_() {
	}
	
	@Test
	public void TC_03_() {
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}