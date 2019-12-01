package org.applitools.hackathon.visual.ai;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.applitools.hackathon.visual.ai.pages.AppPage;
import org.applitools.hackathon.visual.ai.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TraditionalTests extends BaseTests {

	@BeforeClass
	public void traditionalTestSuite() {
		loginPage = new LoginPage(driver);
		appPage = new AppPage(driver);
	}

	@Test(priority = 1)
	public void loginPageUIElementsTest() {
		try {
			Thread.sleep(2000);

			softAssert = new SoftAssert();
			softAssert.assertEquals(driver.findElement(loginPage.getAuthHeader()).getText(), "Login Form");
			softAssert.assertEquals(driver.findElement(loginPage.getUsernameField()).getAttribute("placeholder"),
					"Enter your username");
			softAssert.assertTrue(isElementExists(loginPage.getUsernameLabel()));
			softAssert.assertEquals(driver.findElement(loginPage.getPasswordField()).getAttribute("placeholder"),
					"Enter your password");
			softAssert.assertTrue(isElementExists(loginPage.getPasswordLabel()), "Password Label Exists:");
			softAssert.assertTrue(isElementExists(loginPage.getUserIcon()), "User icon exists");
			softAssert.assertTrue(isElementExists(loginPage.getFingerpintIcon()), "Finger print icon exists:");
			softAssert.assertEquals(driver.findElement(loginPage.getLogInButton()).getText(), "Log In");
			softAssert.assertTrue(isElementExists(loginPage.getRememberMeCheckBox()), "RememberMe checkbox exists");
			softAssert.assertTrue(isElementExists(loginPage.getRememberMeCheckLabel()), "RememberMe label exists");
			softAssert.assertTrue(isElementExists(loginPage.getTwitterIcon()), "Twitter button exists");
			softAssert.assertTrue(isElementExists(loginPage.getFacebookIcon()), "Facebook button exists");
			softAssert.assertTrue(isElementExists(loginPage.getLinkedInIcon()), "LinkedIn button exists");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			softAssert.assertAll();
		}

	}

	@Test(priority = 2, dataProvider = "LoginCredentialsProvider")
	public void dataDrivenTest(String username, String password, String warningText) {
		driver.navigate().to(appUrl);
		loginPage.loginToApp(username, password);
		if (warningText != "")
			assertEquals(driver.findElement(loginPage.getWarningText()).getText(), warningText);
		else
			assertTrue(driver.findElement(appPage.getTransactionsTable()).isDisplayed());
	}

	@Test(priority = 3)
	public void tableSortTest() {
		softAssert = new SoftAssert();
		HashMap<String, Double> mp = new HashMap<String, Double>();
		mp = getTableData();
		List<Double> trAmountList1 = new ArrayList<Double>(mp.values());
		Collections.sort(trAmountList1);

		appPage.clickOnAmountInTransactionTable();

		HashMap<String, Double> mp1 = new HashMap<String, Double>();
		mp1 = getTableData();
		List<Double> trAmountList2 = new ArrayList<Double>(mp1.values());

		// validate sorting of transaction amount
		softAssert.assertEquals(trAmountList2, trAmountList1);
		// validate the integrity of transaction
		softAssert.assertEquals(mp.keySet(), mp1.keySet());

		softAssert.assertAll();

	}

	// unable to extract data from the DOM
	@Test(priority = 4)
	public void canvasChartTest() {
		appPage.clickOnCompareExpenses();

	}

	@Test(priority = 5)
	public void dynamicContentTest() {
		softAssert = new SoftAssert();
		driver.navigate().to(appUrl + "?showAd=true");
		loginPage.loginToApp("testadd", "adds");
		softAssert.assertTrue(driver.findElements(appPage.getFlashSaleGif1()).size() > 0, "falshSaleGif1 exists:");
		softAssert.assertTrue(driver.findElements(appPage.getFlashSaleGif2()).size() > 0, "falshSaleGif2 exists:");
		softAssert.assertAll();

	}

	private HashMap<String, Double> getTableData() {
		HashMap<String, Double> mp = new LinkedHashMap<String, Double>();
		WebElement table = driver.findElement(appPage.getTransactionsTable());
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
			String status = cells.get(0).findElement(By.xpath("./span[2]")).getText();
			String date = cells.get(1).findElement(By.xpath("./span[1]")).getText()
					+ cells.get(1).findElement(By.xpath("./span[2]")).getText();
			String description = cells.get(2).findElement(By.xpath("./span")).getText();
			String category = cells.get(3).findElement(By.xpath("./a")).getText();
			String amount = cells.get(4).findElement(By.xpath("./span")).getText().trim();
			Double parsedAmount = Double.parseDouble(amount.split(" USD")[0].replace(" ", "").replace(",", ""));
			String rowData = status + "|" + date + "|" + description + "|" + category + "|" + amount + "|"
					+ parsedAmount;
			mp.put(rowData, parsedAmount);
		}
		return mp;
	}

}
