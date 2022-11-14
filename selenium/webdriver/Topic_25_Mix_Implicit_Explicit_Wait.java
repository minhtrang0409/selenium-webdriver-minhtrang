package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_Implicit_Explicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//Wait rõ ràng: với các điền kiện/status cụ thể 
	WebDriverWait expliciteWait;

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
	
		public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.id("email");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start implicit = " + getTimenow());
		driver.findElement(emailID).isDisplayed();
		System.out.println("End implicit = " + getTimenow());
		expliciteWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit = " + getTimenow());
		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		System.out.println("End explicit = " + getTimenow());	
	}

		public void TC_02_Element_Not_Found_Only_Implicit() {
			driver.get("https://www.facebook.com/");
			
			By emailID = By.id("vietnam");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			System.out.println("Start implicit = " + getTimenow());
			try {
				driver.findElement(emailID).isDisplayed();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("End implicit = " + getTimenow());
		}

		public void TC_03_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.id("vietnam");
		expliciteWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit = " + getTimenow());
		try {
			expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit = " + getTimenow());	
	
	}
		@Test
		public void TC_04_Element_Found_Mix_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		By emailID = By.id("vietnam ");
		
		//1- Implicit < Explicit
		//1- Implicit = Explicit
		//1- Implicit > Explicit
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Start implicit = " + getTimenow());
		try {
			driver.findElement(emailID).isDisplayed();
		} catch (Exception e1) {
		}
		System.out.println("End implicit = " + getTimenow());
		expliciteWait = new WebDriverWait(driver, 5);
		System.out.println("Start explicit = " + getTimenow());
		try {
			expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
		} catch (Exception e) {
			}
		System.out.println("End explicit = " + getTimenow());	
	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getTimenow () {
		Date date = new Date();
		return date.toString();
	}

}