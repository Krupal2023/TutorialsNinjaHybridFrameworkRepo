package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	LoginPage loginpage;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndAppURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		loginpage = homepage.navigateToLoginpage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		AccountPage accountpage = loginpage.login(email, password);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account infomation is not displayed");

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		loginpage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(
				loginpage.retriveEmailpasswordNotMatchingWarningMassageText()
						.contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Expected warning massage is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginpage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(
				loginpage.retriveEmailpasswordNotMatchingWarningMassageText()
						.contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Expected warning massage is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginpage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(
				loginpage.retriveEmailpasswordNotMatchingWarningMassageText()
						.contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Expected warning massage is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutprovidingCredentials() {

		loginpage.clickOnLoginButton();
		Assert.assertTrue(
				loginpage.retriveEmailpasswordNotMatchingWarningMassageText()
						.contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Expected warning massage is not displayed");

	}

}
