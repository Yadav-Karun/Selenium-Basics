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
	
	@FindBy(css = "iframe[class='demo-frame lazyloaded']")
	WebElement iframeDragAndDrop;

	public void mainActionMethod() {
		goToAction();
		doubleClick();
		hoverOver();
		openNewWindow();
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

	public void openNewWindow() {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.globalsqa.com/demo-site/draganddrop/");

		dragAndDrop();
	}
	public void dragAndDrop() {
		driver.switchTo().frame(iframeDragAndDrop);
	}
}
