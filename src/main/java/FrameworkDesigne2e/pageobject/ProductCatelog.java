package FrameworkDesigne2e.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesigne2e.AbstractComponents.Abstractcomponents;

public class ProductCatelog extends Abstractcomponents {
	// Assigning life of driver toyour test
	WebDriver driver;

	public ProductCatelog(WebDriver driver) {
		super(driver);
		// Intialiation at the begining of the code, at first this constuctor starts
		// executing.
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	

	// waiting for products to appear
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By tostMessage = By.cssSelector("#toast-container");
	

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String ProductName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;

	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(tostMessage);
		waitForElementToDisapper(spinner);
		
		
	}
	
	
	

}
