package dataprovider;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import testbase.BaseTest;
import pojo.TestData;

public class Dataprovider {
	 @DataProvider(name = "sendData")
	public Object[][] sendData() throws IOException {
		List<TestData> data = BaseTest.getJsonData(
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
