package com.inetbanking.testCases;


import java.io.IOException;

//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test
	public void loginTest() throws IOException{
		

		logger.info("Url is opened");
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		try {
		lp.clickSubmit();
		}
		catch (Exception e) {
			System.out.println("login failed"+e.getMessage());
		}
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage123")) {
				Assert.assertTrue(true);
				logger.info("Login test passed ");
			}
			else {
				captureScreen(driver,"loginTest" );
				Assert.assertTrue(false);
				logger.info("Login test Failed ");
			}
		
		
	}
}
