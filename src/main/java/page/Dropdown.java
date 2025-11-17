package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import base.CommonActions;
import pojo.TestData;

public class Dropdown extends CommonActions {

	public Dropdown(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='autosuggest']")
	WebElement selectCountry;

	@FindBy(xpath = "//ul[@id='ui-id-1'] //li[@class='ui-menu-item']")
	List<WebElement> listOfCountries;

	@FindBy(xpath = "//label[text()='Round Trip']")
	WebElement roundTrip;

	@FindBy(xpath = "//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")
	WebElement from;

	@FindBy(xpath = "//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR'] //li")
	List<WebElement> fromStateList;

	@FindBy(xpath = "//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //li")
	List<WebElement> tooStateList;

	By dprtDate = By.cssSelector("td[data-handler='selectDay']");

	@FindBy(css = "input[id='ctl00_mainContent_view_date2']")
	WebElement clickOnRtnDate;

	@FindBy(css = "div[class='ui-datepicker-group ui-datepicker-group-last'] a")
	List<WebElement> rtnDate;

	@FindBy(xpath = "//div[@id='divpaxinfo']")
	WebElement passengers;

	@FindBy(xpath = "//span[@id='hrefIncAdt']")
	WebElement adult;

	@FindBy(xpath = "//input[@id='btnclosepaxoption']")
	WebElement doneSelectingPassengers;

	@FindBy(xpath = "//select[@id='ctl00_mainContent_DropDownListCurrency']")
	WebElement currencyButton;

	@FindBy(css = "input[id='ctl00_mainContent_chk_StudentDiscount']")
	WebElement checkBoxStudent;

	@FindBy(css = "input[id='ctl00_mainContent_btn_FindFlights']")
	WebElement search;

	public void goToDropdown() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
	}

	public void dropdownFunctionality(TestData input) {
		goToDropdown();

		// Dynamic Dropdown
		selectCountry.sendKeys(input.getSelectCountry());

		waitForElementToAppear(listOfCountries);

		for (WebElement country : listOfCountries) {
			String countryName = country.getText();
			if (countryName.equalsIgnoreCase(input.getSelectCountry())) {
				country.click();
			}
		}

		// Radio Button
		roundTrip.click();

		from(input);
	}

	public void from(TestData input) {

		// From
		from.click();

		// Choose the city
		waitForElementToAppear(fromStateList);
		for (WebElement state : fromStateList) {
			String stateName = state.getText();
			if (stateName.equalsIgnoreCase(input.getFrom())) {
				state.click();
			}
		}

		too(input);
	}

	public void too(TestData input) {

		// Choose the city
		waitForElementToAppear(tooStateList);
		for (WebElement state : tooStateList) {
			String stateName = state.getText();
			if (stateName.equalsIgnoreCase(input.getTo())) {
				state.click();
			}
		}
		fromDate(input);
	}

	public void fromDate(TestData input) {
		waitForElementToAppear(dprtDate);

		List<WebElement> departDates = driver.findElements(dprtDate);

		for (int i = 0; i < departDates.size(); i++) {

			WebElement date = departDates.get(i);
			String dateName = date.getText();

			if (dateName.equalsIgnoreCase(input.getDepartDate())) {
				date.click();
				waitForstale(date);
				break;
			}

		}

		toDate(input);
	}

	public void toDate(TestData input) {

		isClickable(clickOnRtnDate);
		clickOnRtnDate.click();

		// Choose the From Date
		waitForElementToAppear(rtnDate);

		for (WebElement date : rtnDate) {
			String dateText = date.getText();
			if (dateText.equalsIgnoreCase(input.getReturnDate())) {
				date.click();
			}
		}
		travellers(input);
	}

	public void travellers(TestData input) {
		isClickable(passengers);
		passengers.click();

		isClickable(adult);
		adult.click();

		isClickable(doneSelectingPassengers);
		doneSelectingPassengers.click();

		chooseCurrency(input);
	}

	public void chooseCurrency(TestData input) {
		isClickable(currencyButton);
		currencyButton.click();

		Select selectCurrency = new Select(currencyButton);
		selectCurrency.selectByValue(input.getChooseCurrency());

		checkBoxStudent.click();
		search.click();
	}
}
