package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Radio_Checkbox {
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
	public void TC_01_Default_checkbox_radio() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//1.Chọn (click)
		By Anemia = By.xpath("//input[@value='Anemia']");
		By Heart = By.xpath("//input[@value='Heart Disease']");
		By Fainting = By.xpath("//input[@value='Fainting Spells']");
		
		By exercise = By.xpath("//input[@value='3-4 days']");
		By eating = By.xpath("//input[@value=\"I don't have a diet plan\"]");
		By smoke = By.xpath("//input[@value='0-1 pack/day']");

				
		driver.findElement(Anemia).click();
		driver.findElement(Heart).click();
		driver.findElement(Fainting).click();
		sleepInSecond(3);
		
		
		driver.findElement(exercise).click();
		driver.findElement(eating).click();
		driver.findElement(smoke).click();
		sleepInSecond(3);
		//2.Verify
		//Chọn rồi isSelected -> True
		Assert.assertTrue(driver.findElement(Anemia).isSelected());
		Assert.assertTrue(driver.findElement(Heart).isSelected());
		Assert.assertTrue(driver.findElement(Fainting).isSelected());
		Assert.assertTrue(driver.findElement(exercise).isSelected());
		Assert.assertTrue(driver.findElement(eating).isSelected());
		Assert.assertTrue(driver.findElement(smoke).isSelected());
		
		//Bỏ chọn
		driver.findElement(Anemia).click();
		driver.findElement(Heart).click();
		driver.findElement(Fainting).click();
		sleepInSecond(3);
		
		
		driver.findElement(exercise).click();
		driver.findElement(eating).click();
		driver.findElement(smoke).click();
		sleepInSecond(3);
		
		//Chọn rồi isSelected -> True
		Assert.assertFalse(driver.findElement(Anemia).isSelected());
		Assert.assertFalse(driver.findElement(Heart).isSelected());
		Assert.assertFalse(driver.findElement(Fainting).isSelected());
		Assert.assertTrue(driver.findElement(exercise).isSelected());
		Assert.assertTrue(driver.findElement(eating).isSelected());
		Assert.assertTrue(driver.findElement(smoke).isSelected());

		
	}

	@Test
	public void TC_02_Select_all_Checkboxes() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allcheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		//select all
		for (WebElement checkbox : allcheckboxes) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				//sleepInSecond(1);
				Assert.assertTrue(checkbox.isSelected());
			}
		}
		//Bỏ chọn
		for (WebElement checkbox : allcheckboxes) {
			if(checkbox.isSelected()) {
				checkbox.click();
				//sleepInSecond(1);
				Assert.assertFalse(checkbox.isSelected());
			}
		}
	}
	
	@Test
	public void TC_03_() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		WebElement dualzone = driver.findElement(By.xpath("//input[@id='eq5']"));
		
		dualzone.click();
		Assert.assertTrue(dualzone.isSelected());
		//bỏ chọn
		dualzone.click();
		Assert.assertFalse(dualzone.isSelected());
	//Radio
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement twoPetrol = driver.findElement(By.xpath("//input[@id='engine3']"));
		
		if(!twoPetrol.isSelected()) {
			twoPetrol.click();
		}
		
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