package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;

public class TC_LoginPage extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		try {
		logger.info("***starting the test case***");
		HomePage hp= new HomePage(driver);
		//homepage
		hp.ClickMyAccount();
		hp.loginpage();
		
		logger.info("***login page started***");
		//login page
		LoginPage lp= new LoginPage(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();
	
		
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage=macc.ismMyAccountPageexist();
		
		Assert.assertTrue(targetPage);
		
		logger.info("***finished***");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
	}

}
