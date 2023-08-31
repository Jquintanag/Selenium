package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {
	@Test
	public void LogTest(){
		System.out.println("Test Started");
		//Create driver
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		WebDriver driver = new ChromeDriver();
		System.out.println("Browser Started");
		//Open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(1);
		driver.manage().window().maximize();
		sleep(1);
		
		System.out.println("Page is Opened");
		//enter wrong username
		WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
		username.sendKeys("tomsmith");
		sleep(1);
		//enter password 
		WebElement password = driver.findElement(By.xpath("/html//input[@id='password']"));
		password.sendKeys("password");
		sleep(1);
		//Click log in button
		WebElement logInButton = driver.findElement(By.xpath("//form[@id='login']//i[@class='fa fa-2x fa-sign-in']"));
		logInButton.click();
		//Unsuccesful login message
		WebElement unsuccessMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
		String actualMessage = unsuccessMessage.getText();
		String expectedMessage = "Your password is invalid!";
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Invalid");
		sleep(1);
		
		//Close test
		driver.quit();
		System.out.println("Test Finished");
	}
	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}catch(InterruptedException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
