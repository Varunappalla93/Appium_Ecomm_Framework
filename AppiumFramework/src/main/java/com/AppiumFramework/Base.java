package com.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	//Server start and stop
	public static AppiumDriverLocalService serv;
	 public static AndroidDriver<AndroidElement>  driver;
	public AppiumDriverLocalService StartServer()
	{
		boolean flag=checkIfServerIsRunnning(4723);
		if(!flag)
		{
		serv=AppiumDriverLocalService.buildDefaultService();
		serv.start();
	     }
		return serv;
}
public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	//Emulator start 
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\com\\resources\\startEmu.bat");
		Thread.sleep(6000);
	}
                                                             //-2nd method-String device,
	public static AndroidDriver<AndroidElement> Capabilities(String app) throws IOException, InterruptedException
	{
	System.getProperty("user.dir");
FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\AppiumFramework\\global.properties");
//dont hardcode like this "/AppiumFramework/src/main/java/com/AppiumFramework/global.properties");
	Properties p=new Properties();
	p.load(fis);
	File f=new File("src");
	File fs=new File(f,	(String) p.get(app));
	DesiredCapabilities cap=new DesiredCapabilities();

	// 1st method using emulator from global properties
	String device=(String) p.get("dev");
	
	if (device.contains("Appalla"))
	{
		startEmulator();
	}
	

	//to start device from mvn commands 
	/*
	String device=System.getProperty("device");  
	if(device.contains("mycmd"))
	{
		startEmulator();
	}
	*/
	//to get device from maven cmd use mvn test -Ddevicename="mycmdemulator"

	
	//2nd method-emulator or real
	/*
	if(device.equals("emulator"))
	{
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,"VarunAppalla");
	}
	else if(device.equals("real"))
	{
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Device");
	}
	*/
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);
	cap.setCapability(MobileCapabilityType.APP,fs.getAbsolutePath());
	//Remove this for screenshot code to work- AndroidDriver<AndroidElement> before driver , still dint work
	driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	return driver;
	}
/*Code not working, tests failing as Null Pointer Exception
	public static void getScreenshot(String s) throws IOException
	{
	File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+s+".png"));
	}
	*/
	}
	