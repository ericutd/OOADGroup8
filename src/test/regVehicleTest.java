package test;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

import org.junit.Test;

public class regVehicleTest {
	WebDriver driver;

	//Before you run the tests
	@Before
	public void openWikipediaEnglishPage() throws InterruptedException{
	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://localhost:8080/CourseProject/login.jsp");
	Assert.assertEquals("Login Page", driver.getTitle());
	}

	//TEST: Register Vehicle
	@Test
	public void testRegristration() throws InterruptedException{
		WebElement textBox = driver.findElement(By.name("email"));;
		textBox.sendKeys("yyq@gmail.com");
		Thread.sleep(1000);
		textBox = driver.findElement(By.name("password"));;
		textBox.sendKeys("yyq");
		Thread.sleep(1000);

		//Click Login
		WebElement button1 = driver.findElement(By.name("submit"));
		button1.click();
		Thread.sleep(5000);
		WebElement link = driver.findElement(By.linkText("Register Vehicle"));
		link.click();
		Thread.sleep(5000);
		//Assert.assertEquals("Register Vehicle Page", driver.getTitle());
		
		//Vehicle Information
		textBox = driver.findElement(By.name("licnum"));;
		textBox.sendKeys("123123");
		Thread.sleep(1000);
		textBox = driver.findElement(By.name("make"));;
		textBox.sendKeys("Toyota");
		Thread.sleep(1000);
		textBox = driver.findElement(By.name("model"));;
		textBox.sendKeys("Corolla");
		Thread.sleep(1000);
		textBox = driver.findElement(By.name("year"));;
		textBox.sendKeys("2018");
		Thread.sleep(1000);
		textBox = driver.findElement(By.name("color"));;
		textBox.sendKeys("Blue");
		Thread.sleep(1000);
		//Click Register
		WebElement button2 = driver.findElement(By.name("submit"));
		button2.click();	
		Thread.sleep(5000);
		Assert.assertEquals("ManageAccount Page", driver.getTitle());
	}

	//After the test is over
		@After
	public void closePage(){
		driver.quit();
	}
}
