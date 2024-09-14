package kowsalyaLearning.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import kowsalyaLearning.AbstractComponents.AbstractComponent;


public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	Actions a;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		 a = new Actions(driver);
	}
	
	By countryResults = By.cssSelector(".ta-results");
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submit;
	

	public void selectOurCountry(String countryName)
	{
		
	a.sendKeys(country,countryName).build().perform();
	waitForElementToVisible(countryResults);
	selectCountry.click();
	}
	public ConfirmationPage submitOrder() 
	{
		
	a.moveToElement(submit).click().build().perform();
    return new ConfirmationPage(driver);
	}
}
