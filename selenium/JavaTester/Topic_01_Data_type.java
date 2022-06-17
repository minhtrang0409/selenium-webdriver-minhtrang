package JavaTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_01_Data_type {

		// Khai báo biến: Kiểu dữ liệu + Tên biến
		int studentNumber;
		
		studentNumber = 100;
		
		//Khao báo + khởi tạo
		int teacherNumber = 20;
		
		//Kiểu dữ liệu 
		
		//I Nguyên Thủy (Primitive)
		//Boolean: Luận lý/Logic - mang 2 giá trị(true/false) tốn rất ít bộ nhớ
		boolean studentSex = true; 
				
		//Số nguyên - k có phần thập phân 
		//byte
		byte bEmployee = 10;
		
		//short tổng nắm giữ 256 giá trị
		short sEmployee = 10;
		
		//int
		int iEmployee = 10;
		
		//long
		long lEmployee = 10000;
		
		// Số thực - có phần thập phân
		//float
		float fEmployee = 7.5f;
		
		//double
		double dEmployee = 8.4d;
		
		
		//ký tự
		//char
		char a = 'B';
		
		
		//II tham chiếu (Reference)
		//string
		String studenName = "B";
		//Array 
		String[] studentAddress = {"Ha noi", "Ho Chi Minh"};
		int[] studentName = {1, 67};
		
		//Interface/ Class
		WebDriver driver = new ChromeDriver();
				
		Actions action = new Actions(driver);
		
		//Collection: List (Arraylist/ Linked List) /Set/ Queue
		
		//Object 
		
		//Collection 
		Object aOject;
		
		// Class
		
		//Chuỗi ký tự: Số, chữ, ký tự đặc biệt\
		//List/Set/Queue/Map
		List<WebElement> homePageLinks = driver.findElements(By.tagName("a"));
		Set<String> allWindowns = driver.getWindowHandles();
		List<String> productName = new ArrayList<String>();	
		


		public void clickToElement() {
			studenName.trim();
			
			studentAddress.clone();
			aOject.toString();
			
		}
		By emailTextboxBy = By.cssSelector("");
		
		WebElement emailTextbox = driver.findElement(By.cssSelector(""));
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
	}
	

		
		
