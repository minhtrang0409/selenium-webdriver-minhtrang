package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_FindElement_FindElements2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
		
		//1- Ảnh hưởng trực tiếp tới 2 hàm FindElement/FindElements
		//2- Ngoại lệ
		// Implicit Wait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng cái giá trị mới/ k dùng giá trị cũ
		// Nếu không xét giá trị implicityWait thì mặc định nó bằng 0
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

	public void TC_01_Findelement() {
		//- Tìm thấy duy nhất 1 node
		//TÌm thấy và thao tác trực tiếp lên node dó
		//vì nó tìm thấy nên không cần phải chờ hết time out 15s
	
		
		// Tìm thấy nhiều hơn 1 element/node
		//Nó sẽ thao tác với node đầu tiên
		//Không quan tâm các node còn lại, k cần chờ hết time out
		//Trong TH bắt locator sai thì nó tìm sai, action sai 
		
		
		// Không tìm thấy element nào
		// Có cơ chế tìm lại = nửa giây (0.5s) sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thất element thì thỏa mãn đk rơi vào các case trên
		// Nếu hết thời gian (15s) mà k tìm thấy thì 
		//+ Đánh fail testcase này tại step này
		//+Throw ra 1 loại lệ: NoSuchElementExxception
		
	}

	public void TC_02_FindElements() {
		//- Tìm thấy duy nhất 1 node
		// Tìm thâý và lưu nó vào list = 1 element
		//vì nó tìm thấy nên không cần phải chờ hết time out 15s
	
		
		// Tìm thấy nhiều hơn 1 element/node
		// Tìm thấy và lưu vào list
		
		// Không tìm thấy element nào
		// K Tìm thâý và lưu nó vào list = 0 element
		
	}
	
	@Test
	public void TC_01_Not_Enough_Time_Implicit_Wait() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		//Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
	}
	@Test
	public void TC_02_Enough_Time_Implicit_Wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
	}
	@Test
	public void TC_03_More_Time_Implicit_Wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
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