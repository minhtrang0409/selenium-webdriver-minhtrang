package webdriver;

import java.sql.Driver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, fullName ;

	

	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		//Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Set thời gian đi tìm element
		//Hàm này chỉ áp dụng cho việc tìm element (findElement/ FindElements)			
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Phóng to browser lên
		driver.manage().window().maximize();
		
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afd"+ generateRandomNumber() + "@hotmail.com";
		password = "123456789";
		fullName = firstName +" "+ lastName;
				
	}
	
	
	@Test
	public void TC_01_Login_Empy_Email_Password() {
	    //Mở app ra
		driver.get("http://live.techpanda.org/index.php/");
		// Click vào button đăng ký
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// Để trống username/password
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		// Verify error message
		// driver.findElement(By.id("advice-required-entry-email")).getText();
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
			
		}

	@Test
	public void TC_02_Login_with_Invalid_Email() {
		
		 //Mở app ra
		driver.get("http://live.techpanda.org/index.php/");
		// Click vào button đăng ký
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// Để trống username/password
		driver.findElement(By.id("email")).sendKeys("134@234.241");
		driver.findElement(By.name("login[password]")).sendKeys("123565");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		// Verify error message
		// driver.findElement(By.id("advice-required-entry-email")).getText();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

		} 

	
	
	@Test
	public void TC_03_Login_with_Password_lessthan_6() {
		
		
		 //Mở app ra
		driver.get("http://live.techpanda.org/index.php/");
		// Click vào button đăng ký
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// Để trống username/password
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		// Verify error message
		// driver.findElement(By.id("advice-required-entry-email")).getText();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

		
			
	}
	
	@Test
	public void TC_04_Login_with_incorrect_Email_Password() {
		   
		    //Mở app ra
			driver.get("http://live.techpanda.org/index.php/");
			// Click vào button đăng ký
			driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
			// Để trống username/password
			driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
			driver.findElement(By.name("login[password]")).sendKeys("123123132");
			driver.findElement(By.cssSelector("button[title='Login']")).click();
			
			// Verify error message
			// driver.findElement(By.id("advice-required-entry-email")).getText();
			
			Assert.assertEquals(driver.findElement(By.cssSelector("li[class='error-msg'] span")).getText(),"Invalid login or password.");


	}
	
	@Test
	public void TC_05_Create_new_account() {
		
	    //Mở app ra
		driver.get("http://live.techpanda.org/index.php/");
		// Click vào button đăng ký
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		//Click  Create an account để tới trang đăng ký tài khoản
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		//Nhập thông tin hợp lệ  vào 
		
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);

		//click register button
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		// Verify tuyệt đối		
		Assert.assertEquals(driver.findElement(By.cssSelector("li[class='success-msg'] span")).getText(),"Thank you for registering with Main Website Store.");
		
		// Kiểm tra thông tin tương đối 
		String contactInforText= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
		
		// logout
		driver.findElement(By.xpath("//header[@id='header']//span[text() = 'Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}
	
	@Test
	public void TC_06_Login_with_valid_Email_Password() {
		 //Mở app ra
		driver.get("http://live.techpanda.org/index.php/");
		// Click vào button đăng ký
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

		// Để trống username/password
		driver.findElement(By.id("email")).sendKeys("emailAddress");
		driver.findElement(By.name("login[password]")).sendKeys("password");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
	}
	    
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	

}