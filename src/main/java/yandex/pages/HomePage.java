package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    // Заголовок для раскрывающегося блока
    private final By accordionHeader = By.className("accordion__heading");

    // Текст в раскрывающемся блоке
    private final By accordionItem = By.xpath(".//div[@class='accordion__panel']/p");

    // Кнопка заказа в шапке
    private final By orderButtonHeader = By.xpath(".//div[starts-with(@class, 'Header_Nav')]//button[starts-with(@class, 'Button_Button')]");

    // Кнопка заказа в теле
    private final By orderButtonBody = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");

    // Кнопка "Принять куки"
    private final By cookieAcceptButton = By.id("rcc-confirm-button");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void waitForLoadItem(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOf(driver.findElements(accordionItem).get(index)));
    }


    public void clickOnCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }


    public String getAccordionItemText(int index) {
        return driver.findElements(accordionItem).get(index).getText();
    }


    public void clickOnAccordionHeader(int index) {
        driver.findElements(accordionHeader).get(index).click();
    }


    public boolean isAccordionItemDisplayed(int index) {
        return driver.findElements(accordionItem).get(index).isDisplayed();
    }


    public void clickOnOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }


    public void clickOnOrderButtonBody() {
        driver.findElement(orderButtonBody).click();
    }

    public void scrollPageToAccordionList() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
            driver.findElement(accordionHeader));
    }

    public void waitForHomePageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver ->
            driver.findElement(orderButtonHeader).getText() != null);
    }
}
