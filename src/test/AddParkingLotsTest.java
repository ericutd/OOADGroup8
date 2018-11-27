package test;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddParkingLotsTest 
{
	WebDriver driver;

	//Before you run the tests
	@Before
	public void openLoginPage() throws InterruptedException{
	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	driver = new ChromeDriver();
	//Put in starting address.
	/*NOTE TO GROUP: Make sure the same address
	 * when you run the program on the server.
	 */
	driver.get("http://localhost:8080/ParkingManagementService/login.jsp");
	Assert.assertEquals("Login Page", driver.getTitle());
	}

	//TEST 1: Register User
	@Test
	public void testParkingLots() throws InterruptedException{
	WebElement textBox = driver.findElement(By.name("email"));;
	textBox.sendKeys("admin@gmail.com");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("admin");
	Thread.sleep(1000);
	
	WebElement button = driver.findElement(By.name("submit"));
	button.click();
	
	Thread.sleep(5000);
	Assert.assertEquals("Welcome Page", driver.getTitle());	
	
	button = driver.findElement(By.xpath("//a[@href='admin.jsp']"));
	button.click();
	Thread.sleep(5000);
	Assert.assertEquals("Manage Parking", driver.getTitle());
	
	button = driver.findElement(By.name("addparkinglots"));
	button.click();
	
	//User information
	textBox = driver.findElement(By.name("lot"));;
	textBox.sendKeys("2");
	Thread.sleep(1000);
	
	//Click Register
	button = driver.findElement(By.name("submitlots"));
	button.click();
	
	Thread.sleep(5000);
	Assert.assertEquals("Manage Parking", driver.getTitle());
	}

	//After the test is over
	@After
	public void closePage(){
	driver.quit();
	}
}