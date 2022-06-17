package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Handle_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
//
//	@Test
//	public void TC_01_Accept_Alert() {
//		
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
//		sleepInSecond(2);
//		//Muốn thao tác được với alert đang bật lên thì cần phải switch vào nó 
//		// Swich vào alert sau lúc alert bật lên
//		alert = driver.switchTo().alert();
//		//Verify title của alert
//		Assert.assertEquals(alert.getText(), "I am a JS Alert");
//		//Accept alert
//		alert.accept();
//		//Verify alert được accept thành công
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
//		
//		
//	}
//
//	@Test
//	public void TC_02_Confirm_Alert() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
//		sleepInSecond(2);
//		//Muốn thao tác được với alert đang bật lên thì cần phải switch vào nó 
//		// Swich vào alert sau lúc alert bật lên
//		alert = driver.switchTo().alert();
//		//Verify title của alert
//		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
//		//Accept alert
//		alert.dismiss();
//		//Verify alert được accept thành công
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
//	
//	}
//	
//	@Test
//	public void TC_03_Prompt_Alert() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
//		sleepInSecond(2);
//		//Muốn thao tác được với alert đang bật lên thì cần phải switch vào nó 
//		// Swich vào alert sau lúc alert bật lên
//		alert = driver.switchTo().alert();
//		Assert.assertEquals(alert.getText(), "I am a JS prompt");
//		alert.sendKeys("Tôi là Minh Trang");
//		sleepInSecond(3);
//		alert.accept();	
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Tôi là Minh Trang");
//	
//	}
//	
//	@Test
//	public void TC_04_Authentication_Alert_Trick() {
//		//Authentication_Alert không dùng được thư viện Alert của selenium
//		//Format: https://username:Password@domain
//		//Không bật alert lên = tự điền vào url nên không yêu cầu bật alert, chạy được trên tất cả các hệ điều hành, brower
//		
//		String username = "admin";
//		String password = "admin";
//		String url = "http://"+ username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
//		
//		driver.get(url);
//		//So sánh tuyệt đối
//		//Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");
//		
//		//So sánh tương đối 
//		Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
//	}
	@Test
	public void TC_05_Authentication_Alert_Trick_Navigate_From_Other_Page() {
		//truy cập từ trang khác vào link có authentication alert

		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		
		String basicAuthenlink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		//Tách link ra thành nhiều chuỗi
		String[] authenLinkArray = basicAuthenlink.split("//");
		//Lấy chuỗi cộng vào
		
		String newAuthenLinkURL = authenLinkArray[0]+ username + ":" + password + "@" + authenLinkArray[1];
		
		driver.get(newAuthenLinkURL);
		//So sánh tuyệt đối
		//Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");
		
		//So sánh tương đối 
		Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
	}
	
//	@Test
//	public void TC_06_Authentication_Alert_AutoIT() {
//		//Chỉ chạy được trên Windows -> Mac/ linux không chạy được
//		//Không bật alert lên = tự điền vào url nên không yêu cầu bật alert, chạy được trên tất cả các hệ điều hành, brower
//		//Trước khi mở Url thì chạy execute file trước để chờ alert bật lên trước
//		String username = "admin";
//		String password = "admin";
//		Runtime.getRuntime().exec(new String[] {AutoITFirefox, username, password});
//		
//		driver.get("http://the-internet.herokuapp.com/basic_auth");
//		Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
//	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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