package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.Logger; //log4j
import org.apache.logging.log4j.LogManager; //log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
public  Logger logger ;	//log4j
public  WebDriver driver;
public  WebDriver getDriver()
{
	return driver;
}
public Properties p;
	
	@BeforeMethod(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os , String br) throws InterruptedException, IOException, URISyntaxException
	{
		System.out.println("setup method started");
		try {
		//loading config properties file
FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		System.out.println("browser value recieved: "+br);
		System.out.println("Execution Env: " + p.getProperty("execution_env"));
		System.out.println("OS: " + os);
		System.out.println("Browser: " + br);
		
		logger=LogManager.getLogger(this.getClass());
		
		String env = p.getProperty("execution_env");

		if ("remote".equalsIgnoreCase(env)) {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		if(os.equalsIgnoreCase("windows")) {
		capabilities.setPlatform(Platform.WINDOWS);
		} else if(os.equalsIgnoreCase("mac")) {
		capabilities.setPlatform(Platform.MAC);
		}

		switch (br.toLowerCase()) {
		case "chrome":
		capabilities.setBrowserName("chrome");
		break;
		case "edge":
		capabilities.setBrowserName("MicrosoftEdge");
		break;
		case "firefox":
		capabilities.setBrowserName("firefox");
		break;
		}

		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		}
		else if ("local".equalsIgnoreCase(env)) {

		switch (br.toLowerCase()) {

		case "chrome":
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		break;

		case "edge":
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		break;

		case "firefox":
     	WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		break;

		default:
		throw new RuntimeException("Invalid browser name");
		}
		}
		else {
		throw new RuntimeException("Invalid execution_env value");
		}
				 
				System.out.println("driver initialised successfully");
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
			    driver.get(p.getProperty("appurl"));
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			    logger.info("Broswer launched");
				
				
				Thread.sleep(3000);
				}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		
		
			
			
			
			
			
			
			/*if("remote".equalsIgnoreCase(p.getProperty("execution_env")))
			{
				driver= new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL() , capabilities);
			}
			else {
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":driver= new ChromeDriver(); break;
			case "edge": driver= new EdgeDriver(); break;
			default:System.out.println("no matching browser"); break;
			}
			}
			
		} */
		
		/*if("local".equalsIgnoreCase(p.getProperty("execution_env")))
		{
		switch (br.toLowerCase()) {
		case "chrome": driver= new ChromeDriver(); break;
		case "edge" 	 : driver=new EdgeDriver(); break;
		default:System.out.println("invalid browser name");
		break;
			
		} */

	
	
  @AfterMethod(groups= {"Sanity","Regression","Master"})
	public void teardown()
	{
	  if (driver !=null) 
	  {
		driver.quit();
		}
	}
	
	public String randomstring()
	{
		RandomStringGenerator generator= new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String generatedstring=generator.generate(7);
		return generatedstring;
	}
	
	public static String randomnumber()
	{
	 return "98" + (int)(Math.random()*100000);
	}
	
	public String randomalphanumeric()
	{
		RandomStringGenerator generator= new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String generatedstring=generator.generate(4);
		
		return(generatedstring+"@" + (int)(Math.random()*10));
		
		
		
		
		
	}
	
	public String captureScreen(String testName) throws IOException {

		String timeStamp = new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());

		if(driver==null) {
			System.out.println("driver is null--can not take screenshot");
			return "";
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		FileUtils.copyFile(sourceFile, targetFile);

		return targetFilePath;
		}


}
