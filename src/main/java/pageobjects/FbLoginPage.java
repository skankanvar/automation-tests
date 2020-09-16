package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FbLoginPage {

	@FindBy(xpath="//input[@name='email']")
	public WebElement un;
	
	private WebElement pw;
	
	@FindBy(name="login")
	public WebElement bu;
	
	private WebDriver wb;
	
	public FbLoginPage(WebDriver driver) {
		wb = driver;
		PageFactory.initElements(wb, this);
	}
	
//	
//	public WebElement getUsername() {
//		un = wb.findElement(By.xpath("//input[@name='email']"));
//		return un;
//		
//	}
//	public WebElement getloginbutton() {
//		bu = wb.findElement(By.name("login"));
//		return  bu;
//		
//	}
	

//	public WebElement getUsername() {	
//		return un;
//		
//	}
//	public WebElement getloginbutton() {
//		return  bu;
//		
//	}
	
}
