package FrameworkDesigne2e.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesigne2e.AbstractComponents.Abstractcomponents;

public class Landingpage extends Abstractcomponents{
	//Assigning life of driver to your test
	WebDriver driver;
	public Landingpage landingpage;
	public Landingpage(WebDriver driver)
	{
		super(driver);
		//Intialiation at the begining of the code, at first this constuctor starts executing.
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement UserEmail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement Username;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatelog Loginapplication(String email,String Pass) 
	{
		Username.sendKeys(email);
		Password.sendKeys(Pass);
		submit.click();
		ProductCatelog productCatelouge = new ProductCatelog(driver);
		return productCatelouge;
	}
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String errorvalidation() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	

}
