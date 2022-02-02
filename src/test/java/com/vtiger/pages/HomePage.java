package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends HeaderPage {

	private WebDriver driver;
	
	public HomePage(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
		this.driver=driver;		
	}
	
	@FindBy(xpath="//table[@class='formOuterBorder']")
	WebElement MyPipeline;
	
	
	public void verifyPipeline()
	{
		gm.ElementDisplay(MyPipeline,"My Pipeline graph is displayed successfully");
	}
	
	

}
