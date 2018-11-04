package domain.login;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class createAccountTest 
{
	WebDriver driver;

	//Before you run the tests
	@Before
	public void openWikipediaEnglishPage() throws InterruptedException{
	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://localhost:8080/OOAD_CourseProject/login.jsp");
	Assert.assertEquals("Login Page", driver.getTitle());
	}

	//TEST 1: Register User
	@Test
	public void testRegristration() throws InterruptedException{
	WebElement link = driver.findElement(By.linkText("registration"));
	link.click();
	Thread.sleep(5000);
	Assert.assertEquals("Register", driver.getTitle());
	
	//User information
	WebElement textBox = driver.findElement(By.name("name"));;
	textBox.sendKeys("tester");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("password"));;
	textBox.sendKeys("test");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("retry-password"));;
	textBox.sendKeys("test");
	Thread.sleep(1000);
	textBox = driver.findElement(By.name("email"));;
	textBox.sendKeys("t@gmail.com");
	Thread.sleep(1000);
	
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
	WebElement button = driver.findElement(By.name("submit"));
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
	}

	//After the test is over
	@After
	public void closePage(){
	driver.quit();
	}
}
