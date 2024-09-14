package kowsalyaLearning.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import kowsalyaLearning.PageObjects.CartPage;
import kowsalyaLearning.PageObjects.ProductCatalogue;
import kowsalyaLearning.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups ={"ErrorHandling"},retryAnalyzer =kowsalyaLearning.TestComponents.Retry.class)
	public void LoginErrorValidations()
	{
		landingPage.loginApplication("arkatkowsalya1721@gmail.com", password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	@Test
	public void ProductErrorValidations() throws InterruptedException
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		
		//List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage  = productCatalogue.goToCartPage();
	    
		Boolean match  =  cartPage.verifyCartProducts("Titan Watch");
		Assert.assertFalse(match);

	}
}
