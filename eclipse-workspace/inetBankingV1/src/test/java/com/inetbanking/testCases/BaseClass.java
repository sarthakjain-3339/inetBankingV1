package com.inetbanking.testCases;





import java.io.File;
import java.io.IOException;
import org.openqa.selenium.TakesScreenshot;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
//import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfiq= new ReadConfig();
	public String baseUrl=readconfiq.getApplicationUrl();
	public String username=readconfiq.userName();
	public String password=readconfiq.getPassword();
	public static WebDriver driver;
	public static Logger logger; 
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		logger= Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfiq.getChromePath());
		driver =new ChromeDriver();
		}
		else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfiq.getFirefoxPath());
			driver=new FirefoxDriver();
		}
		else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",readconfiq.getIEPath());
			driver=new InternetExplorerDriver();
		}
		driver.get(baseUrl);
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void  captureScreen(WebDriver driver, String tname) throws IOException{
	TakesScreenshot ts= (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
