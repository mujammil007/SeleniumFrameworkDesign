package SeleniumFrameworkDesign.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	@FindBy(css="[placeholder='Select Country']") WebElement CountryElement;
	@FindBy(css=".action__submit") WebElement Submit;
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]") WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public void selectCountry(String CountryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(CountryElement, CountryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		
		Submit.click();
		return new ConfirmationPage(driver);
		
	}
	
}
