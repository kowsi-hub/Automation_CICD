package kowsalyaLearning.PageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kowsalyaLearning.AbstractComponents.AbstractComponent;


public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
    public Boolean verifyCartProducts(String productName)
    {
    	 return  cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
    }
	
    public CheckOutPage goToCheckout()
    {
	checkOut.click();
	return new CheckOutPage(driver);
    }
	
}
