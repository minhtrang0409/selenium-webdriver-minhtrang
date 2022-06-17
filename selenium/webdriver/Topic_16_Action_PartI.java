package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Action_PartI {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;

	String projectPath = System.getProperty("user.dir");
	String jsFileContent;
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(ageTextbox).perform();	
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}


	public void TC_02_Hover() {
		driver.get("http://www.myntra.com/");
		
		WebElement kidlink = driver.findElement(By.xpath("//a[@data-group = 'kids']"));
		action.moveToElement(kidlink).perform();	
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text() = 'Home & Bath']")).click();
		//action.click(driver.findElement(By.xpath("//a[text() = 'Home & Bath']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());	
	}
	
	
	public void TC_03_Click_And_Hold_Clock() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
			
		//Store all 12 elements
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumber.size(), 12);
		
		allNumber.get(0).click();
		//chọn từ 1 tới 11
		//click và giữ chuột tại số 1
		action.clickAndHold(allNumber.get(0))
		//move tới số 11
		.moveToElement(allNumber.get(10))

		//Nhả chuột trái ra
		.release()
		// Thực thi các action trên
		.perform();
		sleepInSecond(3);
		
		allNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(allNumber.size(), 9);
	}

	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
			
		//Store all 12 elements
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumber.size(), 12);
		//Nhấn phím control
		action.keyDown(Keys.CONTROL).perform();
		action.click(allNumber.get(0))
		.click(allNumber.get(5))
		.click(allNumber.get(7))
		.click(allNumber.get(10))
		.perform();
		

		//Nhả phím control
		action.keyUp(Keys.CONTROL).perform();

		// Thực thi các action trên
		sleepInSecond(3);
		
		allNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(allNumber.size(), 4);
	}
	
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickme = driver.findElement(By.xpath("//button[text()='Double click me']"));
		//Scroll to element
		//true: mép trên của element và kéo element lên trên cùng
		//false: mép dưới của element và kéo element xuống dưới cùng
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickme);
		action.doubleClick(doubleClickme).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	
	}
	
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		WebElement deleteBefore = driver.findElement(By.cssSelector("li.context-menu-icon-delete"));
		action.moveToElement(deleteBefore).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible")).isDisplayed());
		action.click(deleteBefore).perform();
		sleepInSecond(2);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();
		sleepInSecond(3);
		Assert.assertFalse(deleteBefore.isDisplayed());
	}
	//@Test
	public void TC_07_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement courceCirle =driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCirle =driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(courceCirle, targetCirle).perform();
		sleepInSecond(3);
		
		//Text
		Assert.assertEquals(targetCirle.getText(), "You did great!");
		
		//BAckgroup color
		String loginButtonBackgroundColor = Color.fromString(targetCirle .getCssValue("background-color")).asHex();
		System.out.println(loginButtonBackgroundColor);
		Assert.assertEquals(loginButtonBackgroundColor, "#03a9f4");
	}
	@Test
	public void TC_07_Drag_Drop_HTML5() throws IOException {
		//Thư viện action của selenium k support kéo thả cho HTML5  
		//Drag & drop là 1 case được khuyến khích không nên auto vì chạy không ổn định. 
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceRectangle = driver.findElement(By.cssSelector(projectPath));
		//Các case không nên làm auto
		// Chart, Captcha, SMS, OTP, QR code, Bar code, google/facebook/Twitter
		//Nếu cung cấp API thì được
		//MAP/Flash/Flex...
		jsFileContent = getContentFile(projectPath + "\\DragAndDrop\\drag_and_drop_helper.js"); 
		
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
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}