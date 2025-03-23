import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import yandex.pages.HomePage;
import yandex.pages.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class OrderPageTest {
    WebDriver driver;

    private static final String browser = System.getProperty("browser", "firefox");

    private final String name, surname, address, metroStation, phoneNumber, date, term, color, comment;

    public OrderPageTest(String name, String surname, String address, String metroStation, String phoneNumber, String date, String term, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Оформление заказа")
    public static Object[][] setDataForOrder() {
        return new Object[][] {
            {"Петр", "Павел", "Москва, ул. Строителей, 17", "Черкизовская", "11111111111", "22.03.2025", "сутки", "чёрный жемчуг", "Тест"},
            {"Павел ", "Петр", "Москва, пер. Кривоколенный, 5", "Сокольники", "11111111111", "23.03.2025", "двое суток", "серая безысходность", "Тест 2"},
        };
    }

    @Before
    public void setUp() {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
    }

    @Test
    public void createAnOrderWithHeaderButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        homePage.waitForHomePageLoaded();
        homePage.clickOnCookieAcceptButton();
        homePage.clickOnOrderButtonHeader();

        orderPage.waitForLoadForm();

        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setMetroStation(metroStation);
        orderPage.setPhoneNumber(phoneNumber);

        orderPage.clickNextButton();

        orderPage.setDate(date);
        orderPage.setTerm(term);
        orderPage.setColor(color);
        orderPage.setComment(comment);

        orderPage.createAnOrder();

        assertThat(
            "Problem with creating a new order",
            orderPage.getNewOrderSuccessMessage(),
            containsString("Заказ оформлен"));

    }

    @Test
    public void createAnOrderWithBodyButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        homePage.waitForHomePageLoaded();
        homePage.clickOnCookieAcceptButton();
        homePage.clickOnOrderButtonBody();

        orderPage.waitForLoadForm();

        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setMetroStation(metroStation);
        orderPage.setPhoneNumber(phoneNumber);

        orderPage.clickNextButton();

        orderPage.setDate(date);
        orderPage.setTerm(term);
        orderPage.setColor(color);
        orderPage.setComment(comment);

        orderPage.createAnOrder();

        assertThat(
            "Problem with creating a new order",
            orderPage.getNewOrderSuccessMessage(),
            containsString("Заказ оформлен"));

    }



    @After
    public void teardown() {
        driver.quit();
    }
}
