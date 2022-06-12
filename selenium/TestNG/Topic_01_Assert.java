package TestNG;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;
	
	@Test
	public void TC_01() {
		//3 hàm Assert hay dùng
		//Kiểm tra tính đúng đắn của dữ liệu
		
		
		//1- Kiểm tra dữ liệu mình mong muốn là đúng
		//Email textbox hiển thị 
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		
		//1- Kiểm tra dữ liệu mình mong muốn là sai
		//Email textbox hiển thị 
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());

		
		//1- Kiểm tra dữ liệu mình mong muốn là với dữ liệu thực tế là bằng nhau
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		
		//GUI: Font/ Size/ Color/ Pixel/...
	


	}

}
