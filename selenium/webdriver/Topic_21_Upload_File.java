package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//Image name
	String DreamFilename = "Dream.png";
	String ImageFileName = "Image.png";
	String LifeName = "Life.png";
	
	//Image path
	//String uploadFileFolderPath = "projectPath"+ "\\Upload File\\";
	String uploadFileFolderPath = projectPath + File.separator + "Upload File" + File.separator;
	
	
	String Dreampath = uploadFileFolderPath + DreamFilename;
	String Imagepath = uploadFileFolderPath + ImageFileName;
	String Lifepath = uploadFileFolderPath + LifeName;

	
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println(Dreampath);
		System.out.println(Imagepath);
		System.out.println(Lifepath);
	}

	@Test
	public void TC_01_One_File_One_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//Selenium Sendkey menthod
	// Không click vào button Add files để bật Open File Dialog lên
	//chỉ được sendkey vào thẻ input của loại này
	
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\babo-automation\\1. Automation testing\\02 Selenium Web driver\\Upload File\\Dream.png");
	sleepInSecond(3);
	}

	@Test
	public void TC_02_Multiple_File_One_Time() {
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