package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	
	}

	@Test
	public void TC_01_Define_Element() {
		//Muốn thao tác với Element thì mình phải tìm element trước, Sau đó mới áp dụng hành vi vào cho Element đó
		
		//1. Tìm element
		//2. Với loại locator gì
		//3. Tương tác lên/ Kiểm tra nó
		driver.findElement(By.id("send2")).click();
		//Thao tác từ 2 lần trở leent hì cần khai báo biến tránh việc lặp lại
		
		driver.findElement(By.id("email")).clear();
		
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys(" ");
		emailTextbox.isDisplayed();
		
		
	}

	@Test
	public void TC_02_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		
		//Xóa dữ liệu trong filed cho phép nhập (Textbox/TextArea/ Editable Dropdown
		element.clear();
		//Nhập dữ liệu trong filed cho phép nhập (Textbox/TextArea/ Editable Dropdown
		element.sendKeys("");
		element.sendKeys(Keys.BACK_SPACE);
		
		driver.findElement(By.id("search")).getAttribute("placeholder");
		//Search entire store here...
		
		//Các hàm để thao tác, action lên Browser/ Element thì nó không có kiểu trả về (void)
		// Các hàm để verify/ lấy dữ liệu ra thì luôn có kiểu trả về
		// Verify chỉ có 1 step thì không cần khai báo biến
		
		//Không khai báo biến
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		
		//Có khai báo biến
		
		String searchTextbox = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextbox, "Search entire store here...");
		
		//GUI: Font type/Font Size/ Color/ Pixel/...
		element.getCssValue("background-color");
		// rgb(204, 180, 51);
		element.getCssValue("font-size");
		// 13px
			
		//GUI: Position/Location/Size of element
	
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Framework: Attach image to report HTML
		element.getScreenshotAs(OutputType.FILE);
		
		element = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		element = driver.findElement(By.cssSelector("#advice-validate-email-email"));
		element.getTagName(); 
		//=> div
		
		//Lấy ra text của element hiện tại hoặc text của những element con
		element.getText();
		
		//Mong muốn 1 element hiển thị / không hiển thị, Hiển thị là người dùng nhìn thấy được và có kích thước cụ thể. 
		// Áp dụng cho tất cả các loại element: Textbox, textarea/ dropdown/ radio/ button/....
		element.isDisplayed();
		
		//Mong muốn 1 element thao tác được lên hoặc không
		//Thao tác được enable => Trả về True, không thao tác được disable => trả về False
		// Áp dụng cho tất cả các loại element: Textbox, textarea/ dropdown/ radio/ button/....		
		element.isEnabled();
		
		//Mong muốn 1 element đã được chọn hay chưa
		// Áp dụng cho 1 vài loại element: Checkbox, Radio button/ Dropdown (Default)
		element.isSelected();
		
		
		//Click vào 1 element: Button/  Link/ Checkbox/ Radio/ Image, Icon....
		element.click();
		
		//Giống hành vi Enter ở các form
		// Dùng cho tagname: form (element bên trong)
		element.submit();
		
		
	
	}
	
	@Test
	public void TC_03_() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}