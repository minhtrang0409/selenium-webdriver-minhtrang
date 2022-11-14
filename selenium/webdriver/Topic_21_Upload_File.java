package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//Image name
	String DreamFilename = "Dream.png";
//	String ImageFileName = "Image.png";
	String LifeName = "Life.png";
	
	//Image path
	//String uploadFileFolderPath = "projectPath"+ "\\Upload File\\";
	String uploadFileFolderPath = projectPath + File.separator + "Upload File" + File.separator;
	
	
	String Dreampath = uploadFileFolderPath + DreamFilename;
//	String Imagepath = uploadFileFolderPath + ImageFileName;
	String Lifepath = uploadFileFolderPath + LifeName;

	
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	
//		FirefoxOptions options = new FirefoxOptions();
//		options.addArguments("-headless"); 
//		options.addArguments("window-size=1920x1080");
//		driver = new FirefoxDriver(options);
		
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
//		options.addArguments("window-size=1920x1080");
//		driver = new ChromeDriver(options);
		
//		driver = new ChromeDriver();
		
//		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//		driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println(Dreampath);
	//	System.out.println(Imagepath);
		System.out.println(Lifepath);
	}

	public void TC_01_One_File_One_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//Selenium Sendkey menthod
	// Không click vào button Add files để bật Open File Dialog lên
	//chỉ được sendkey vào thẻ input của loại này
		
		//Load file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Dreampath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Lifepath);
		sleepInSecond(3);
		//Verify image
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ DreamFilename +"']")).isDisplayed());
	//	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ ImageFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ LifeName +"']")).isDisplayed());
		
		//Click to Upload button at each file
		List<WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement Button : uploadButton) {
			Button.click();
			sleepInSecond(5);
		}
			//Verify các file được upload lên
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ DreamFilename +"']")).isDisplayed());
	//	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ ImageFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ LifeName +"']")).isDisplayed());
		
	}

	public void TC_02_Multiple_File_One_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Selenium Sendkey menthod
		// Không click vào button Add files để bật Open File Dialog lên
		//chỉ được sendkey vào thẻ input của loại này
			
			//Load 2 file
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Dreampath +"\n"+ Lifepath);
			sleepInSecond(3);
			//Verify image
			
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ DreamFilename +"']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ LifeName +"']")).isDisplayed());
			
			//Click to Upload button at each file
			List<WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
			
			for (WebElement Button : uploadButton) {
				Button.click();
				sleepInSecond(5);
			}
				//Verify các file được upload lên
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ DreamFilename +"']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ LifeName +"']")).isDisplayed());
			
		}
	
	
	
	@Test
	public void TC_03_Go_File() {
		driver.get("https://gofile.io/uploadFiles");
		By uploadfile = By.xpath("//input[@type='file']");
		driver.findElement(uploadfile).sendKeys(Dreampath +"\n"+ Lifepath);
		sleepInSecond(10);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		String Linkupload = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href");
		driver.get(Linkupload);
		
		//Download button
		//span[@class='contentName'and text()='Dream.png']/parent::a/parent::div/following-sibling::div
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName'and text()='"+ DreamFilename +"']/parent::a/parent::div/following-sibling::div/a/button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName'and text()='"+ LifeName +"']/parent::a/parent::div/following-sibling::div/a/button[@id='contentId-download']")).isDisplayed());
		
		//Play button
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName'and text()='"+ DreamFilename +"']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName'and text()='"+ LifeName +"']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		
		
		
		
		
	}

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