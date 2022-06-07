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

import net.bytebuddy.dynamic.loading.ByteArrayClassLoader.ChildFirst;

public class Topic_09_Textbox_Textarea_Homework {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, employeeID, newfirstName, newlastName ;
	WebElement firstnameSave, lastnameSave, employeeIDSave ;

	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		firstName = "Hanaa";
		lastName = "Minh";
		newfirstName ="Duc";
		newlastName = "Anh";
		
	}

	@Test
	public void TC_01_Login() {
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		sleepInSecond(3);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		driver.findElement(By.xpath("//input[@id = 'firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id = 'lastName']")).sendKeys(lastName);

		//Lấy dữ liệu EmployeeID lưu vào biến để sd sau
		
		employeeID = driver.findElement(By.xpath("//input[@id = 'employeeId']")).getAttribute("value");
		
		driver.findElement(By.xpath("//input[@id = 'btnSave']")).click();
		
	}

	@Test
	public void TC_02_Verify_data() {
		
		//Verify dữ liệu
		firstnameSave = driver.findElement(By.xpath("//input[@id = 'personal_txtEmpFirstName']"));
		lastnameSave = driver.findElement(By.xpath("//input[@id = 'personal_txtEmpLastName']"));
		employeeIDSave = driver.findElement(By.xpath("//input[@id = 'personal_txtEmployeeId']"));
		
		
		Assert.assertEquals(firstnameSave.getAttribute("value"), firstName);
		Assert.assertEquals(lastnameSave.getAttribute("value"), lastName);
		Assert.assertEquals(employeeIDSave.getAttribute("value"), employeeID);
		
		//Verify disable
		if (firstnameSave.isEnabled()) { 
			System.out.println("FirstName textarea is enabled");
		}
		else System.out.println("FirstName textarea is disabled");
		
		if (lastnameSave.isEnabled()) { 
			System.out.println("lastname textarea is enabled");
		}
		else System.out.println("lastname textarea is disabled");
		
		if (employeeIDSave.isEnabled()) { 
			System.out.println("employeeID textarea is enabled");
		}
		else System.out.println("employeeID textarea is disabled");
		
		driver.findElement(By.xpath("//input[@id = 'btnSave']")).click();
		
		
	}
	
	@Test
	public void TC_03_Input_newData() {
		
		driver.findElement(By.xpath("//input[@id = 'personal_txtEmpFirstName']")).clear();
		driver.findElement(By.xpath("//input[@id = 'personal_txtEmpFirstName']")).sendKeys(newfirstName);
		driver.findElement(By.xpath("//input[@id = 'personal_txtEmpLastName']")).clear();
		driver.findElement(By.xpath("//input[@id = 'personal_txtEmpLastName']")).sendKeys(newlastName);
		
		//Verify enable
		if (firstnameSave.isEnabled()) { 
			System.out.println("FirstName textarea is enabled");
		}
		else System.out.println("FirstName textarea is disabled");
		
		if (lastnameSave.isEnabled()) { 
			System.out.println("lastname textarea is enabled");
		}
		else System.out.println("lastname textarea is disabled");
		
		driver.findElement(By.xpath("//input[@value = 'Save']")).click();
	}
	
	@Test
	public void TC_04_Verify_dataUpdate() {
		
		//Verify dữ liệu
		firstnameSave = driver.findElement(By.xpath("//input[@id = 'personal_txtEmpFirstName']"));
		lastnameSave = driver.findElement(By.xpath("//input[@id = 'personal_txtEmpLastName']"));
		employeeIDSave = driver.findElement(By.xpath("//input[@id = 'personal_txtEmployeeId']"));
		
		
		Assert.assertEquals(firstnameSave.getAttribute("value"), newfirstName);
		Assert.assertEquals(lastnameSave.getAttribute("value"), newlastName);
		Assert.assertEquals(employeeIDSave.getAttribute("value"), employeeID);
		
		//Verify disable
		if (firstnameSave.isEnabled()) { 
			System.out.println("FirstName textarea is enabled");
		}
		else System.out.println("FirstName textarea is disabled");
		
		if (lastnameSave.isEnabled()) { 
			System.out.println("lastname textarea is enabled");
		}
		else System.out.println("lastname textarea is disabled");
		
		if (employeeIDSave.isEnabled()) { 
			System.out.println("employeeID textarea is enabled");
		}
		else System.out.println("employeeID textarea is disabled");
		
		driver.findElement(By.xpath("//input[@id = 'btnSave']")).click();
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