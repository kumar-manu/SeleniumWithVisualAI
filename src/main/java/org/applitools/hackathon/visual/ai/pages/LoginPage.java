package org.applitools.hackathon.visual.ai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;

	By authHeader = By.xpath("//h4[@class='auth-header']");

	By usernameLabel = By.xpath("//label[contains(text(),'Username')]");

	By usernameField = By.id("username");

	By passwordLabel = By.xpath("//label[contains(text(),'Password')]");

	By passwordField = By.id("password");

	By logInButton = By.id("log-in");

	By rememberMeCheckLabel = By.xpath("//label[@class='form-check-label']");

	By rememberMeCheckBox = By.xpath("//input[@class='form-check-input']");

	By twitterIcon = By.xpath("//a[1]//img[1]");

	By facebookIcon = By.xpath("//a[2]//img[1]");

	By linkedInIcon = By.xpath("//a[3]//img[1]");

	By userIcon = By.xpath("//div[@class='pre-icon os-icon os-icon-user-male-circle']");

	By fingerpintIcon = By.xpath("//div[@class='pre-icon os-icon os-icon-fingerprint']");

	By warningText = By.cssSelector(".alert.alert-warning");

	public By getAuthHeader() {
		return authHeader;
	}

	public By getUsernameLabel() {
		return usernameLabel;
	}

	public By getUsernameField() {
		return usernameField;
	}

	public By getPasswordLabel() {
		return passwordLabel;
	}

	public By getPasswordField() {
		return passwordField;
	}

	public By getLogInButton() {
		return logInButton;
	}

	public By getRememberMeCheckLabel() {
		return rememberMeCheckLabel;
	}

	public By getRememberMeCheckBox() {
		return rememberMeCheckBox;
	}

	public By getTwitterIcon() {
		return twitterIcon;
	}

	public By getFacebookIcon() {
		return facebookIcon;
	}

	public By getLinkedInIcon() {
		return linkedInIcon;
	}

	public By getUserIcon() {
		return userIcon;
	}

	public By getFingerpintIcon() {
		return fingerpintIcon;
	}

	public By getWarningText() {
		return warningText;
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void loginToApp(String username, String password) {
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(logInButton).click();
	}

}
