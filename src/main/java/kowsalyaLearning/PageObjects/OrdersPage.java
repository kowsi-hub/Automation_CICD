package kowsalyaLearning.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kowsalyaLearning.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	public WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	//@FindBy(css = "tbody > tr > td:nth-child(3)")
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> products;

	public Boolean verifyOrderDisplay(String ProductName)
	{
		Boolean match = products.stream().anyMatch(product->product.getText().equalsIgnoreCase(ProductName));
		return match;
	}
}
