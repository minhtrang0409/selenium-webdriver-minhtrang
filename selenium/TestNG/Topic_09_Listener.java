package TestNG;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Listener {
	public static WebDriver driver;
	String firstName, lastName, password, fullName ;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firstName = "Automation";
		lastName = "FC";
		password = "123456789";
		fullName = firstName +" "+ lastName;
	}
	
	@Test(invocationCount=5)
	//Testcase được chạy 5 lần. 
	public void TC_Register_Account() {
	driver.get("http://live.techpanda.org/index.php/");
	driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
	driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
	driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
	String emailAddress = "afd"+ generateRandomNumber() + "@hotmail.com";
	driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAddress);
	driver.findElement(By.cssSelector("#password")).sendKeys(password);
	driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);
	driver.findElement(By.cssSelector("button[title='Register']")).click();
	Assert.assertEquals(driver.findElement(By.cssSelector("li[class='success-msg'] span")).getText(),"Thank you for registering with Main Website Store.");
	driver.findElement(By.xpath("//header[@id='header']//span[text() = 'Account']")).click();
	driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
