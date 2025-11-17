package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected Actions action;

	public CommonActions(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void scrollToTop() {
		js.executeScript("window.scrollTo(0, 0)");
	}

	public void scrollToBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickWithJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void disablePointerEvents(WebElement element) {
		js.executeScript("arguments[0].style.pointerEvents='none';", element);
	}

	public void removeDesigne(WebElement element) {
		js.executeScript("arguments[0].style.display='none';", element);
	}

	public void isClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void isAlertVisible() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForCartProductsToLoad(By locator) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	public void waitForstale(WebElement locator) {
		wait.until(ExpectedConditions.stalenessOf(locator));
	}
	
	public void waitForUrl(String keyword) {
	    wait.until(ExpectedConditions.urlContains(keyword));
	}

	public boolean isVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void isInvisible(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementToAppear(List<WebElement> listOfCountries) {
		wait.until(ExpectedConditions.visibilityOfAllElements(listOfCountries));
	}

	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisappear(WebElement webElement) {
		wait.until(ExpectedConditions.invisibilityOf(webElement));
	}

	public void waitForElementToDisappear(By findBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void waitForAllElement(By findBy) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	public void customWait(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}
}
