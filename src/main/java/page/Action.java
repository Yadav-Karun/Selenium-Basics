package page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;

import base.CommonActions;

public class Action extends CommonActions {
	public Action(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement moveToSearchSection;

	@FindBy(xpath = "//span[normalize-space()='Account & Lists']")
	WebElement hover;

	By botCheckSubmitBY = By.xpath("//button[@type='submit']");

	@FindBy(xpath = "//button[@type='submit']")
	WebElement botCheckSubmit;

	@FindBy(xpath = "//iframe[contains(@src, 'photo-manager.html')]")
	WebElement iframeDragAndDrop;

	@FindBy(xpath = "//ul[contains(@id,'gallery')]//h5[text()='High Tatras']/parent::li")
	WebElement iframeDrag;

	@FindBy(xpath = "//div[contains(@id, 'trash')]")
	WebElement iframeDrop;

	@FindBy(xpath = "//div[contains(@class, 'elementor elementor-1306 elementor-location-footer')]")
	WebElement footer;

	@FindBy(xpath = "//ul[contains(@id, 'menu-1-32c3b995')]//a")
	List<WebElement> footerLinks;

	public void mainActionMethod() {
		goToAction();
		doubleClick();
		hoverOver();
		openAllTab();
	}

	public void goToAction() {
		driver.get("https://www.amazon.in/");

		if (isVisible(botCheckSubmitBY)) {
			botCheckSubmit.click();
			waitForUrl("amazon.in");
		}
	}

	public void doubleClick() {
		action.moveToElement(moveToSearchSection).click().keyDown(Keys.LEFT_SHIFT).keyUp(Keys.LEFT_SHIFT).sendKeys("PS")
				.sendKeys("5").click().doubleClick().build().perform();
	}

	public void hoverOver() {
		action.moveToElement(hover).contextClick().build().perform();
	}
	
	public void openAllTab() {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.path2usa.com/travel-companion/");

		scrollIntoView(footer);

		for (WebElement footerLink : footerLinks) {
			isClickable(footerLink);

			String url = footerLink.getAttribute("href");
			if (url == null || url.isEmpty()) {
				continue;
			}

			String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			footerLink.sendKeys(newTab);
		}
		openNewWindow();
	}

	public void openNewWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://www.globalsqa.com/demo-site/draganddrop/");

		dragAndDrop();
	}

	public void dragAndDrop() {
		driver.switchTo().frame(iframeDragAndDrop);
		action.clickAndHold(iframeDrag).moveToElement(iframeDrop).release().build().perform();

		driver.switchTo().defaultContent();
		trackWindows();
	}

	public void trackWindows() {
		Set<String> handles = driver.getWindowHandles();
		List<String> windows = new ArrayList<>(handles);
		int numberOfTabs = windows.size();
		for (int i = 0; i < numberOfTabs; i++) {
			System.out.println("Index: " + i);
		}
		switchToWindow(0);
	}
	
	public void switchToWindow(int index) {
	    List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(windows.get(index));
	}
}
