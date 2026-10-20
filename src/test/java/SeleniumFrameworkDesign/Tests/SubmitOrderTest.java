package SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObject.CartPage;
import SeleniumFrameworkDesign.pageObject.CheckOutPage;
import SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import SeleniumFrameworkDesign.pageObject.OrderPage;
import SeleniumFrameworkDesign.pageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	String ProductName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitorder(HashMap<String, String> input) throws IOException, InterruptedException{
		
		String Country = "India";
		
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("pass"));
		//ProductCatalogue pc = new ProductCatalogue(driver);  -> We write it above with lp as we already written this step in LndingPage class
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("ProductName"));
		CartPage cp = pc.goToCartPage();

		Boolean match = cp.VerifyProductDisplayed(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cp.CheckoutOrder();
		
		checkOutPage.selectCountry(Country);
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		String ConfirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistory() {
		
		ProductCatalogue pc = lp.loginApplication("sahil@test.com", "Sahil@1998");
		OrderPage op = pc.goToOrdersPage();  //It returns the object so catching it here
		Assert.assertTrue(op.VerifyOrderDisplayed(ProductName));  
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"sahil@test.com", "Sahil@1998", "ZARA COAT 3"}, {"sahil1@test.com", "Sahil@1998", "ADIDAS ORIGINAL"}};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "sahil@test.com");
//		map.put("pass", "Sahil@1998");
//		map.put("ProductName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "sahil1@test.com");
//		map1.put("pass", "Sahil@1998");
//		map1.put("ProductName", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\SeleniumFrameworkDesign\\Data\\PurchaseOrder.json"); //Making this as generic so that we can pass any json file in future.
		
		return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
	}
	
}
