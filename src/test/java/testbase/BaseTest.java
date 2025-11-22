package testbase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import page.Action;
import page.Alerts;
import page.Dropdown;
import pojo.TestData;

public class BaseTest {
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	private static final ObjectMapper mapper = new ObjectMapper();

	@BeforeMethod
	public void SetupBrowser() throws IOException {
		Properties property = new Properties();

		InputStream browserPath = getClass().getClassLoader().getResourceAsStream("globaldata/GlobalData.properties");
		if (browserPath == null) {
			throw new FileNotFoundException("GlobalData.properties not found in globaldata folder");
		}
		property.load(browserPath);
		String browserName = property.getProperty("Browser", "firefox").toLowerCase();
		boolean incognito = Boolean.parseBoolean(property.getProperty("Incognito", "false"));

		WebDriver driver;

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions optionsChrome = new ChromeOptions();
			if (incognito) {
				optionsChrome.addArguments("--incognito");
				optionsChrome.addArguments("--start-maximized");
			}
			driver = new ChromeDriver(optionsChrome);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions optionsFirefox = new FirefoxOptions();
			if (incognito) {
				optionsFirefox.addArguments("--private");
				optionsFirefox.addArguments("--start-maximized");
			}
			driver = new FirefoxDriver(optionsFirefox);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions optionsEdge = new EdgeOptions();
			if (incognito) {
				optionsEdge.addArguments("--incognito");
				optionsEdge.addArguments("--start-maximized");
			}
			driver = new EdgeDriver(optionsEdge);
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
		threadLocalDriver.set(driver);
		driver.manage().deleteAllCookies();
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public Dropdown testDropdown() {
		Dropdown goToDropdown = new Dropdown(getDriver());
		return goToDropdown;
	}

	public Alerts testAlerts() {
		Alerts alert = new Alerts(getDriver());
		return alert;
	}

	public Action testAction() {
		Action action = new Action(getDriver());
		return action;
	}

	public static List<TestData> getJsonData(String filePath) throws IOException {
		String jsonData = Files.readString(Paths.get(filePath));

		List<TestData> data = mapper.readValue(jsonData, new TypeReference<List<TestData>>() {
		});
		return data;
	}

	public static String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) getDriver();

		File source = screenshot.getScreenshotAs(OutputType.FILE);

		File screenshotFolder = new File(
				System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + testCaseName);

		screenshotFolder.mkdirs();

		File screenshotFile = new File(screenshotFolder, testCaseName + ".png");

		// 5. Copy screenshot from temporary location to final file
		FileUtils.copyFile(source, screenshotFile);

		// 6. Return full path so it can be attached in reports
		return screenshotFile.getAbsolutePath();
	}

//	@AfterMethod(alwaysRun = true)
//	public void endSetUp() {
//		if (getDriver() != null) {
//			getDriver().quit();
//			threadLocalDriver.remove();
//		}
//	}
}