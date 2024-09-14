package kowsalyaLearning.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kowsalyaLearning.PageObjects.CartPage;
import kowsalyaLearning.PageObjects.ConfirmationPage;

import kowsalyaLearning.PageObjects.OrdersPage;

public class AbstractComponent { //this class contains all reusable code and a parent of all page objects.
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*=cart]")
	WebElement cartButton;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersButton;
	
	@FindBy(css = "nav > ul > li:nth-child(5) > button > i")
	WebElement signOut;
	
	public void waitForElementToVisible(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToVisible(WebElement by)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(by));
	}

	public void waitForElementToInvisible(WebElement web) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(web));
	}
	public CartPage goToCartPage()
	{
	  cartButton.click();
	  return new CartPage(driver);
	}
	public OrdersPage goToOrdersPage()
	{
	  ordersButton.click();
	  return new OrdersPage(driver);
	}
	public ConfirmationPage signOutFromApp()
	{
		signOut.click();
		return new ConfirmationPage(driver);
		}
}

