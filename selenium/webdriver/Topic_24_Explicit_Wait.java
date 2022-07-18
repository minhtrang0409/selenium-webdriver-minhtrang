package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Explicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//Wait rõ ràng: với các điền kiện/status cụ thể 
	WebDriverWait expliciteWait;
	By Loadingicon = By.cssSelector("div#loading");
	By Helloworldtext = By.cssSelector("div#finish>h4");
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
		expliciteWait = new WebDriverWait(driver, 15);
	}
// Static wait, wait cứng=hard wait
	// Cứng: Nếu như thỏa mãi điều kiện rồi nhưng vẫn chờ hết timeout
	//Implicit/Explicit/ Fluent: NẾu như thỏa mãi đk thì k cần chờ hết timeout
	
	public void TC_01_Invisible() {
		expliciteWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Loading icon biến mất sau 5s
		expliciteWait.until(ExpectedConditions.invisibilityOfElementLocated(Loadingicon));
		//Get text và verify 
		Assert.assertEquals(driver.findElement(Helloworldtext).getText(), "Hello World!");
	}
	
	public void TC_02_visible() {
		expliciteWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Helloworld text hiển thị
		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(Helloworldtext));
		//Get text và verify 
		Assert.assertEquals(driver.findElement(Helloworldtext).getText(), "Hello World!");
	}
	
	public void TC_03_Number() {
		expliciteWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Helloworld text element = 1
		expliciteWait.until(ExpectedConditions.numberOfElementsToBe(Helloworldtext, 1));
		//Get text và verify 
		Assert.assertEquals(driver.findElement(Helloworldtext).getText(), "Hello World!");
	}
	@Test
	public void TC_04_Ajaxloading() {
		expliciteWait = new WebDriverWait(driver, 30);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// Wait cho Date picker xuất hiện
		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		WebElement selectedDateText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display.");
		//Wait cho ngày 17 có thể click và sau đó click lên nó
		expliciteWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='17']")));
		driver.findElement(By.xpath("//a[text()='17']")).click();
				
		//Wait loading icon biến mất
		expliciteWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*=RadCalendar1]>div.raDiv")));
		//Verify ngày được update
		WebElement selectedDateText2 = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText2.getText(), "Sunday, July 17, 2022");
		//Wait cho ngày được selected thành công (visible)
		WebElement todaySelected = expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.rcSelected")));
		//Verify ngày được chọn
		Assert.assertTrue(todaySelected.isDisplayed());

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