package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import TestBase.BaseClass;

public class TC_AccountRegistrationTest extends BaseClass{
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****Starting TC_AccountRegistrationTest**** ");
		
		try 
		{
		HomePage hp = new HomePage(driver);
		
		hp.ClickMyAccount();
		logger.info("clicked on my account ");
		hp.clickRegister();
		logger.info("clicked on register ");
		
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		
		logger.info("providing details");
		arp.firstname(randomstring().toUpperCase());
		arp.lastname(randomstring().toUpperCase());
		arp.email(randomstring()+"@gmail.com");
		arp.telephone(randomnumber());
		
		String password=randomalphanumeric();
		arp.password(password);
		arp.confirmpassword(password);
		arp.agree();
		arp.continueclick();
		String cnfrmsg=arp.getconfirmationmsg();
		
		
		logger.info("validating msg");
		String cnfmsg=arp.getconfirmationmsg();
		if(cnfmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("test failed");
			logger.debug("debug logs");
			Assert.assertTrue(false);
			
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			
			
			Assert.fail();
			
		}
		
		logger.info("****finished**** ");
		
	}
	
	
	

}
