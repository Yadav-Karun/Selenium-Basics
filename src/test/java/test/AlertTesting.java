package test;

import org.testng.annotations.Test;

import dataprovider.Dataprovider;
import page.Alerts;
import pojo.TestData;
import testbase.BaseTest;

public class AlertTesting extends BaseTest {

	@Test(dataProvider = "sendData", retryAnalyzer = retry.RetryAnalyzer.class, dataProviderClass = Dataprovider.class)
	public void alertTesting(TestData input) throws InterruptedException {

		Alerts alert = testAlerts();
		alert.promtAlert(input);
	}

}
