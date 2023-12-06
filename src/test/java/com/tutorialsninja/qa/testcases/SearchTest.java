package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	SearchPage searchpage;
	HomePage homepage;

	public SearchTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndAppURL(prop.getProperty("browserName"));
		 homepage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearcingWithValidProduct() {
		searchpage=homepage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchpage.displayStatusOfHPValidProduct(),
				"Valid product HP is not displayed in the search product");
	}

	@Test(priority = 2)
	public void verifySearcingWithInValidProduct() {
		searchpage=homepage.searchForAProduct(dataProp.getProperty("InvalidProduct"));
		Assert.assertEquals(searchpage.retieveNoProductMassegeText(),
				dataProp.getProperty("NoProductTextInSearchResults"), "No product in search result is not dispalyed");

	}

	@Test(priority = 3,dependsOnMethods= {"verifySearcingWithValidProduct","verifySearcingWithInValidProduct"})
	public void verifySearcingWithoutAnyProduct() {
		
		searchpage = homepage.clickOnSearchButton();
		Assert.assertEquals(searchpage.retieveNoProductMassegeText(),
				dataProp.getProperty("NoProductTextInSearchResults"), "No product in search result is not dispalyed");

	}
}