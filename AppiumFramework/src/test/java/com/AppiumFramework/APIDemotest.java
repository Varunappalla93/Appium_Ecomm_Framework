package com.AppiumFramework;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import com.pageobjects.preference;
import com.pageobjects.dependencies;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class APIDemotest extends Base{

	//public static void main(String[] args) throws MalformedURLException {
	
		//@Test(groups = { "apidemo" }) //to exlude this file if we want in testng.xml file
	//data driven
	@Test(dataProvider="InputData",dataProviderClass=testData.class)
	public void APIdemo(String input) throws IOException, InterruptedException
		{
		serv=StartServer();
	                                           //2nd method "emulator",
		AndroidDriver<AndroidElement> driver=Capabilities("APIdemo");
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		preference adl=new preference(driver);
		adl.Preferences.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		
		dependencies pd=new dependencies(driver);
		pd.PDependencies.click(); //from pagefactory 
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);//data driven
		//driver.findElementByXPath("(//android.widget.Button)[1]").click(); or
		
		pd.btn.get(1).click();
		//driver.findElementsByClassName("android.widget.Button").get(1).click();		
		serv.stop();
		}
		
	}
