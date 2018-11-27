package test;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class manageVehiclesTest {
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
	driver.get("http://localhost:8080/OOAD_CourseProject/login.jsp");
	Assert.assertEquals("Login Page", driver.getTitle());
	
	
	}

	//TEST 1: Edit user account
	@Test
	public void testRegristration() throws InterruptedException{
	//Login in user
	//User information
	WebElement textBox = driver.findElement(By.name("email"));;
	textBox.sendKeys("t@gmail.com");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("test");
	Thread.sleep(1000);
	
	
	//Click Register
	WebElement button = driver.findElement(By.name("submit"));
	button.click();
	
	Thread.sleep(5000);
	Assert.assertEquals("Welcome Page", driver.getTitle());
	Assert.assertEquals("Toyota Corolla\nLicense Number: 123456", driver.findElement(By.className("list-unstyled")).getText());
	
	//Go to manage account page
	button = driver.findElement(By.xpath("//a[@href='manageaccount.jsp']"));
	button.click();
	Thread.sleep(5000);
	Assert.assertEquals("ManageAccount", driver.getTitle());

	//Vehicle information
	textBox = driver.findElement(By.name("licnum"));;
	textBox.sendKeys("987987");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("make"));;
	textBox.sendKeys("Honda");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("model"));;
	textBox.sendKeys("Odyssey");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("year"));;
	textBox.sendKeys("1987");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("color"));;
	textBox.sendKeys("tan");
	Thread.sleep(1000);

	// Make sure the manageaccount.jsp page has different names for the different submit buttons
	button = driver.findElement(By.name("submitVehicleEdit"));
	button.click();
	Thread.sleep(5000);
	
	button = driver.findElement(By.xpath("//a[@href='logout.jsp']"));
	button.click();
	Thread.sleep(5000);
	Assert.assertEquals("Login Page", driver.getTitle());
	
	}
	
	//Test 2: Login user
	@Test
	public void testLogin() throws InterruptedException{
	//User information
	WebElement textBox = driver.findElement(By.name("email"));;
	textBox.sendKeys("t@gmail.com");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("test");
	Thread.sleep(1000);
	
	
	//Click Register
	WebElement button = driver.findElement(By.name("submit"));
	button.click();
	
	Thread.sleep(5000);
	Assert.assertEquals("Welcome Page", driver.getTitle());
	Assert.assertEquals("Honda Odyssey\nLicense Number: 987987", driver.findElement(By.className("list-unstyled")).getText());
	}

	//After the test is over
	@After
	public void closePage(){
	driver.quit();
	}

}
