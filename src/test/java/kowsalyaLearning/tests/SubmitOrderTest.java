package kowsalyaLearning.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import kowsalyaLearning.PageObjects.CartPage;
import kowsalyaLearning.PageObjects.CheckOutPage;
import kowsalyaLearning.PageObjects.ConfirmationPage;

import kowsalyaLearning.PageObjects.OrdersPage;
import kowsalyaLearning.PageObjects.ProductCatalogue;
import kowsalyaLearning.TestComponents.BaseTest;


public class SubmitOrderTest extends BaseTest{
	
	ConfirmationPage confirmationPage;

	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		//List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage  = productCatalogue.goToCartPage();
	    
		Boolean match  =  cartPage.verifyCartProducts(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		
		checkOutPage.selectOurCountry(countryName);
		confirmationPage = checkOutPage.submitOrder();
		
		Assert.assertTrue(confirmationPage.getSuccessMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}

	@Test(dependsOnMethods = "submitOrder")
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		//confirmationPage.signOutFromApp();
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	List<HashMap<String,String>> data = getJsonDatatoHashMap(System.getProperty("user.dir")+"//src//test//java//kowsalyaLearning//data//PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map =  new HashMap<String, String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password","Iamking@000");
//		map.put("productName","ZARA COAT 3");
//		HashMap<String,String> map1 =  new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password","Iamking@000");
//		map1.put("productName","IPHONE 13 PRO");
//		return new Object[][] {{map},{map1}};
//	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][]{ {"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","IPHONE 13 PRO"} };
//	}
}
