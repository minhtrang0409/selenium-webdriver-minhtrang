package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
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
		
	    // Mở app lên
		driver.get("");
	}

	@Test
	public void TC_01_() {
		
		//Tìm (Find) số ít- trả về 1 cái
		//thao tác trực tiếp, không khai báo biến- sử dụng 1 lần k dùng lại element này
		driver.findElement(By.id("")).click();
		driver.findElement(By.id("")).isDisplayed();
		
		//Khai báo biến - dùng lại element nhiều lần
		WebElement loginButton = driver.findElement(By.id(""));
		loginButton.click();
		loginButton.isDisplayed();
		
		
		// Tìm find số nhiều- trả về 1 hoặc >1
		driver.findElements(By.id("")).size();
		
		// Lặp lại nhiều lần, muốn thao tác nhiều lần thì phải khai báo biến
		List<WebElement> loginCheckboxes = driver.findElements(By.id(""));
		


		//Thao tác(Action): click/ type/ select/hover(Di chuột)/drop....
		
		//Kiem tra (Verify/Assert) getText/ GetAttribute/ Get Css
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
}