package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.inetbanking.utilities.XLUtils;
import com.inetbanking.pageObjects.LoginPage;


public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd)
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Url is opened");
		lp.setPassword(pwd);
		logger.info("password is provided");
		lp.clickSubmit();
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept();//close the logout alert
			driver.switchTo().defaultContent();
		}

	}

	public boolean isAlertPresent() //user defined method to check whether alert is present or not
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		String path=System.getProperty("user.dir")+ "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum= XLUtils.getRowCount(path, "Sheet1");
		int colcount= XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][]=new String [rownum][colcount];

		for (int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++) 
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j);
			}
		}
		return logindata;
	}
}
