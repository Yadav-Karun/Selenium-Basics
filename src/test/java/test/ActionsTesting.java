package test;

import org.testng.annotations.Test;

import dataprovider.Dataprovider;
import page.Action;
import pojo.TestData;
import testbase.BaseTest;

public class ActionsTesting extends BaseTest {

	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, dataProviderClass = Dataprovider.class, enabled = true)
	public void actionFunctionalityTesting(TestData input) throws InterruptedException {

		Action action = testAction();
		action.mainActionMethod();
	}

}
