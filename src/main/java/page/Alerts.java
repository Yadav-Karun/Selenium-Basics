package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.CommonActions;
import pojo.TestData;

public class Alerts extends CommonActions {

	public Alerts(WebDriver driver) {
		super(driver);
	}

	public void goToAlert() {
		driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");
	}

	@FindBy(xpath = "//button[@class='btn btn-primary' and text()='Alert']")
	WebElement SimpleAlert;

	@FindBy(xpath = "//button[@class='btn btn-primary' and @onclick='myMessage()']")
	WebElement confirmationAlert;
	
	@FindBy(xpath = "//button[@class='btn btn-primary' and @onclick='myPromp()']")
	WebElement promtAlert;
	

	public void promtAlert(TestData input) throws InterruptedException {
		goToAlert();
		
		isClickable(promtAlert);
		promtAlert.click();
		
		isAlertVisible();
		Alert acceptPromtAlert = driver.switchTo().alert();
		acceptPromtAlert.sendKeys(input.getPromptBox());
		acceptPromtAlert.accept();
		
		confirmationAlert();
	}

	public void confirmationAlert() throws InterruptedException {
		isClickable(confirmationAlert);
		confirmationAlert.click();
		
		isAlertVisible();
		driver.switchTo().alert().accept();

		simpleAlert();
	}

	public void simpleAlert() throws InterruptedException {
		isClickable(SimpleAlert);
		SimpleAlert.click();
		
		isAlertVisible();
		driver.switchTo().alert().accept();
	}
}