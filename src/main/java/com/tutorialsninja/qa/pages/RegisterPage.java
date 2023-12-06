package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	// Objects
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmFeild;

	@FindBy(xpath = "//input[@name='agree'][@value='1']")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Actions
	public String retrivePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}

	public String retriveTelephoneWarning() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}

	public String retriveEmailWarning() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}

	public String retriveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retriveFirstNameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrivePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterpassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String passwordText) {
		passwordConfirmFeild.sendKeys(passwordText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}

	public AccountSuccesspage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccesspage(driver);
	}

	public void selectYesNewsletterOption() {
		yesNewsletterOption.click();
	}

	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningtext = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningtext;
	}

	public AccountSuccesspage registerWithmandatoryFields(String firstNameText, String lastNameText, String emailText,
			String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmFeild.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccesspage(driver);
	}

	public AccountSuccesspage registerWithAllFields(String firstNameText, String lastNameText, String emailText,
			String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmFeild.sendKeys(passwordText);
		yesNewsletterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccesspage(driver);
	}

	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,
			String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning,
			String expectedPasswordWarning) {
		boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPasswordWarning);
		boolean firstNamewarningStatus = firstNameWarning.getText().equals(expectedFirstNameWarning);
		boolean lastNamewarningStatus = lastNameWarning.getText().equals(expectedLastNameWarning);
		boolean emailwarningStatus = emailWarning.getText().equals(expectedEmailWarning);
		boolean telephonewarningStatus = telephoneWarning.getText().equals(expectedTelephoneWarning);
		boolean passwordwarningStatus = passwordWarning.getText().equals(expectedPasswordWarning);
		return privacyPolicyWarningStatus && firstNamewarningStatus && lastNamewarningStatus && emailwarningStatus
				&& telephonewarningStatus && passwordwarningStatus;

	}
}
