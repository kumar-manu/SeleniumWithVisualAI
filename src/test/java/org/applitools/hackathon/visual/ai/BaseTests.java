package org.applitools.hackathon.visual.ai;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.applitools.hackathon.visual.ai.pages.AppPage;
import org.applitools.hackathon.visual.ai.pages.LoginPage;
import org.applitools.hackathon.visual.ai.utils.EyesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.applitools.eyes.selenium.Eyes;


public class BaseTests {

	protected static WebDriver driver;
	protected SoftAssert softAssert;
	protected static Eyes eyes;
	protected static EyesManager eyesManager;
	protected static String appUrl;
	protected static Properties config;
	protected static LoginPage loginPage;
	protected static AppPage appPage;
	
	@DataProvider(name = "LoginCredentialsProvider")
	protected Object[][] getDataFromDataprovider() {
		return new Object[][] { { "", "", "Both Username and Password must be present" },
				{ "testauto", "", "Password must be present" }, { "", "password", "Username must be present" },
				{ "testauto", "password", "" }

		};

	}
	
	
	protected Boolean isElementExists(By webElement) {
		if(driver.findElements(webElement).size()>0)
			return true;
		else return false;
		
	}

	private static Properties getConfigProperties() throws IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
		Properties props = new Properties();
		props.load(fis);
		return props;
	}

	@BeforeSuite
	public void setUp() throws IOException {

		config = getConfigProperties();
		System.setProperty("webdriver.chrome.driver", config.getProperty("chrome.driver"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		eyesManager = new EyesManager(driver, "Hackathon", config.getProperty("applitools.api.key"));
		appUrl = config.getProperty("app.url");
		driver.navigate().to(appUrl);

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		eyesManager.abort();
	}

}
