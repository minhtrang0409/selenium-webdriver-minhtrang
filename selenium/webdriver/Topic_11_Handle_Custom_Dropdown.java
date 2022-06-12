package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait; 
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Luôn khởi tạo wait sau driver -> vì nó cần giá trị driver để khởi tạo
		//Wait cho các element theo điều kiện có sẵn: visible / invisible/ presence/ clickable...
		expliciWait = new WebDriverWait(driver, 15);
		
		//Ép kiểu tường minh trong Java
		jsExecutor =  (JavascriptExecutor) driver;
		
		//Wait cho việc tìm element: findElement/ findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}

	@Test
	public void TC_01_JQuery() {
		
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectitemInCustomDropdown("span#number-button", "ul#number-menu > li > div", "4");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "4");
			
		selectitemInCustomDropdown("span#number-button", "ul#number-menu > li > div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
				
		selectitemInCustomDropdown("span#number-button", "ul#number-menu > li > div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
		
	}
	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectitemInCustomDropdown("i.dropdown", "div.item>span", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
		
		
		selectitemInCustomDropdown("i.dropdown", "div.item>span", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		
		
		selectitemInCustomDropdown("i.dropdown", "div.item>span", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
		
	}
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectitemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu >li>a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectitemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu >li>a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectitemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu >li>a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
		
	}
	@Test
	public void TC_04_Default_NopCommerce() {
		
		driver.get("https://demo.nopcommerce.com/register");
		
		selectitemInCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "6");
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='6']")).isSelected());
		
		selectitemInCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='10']")).isSelected());
		
		selectitemInCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "Day");
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='Day']")).isSelected());
		
	}
	@Test
	public void TC_05_Editable() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enteritemInCustomDropdown("input.search", "div.item>span", "Albania");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Albania");
		
		enteritemInCustomDropdown("input.search", "div.item>span", "Argentina");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Argentina");
		
		enteritemInCustomDropdown("input.search", "div.item>span", "Andorra");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Andorra");
		
	}
	

	//Truyền qua tham số
	public void selectitemInCustomDropdown(String parentLocator, String childLocator, String expectedTextitem) {
		//Click vào dropdown cho xở hết tất cả item con bên trong ra => Click
		driver.findElement(By.cssSelector(parentLocator)).click(); 
		sleepInSecond(1);
		//Chờ cho tất cả các item con bên trong được load ra => WebDriverWait
		//Lấy 1 By locator đại diện cho tất cả các item con bên trong
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		//Tìm item mong muốn nếu như không hiển thị thì cần cuộn chuột xuống để tìm => Dùng vòng lặp để duyệt qua -getText từng cái
		//thấy item cần chọn thì click vào => So sánh với item mong muốn sau đó click
		//Lấy hết tất cả item lưu vào 1 list WebElement, //19 item được lưu trong list
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		
		//Duyệt qua từng item
//		allDropdownItems.get(0).getText();
//		allDropdownItems.get(1).getText();
//		allDropdownItems.get(2).getText();
		//Duyệt qua vòng lặp
		for (WebElement item : allDropdownItems) {
			String actualTextitem = item.getText();
			System.out.println("current: " + actualTextitem);
			//thấy item cần chọn thì click vào => So sánh với item mong muốn sau đó click
			if(actualTextitem.equals(expectedTextitem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
//				jsExecutor.executeScript("arguments[0].click()", item);
				sleepInSecond(2);
				item.click();
				System.out.println("item: " + item);
				System.out.println("Item text = "+ actualTextitem);
				sleepInSecond(1);
				break;
			}
			}
	}
	public void enteritemInCustomDropdown(String editableLocator, String childLocator, String expectedTextitem) {

		driver.findElement(By.cssSelector(editableLocator)).sendKeys(expectedTextitem);; 
		sleepInSecond(1);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

		for (WebElement item : allDropdownItems) {
			String actualTextitem = item.getText();
			System.out.println("current: " + actualTextitem);
			//thấy item cần chọn thì click vào => So sánh với item mong muốn sau đó click
			if(actualTextitem.equals(expectedTextitem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(2);
				item.click();
				System.out.println("item: " + item);
				System.out.println("Item text = "+ actualTextitem);
				sleepInSecond(1);
				break;
			}
		}
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