package FrameworkDesigne2e.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesigne2e.AbstractComponents.Abstractcomponents;

public class cartPage extends Abstractcomponents {

	// Assigning life of driver toyour test
	WebDriver driver;
	@FindBy(css = ".cartSection h3")
	List<WebElement> CartProducts;
	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public cartPage(WebDriver driver) {

		// Intialiation at the begining of the code, at first this constuctor starts
		// executing.
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyProductDisplay(String ProductName) throws InterruptedException {
		Thread.sleep(2000);
		Boolean match = CartProducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

	public void goToPaymentpage() throws InterruptedException {

		WebElement checkOutButton = checkOut;
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkOutButton);

	}

}
