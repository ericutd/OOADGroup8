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

public class PermitTestCase {
	
	WebDriver driver;
	
	@Before
	public void openWikipediaEnglishPage() throws InterruptedException{
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/CourseProject/login.jsp");
		Assert.assertEquals("Login Page", driver.getTitle());
	}

	@Test
	public void addPermit() {
		try {		
			// Login
			WebElement textBox = driver.findElement(By.name("email"));;
			textBox.sendKeys("siva");
			Thread.sleep(1000);
			textBox = driver.findElement(By.name("password"));;
			textBox.sendKeys("siva");
			Thread.sleep(1000);
			WebElement login = driver.findElement(By.name("submit"));
			login.click();
			Thread.sleep(5000);
			
			// Add Permit
			WebElement permit = driver.findElement(By.linkText("Add Permit"));
			permit.click();
			textBox = driver.findElement(By.name("price"));
			textBox.sendKeys("10.0");
			Thread.sleep(2000);
			Select dropdown = new Select(driver.findElement(By.name("color")));
			dropdown.selectByIndex(2);
			Thread.sleep(2000);
			WebElement date = driver.findElement(By.name("exp_date"));
			date.sendKeys("05082021");
			Thread.sleep(2000);
			WebElement addPermit = driver.findElement(By.name("Add Permit"));
			addPermit.click();
			Thread.sleep(5000);
			Assert.assertEquals("Welcome Page", driver.getTitle());
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
