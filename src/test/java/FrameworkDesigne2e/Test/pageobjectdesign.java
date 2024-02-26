package FrameworkDesigne2e.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkDesigne2e.TestComponent.BaseTest;
import FrameworkDesigne2e.pageobject.Landingpage;
import FrameworkDesigne2e.pageobject.OrderPage;
import FrameworkDesigne2e.pageobject.ProductCatelog;
import FrameworkDesigne2e.pageobject.cartPage;
import FrameworkDesigne2e.pageobject.paymentsPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class pageobjectdesign extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatelog productCatelouge = landingpage.Loginapplication(input.get("email"), input.get("Password"));
		List<WebElement> products = productCatelouge.getProductList();
		productCatelouge.addProductToCart(input.get("product"));
		cartPage cartPage = productCatelouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		cartPage.goToPaymentpage();
		paymentsPage PaymentPage = new paymentsPage(driver);
		PaymentPage.SelectCountry("india");
		PaymentPage.ProceedPayment();
		Thread.sleep(1000);

		// Click the submit button using JavaScript executor
		// Wait for the confirmation message to appear

	}

//	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() throws InterruptedException {

		ProductCatelog productCatelouge = landingpage.Loginapplication("Testshiva@gmail.com", "Test@123");
		OrderPage OrdersList = productCatelouge.goToOrdersPage();
		Assert.assertTrue(OrdersList.VerifyOrdersDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		

		// -- To add data to ur test(process2)
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "Testshiva@gmail.com");
		map.put("Password", "Test@123");
		map.put("product", "ADIDAS ORIGINAL");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("Password", "Iamking@000");
		map1.put("product", "ZARA COAT 3");
		return new Object[][] { { map }, { map1 } };
		

		// -- To add data to ur test(process1)
//		@DataProvider
//		public Object[][] getData() {
//		return new Object[][] { { "Testshiva@gmail.com", "Test@123", "ADIDAS ORIGINAL" },
//				{ "shetty@gmail.com", "Iamking@000", "ZARA COAT 3" } };

		// -- To get data from Json file to Testcase.
//		List<HashMap<String, String>> data = getJsondataToMap(
//				System.getProperty("user.dir") + "\\src\\test\\java\\Designe2e\\data\\PurchaseOrder.json");
//		return new Object[][] { { data.get(0) }, { data.get(1) } };
//	}
	

	}
}
