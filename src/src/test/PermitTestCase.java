/*package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PermitTestCase {
	
	WebDriver driver;
	
	@Before
	public void openWikipediaEnglishPage() throws InterruptedException{
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/CourseProject/permit.jsp");
		Assert.assertEquals("Add Permit", driver.getTitle());
	}

	@Test
	public void addPermit() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/