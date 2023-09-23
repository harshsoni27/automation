package test_Scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import generic.Base_Test;
import generic.JavaScriptUtil;
import generic.ReadExcel;
import pom_scripts.LoginPage;
import pom_scripts.WelcomePage;

public class TestScript1 extends Base_Test{
	
	@Test(dataProvider = "testdata")
	public void testScript1(String email, String pwd) throws IOException, InterruptedException {
		
		  WelcomePage Wp=new WelcomePage(driver); 
		  Wp.clickLogin();
		  LoginPage lp=new LoginPage(driver); 
		  lp.enterValueInEmail(email); 
		  lp.enterValueInPwd(pwd);
		  
		  test.log(LogStatus.INFO, test.addScreenCapture(getPhoto(driver))); 
		  
		  lp.clickLoginButton();
		  Thread.sleep(5000);
		  JavaScriptUtil javScript=new JavaScriptUtil();
		  javScript.jsScrollBy(0, 500);
		  //action.sendKeys(Keys.PAGE_DOWN).build().perform();
		  try {
			  Assert.assertEquals(driver.findElement(By.partialLinkText("Log out")).isDisplayed(), true);
		  test.log(LogStatus.PASS, "User is logged in");
		  }catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "User is not logged in");
		}
	}
	
	@DataProvider(name = "testdata")
	public Object[][] getTestData() throws IOException {
		Object [][]testdata=ReadExcel.getMultipleData("Sheet1");
		return testdata;
	}
	

}
