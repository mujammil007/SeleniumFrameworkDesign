package SeleniumFrameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObject.CartPage;
import SeleniumFrameworkDesign.pageObject.CheckOutPage;
import SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import SeleniumFrameworkDesign.pageObject.LandingPage;
import SeleniumFrameworkDesign.pageObject.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImplementation extends BaseTest{
	
	String Country = "India";
	public LandingPage lp;
	public ProductCatalogue pc;
	public CartPage cp;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		
		lp = launchApplcation();
	}
	
	@Given("^Logged in with email (.+) and password (.+)$")
	public void Logged_in_with_email_and_password(String email, String password) {
		
		pc = lp.loginApplication(email, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException{
		
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
	}
	
	@And("^Chechout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		
		cp = pc.goToCartPage();
		Boolean match = cp.VerifyProductDisplayed(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cp.CheckoutOrder();
		
		checkOutPage.selectCountry(Country);
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		
		String ConfirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String strArg1) {

		Assert.assertEquals(strArg1, lp.getErrorMessage());
		driver.close();
	}

}
