package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import page.Action;
import page.Alerts;
import page.Dropdown;
import pojo.TestData;
import testbase.BaseTest;

public class BasicFunctionality extends BaseTest {

	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, priority = 3)
	public void dropdownTesting(TestData input) throws InterruptedException {
		
		Dropdown dropdown = testDropdown();
		dropdown.dropdownFunctionality(input);
	}
	
	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, priority = 2)
	public void alertTesting(TestData input) throws InterruptedException {
		
		Alerts alert = testAlerts();
		alert.promtAlert(input);
	}
	
	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, priority = 1)
	public void actionFunctionalityTesting(TestData input) throws InterruptedException {
		
		Action action = testAction();
		action.mainActionMethod();
	}

	@DataProvider
	public Object[][] sendData() throws IOException {
		List<TestData> data = getJsonData(
				System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
						+ "resources" + File.separator + "testdata" + File.separator + "InputData.json");

		int arraySize = data.size();

		Object[][] result = new Object[arraySize][1];
		for (int i = 0; i < arraySize; i++) {
			result[i][0] = data.get(i);
		}
		return result;
	}
}