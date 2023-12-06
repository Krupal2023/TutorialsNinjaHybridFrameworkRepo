package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMassage;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//Actions 
	public String retieveNoProductMassegeText() {
		String noProductMassageText=noProductMassage.getText();
		return noProductMassageText;
	}
	public boolean displayStatusOfHPValidProduct() {
		boolean displayStatus=validHPProduct.isDisplayed();
		return displayStatus;
	}
	

}
