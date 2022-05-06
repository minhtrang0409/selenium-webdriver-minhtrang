package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//
	@Test
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
		
		//lấy ra URL của page hiện tại
		driver.getCurrentUrl();
		
		//Lấy ra Source code  (HTML/ CSS/JS) của page hiện tại
		driver.getPageSource();
		
		//lấy ra title của page hiện tại
		//driver.getTitle();
		
		//****Windowns/ Tab *******
		//Dùng để xử lý windown/tab
		//Láy ra id của window/tab đang active
		driver.getWindowHandle();
		
		//Lấy ra ID của tất cả windowns/tab đang có
		driver.getWindowHandles();
		
		
		//****Framework- Cookie***********
		//Cookie: Lưu lại phiên đăng nhập/session/ dữ liệu duyệt web/....
		//driver.manage().addCookie(null);
		//*******Framework-Log****
		//driver.manage().logs().
		
		//Thời gian chờ cho findElement/FindElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Chờ cho 1 page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//Chờ cho 1 đoạn code JavaScrip được thực thi thành công
		
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		
		driver.manage().window().getPosition();
		driver.manage().window().setPosition(new Point(0, 0));
		
		
		driver.manage().window().getSize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().window().fullscreen();
		
		
	}
		
	@Test
	public void TC_01_() {
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