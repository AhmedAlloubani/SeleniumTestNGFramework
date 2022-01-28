package test;

import org.testng.annotations.Test;

import pages.AmazonSerachPage;
import utilities.Driver;
import utilities.PropertiesReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AmazonSearchTests {
	
	AmazonSerachPage amazonPage;
	WebDriverWait wait;
	
	
 //Passing data to the test
 @DataProvider
 public String[] dataBucket() {
	 
	 String[] testData = new String[5];
	 testData[0] = "coffee mug";
	 testData[1] = "pretty coffee mug";
	 testData[2] = "cool coffee mug";
	 testData[3] = "cute coffee mug";
	 testData[4] = "ugly coffee mug";
	 
	 return testData;
 }
 
 
 //Passing data to the test
 @DataProvider
 public Object[] myDataBucket() {
	 
	return new Object[] {
			"coffee mug",
			"pretty coffee mug",
			"cool coffee mug",
			"cute coffee mug",
			"ugly coffee mug",
			75566,
			68889990
	};
 }
 
 
 @Test (dataProvider = "myDataBucket")
 public void objectData(Object obj) {
	 
	 System.out.println(obj);
	 
 }
 
 
 
 
 
 
 
	
	
  @Test (dataProvider = "dataBucket")
  public void amazonSerach(String data) {
	  
	  amazonPage = new AmazonSerachPage();
	  wait = new WebDriverWait(Driver.getDriver(), 10);
	  Driver.getDriver().get(PropertiesReader.getProperty("amazonURL"));
	  System.out.println(data);
	  
	  String actualTitle = Driver.getDriver().getTitle();
	  Assert.assertEquals(actualTitle, "Amazon.com. Spend less. Smile more.");
	  
	  amazonPage.searchBox.sendKeys(data);
	  amazonPage.searchBtn.click();
	  
	  wait.until(ExpectedConditions.visibilityOf(amazonPage.searchResults));
	  
	  String actualText = amazonPage.searchResults.getAttribute("value");
			  
	//  String trimmedText = actualText.substring(1, actualText.length()-1)	;	  
	  
	 
	  
//	  System.out.println("Actual text " + actualText);
//	  System.out.println("Trimmed text " + trimmedText);
//	  System.out.println("Search text " + data);
	  
	  Assert.assertEquals(actualText, data);
  }
  
  
  
  
  @BeforeMethod
  public void beforeMethod() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  
  
  
  

  @AfterMethod
  public void afterMethod() {
	  Driver.getDriver().quit();
  }

}
