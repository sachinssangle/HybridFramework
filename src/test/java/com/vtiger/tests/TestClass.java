package com.vtiger.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class TestClass {
	
public static WebDriver driver;
	
	@BeforeClass
	public  void launchBrowser()
	{
		System.setProperty("webdriver.edge.driver", "C:/Selenium/Selenium_Software/msedgedriver.exe");
		driver = new EdgeDriver();		
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.quit();
	}
	
	@BeforeMethod
	public void login()
	{
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).clear();
		driver.findElement(By.name("user_password")).sendKeys("admin");	
		driver.findElement(By.name("Login")).click();
	}
	
	@AfterMethod
	public void logout()
	{
		driver.findElement(By.linkText("Logout")).click();
	}
	
	@Test
	public void testcase1_LeadCreation()
	{
		driver.findElement(By.linkText("New Lead")).click();
		driver.findElement(By.name("lastname")).sendKeys("Modi");
		driver.findElement(By.name("company")).sendKeys("BJP");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//td[text()='Lead:   Modi']")).isDisplayed());
		
	}
	
	@Test
	public void testcase2_AccountCreation()
	{
		driver.findElement(By.linkText("New Account")).click();
		driver.findElement(By.name("accountname")).sendKeys("India");		
		driver.findElement(By.name("button")).click();		
		Assert.assertEquals(true, driver.findElement(By.xpath("//td[text()='Account:  India']")).isDisplayed());
	}


}
