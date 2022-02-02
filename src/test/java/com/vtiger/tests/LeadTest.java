package com.vtiger.tests;

import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class LeadTest extends BaseTest {
	
	@Test
	public void validLeadCreation_TC02()
	{
		String TCName = "validLeadCreation_TC02";
		logger = extent.createTest(TCName);
		LoginPage lp = new LoginPage(driver,logger);
		lp.Login(TestData.get(TCName).get("Userid"),TestData.get(TCName).get("Password"));
		HomePage hp = new HomePage(driver,logger);		
		hp.verifyPipeline();
		hp.clickNewLead();
		LeadPage ldp = new LeadPage(driver,logger);
		ldp.CreateLead(TestData.get(TCName).get("FirstName"), TestData.get(TCName).get("LastName"), TestData.get(TCName).get("Company"));
		hp.clickLogout();		
		extent.flush();
	}


}
