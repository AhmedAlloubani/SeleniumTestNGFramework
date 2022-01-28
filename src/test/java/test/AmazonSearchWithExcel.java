package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AmazonSerachPage;
import utilities.Driver;
import utilities.ExcelUtils;
import utilities.PropertiesReader;

public class AmazonSearchWithExcel {
	
	
	AmazonSerachPage amazonPage;
	WebDriverWait wait;
	
	
 @BeforeMethod
	  public void beforeMethod() {
		  Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
	
	
  @Test (dataProvider = "serachData")
  public void amazonSearchWithExcel(String data) {
	  amazonPage = new AmazonSerachPage();
	  wait = new WebDriverWait(Driver.getDriver(), 10);
	  
	  System.out.println(data);
	  
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
  
  
  
  @DataProvider
  public Object[] serachData() {
	  Object[] obj =  ExcelUtils.getExcelDataInAColumn("./src/test/resources/test_data/amazonTestData.xlsx", "Sheet1");
	  return obj;
  }
  
  
  
  
  @AfterMethod
  public void afterMethod() {
	  Driver.getDriver().quit();
  }
}
