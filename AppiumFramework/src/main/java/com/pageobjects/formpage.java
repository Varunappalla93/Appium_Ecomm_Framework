package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class formpage {
	public formpage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
public WebElement namefield;
//private WebElement namefield;

@AndroidFindBy(uiAutomator ="new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"))")
public WebElement country;

//2nd method using method, we should call method name instead of variables if we declare variable as private
public WebElement getnamefield()
{
	System.out.println("name field is varun appalla");
	return namefield;
}
}