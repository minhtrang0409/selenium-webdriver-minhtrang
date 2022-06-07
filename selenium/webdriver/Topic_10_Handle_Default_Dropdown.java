package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Default_Dropdown {
	WebDriver driver;
	Select select;
	WebDriverWait explicitWait;
	Actions action;
	
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,15);
		action = new Actions(driver);
		
		firstName = "Hana";
		lastName = "Minh Trang";
		day = "20";
		month = "May";
		year = "1993";
		emailAddress = "minhtrang" + "hotmail@.com";
		companyName = "White House";
		password = "123456";		
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//Dropdown
		//Dùng để thao tác với element thì mới khởi tạo
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		//thao tác với dropdown
		
		//Index: Thứ tự của thẻ option
		//select.selectByIndex(1);
		 //Value: Giá trị thuộc tính value
		//select.selectByValue("5");
		//Text: Item text
		//select.selectByVisibleText("Day");
		
		select.selectByVisibleText(day);
		
		//Kiểm tra đã chọn được hay chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
		
		//Kiểm tra dropdown có phải là multiple hay không
		
		Assert.assertFalse(select.isMultiple());
		
		//Month
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
		
		//Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		//II. Verrify
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		driver.findElement(By.className("ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		Assert.assertEquals(driver.findElement(By.id("Password")).getAttribute("value"), password);
		Assert.assertEquals(driver.findElement(By.id("Password")).getAttribute("value"), password);
		Assert.assertEquals(driver.findElement(By.id("Password")).getAttribute("value"), password);
		

		
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