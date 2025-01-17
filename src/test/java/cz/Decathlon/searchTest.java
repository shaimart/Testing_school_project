package cz.Decathlon;

import Pages.MainPage;
import Pages.SearchResultsPage;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class searchTest {
    private WebDriver driver;
    private MainPage mainPage;
    private SearchResultsPage searchPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\2semestr\\TS\\chromedriver-win32\\chromedriver.exe");
        Dimension newDimension = new Dimension(1920, 1080);
        driver = new ChromeDriver();
        driver.manage().window().setSize(newDimension);
        mainPage = new MainPage(driver);
        searchPage = new SearchResultsPage(driver);
        driver.get("https://www.decathlon.cz/");
    }
//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
    //ok
    @Test
    public void testInvalidSearch() {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct("dfgsdfg");
        mainPage.clickSearchButton();
        Assertions.assertTrue(searchPage.isSearchingFailed());
    }

    //ok
    @ParameterizedTest
    @CsvFileSource(resources = "/searchData.csv", numLinesToSkip = 1)
    public void searchTest(String productName) {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct(productName);
        mainPage.clickSearchButton();
        searchPage.scrollDown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchPage.clickChoiceButton();
        searchPage.scrollDown();
        searchPage.clickChoiceSizeButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchPage.clickSizeSButton();
        searchPage.clickaddButton();

    }

    //ok
    @ParameterizedTest
    @CsvFileSource(resources = "/invalidSearchData", numLinesToSkip = 1)
    public void searchInvalidTest(String productName) {
        mainPage.clickAcceptCookiesButton();
        mainPage.searchProduct(productName);
        mainPage.clickSearchButton();
        searchPage.scrollDown();
    }
}


