package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ParkingTest {
	
	WebDriver driver;
	
	@Before
	public void openWikipediaEnglishPage() throws InterruptedException{
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/OOAD_CourseProject/login.jsp");
		Assert.assertEquals("Login Page", driver.getTitle());
	}

	@Test
	public void parkInSpot() {
		try {		
			// Login
			WebElement textBox = driver.findElement(By.name("email"));;
			textBox.sendKeys("t@gmail.com");
			Thread.sleep(1000);
			textBox = driver.findElement(By.name("password"));;
			textBox.sendKeys("test");
			Thread.sleep(1000);
			WebElement login = driver.findElement(By.name("submit"));
			login.click();
			Thread.sleep(5000);
			Assert.assertEquals("false", driver.findElement(By.id("occupied2")).getText());

			// Park
			WebElement button = driver.findElement(By.linkText("Select a Spot"));
			button.click();
			textBox = driver.findElement(By.name("parkingLotId"));
			textBox.sendKeys("7");
			Thread.sleep(2000);
			textBox = driver.findElement(By.name("parkingSpotId"));
			textBox.sendKeys("3");
			Thread.sleep(2000);
			button = driver.findElement(By.name("reserveSubmit"));
			button.click();
			Thread.sleep(5000);
			Assert.assertEquals("Success", driver.getTitle());
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void unparkFromSpot() {
		try {		
			// Login
			WebElement textBox = driver.findElement(By.name("email"));;
			textBox.sendKeys("t@gmail.com");
			Thread.sleep(1000);
			textBox = driver.findElement(By.name("password"));;
			textBox.sendKeys("test");
			Thread.sleep(1000);
			WebElement login = driver.findElement(By.name("submit"));
			login.click();
			Thread.sleep(5000);
			Assert.assertEquals("true", driver.findElement(By.id("occupied2")).getText());
			Assert.assertEquals("987987", driver.findElement(By.id("currentLicenseNum2")).getText());
			Assert.assertEquals("Odyssey", driver.findElement(By.id("currentModel2")).getText());

			// Park
			WebElement button = driver.findElement(By.linkText("Select a Spot"));
			button.click();
			textBox = driver.findElement(By.name("parkingLotId"));
			textBox.sendKeys("7");
			Thread.sleep(2000);
			textBox = driver.findElement(By.name("parkingSpotId"));
			textBox.sendKeys("3");
			Thread.sleep(2000);
			button = driver.findElement(By.name("reserveSubmit"));
			button.click();
			Thread.sleep(5000);
			Assert.assertEquals("Success", driver.getTitle());
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void close() {
		driver.quit();
	}

}
