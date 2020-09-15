package xpath;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class XpathAxis {

	WebDriver driver;

	@Before
	public void launchBrowser() {
		// setting property
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		// navigating to the website
		driver.get("http://www.techfios.com/ibilling/?ng=dashboard/");

		// maximizing the widow
		driver.manage().window().maximize();
		// use of implicit wait
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@Test
	public void loginTest() throws InterruptedException {
		
		//validate login page
		String loginTittle=driver.getTitle();
		Assert.assertEquals("login_iBilling",loginTittle);
		System.out.println("pass");

		WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.id("username"));
		USERNAME_FIELD_ELEMENT.clear();
		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");

		// driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
		// identifying the web element using the password
		By PASSWORD_FIELD_LOCATOR = By.id("password");
		driver.findElement(PASSWORD_FIELD_LOCATOR).clear();
		driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys("abc123");
		// identifying the web element and click on sign in button
		driver.findElement(By.name("login")).click();

		// variable type variable name=variable value-syntax to declare any variable

		// store WebElement and By locator

		By DASHBOARD_PAGE_TITTLE_LOCATOR = By.xpath("//h2[contains(text (),'Dashboard')]");
		
		boolean pageTitleDisplayStatus = false;
		try {
			WebElement DASHBOARD_PAGE_TITLE = driver.findElement(By.xpath("//h2[contains(text (),'Dashboard')]"));
			 pageTitleDisplayStatus=true;
		}catch(NoSuchElementException e) {
			boolean pageTittleDisplayStatus=false;
			e.printStackTrace();
		}
		
		//By ELEMENT_BY_LOCATOR = By.xpath("//h2[contains(text (),'Dashboard')]");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOfElementLocated( DASHBOARD_PAGE_TITTLE_LOCATOR));

		Assert.assertTrue("Dashboard page not found,!!", pageTitleDisplayStatus);
		driver.close();
		driver.quit();
	}
}