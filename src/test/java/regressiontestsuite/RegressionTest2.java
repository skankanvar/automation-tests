package regressiontestsuite;

import org.testng.annotations.Test;
import configs.ConfigManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class RegressionTest2 {

	@BeforeClass
	public void beforeClass() {
		System.out.println(RegressionTest2.class.getName()+"beforeclass with browser:"+ConfigManager.GetProperty("browser"));
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println(RegressionTest2.class.getName()+"beforemethod with url:"+ConfigManager.GetProperty("url"));
	}


	@Test(groups = {"failed"})
	public void failedTest() throws Exception  {
	   System.out.println(RegressionTest2.class.getName()+"Failed Test failed");
	}
	
	@Test(groups = {"success"})
	public void successTest() throws Exception  {
	   System.out.println(RegressionTest2.class.getName()+"Success Test success");
	}


	@AfterClass
	public void afterClass() {
		System.out.println(RegressionTest2.class.getName()+"in afterclass");
	}

}
