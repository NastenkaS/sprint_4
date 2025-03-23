package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    // Форма заказа 
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");

    // Поле ввода имени 
    private final By nameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Имя')]");

    // Поле ввода фамилии 
    private final By surnameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Фамилия')]");

    // Поле ввода адреса 
    private final By addressInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Адрес')]");

    // Поле ввода метро
    private final By metroInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Станция метро')]");

    // Поле ввода телефона 
    private final By phoneInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Телефон')]");

    // Поле ввода комментария 
    private final By commentInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Комментарий')]");

    // Поиск станций метро
    private final By metroList = By.className("select-search__select");

    // Список станций метро 
    private final By metroListItems = By.xpath(".//div[@class='select-search__select']//div[starts-with(@class,'Order_Text')]");

    // Кнопка "Далее" 
    private final By nextButton = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button");

    // Дата 
    private final By dateSelected = By.className("react-datepicker__day--selected");

    // Поле ввода даты 
    private final By dateInput = By.xpath(".//div[starts-with(@class, 'react-datepicker__input-container')]//input");

    // Поиск списков аренды
    private final By termDropdownRoot = By.className("Dropdown-root");

    // Список опций аренды 
    private final By termDropdownOption = By.className("Dropdown-option");

    // Список выбора цветов 
    private final By colorLabels = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label");

    // Кнопка "Заказать" 
    private final By orderButton = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[not(contains(@class,'Button_Inverted'))]");

    // Кнопка "Да" для подтверждения заказа
    private final By acceptOrderButton = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[not(contains(@class,'Button_Inverted'))]");

    // Текст об успешном оформлении заказа
    private final By newOrderSuccessMessage = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//div[(starts-with(@class,'Order_ModalHeader'))]");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOf(driver.findElement(orderForm)));
    }


    private void waitForElementLoad(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOf(driver.findElement(element)));

    }


    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }


    public void setMetroStation(String metroStation) {
        driver.findElement(metroInput).sendKeys(metroStation);
        waitForElementLoad(metroList);
        chooseElementFromDropdown(metroListItems, metroStation);
    }


    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneInput).sendKeys(phoneNumber);
    }


    public void setDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
        waitForElementLoad(dateSelected);
        clickDateSelected();
    }


    public void setTerm(String term) {
        clickTermDropdown();
        chooseElementFromDropdown(termDropdownOption, term);
    }


    public void setColor(String color) {
        chooseElementFromDropdown(colorLabels, color);
    }


    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }


    public void createAnOrder() {
        clickOnOrderButton();
        waitForElementLoad(acceptOrderButton);
        clickOnAcceptOrderButton();
    }


    public String getNewOrderSuccessMessage() {
        return driver.findElement(newOrderSuccessMessage).getText();
    }


    private void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }


    private void clickOnAcceptOrderButton() {
        driver.findElement(acceptOrderButton).click();
    }


    private void chooseElementFromDropdown(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = driver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }


    private void clickDateSelected() {
        driver.findElement(dateSelected).click();
    }


    private void clickTermDropdown() {
        driver.findElement(termDropdownRoot).click();
    }
}
