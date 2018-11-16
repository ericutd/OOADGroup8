package test;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class editAccountTest {
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
	driver.get("http://localhost:8080/OOADGroup8/login.jsp");
	Assert.assertEquals("Login Page", driver.getTitle());
	
	
	}

	//TEST 1: Edit user account
	@Test
	public void testRegristration() throws InterruptedException{
	//Login in user
	//User information
	WebElement textBox = driver.findElement(By.name("email"));;
	textBox.sendKeys("mt@yahoo.net");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("test");
	Thread.sleep(1000);
	
	
	//Click Register
	WebElement button = driver.findElement(By.name("submit"));
	button.click();
	
	Thread.sleep(5000);
	Assert.assertEquals("Welcome Page", driver.getTitle());	
	
	//Go to manage account page
	button = driver.findElement(By.xpath("//a[@href='manageaccount.jsp']"));
	button.click();
	Thread.sleep(5000);
	Assert.assertEquals("ManageAccount", driver.getTitle());	
	
	//User information
	textBox = driver.findElement(By.name("name"));;
	textBox.sendKeys("Mike D Test");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("tester");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("email"));;
	textBox.sendKeys("mdt@yahoo.net");
	Thread.sleep(1000);
	
	button = driver.findElement(By.name("submit"));
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
	textBox.sendKeys("mdt@yahoo.net");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("tester");
	Thread.sleep(1000);
	
	
	//Click Register
	WebElement button = driver.findElement(By.name("submit"));
	button.click();
	
	Thread.sleep(5000);
	Assert.assertEquals("Welcome Page", driver.getTitle());
	}

	//After the test is over
	@After
	public void closePage(){
	driver.quit();
	}

}
