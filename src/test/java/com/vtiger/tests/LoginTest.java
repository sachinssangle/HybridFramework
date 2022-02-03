package com.vtiger.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	
	
	@Test
	public void validLogin_TC01()
	{
		System.out.println("Hello");
		String TCName = "validLogin_TC01";
		logger = extent.createTest(TCName);
		LoginPage lp = new LoginPage(driver,logger);
		lp.Login(TestData.get(TCName).get("Userid"),TestData.get(TCName).get("Password"));
		HomePage hp = new HomePage(driver,logger);		
		hp.verifyPipeline();
		hp.clickLogout();		
		extent.flush();
	}

}
