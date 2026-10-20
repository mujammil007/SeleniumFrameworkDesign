package SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;

	@FindBy(css=".cartSection h3") List<WebElement> cartProducts;
	@FindBy(xpath="//button[text()='Checkout']") WebElement checkOutElement;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplayed(String ProductName) {
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckOutPage CheckoutOrder() {
		
		checkOutElement.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
		
	}

}
