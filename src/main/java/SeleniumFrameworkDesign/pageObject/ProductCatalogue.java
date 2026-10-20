package SeleniumFrameworkDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By productLocator = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	@FindBy(css=".mb-3") List<WebElement> products;
	@FindBy(css=".ng-animating") WebElement spinnerElement;  //loading icon after added to cart

	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productLocator);
		return products;
	}
	
	public WebElement getProductByName(String ProductName) {
		
		WebElement product = getProductList().stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(ProductName))
				.findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String ProductName) throws InterruptedException {
		
		WebElement product = getProductByName(ProductName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinnerElement);

	}

}
