package test;

import org.testng.annotations.Test;

import dataprovider.Dataprovider;
import page.Dropdown;
import pojo.TestData;
import testbase.BaseTest;

public class DropdownTesting extends BaseTest {

	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, dataProviderClass = Dataprovider.class)
	public void dropdownTesting(TestData input) throws InterruptedException {

		Dropdown dropdown = testDropdown();
		dropdown.dropdownFunctionality(input);
	}

}
