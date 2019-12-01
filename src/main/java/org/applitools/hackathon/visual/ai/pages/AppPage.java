package org.applitools.hackathon.visual.ai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppPage {

	WebDriver driver;

	By amount = By.id("amount");
	
	By showExpensesChart = By.id("showExpensesChart");
	
	By addDataset = By.id("addDataset");
	
	By transactionsTable = By.id("transactionsTable");
	
	By flashSaleGif1 = By.xpath("//div[@id='flashSale']/img");
	
	By flashSaleGif2 = By.xpath("//div[@id='flashSale2']/img");

	public AppPage(WebDriver driver) {
		this.driver = driver;
	}

	public By getAmount() {
		return amount;
	}

	public By getShowExpensesChart() {
		return showExpensesChart;
	}

	public By getAddDataset() {
		return addDataset;
	}

	public By getTransactionsTable() {
		return transactionsTable;
	}

	public By getFlashSaleGif1() {
		return flashSaleGif1;
	}

	public By getFlashSaleGif2() {
		return flashSaleGif2;
	}

	public void clickOnAmountInTransactionTable() {
		driver.findElement(amount).click();
	}

	public void clickOnShowDataForNextYear() {
		driver.findElement(addDataset).click();
	}

	public void clickOnCompareExpenses() {
		driver.findElement(showExpensesChart).click();
	}

}
