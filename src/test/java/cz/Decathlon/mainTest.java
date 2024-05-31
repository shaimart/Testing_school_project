package cz.Decathlon;

import Pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class mainTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\2semestr\\TS\\chromedriver-win32\\chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        driver.get("https://www.decathlon.cz/");
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
// okk
    @Test
    public void testAcceptCookies() {
        mainPage.clickAcceptCookiesButton();
    }
// okk
    @ParameterizedTest
    @CsvFileSource(resources = "/emailLginData.csv", numLinesToSkip = 1)
    public void testSuccessfulLogin(String email, String password) {
        mainPage.clickAcceptCookiesButton();
        mainPage.loginWithCsv(email, password);
        Assertions.assertFalse(mainPage.isLoginFailed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidLoginData.csv", numLinesToSkip = 1)
    public void testFailedLogin(String email, String password) {
        mainPage.clickAcceptCookiesButton();
        mainPage.loginWithCsv(email, password);
        Assertions.assertTrue(mainPage.isLoginFailed() || !mainPage.isDalsiButtonVisible());
    }
// okk
    @Test
    public void testSearchProduct() {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct("Pánské turistické kalhoty MH500");
        mainPage.clickSearchButton();

    }
}
