package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.Dataprovider;
import page.Action;
import page.Alerts;
import page.Dropdown;
import pojo.TestData;
import testbase.BaseTest;

public class BasicFunctionality extends BaseTest {

	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, priority = 3, dataProviderClass = Dataprovider.class)
	public void dropdownTesting(TestData input) throws InterruptedException {
		
		Dropdown dropdown = testDropdown();
		dropdown.dropdownFunctionality(input);
	}
	
	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, priority = 2, dataProviderClass = Dataprovider.class)
	public void alertTesting(TestData input) throws InterruptedException {
		
		Alerts alert = testAlerts();
		alert.promtAlert(input);
	}
	
	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, priority = 1, dataProviderClass = Dataprovider.class)
	public void actionFunctionalityTesting(TestData input) throws InterruptedException {
		
		Action action = testAction();
		action.mainActionMethod();
	}

}