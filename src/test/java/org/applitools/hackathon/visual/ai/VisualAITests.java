package org.applitools.hackathon.visual.ai;

import org.applitools.hackathon.visual.ai.pages.AppPage;
import org.applitools.hackathon.visual.ai.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VisualAITests extends BaseTests {

	@BeforeClass
	public void visualAITestSuite() {
		loginPage = new LoginPage(driver);
		appPage = new AppPage(driver);
		eyesManager.setBatchName("Hackathon");
	}

	@Test(priority = 1)
	public void loginPageUIElementsTest() throws InterruptedException {
		Thread.sleep(2000);
		eyesManager.validateWindow();
	}

	@Test(priority = 2, dataProvider = "LoginCredentialsProvider")
	public void dataDrivenTest(String username, String password, String warningText) {
		driver.navigate().to(appUrl);
		loginPage.loginToApp(username, password);
		eyesManager.validateWindow("dataDrivenTest_" + warningText);
	}

	@Test(priority = 3)
	public void tableSortTest() {
		eyesManager.validateWindow("tableSortTest_beforeSort");
		appPage.clickOnAmountInTransactionTable();
		eyesManager.validateWindow("tableSortTest_afterSort");

	}

	@Test(priority = 4)
	public void canvasChartTest() {
		appPage.clickOnCompareExpenses();
		eyesManager.validateWindow("canvasChartTest_2017-2018");
		appPage.clickOnShowDataForNextYear();
		eyesManager.validateWindow("canvasChartTest_2017-2018-2019");

	}

	@Test(priority = 5)
	public void dynamicContentTest() {
		driver.navigate().to(appUrl + "?showAd=true");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp("dynamicUser", "wertyui");
		eyesManager.validateWindow();

	}

}
