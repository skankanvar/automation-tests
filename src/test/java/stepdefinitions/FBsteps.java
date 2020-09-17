package stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.FbLoginPage;

public class FBsteps {

  @Given("^initiate web driver and launch fb home page$")
  public void given() throws Throwable {
	   System.out.println(FBsteps.class.getName()+"given");
  }

  @When("^feilds are blank$")
  public void when() throws Throwable {
	  System.out.println(FBsteps.class.getName()+"when");
  }
  
  @And("^sign in clicked$")
  public void and() throws Throwable {
	  System.out.println(FBsteps.class.getName()+"and");
  }

  @Then("^validate the error message and close the window$")
  public void then() throws Throwable {
	  System.out.println(FBsteps.class.getName()+"then"); 
  }
  
  @And("^sign in not clicked$")
  public void sign_in_not_clicked() throws Throwable {
	  System.out.println(FBsteps.class.getName()+"and");
  }

  @Then("^do nothing$")
  public void do_nothing() throws Throwable {
	  System.out.println(FBsteps.class.getName()+"then"); 
  }


}
