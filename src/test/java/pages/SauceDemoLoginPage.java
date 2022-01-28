package pages;

import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static utilities.Driver.*;

import java.util.List;

import utilities.Driver;

public class SauceDemoLoginPage {
	
	
	public SauceDemoLoginPage() {
		PageFactory.initElements(Driver.getDriver(), this); // we want to initialize these elements of this class
	}
	
	
	
	@FindBy (id = "user-name")
	public WebElement userName;
	
	@FindBy (id = "password")
	public WebElement passwordBox;
	
	@FindBy (id = "login-button")
	public WebElement loginBtn;
	
	
	@FindBy (xpath = "//h3[contains(text(), 'Epic sadface: Username and password do not match')]")
	public WebElement errorMessage;
	
	
	@FindBy (tagName = "span")
	public WebElement homePage;
	
	@FindBy (xpath = "//h3[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]")
	public WebElement lockedOutMessage;
	
	@FindBy (css = ".inventory_item")
	public List<WebElement> items;
	
	@FindBy (css = ".inventory_item_price")
	public List<WebElement> itemPrices;

}
