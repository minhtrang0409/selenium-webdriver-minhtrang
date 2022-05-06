package webdriver;

import java.sql.Driver;
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

public class Topic_03_Xpath_partII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//
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
		
	}

	@Test
	public void TC_01_Register_Empty_Data() {
	    //Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Kiểm tra message lỗi hiển thị
		//driver.findElement(By.id("txtFirstname-error")).getText();
	
		
		//Ham get.Text kiểm tra 3 TH
		// Kiêm tra 1 điều kiện trả về là đúng
		//Assert.assertTrue(true);
		// Kiêm tra 1 điều kiện trả về là sai
		//Assert.assertFalse(false);
		
		// Kiêm tra 1 điều kiện trả về giống với điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		
    	//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Nhập liệu
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Minh Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("123@123@123");		
		driver.findElement(By.id("txtCEmail")).sendKeys("123@123@123");		
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0336258752");
		
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra các error message hiển thị tại Email và Confirm email

		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
		} 

	
	
	@Test
	public void TC_03_Register_incorrect_Confirm_Email() {
		
		//Mở app ra
				driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
				// Nhập liệu
				
				driver.findElement(By.id("txtFirstname")).sendKeys("Minh Trang");
				driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");		
				driver.findElement(By.id("txtCEmail")).sendKeys("12e3@gmail.com");		
				driver.findElement(By.id("txtPassword")).sendKeys("123456");
				driver.findElement(By.id("txtCPassword")).sendKeys("123456");
				driver.findElement(By.id("txtPhone")).sendKeys("0336258752");
				
				
				//Click đăng ký button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				// Kiểm tra các error message hiển thị tại Email và Confirm email
				Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
				
	}
	
	@Test
	public void TC_04_Register_Password_Less_Than_6_Chars() {
		
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Nhập liệu
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Minh Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");		
		driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");		
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0336258752");
		
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra các error message hiển thị tại Email và Confirm email

		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}
	
	@Test
	public void TC_05_Register_incorrect_Confirm_Password() {
		//Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Nhập liệu
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Minh Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");		
		driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");		
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123654");
		driver.findElement(By.id("txtPhone")).sendKeys("0336258752");
		
		
		//Click đăng ký button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra các error message hiển thị tại Email và Confirm email


		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_Register_incorrect_PhoneNumber() {
		//Mở app ra
				driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
				// Nhập liệu
				
				driver.findElement(By.id("txtFirstname")).sendKeys("Minh Trang");
				driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");		
				driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");		
				driver.findElement(By.id("txtPassword")).sendKeys("1234567");
				driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
				driver.findElement(By.id("txtPhone")).sendKeys("0336252");
				
				
				//Click đăng ký button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				// Kiểm tra các error message hiển thị

				Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Số điện thoại phải từ 10-11 số.");
				
				// Nhập liệu
				
				driver.findElement(By.id("txtFirstname")).sendKeys("Minh Trang");
				driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");		
				driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");		
				driver.findElement(By.id("txtPassword")).sendKeys("123456");
				driver.findElement(By.id("txtCPassword")).sendKeys("123456");
				driver.findElement(By.id("txtPhone")).sendKeys("3625212345");
				
				Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Số điện thoại phải từ 10-11 số.");
				
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}