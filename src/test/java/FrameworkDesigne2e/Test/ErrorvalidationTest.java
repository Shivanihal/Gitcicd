package FrameworkDesigne2e.Test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import FrameworkDesigne2e.TestComponent.BaseTest;
import FrameworkDesigne2e.pageobject.ProductCatelog;
import FrameworkDesigne2e.pageobject.cartPage;
import FrameworkDesigne2e.pageobject.paymentsPage;

public class ErrorvalidationTest extends BaseTest {

	@Test(groups = {"ErrorHandles"})
	public void LoginErrorValidation() throws IOException, InterruptedException {

//		String ProductName = "ADIDAS ORIGINAL";
		landingpage.Loginapplication("Testshiva11@gmail.com", "Test@12311");
		Assert.assertEquals("Incorrect email password.", landingpage.errorvalidation());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String ProductName = "ADIDAS ORIGINAL";
		ProductCatelog productCatelouge = landingpage.Loginapplication("Testshiva@gmail.com", "Test@123");
		List<WebElement> products = productCatelouge.getProductList();
		productCatelouge.addProductToCart(ProductName);
		cartPage cartPage = productCatelouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ORIGINAL ADIDAS");
		Assert.assertFalse(match);

	}
}
