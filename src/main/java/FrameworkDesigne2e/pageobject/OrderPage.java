package FrameworkDesigne2e.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesigne2e.AbstractComponents.Abstractcomponents;

public class OrderPage extends Abstractcomponents {

	// Assigning life of driver to your test
	WebDriver driver;
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ProductsinTable;

	public OrderPage(WebDriver driver) {

		// Intialiation at the begining of the code, at first this constuctor starts
		// executing.
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrdersDisplay(String ProductName) throws InterruptedException {
		Thread.sleep(2000);
		Boolean match = ProductsinTable.stream()
				.anyMatch(orderproduct -> orderproduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

}
