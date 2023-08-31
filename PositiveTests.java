package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	
	@Test
	public void loginTest() {
		System.out.println("Test Started");
//Create Driver
		WebDriver driver = new ChromeDriver();
		System.out.println("Browser Started");
		sleep(1);
//		open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(1);
		
		driver.manage().window().maximize();
		sleep(1);
		
		System.out.println("Page is opened");
		
//		enter username 
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
//		enter password 
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		
//		click login button
		WebElement loginInButton = driver.findElement(By.tagName("button"));
		loginInButton.click();
		
//		verifications:
//		new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		
//		logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//div[@id='content']//a[@href='/logout']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log Out Button is not visible");
		
//		succesful login message
		WebElement successMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String expectedMessage = "";
		String actualMessage = successMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message.\nActual Message: "
		+ actualMessage + "\nExpected Message: " + expectedMessage);
		
		
		// Close browser
		//driver.quit();
		
		driver.close();
		System.out.println("Test finished");
	}

	/**
	 * Stop execution for the given amount of time
	 * @param seconds
	 */
	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}catch(InterruptedException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
