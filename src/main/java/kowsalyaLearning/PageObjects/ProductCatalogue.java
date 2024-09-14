package kowsalyaLearning.PageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import kowsalyaLearning.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
	By products  = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");

	@FindBy(css =".mb-3")
	List<WebElement> productList;
	@FindBy(css =".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList() {
		waitForElementToVisible(products);
		return productList;
	}
	
	public WebElement getProductByname(String productName)
	 {

		WebElement product =getProductList().stream().filter(s->
		s.findElement(By.tagName("b")).getText().contains(productName)).findFirst().orElse(null);
		return product;
	 }
		
	public void addProductToCart(String productName) throws InterruptedException
	{
		getProductByname(productName).findElement(addTocart).click();
		waitForElementToVisible(toastMessage);
		waitForElementToInvisible(spinner);
	}
}

	
