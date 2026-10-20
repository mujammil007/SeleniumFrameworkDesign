package SeleniumFrameworkDesign.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement user = driver.findElement(By.cssSelector("#userEmail")); -> This s traditional way we use to find WebElement
	
	//We can user PageFactory design pattern which will have less code

	@FindBy(id="userEmail") WebElement userEmail;
	@FindBy(id="userPassword") WebElement userPassword;
	@FindBy(id="login") WebElement submit;
	@FindBy(css="[class*='flyInOut']") WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	public void goToUrl() {
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
