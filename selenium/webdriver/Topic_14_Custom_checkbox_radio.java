package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Custom_checkbox_radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_01_() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		
		
		WebElement beforeRadio = driver.findElement(By.xpath("//span[text()='Before']/preceding-sibling::span/input"));
		jsExecutor.executeScript("arguments[0].click();", beforeRadio);
		Assert.assertTrue(beforeRadio.isSelected());
	}

	@Test
	public void TC_02_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		//Radio
		By radio = By.xpath("//div[@data-value='Cần Thơ']");
		driver.findElement(radio).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(radio).getAttribute("aria-checked"), "true");
		sleepInSecond(1);
		//Checkbox
		By myquangCheckbox = By.xpath("//div[@aria-label = 'Mì Quảng']");
		driver.findElement(myquangCheckbox).click();
		Assert.assertEquals(driver.findElement(myquangCheckbox).getAttribute("aria-checked"), "true");
		
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