package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Fixed_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	public void TC_01_Fixed_IN_DOM_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		WebElement loginpopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		//Verify login popup is undisplay
		Assert.assertFalse(loginpopup.isDisplayed());
	
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		//Verify login popup is undisplay
		Assert.assertTrue(loginpopup.isDisplayed());
		
		driver.findElement(By.id("account-input")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("123456");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains (@class,'btn-login-v1')]")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][class*='modal fade in'] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		//Click để close popup
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][class*='modal fade in'] button.close")).click();
		Assert.assertFalse(loginpopup.isDisplayed());
		sleepInSecond(3);
	
	}

	public void TC_02_Fixed_Not_In_Dom() {
		driver.get("https://kyna.vn/");
	
		WebElement loginpopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		//Verify login popup is undisplay
		Assert.assertFalse(loginpopup.isDisplayed());
	
		driver.findElement(By.cssSelector("a.login-btn")).click();
		//
		sleepInSecond(3);
		//Verify login popup is undisplay
		Assert.assertTrue(loginpopup.isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		//Click để close popup
		driver.findElement(By.cssSelector("div#k-popup-account-login-mb button.k-popup-account-close")).click();
		Assert.assertFalse(loginpopup.isDisplayed());
		sleepInSecond(3);
	
	}
	
	
	public void TC_03_Fixed_Not_In_Dom_tiki() {
		driver.get("https://tiki.vn/");
		
		//Khi mới mở trang ra thì poup không hề có trong DOM nên sẽ không thể findelement được
		//Show ra lỗi NoSuchElementException sau khoảng thời gian là xx giây(impliciWait)
	
		List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		
		Assert.assertEquals(loginPopup.size(), 0);
		//Click vào đăng nhập đăng ký để show popup lên
		driver.findElement(By.xpath("//span[text()= 'Đăng Nhập / Đăng Ký']")).click();
		sleepInSecond(3);
		//Display (single element)
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
		//Display multiple element
	
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 1);
		Assert.assertTrue(loginPopup.get(0).isDisplayed());
		//Đóng popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);
		
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 0);
	}
	
	public void TC_04_Random_In_Dom_KMP() {
		driver.get("http://www.kmplayer.com/");
		sleepInSecond(5);
		WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-layer"));
		//Phải luôn chạy được test case dù popup có hiển thị hay không
		// Đang trong đợt sale nó hiển thị script mình phải đóng nó đi để chạy tiếp
		// Hết đợt sale thì không heiern thị thì scrip chạy luôn
		if (popupLayer.isDisplayed()) {
			//close nó đi để chạy step tiếp theo
			System.out.println("Close để chạy step tiếp theo");
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(3);			
		}
		// Step tiếp theo
		System.out.println("Step tiếp theo");
		driver.findElement(By.cssSelector("p.donate-coffee")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
				
	}
	
	
	@Test
	public void TC_05_Random_Not_In_Dom_dehieu() {
		//Set độ phân giản màn hình thấp 1366x768
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		//Đang trong đợt sale thì nó hiển thị, thì srcipt mình phải đóng nó đi để chạy
		//2. Hết đợt sale k hiển thị thì script chạy luôn
		List<WebElement> contentPopup = driver.findElements(By.cssSelector("div.popup-content"));
		
		//Nếu size >0 thì là element nó có suất hiện
		if(contentPopup.size() >0) {
				
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("John Weakk");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("John@gmail.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0124567899");
			sleepInSecond(3);
			//Vào đóng popup
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
			sleepInSecond(3);
		}
		System.out.println("Step tiếp theo");
	}
	private By ByCssSelector(String string) {
		// TODO Auto-generated method stub
		return null;
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