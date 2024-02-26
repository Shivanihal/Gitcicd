package FrameworkDesigne2e.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkDesigne2e.pageobject.OrderPage;
import FrameworkDesigne2e.pageobject.cartPage;

public class Abstractcomponents {
	WebDriver driver;

	public Abstractcomponents(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderButton;
	
	public cartPage goToCartPage() {
		cartButton.click();
		cartPage cartPage = new cartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderButton.click();
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
	}


	public void waitForElementToAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	
	public void waitForElementToDisapper(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
}
