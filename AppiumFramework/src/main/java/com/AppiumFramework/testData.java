package com.AppiumFramework;

import org.testng.annotations.DataProvider;

public class testData {

	@DataProvider(name="InputData")
	public Object[][] getDataforedit()
	{
		//2 sets of data
		Object[][] obj=new Object[][]
				{
			{"hello"},{"^%$^"}
				};
			return obj;
	}
}
