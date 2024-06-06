package cz.Decathlon;

import Pages.CartPage;
import Pages.MainPage;
import Pages.PersonalInfoPage;
import Pages.SearchResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class personalInfoTest {
    private WebDriver driver;
    private MainPage mainPage;
    private SearchResultsPage searchPage;
    private CartPage cartPage;
    private PersonalInfoPage personalInfoPage;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\2semestr\\TS\\chromedriver-win32\\chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchResultsPage(driver);
        cartPage = new CartPage(driver);
        personalInfoPage = new PersonalInfoPage(driver);
        mainPage.loginWithCsv("openplan@seznam.cz", "LalaLa12");
        driver.get("https://www.decathlon.cz/");
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    // not ok
    @ParameterizedTest
    @CsvFileSource(resources = "/persInfo.csv", numLinesToSkip = 1)
    public void makeOrderTest(String name, String surname) {
        mainPage.clickAcceptCookiesButton();
        mainPage.clickMyProfileButton();
        mainPage.clickGoToMyPersonalInfoButton();
        mainPage.clickGoToMyNameSurmaneMobileSexButton();
        mainPage.clickChangeNameSurmaneButton();
        personalInfoPage.fillPersonalInfo(name, surname);
    }
}


