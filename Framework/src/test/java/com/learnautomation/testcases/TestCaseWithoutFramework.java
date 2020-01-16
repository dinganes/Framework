package com.learnautomation.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCaseWithoutFramework {

	@Test
	public void test1() throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Chandani_50\\Desktop\\Selenium Jar\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
	
		driver.get("http://freecrm.com/");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.name("username")).sendKeys("Selenium_50");

		driver.findElement(By.name("password")).sendKeys("Abcd@123456");

		driver.findElement(By.xpath("//input[@value='Login']")).click();

		driver.quit();
	}
	
		System.out.println("Added new line");

}
