package FrameworkDesigne2e.pageobject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkDesigne2e.AbstractComponents.Abstractcomponents;



public class paymentsPage extends Abstractcomponents {

	// Assigning life of driver toyour test
	WebDriver driver;
	
	public paymentsPage(WebDriver driver) {

		// Intialiation at the begining of the code, at first this constuctor starts
		// executing.
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css = ".action__submit")
	WebElement PlaceOrder;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement SelectCountry;
	
	By results = By.cssSelector(".ta-results");
	By Buttonvisible =By.className("hero-primary");
	

	public void SelectCountry(String CountryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(Country, CountryName).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForElementToAppear(results);
		SelectCountry.click();
		
	}
	
	public void ProceedPayment() {
		
		WebElement submitButton = PlaceOrder;
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
		
	}
	
	
	
}
