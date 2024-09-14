package kowsalyaLearning.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		//System.getProperty("webdriver.chrome.driver","C:/Users/Asus/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String email = "arkatkowsalya@gmail.com";
		String password = "Hello@123";
		String productName = "ZARA COAT 3";
		
		//LANDING PAGE
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait for all products to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// stores all products
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//stores and filter the first product which matches with product name
		WebElement product = products.stream().filter(s->
		s.findElement(By.tagName("b")).getText().contains(productName)).findFirst().orElse(null);
		//click on add to cart by limiting webdriver scope within the product.
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//wait for message to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//wait for animation to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//Thread.sleep(5000);
		//click on cart button
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		//CART PAGE
		//storing all the added products in cart section
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		//stores the boolean value and checks whether the product added matches the cart section
		Boolean match  = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		//System.out.println(match);//prints the true value
		Assert.assertTrue(match); //continues the execution if it returns true or else stops.
		//clicks on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//CHECKOUT PAGE
		//Send text "India" in select country text box
		//driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("India");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country']")),"India").build().perform();
		//Wait for results to be visible which matches India.
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
        //select the second element which is in the list
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		//click on place order
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();
		
		//LAST PAGE
        //Storing the success message text and asserts it
		String successMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(successMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
