package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Brower_PII {
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
	public void TC_01_verifyUrl() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		//Step 03
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.techpanda.org/index.php/customer/account/login/");
		//Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Step 05
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		
		
		
	}

	@Test
	public void TC_02_verifyTitle() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(4);
		//Step 03
		String loginPage = driver.getTitle();
		Assert.assertEquals(loginPage, "Customer Login");
		//Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(4);
		//Step 05
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(4);
		//Step 03
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(4);
		//Step 04
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		//Step 05
		driver.navigate().back();
		//Step 06
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.techpanda.org/index.php/customer/account/login/");
		//Step 07
		driver.navigate().forward();
		//Step 08
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	@Test
	public void TC_04_getPageSource() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(3);
		//Step 03
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		//Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
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