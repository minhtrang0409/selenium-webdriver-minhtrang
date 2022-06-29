package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Windown_Tabs {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
// MAC
	//String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		if(osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {			
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
		
		public void TC_01_Basic_form() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
//		driver.switchTo().w
		//Lấy ra ID của driver đang đứng tại tab/windown(Active)
		String formTabID = driver.getWindowHandle();
		System.out.println("Form tab id " + formTabID);
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		swichToWindownbytitle("Google");
		System.out.println(driver.getCurrentUrl());
		//Lấy ID trang B
		String googleID = driver.getWindowHandle();
		
		//Quay về trang A
		swichToWindownbytitle("SELENIUM WEBDRIVER FORM DEMO");
		System.out.println(driver.getCurrentUrl());
		
		//Mở sang Facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		
		sleepInSecond(2);
		swichToWindownbytitle("Facebook – log in or sign up");
		sleepInSecond(2);
		System.out.println(driver.getCurrentUrl());
		//Quay về trang A
		swichToWindownbytitle("SELENIUM WEBDRIVER FORM DEMO");
		System.out.println(driver.getCurrentUrl());
		//Chuyển sang tiki
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		swichToWindownbytitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println(driver.getCurrentUrl());
		
		Closealltabwithoutparent(formTabID);
		
	}

	
	
	public void TC_02_TechPanda() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
		//driver.findElement(By.cssSelector("button[title='Compare']")).click();
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		
		//Swich qua Windowns compare
		swichToWindownbytitle("Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		driver.findElement(By.xpath("//span[text()='Close Window']")).click();
		
		// Chuyển driver về tab Mobile
		swichToWindownbytitle("Mobile");
		sleepInSecond(2);
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Mobile']")).isDisplayed());
		
	}
		
	
	@Test
	public void TC_03_Cambrige() {
	driver.get("https://dictionary.cambridge.org/vi/");
	sleepInSecond(4);
	driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
	
	String parentID = driver.getWindowHandle();
	swichToWindownbyID(parentID);
	sleepInSecond(2);
	
	driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
	driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Automation000***");
	driver.findElement(By.xpath("//input[@value='Log in']")).click();
	sleepInSecond(3);
	swichToWindownbytitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
	
	Assert.assertEquals(driver.findElement(By.cssSelector("span.cdo-username")).getText(), "Automation FC");
	
	}
	//Chỉ dùng cho 2 tab/windowns
	public void swichToWindownbyID(String parentID) {
		//lấy ra tất cả IDs của tab/windown đang mở
		Set<String> allWindownsID = driver.getWindowHandles();
		//Dùng vòng lặp để duyệt qua từng id
		for(String ID : allWindownsID) {
			//nếu như có ID nào khác với parentID
			if(!ID.equals(parentID)) {
				//Swich vào
				driver.switchTo().window(ID);
				sleepInSecond(2);
			}
		}

	}
	
	
	//Dùng được cho >2 tab/windowns
	public void swichToWindownbytitle(String expectedPageTitle) {
		//lấy ra tất cả IDs của tab/windown đang mở
		Set<String> allWindownsID = driver.getWindowHandles();
		//Dùng vòng lặp để duyệt qua từng id
		for(String ID : allWindownsID) {	
			//Swich vào từng tab
			driver.switchTo().window(ID);
			//Lấy ra title của page đã swich vào rồi
			String titlecurrenttab = driver.getTitle();
			
			if(titlecurrenttab.equals(expectedPageTitle)) {
				//Thoái khỏi vòng lặp
				break;
			}
		}
				sleepInSecond(2);
	}
	//Close all tab trừ parent page
	public void Closealltabwithoutparent(String parentID) {
		//lấy ra tất cả IDs của tab/windown đang mở
				Set<String> allWindownsID = driver.getWindowHandles();
				//Dùng vòng lặp để duyệt qua từng id
				for(String ID : allWindownsID) {	
					
					if(!ID.equals(parentID)) {
						driver.switchTo().window(ID);
						//Đóng tab/windows active
						driver.close();
						//Đóng cả brower
						//driver.quit;
					}
		}
				driver.switchTo().window(parentID);
				System.out.println(driver.getCurrentUrl());
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