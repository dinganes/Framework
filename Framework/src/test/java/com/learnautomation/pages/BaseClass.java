package com.learnautomation.pages;



import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	String reportPath;

	@BeforeSuite
	public void setUpSuite() {

		Reporter.log("Setting up reports and Test is getting ready", true);

		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		reportPath = System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html";

		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(reportPath));
		report = new ExtentReports();
		report.attachReporter(extent);

		Reporter.log("Setting Done- Test can be started", true);
	}

//	@Parameters({ "browser", "urlToBeTested" })

	@Parameters({ "browser", "urlNeedsToBeTested" })
	@BeforeClass
	public void setup(String browser, String url) {

		Reporter.log("Trying to start Browser and Getting application ready", true);

		driver = BrowserFactory.startApplication(driver, browser, url);

//		driver = BrowserFactory.startApplication(driver, browser, url);

		Reporter.log("Browser and Application is up and running", true);

	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		Reporter.log("Test is about to end ", true);

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test Failed ",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test passed ",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		}

		report.flush();

		Reporter.log("Test Completed >>> Reports Generated", true);

		Reporter.log("Report can be accessed via >>> " + reportPath, true);

	}

}
