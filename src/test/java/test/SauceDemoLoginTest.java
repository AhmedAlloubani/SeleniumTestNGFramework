package test;

import static org.testng.Assert.assertTrue;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SauceDemoLoginPage;
import utilities.Driver;
import utilities.PropertiesReader;

	public class SauceDemoLoginTest {
		
		SauceDemoLoginPage page;
		WebDriverWait wait;
		
		
		@BeforeMethod
		public static void setUp() {
			Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Driver.getDriver().manage().window().maximize();
			//WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
		}
		
		
		
		@AfterMethod
		public static void quitDriver() {
			Driver.quitDriver();
		}
		
	  @Test
	  public void sauceDemoTest1() {
		  
		  page = new SauceDemoLoginPage();
		  
		 Driver.getDriver().get(PropertiesReader.getProperty("url"));
		 page.userName.sendKeys(PropertiesReader.getProperty("validUserName"));
		 page.passwordBox.sendKeys(PropertiesReader.getProperty("validPassword"));
		 page.loginBtn.click();
		 
		 System.out.println(page.items.size());
		 
		 for (WebElement itemPrice  : page.itemPrices) {
			 System.out.println(itemPrice.getText().trim());
		 }
		 
		 String homePage1 = page.homePage.getText().trim();
		 String expectedTitle = "PRODUCTS";
		 assertTrue(homePage1.equals(expectedTitle));
		 System.out.println("We logged in succesfully");
	  }
	  
	  @Test
	  public void sauceDemoTest2() {
		  
		  page = new SauceDemoLoginPage();
		  
		 Driver.getDriver().get(PropertiesReader.getProperty("url"));
		 page.userName.sendKeys(PropertiesReader.getProperty("invalidUserName"));
		 page.passwordBox.sendKeys(PropertiesReader.getProperty("validPassword"));
		 page.loginBtn.click();
		 assertTrue(page.loginBtn.isDisplayed());
		 String errorMessage = "Epic sadface: Username and password do not match any user in this service";
		 assertTrue(page.errorMessage.getText().trim().equals(errorMessage));
		 System.out.println("logging in fails");
	  }
	  
	  
	  @Test
	  public void sauceDemoTest3() {
		  
		  page = new SauceDemoLoginPage();
		  
		 Driver.getDriver().get(PropertiesReader.getProperty("url"));
		 page.userName.sendKeys(PropertiesReader.getProperty("lockedOutUserName"));
		 page.passwordBox.sendKeys(PropertiesReader.getProperty("validPassword"));
		 page.loginBtn.click();
		 
		 
		 
		 
		 assertTrue(page.loginBtn.isDisplayed());
		 String lockedOutMessage1 = "Epic sadface: Sorry, this user has been locked out.";
		 assertTrue(page.lockedOutMessage.getText().trim().equals(lockedOutMessage1));
		 System.out.println("logging in fails. Sorry you're locked out!");
	  }
	
	
	  
	  
	  
	  
	  
	  
	  

}
