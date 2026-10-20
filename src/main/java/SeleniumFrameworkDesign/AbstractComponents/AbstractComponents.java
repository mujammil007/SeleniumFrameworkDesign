package SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.pageObject.CartPage;
import SeleniumFrameworkDesign.pageObject.OrderPage;

public class AbstractComponents { //this class is our base parent class only which contains all reusable methods
	
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*= 'cart']") WebElement cartButton;
	@FindBy(css="[routerlink*= 'myorders']") WebElement orderButton;
	
	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		
		Thread.sleep(2000);
		//Because of some backend load applicaion is waiting for 4-5 seconds so thats why using the thread.sleep as workaround here for faster test execution.
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	public CartPage goToCartPage() {
		
		cartButton.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrdersPage() {
		
		orderButton.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}
	
}
