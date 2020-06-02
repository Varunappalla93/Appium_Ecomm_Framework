package com.AppiumFramework;

import java.io.IOException;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pageobjects.formpage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class EcomTC_4 extends Base{
	//public static void main(String[] args) throws InterruptedException, IOException {
		                                               
	//for testng project conversion
	@Test
	public void twoproductsval() throws IOException, InterruptedException
	{
	serv=StartServer();
	                                                  //2nd method "emulator",
	AndroidDriver<AndroidElement> driver=Capabilities("Gstore");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//using page factory
	formpage fp=new formpage(driver);
	fp.namefield.sendKeys("Varun Appalla");
	//or 2nd method
	//fp.getnamefield().sendKeys("varun Appalla");
	//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Varun");
	
	driver.findElementByXPath("(//android.widget.RadioButton)[1]").click(); //not index its number
	driver.hideKeyboard();
	driver.findElementById("android:id/text1").click();
	
	//using page factory
	fp.country.click();
	//using utilites class
	//Utilities u=new Utilities(driver);
    //u.scrollToText("Austria");
	//or below normal
	//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));").click();
	
	driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
	driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(0).click();
	driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(1).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	Thread.sleep(1000);
	int count=driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
	double sum=0;
	for(int i=0;i<count;i++)
	{
		String shoeamnt=driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();	
		double amnt=getAmount(shoeamnt);
		sum=sum+amnt;
	}
	/*
	String shoe1=driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(0).getText();
	double am1=getAmount(shoe1);
	String shoe2=driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(1).getText();
	double am2=getAmount(shoe2);
	double total=am1+am2;
	*/
	System.out.println("sum of products "+sum);
	String tot=driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
	tot=tot.substring(1);
	double totalval=Double.parseDouble(tot);
	System.out.println("Total Sum of Products"+totalval);
	//Assert.assertEquals(sum,totalval);
	serv.stop();
	}
	@BeforeTest
	public void killnodes() throws IOException
	{
		//to kill all node process->taskkill /F /IM node.exe;
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	}
	public static double getAmount(String value)
	{
		value=value.substring(1);
		double amnt=Double.parseDouble(value);
		return amnt;
	}
	
}

	