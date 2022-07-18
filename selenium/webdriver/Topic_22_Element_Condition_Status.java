package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		if(osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {			
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Visible_Display_Visibility() {
		driver.get("https://www.facebook.com/");
		//1. Có trên UI (bắt buộc
		//2. Có trong HTML DOM (bắt buộc)
		
		//Wait cho email address textbox hiển thị
		//Chờ cho email textbox hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
		
		
	}

	
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		//1. Không có trên UI (bắt buộc)
		//2. Có trong HTML
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void TC_03_Invisible_Undisplayed_Invisibility_II() {
		//1. Không có trên UI (bắt buộc)
		//2. Không Có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		//Chờ cho Re-enter emal texbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
	}
	@Test
	public void TC_04_Presence_I() {
		//1. Có ở trên UI
		//2. Có ở trong cây HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		//Chờ cho email address textbox presence trong HTML trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
	}
	@Test
	public void TC_04_Presence_II() {
		//1. Khong Có ở trên UI
		//2. Có ở trong cây HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		//Chờ cho email address textbox presence trong HTML trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}
	@Test
	public void TC_05_Staless() {
		//1. Không có trên UI (bắt buộc)
		//2. Không có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		//Phase1: Element cso trong cây HTML
		WebElement reEnteremailtexbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		//Thao tac với element nào đó làm cho element reEnter email không còn trong DOM
		//close popup
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		//Chờ cho Re-enter emal texbox không hiển thị trong DOM trong vòng 10s
		expliciWait.until(ExpectedConditions.stalenessOf(reEnteremailtexbox));

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