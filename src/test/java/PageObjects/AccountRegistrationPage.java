package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;		
			
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txtConfirmpassword;
    
    	@FindBy(xpath="//input[@name='agree']")
    	WebElement txtAgree;
    	
    	@FindBy(xpath="//input[@value='Continue']")
    	WebElement txtContinue;
    	
    	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    	WebElement msgConfiguration;
    	
    	public void firstname(String fname)
    	{
    		txtFirstname.sendKeys(fname);
    	}
    	
    	public void lastname(String lname)
    	{
    		txtLastname.sendKeys(lname);
    	}
    	
    	public void email(String gmail)
    	{
    		txtEmail.sendKeys(gmail);
    	}
    	
    	public void telephone(String number)
    	{
    		txtTelephone.sendKeys(number);
    	}
    	
    	public void password(String pwd)
    	{
    		txtPassword.sendKeys(pwd);
    	}
    	
    	public void confirmpassword(String cpwd)
    	{
    		txtConfirmpassword.sendKeys(cpwd);
    	} 
    	
    	public void agree()
    	{
    		txtAgree.click();
    	}
		
    	public void continueclick()
    	{
    		 txtContinue.click();
    	}
    	
    	public String getconfirmationmsg()
    	{
    		try {
				return (msgConfiguration.getText());
			} catch (Exception e) {
				// TODO: handle exception
				return (e.getMessage());
			}
    	}
    	
    
		
			
	
	
	

}
