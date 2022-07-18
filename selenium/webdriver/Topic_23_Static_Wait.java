package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Static_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		if(osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {			
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
	
	}
// Static wait, wait cứng=hard wait
	// Cứng: Nếu như thỏa mãi điều kiện rồi nhưng vẫn chờ hết timeout
	//Implicit/Explicit/ Fluent: NẾu như thỏa mãi đk thì k cần chờ hết timeout
	@Test
	public void TC_01_Equal() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Thread.sleep(5000);
		
		//Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
	}
	@Test
	public void TC_02_Less() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//3s k đủ để loading icon biến mất
		Thread.sleep(3000);
		
		//Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
	}
	@Test
	public void TC_03_Greater() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();
		//10s dư time để icon biến mất
		Thread.sleep(10000);

		//Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
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