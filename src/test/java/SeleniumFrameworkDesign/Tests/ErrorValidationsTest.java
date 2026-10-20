package SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.TestComponents.Retry;
import SeleniumFrameworkDesign.pageObject.CartPage;
import SeleniumFrameworkDesign.pageObject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class) //this will rerun if failed.
	public void LoginErrorValidation() throws IOException, InterruptedException{
		
		lp.loginApplication("sahil@test.com", "Sahil12345");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String ProductName = "ZARA COAT 3";
		
		ProductCatalogue pc = lp.loginApplication("sahil1@test.com", "Sahil@1998");
		//ProductCatalogue pc = new ProductCatalogue(driver);  -> We write it above with lp as we already written this step in LndingPage class
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(ProductName);
		CartPage cp = pc.goToCartPage();

		Boolean match = cp.VerifyProductDisplayed("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}
