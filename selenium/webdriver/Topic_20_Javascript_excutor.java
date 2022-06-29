package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Javascript_excutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	//FirefoxDriver fDriver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
//
	@BeforeClass
	public void beforeClass() {
		//SET tương đối: Máy Windows nào cũng chạy được
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//fDriver = new FirefoxDriver();
		
		//Ép kiểu 
		jsExecutor = (JavascriptExecutor) driver;
		//jsExecutor = fDriver;
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailAddress = "afd"+ generateRandomNumber() + "@hotmail.com";

	}


	public void TC_01_() {
		//driver.get("http://live.techpanda.org/");
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		//executeForBrowser("window.location = 'http://live.techpanda.org/'");
		//getdomain
		String homePagedomain = (String) executeForBrowser("return document.domain;");
		sleepInSecond(3);
		Assert.assertEquals(homePagedomain, "live.techpanda.org");
		
		String homePageURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageURL, "http://live.techpanda.org/");
		
		//Click bằng JS
		//jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button");
		sleepInSecond(3);
		String ShoppingcartText = getInnerText();
		Assert.assertTrue(ShoppingcartText.contains("The product Samsung Galaxy has been added to comparison list."));
		clickToElementByJS("//a[@title='IPhone']/parent::h2/following-sibling::div/button");
		sleepInSecond(1);
		Assert.assertTrue(ShoppingcartText.contains("The product IPhone has been added to comparison list."));
		//open Customer Service
		clickToElementByJS("//a[text()='Customer Service']");
		String customerPageTitle = (String) executeForBrowser("return document.title;");
		System.out.println(customerPageTitle);
		Assert.assertEquals(customerPageTitle, "Customer Service");
		
		scrollToElementOnDown("//span[text()='Newsletter']");
		sleepInSecond(4);
		
		sendkeyToElementByJS("//input[@type='email']", emailAddress);
	}

	@Test
	public void TC_02_Get_HTML5_validation_messasge() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		
		By lastName = By.id("lastName");
		By firstName = By.id("firstName");
		By email = By.id("email");
		By password = By.id("password");
		
		By button = By.xpath("//button[text()='Đăng ký']");
		
		driver.findElement(button).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(lastName), "Please fill out this field.");
		
		driver.findElement(lastName).sendKeys("Automation");
		driver.findElement(button).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(firstName), "Please fill out this field.");
		
		driver.findElement(lastName).sendKeys("Automation");
		driver.findElement(firstName).sendKeys("FC");
		driver.findElement(button).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(email), "Please fill out this field.");
		
		driver.findElement(lastName).sendKeys("Automation");
		driver.findElement(firstName).sendKeys("FC");
		driver.findElement(email).sendKeys("123@123@13");
		driver.findElement(button).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(email), "Please enter an email address.");
		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys("automation@gmail.com");
		driver.findElement(button).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");

		
		
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
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(By bylocator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(bylocator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}