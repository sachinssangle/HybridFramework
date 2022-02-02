package com.vtiger.lib;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class GenericMethods {
	
	private WebDriver driver;
	private ExtentTest logger;
	
	public GenericMethods(WebDriver driver,ExtentTest logger) 
	{
		this.driver=driver;
		this.logger = logger;
	}
	
	public void EnterValue(WebElement elm, String txt,String reportMsg)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,10);	
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(txt);
		if(elm.getAttribute("value").equals(txt))
		{
			logger.pass(reportMsg);
		}
		else
		{
			logger.fail("Unable to enter value in textfiled");
			logger.fail("Unable to enter value in textfiled <span class='label end-time'><a href="+ getScreenshot()+">Screeshot</a></span>");
		}
		}
		catch(Exception e)
		{
			
			logger.fail("Unable to enter value in textfiled due to "+e.getMessage()+"<span class='label end-time'><a href="+ getScreenshot()+">Screeshot</a></span>");
		}
	}
	
	public void ClickElement(WebElement elm,String reportMsg)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,10);	
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		elm.click();	
		logger.pass(reportMsg);
		}
		catch(Exception e)
		{
			logger.fail("Unable to click on element due to "+e.getMessage()+"<span class='label end-time'><a href="+ getScreenshot()+">Screeshot</a></span>");
		}
	}
	
	public void ElementDisplay(WebElement elm,String reportMsg)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,10);	
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isDisplayed();
		logger.pass(reportMsg);
		}
		catch(Exception e)
		{
			logger.fail("Element did not display due to "+e.getMessage()+"<span class='label end-time'><a href="+ getScreenshot()+">Screeshot</a></span>");
		}
	}
	
	public String getScreenshot()  {
		//below line is just to append the date format with the screenshot name to avoid duplicate names		
	    String destination=null;
		try
		{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		destination = System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/screenshot/"+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Returns the captured file path
		return destination;
	}

}
