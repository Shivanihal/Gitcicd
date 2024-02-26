package FrameworkDesigne2e.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameworkDesigne2e.pageobject.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Landingpage landingpage;

	public WebDriver initialiseDriver() throws IOException {

		// Properties class can read the global Properties

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Designe2e\\resources\\GlobalData.properties");
		prop.load(fis);
		String Browsername = prop.getProperty("browser");

		if (Browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (Browsername.equalsIgnoreCase("firefox")) {
			// firefox browser code
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().deleteAllCookies();
		return driver;
	}

	public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException {
		// ReadJson to String
		String JsonContent = FileUtils.readFileToString(
				new File(System.getProperty("User.dir") + "\\src\\test\\java\\Designe2e\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String to HashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public Landingpage LaunchApplication() throws IOException {
		driver = initialiseDriver();
		landingpage = new Landingpage(driver);
		landingpage.goTo();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
