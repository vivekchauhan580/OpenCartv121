package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class TC_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class , groups="Datadriven") //gettong dataprovider from different classs
	public void verify_loginDDT(String email , String pwd , String exp)
	{
	//homepage
		try {
		
		logger.info("***TC_LoginDDT started***");
		HomePage hp=new HomePage(driver);
			hp.ClickMyAccount();
			hp.loginpage();
			
			logger.info("***login page started***");
			//login page
			LoginPage lp= new LoginPage(driver);
			lp.setemail(email);
			lp.setpassword(pwd);
			lp.clicklogin();
		
			
			MyAccountPage macc= new MyAccountPage(driver);
			boolean targetPage=macc.ismMyAccountPageexist();
			
			/*
			 * data is valid--login success--test pass--logout
			 *              --login failed--test fail
			 * data is imvalid--login success-- test fail--logout
			 *                -- login failed--test pass
			 */
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetPage==true)
				{
					macc.clicklogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("invalid"))
			{
				if(targetPage==true)
				{
					macc.clicklogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			
        }
			}
		catch (Exception e) {
			Assert.fail();
		}
			logger.info("***TC_LoginDDT finished***");
    }
}
