package org.applitools.hackathon.visual.ai.utils;

import org.openqa.selenium.WebDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;

public class EyesManager {

	private Eyes eyes;
	private String appName;
	private WebDriver driver;

	public EyesManager(WebDriver driver, String appName, String applitoolsApiKey) {
		this.driver = driver;
		this.appName = appName;
		eyes = new Eyes();
		eyes.setApiKey(applitoolsApiKey);

	}

	public void setBatchName(String batchName) {
		eyes.setBatch(new BatchInfo(batchName));
	}

	public void validateWindow() {
		eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
		eyes.checkWindow();
		eyes.close();
	}
	
	public void validateWindow(String testName) {
		eyes.open(driver, appName, testName);
		eyes.checkWindow();
		eyes.close();
	}

	public void validateDynamicContent() {
		eyes.open(driver, appName, Thread.currentThread().getStackTrace()[2].getMethodName());
		eyes.setMatchLevel(MatchLevel.LAYOUT);
		eyes.checkWindow();
		eyes.close();
	}
	
	public void abort() {
		eyes.abortIfNotClosed();
	}
	
	public Eyes getEyes() {
		return eyes;
	}
}
