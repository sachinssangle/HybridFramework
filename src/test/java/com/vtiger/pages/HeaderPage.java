package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.lib.GenericMethods;

public class HeaderPage {
	
	private WebDriver driver;
	public GenericMethods gm;
	private ExtentTest logger;
	
	public HeaderPage(WebDriver driver,ExtentTest logger)
	{
		this.driver = driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
		gm = new GenericMethods(driver,logger);
	}
	
	@FindBy(linkText="Logout")
	WebElement lnkLogout;
	
	@FindBy(linkText="New Lead")
	WebElement lnkNewLead;
	
	
	public void clickLogout()
	{
		gm.ClickElement(lnkLogout,"Clicked on Logout Link");
	}
	
	public void clickNewLead()
	{
		gm.ClickElement(lnkNewLead,"Clicked on New Lead Link");
	}
	

}
