package kowsalyaLearning.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import kowsalyaLearning.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	//constructor is created to call the webdriver from another class
	public LandingPage(WebDriver driver)
	{
		super(driver); //super keyword is used to pass the driver to parent or abstract component class
		this.driver = driver; //this keyword refers to local variable
		PageFactory.initElements(driver,this); //initailize driver for findby
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//	WebElement userPassword = driver.findElement(By.id("userPassword"));
//	WebElement login = driver.findElement(By.id("login"));
	//pageFactory method 
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css ="[class*='flyInOut']")
	WebElement errormsg;
	
	public String getErrorMessage()
	{
		waitForWebElementToVisible(errormsg);
		return errormsg.getText();
	}
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		return new ProductCatalogue(driver);
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
