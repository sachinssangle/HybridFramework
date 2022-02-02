package com.vtiger.tests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.lib.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static Properties prop;
	public static Map<String,Map<String,String>> TestData; 
	
	
	@BeforeSuite
	public void lauchapp()
	{
		loadconfig();
		TestData = LoadTestData(System.getProperty("user.dir")+"/src/test/java/com/vtiger/testdata/Data.xlsx","Sheet1");
		createExtentReport();
		if(prop.getProperty("browser").equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();		
		driver = new ChromeDriver();	
		}
		else if(prop.getProperty("browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(prop.getProperty("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		int time = Integer.parseInt(prop.getProperty("GlobalTimeOut"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		
	}
	
	@AfterSuite
	public void closeapp()
	{
		driver.quit();
	}
	
	public void createExtentReport()
	{
		DateFormat fmat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		Date d = new Date();
		String str = fmat.format(d);
		System.out.println(str);
		String ReportPath = System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/";
		String ReportName = "vTigerAutomationResult_"+str+".html";
		htmlReporter = new ExtentHtmlReporter(ReportPath+ReportName);
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
	}
	
	public void loadconfig()
	{
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/vtiger/lib/config.properties");
			
			prop.load(fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String,Map<String,String>> LoadTestData(String path,String sheetName)
	{
		//Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+"/src/test/java/com/vtiger/testdata/Data.xlsx");
		//int rowcount = xr.getRowCount("Sheet1");
		Xls_Reader xr = new Xls_Reader(path);
		int rowcount = xr.getRowCount(sheetName);
		int colcount = xr.getColumnCount(sheetName);
		Map<String,Map<String,String>> Mapdata = new HashMap<String,Map<String,String>>();
		for(int i=2;i<=rowcount;i++)
		{
			Map<String,String> colData = new HashMap<String,String>();
			for(int j=1;j<=colcount;j++)
			{
				String ColName = xr.getCellData(sheetName, j, 1).trim();
				String td = xr.getCellData(sheetName, j, i).trim();
				colData.put(ColName, td);				
			}
			Mapdata.put(xr.getCellData(sheetName, 0, i).trim(), colData);
		}
		
		return Mapdata;
		
	}

}
