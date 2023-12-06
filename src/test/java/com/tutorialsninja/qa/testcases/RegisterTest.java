package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccesspage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	RegisterPage registerpage;
	AccountSuccesspage accountsuccesspage;

	public RegisterTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndAppURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		registerpage = homepage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {
		accountsuccesspage = registerpage.registerWithmandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telePhoneNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveaccountSuccessPageHeading(),
				dataProp.getProperty("accountSuccessfullHeading"), "Account success page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountWithAllFields() {
		accountsuccesspage = registerpage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telePhoneNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveaccountSuccessPageHeading(),
				dataProp.getProperty("accountSuccessfullHeading"), "Account success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountByExistingEmailAddress() {
		accountsuccesspage = registerpage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), prop.getProperty("validEmail"),
				dataProp.getProperty("telePhoneNumber"), prop.getProperty("validPassword"));
		Assert.assertTrue(
				registerpage.retrieveDuplicateEmailAddressWarning()
						.contains(dataProp.getProperty("duplicateEmailWarning")),
				"Warning massage regarding duplicate email address is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutProvidingAnyField() {

		registerpage.clickOnContinueButton();
		Assert.assertTrue(registerpage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWaring"),
				dataProp.getProperty("firstNameWaring"), dataProp.getProperty("lastNameWaring"),
				dataProp.getProperty("emailWarning"), dataProp.getProperty("telePhoneWarning"),
				dataProp.getProperty("passwordWarning")));

	}
}
