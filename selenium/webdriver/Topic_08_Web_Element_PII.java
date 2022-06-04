package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Display() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
		
		if (emailTextbox.isDisplayed()) { 
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email texbox is displayed");
		}
		else System.out.println("Email texbox is not displayed");
		

		WebElement ageUnder18 = driver.findElement(By.cssSelector("#under_18"));
		
		if (ageUnder18.isDisplayed()) { 
			ageUnder18.click();
			System.out.println("Under 18 radio button is displayed");
		}
		else System.out.println("Under 18 radio button is not displayed");
		

		WebElement education = driver.findElement(By.cssSelector("#edu"));
		
		if (education.isDisplayed()) { 
			education.sendKeys("Automation Testing");
			System.out.println("Education textare is displayed");
		}
		else System.out.println("Education textare is not displayed");
		//
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		
		if (name5Text.isDisplayed()) { 
			System.out.println("Name user 5 text is displayed");
		}
		else System.out.println("Name user 5 text is not displayed");
		
	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Enabled
		WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
		
		if (emailTextbox.isEnabled()) { 
			System.out.println("Email texbox is enabled");
		}
		else System.out.println("Email texbox is disabled");
		
				
		WebElement ageUnder18 = driver.findElement(By.cssSelector("#under_18"));
		
		if (ageUnder18.isEnabled()) { 
			System.out.println("Under 18 radio button is enabled");
		}
		else System.out.println("Under 18 radio button is disabled");
		
		WebElement education = driver.findElement(By.cssSelector("#edu"));
		
		if (education.isEnabled()) { 
			System.out.println("Education textarea is enabled");
		}
		else System.out.println("Education textarea is disabled");
	//Disabled
		WebElement dropDownJob3 = driver.findElement(By.cssSelector("#job3"));
		
		if (dropDownJob3.isEnabled()) { 
			System.out.println("Job3 is enabled");
		}
		else System.out.println("Job3 is disabled");
		
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18 = driver.findElement(By.cssSelector("#under_18"));
		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
		
		ageUnder18.click();
		javaCheckbox.click();
		//Verify 2 field là selected
		
		Assert.assertTrue(ageUnder18.isSelected());
		Assert.assertTrue(javaCheckbox.isSelected());
		if(ageUnder18.isSelected()) {
			System.out.println("Age under 18 is selected");
		} else
			System.out.println("Age under 18 is not selected");
		if(javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");
		} else
			System.out.println("Java Checkbox is not selected");
		
		ageUnder18.click();
		javaCheckbox.click();
		
	
		if(ageUnder18.isSelected()) {
			System.out.println("Age under 18 is selected");
		} else
			System.out.println("Age under 18 is not selected");
		if(javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");
		} else
			System.out.println("Java Checkbox is not selected");
		
	}
	
	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input#new_username")).sendKeys("automation");
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
		WebElement signupButton = driver.findElement(By.cssSelector("button#create-account"));
		sleepInSecond(1);
		//lowercase
		passwordTextbox.sendKeys("auto");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char completed' and text() = 'One lowercase character']")).isDisplayed());	
		Assert.assertFalse(signupButton.isEnabled());
		
		//Upper case
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		
		passwordTextbox.clear();
		passwordTextbox.sendKeys("1243");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		passwordTextbox.clear();
		passwordTextbox.sendKeys("123456789");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char completed' and text()='8 characters minimum'] ")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		passwordTextbox.clear();
		passwordTextbox.sendKeys("ABCacb123!@#");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		Assert.assertTrue(signupButton.isEnabled());
		
		
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