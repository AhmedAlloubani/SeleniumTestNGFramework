package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;

public class Driver {
	
	
	private static WebDriver driver;
	
	
	public static WebDriver getDriver() {
		
		String browser = System.getProperty("browser");
		
		if (browser == null) {
			browser = PropertiesReader.getProperty("browser");
		}
		
		
		if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
			
			switch(browser) {
			
			case("FireFox"):
				FirefoxDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case("IE"):
				InternetExplorerDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
				
			case("Safari"):
				driver = new SafariDriver();
				break;
				
			case("chrome"):		
			default:
				ChromeDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			}
		}
		
		
		return driver;
		
		
	}
	
	
	public static void quitDriver() {
		if (driver!=null) {
			driver.quit();
			driver = null;
		}
	}

}
