package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SearchResultsPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"search-suggestions-banner\"]")
    private WebElement invalidSearching;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div[2]/section[1]/div/div/div[1]/article/details/div/div/div/div/a[1]")
    private WebElement filterKalhoty;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div[2]/section[1]/div/div/div[1]/article/details/div/div/div/div/a[1]")
    private WebElement filterTuristika;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div[2]/section[2]/div/div[1]/div[3]/div[1]/div/a/img")
    private WebElement choiceKalhoty;

    @FindBy(xpath = "//*[@id=\"sku-selector\"]")
    private WebElement choiceSize;

    @FindBy(xpath = "//*[@id=\"sku-0\"]")
    private WebElement sizeS;

    @FindBy(xpath = "//*[@id=\"app\"]/main/article/div[2]/section/div[7]/button")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id=\"popin-cross-sell\"]/div/div/section[1]/div/span/button/span[1]")
    private WebElement endOrderButton;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div[2]/section[2]/div")
    private List<WebElement> searchResults;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-300)");
    }
    public void clickChoiceButton() {
        waitForElementVisibility(choiceKalhoty);
        choiceKalhoty.click();
    }

    public void clickChoiceSizeButton() {
        waitForElementVisibility(choiceSize);
        choiceSize.click();
    }

    public void clickSizeSButton() {
        waitForElementVisibility(sizeS);
        sizeS.click();
    }
    public void clickaddButton() {
        waitForElementVisibility(addButton);
        addButton.click();
    }


    public boolean isSearchingFailed() {
        try {
            return invalidSearching.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickEndOrderButton() {
        waitForElementVisibility(endOrderButton);
        endOrderButton.click();
    }
    public boolean verifySearchResults(String expectedProductName) {
        for (WebElement result : searchResults) {
            if (result.getText().contains(expectedProductName)) {
                return true;
            }
        }
        return false;
    }
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
