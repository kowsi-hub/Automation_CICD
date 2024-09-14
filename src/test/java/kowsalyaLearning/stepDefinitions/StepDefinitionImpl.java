package kowsalyaLearning.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kowsalyaLearning.PageObjects.CartPage;
import kowsalyaLearning.PageObjects.CheckOutPage;
import kowsalyaLearning.PageObjects.ConfirmationPage;
import kowsalyaLearning.PageObjects.LandingPage;
import kowsalyaLearning.PageObjects.ProductCatalogue;
import kowsalyaLearning.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public  ConfirmationPage confirmationPage;
	@Given("user is on landing Page")
	public void user_is_on_landing_Page() throws IOException
	{
		landingPage = launchApplication();
	}

	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String email, String password)
	{
		productCatalogue = landingPage.loginApplication(email, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_prodcut_to_cart(String product) throws InterruptedException
	{
		productCatalogue.addProductToCart(product);

	}
	
	@And("^checkout productname (.+) and submit the order$")
	public void checkout_productname_and_submit_the_order(String product)
	{
		CartPage cartPage  = productCatalogue.goToCartPage();
	    
		Boolean match  =  cartPage.verifyCartProducts(product);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		
		checkOutPage.selectOurCountry(countryName);
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation Page")
	public void message_is_displayed_on_confirmation_Page(String string)
	{
		Assert.assertTrue(confirmationPage.getSuccessMessage().equalsIgnoreCase(string));
	}
	@Then("{string} message is displayed on landingPage")
	public void message_is_displayed_on_landingPage(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
	}
}
