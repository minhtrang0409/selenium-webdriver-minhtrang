package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Frame_Iframe {
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
	
	@Test
	public void TC_01_Iframe_Kyna() {
		//A
		driver.get("https://kyna.vn/");
		
		//Switch to
		// Alert
		// Frame/Iframe
		//Windown
		//Cách 1, dùng index
		//A->B
		//driver.switchTo().frame(0);
		
		//Cách 2: Dùng ID/name
		//driver.switchTo().frame(osName)
		
		//Cách 3: Dùng element: all cases
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		
		String facebooklikeNumber = driver.findElement(By.xpath("//a[text() = 'Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebooklikeNumber, "166K likes");
		sleepInSecond(1);
		//Không support nhảy iframe B qua iframe C ( 2 iframe đang thuộc A)
		//B->A
		driver.switchTo().defaultContent();
		
		//A->C
		driver.switchTo().frame("cs_chat_iframe");
		
		//Thao tác element thuộc C
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Jouhn");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456789");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name ='message']")).sendKeys("testing");
		
		sleepInSecond(1);
		//C->A
		driver.switchTo().defaultContent();
		//A Element thuộc A
		String keyword = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(5);
		
		//Verify 
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		//Number
		Assert.assertEquals(courseName.size(), 10);
		
		for (WebElement course : courseName) {
			System.out.println(course.getText());
			
			//Tên khóa học chứa từ Excel
			Assert.assertTrue(course.getText().contains(keyword));
		}
	 
	}

	@Test
	public void TC_02_HDFC_Back() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		WebElement password = driver.findElement(By.xpath("//input[@name='fldPassword']"));
		
		Assert.assertTrue(password.isDisplayed());
		password.sendKeys("123456");
		sleepInSecond(3);
	
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