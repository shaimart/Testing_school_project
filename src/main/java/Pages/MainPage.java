package Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[@id=\"didomi-notice-agree-button\"]")
    private WebElement acceptCookiesButton;


    @FindBy(xpath = "//*[@id=\"app\"]/header/div[1]/nav[1]/div[4]/div[4]/a")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"input-email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"lookup-btn\"]")
    private WebElement dalsiButton;

    @FindBy(xpath = "//*[@id=\"input-password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"signin-button\"]")
    private WebElement prihlasitButton;


    @FindBy(xpath = "//*[@id=\"search-bar\"]/div/form/div/input")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id=\"search-bar\"]/div/form/div/div/button[2]/span")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"app\"]/header/div[1]/nav[1]/div[4]/div[4]/a")
    private WebElement invalidValidation;

    @FindBy(xpath = "//*[@id=\"app\"]/header/div[1]/nav[1]/div[4]/div[4]/a")
    private WebElement myProfileButton;

    @FindBy(xpath = "//*[@id=\"app\"]/header/div[1]/nav[1]/div[4]/div[4]/a")
    private WebElement goToMyProfileButton;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div/aside/nav/section[3]/ul/li[1]/div/span/a")
    private WebElement goToMyPersonalInfoButton;


    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div/div/div/div[3]/div[3]/span/a")
    private WebElement goToMyNameSurmaneMobileSexButton;


    @FindBy(xpath = "//*[@id=\"main-content\"]/section/div/div/div[2]/div[1]/button")
    private WebElement changeNameSurmaneButton;

    @FindBy(xpath = "//*[@id=\"app\"]/header/div[1]/nav[1]/div[4]/div[5]/a")
    private WebElement MyCartButton;
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickAcceptCookiesButton() {
        waitForElementVisibility(acceptCookiesButton);
        acceptCookiesButton.click();
    }

    public void loginWithCsv(String email, String password) {
        clickLoginButton();
        enterEmail(email);

        if (!isElementVisible(dalsiButton)) {

            return;
        }

        clickDalsiButton();
        enterPassword(password);
        clickPrihlasitButton();
    }

    public void clickLoginButton() {
        waitForElementVisibility(loginButton);
        loginButton.click();
    }

    public void clickMyProfileButton() {
        waitForElementVisibility(myProfileButton);
        myProfileButton.click();
    }

    public void clickGoToMyPersonalInfoButton() {
        waitForElementVisibility(goToMyPersonalInfoButton);
        goToMyPersonalInfoButton.click();
    }

    public void clickGoToMyNameSurmaneMobileSexButton() {
        waitForElementVisibility(goToMyNameSurmaneMobileSexButton);
        goToMyNameSurmaneMobileSexButton.click();
    }


    public void clickChangeNameSurmaneButton() {
        waitForElementVisibility(changeNameSurmaneButton);
        changeNameSurmaneButton.click();
    }


    private void enterEmail(String email) {
        waitForElementVisibility(emailField);
        emailField.sendKeys(email);
    }

    private void clickDalsiButton() {
        waitForElementToBeClickable(dalsiButton);
        dalsiButton.click();
    }

    private void enterPassword(String password) {
        waitForElementVisibility(passwordField);
        passwordField.sendKeys(password);
    }

    private void clickPrihlasitButton() {
        waitForElementToBeClickable(prihlasitButton);
        prihlasitButton.click();
    }

    public boolean isLoginFailed() {
        try {
            return invalidValidation.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDalsiButtonVisible() {
        return isElementVisible(dalsiButton);
    }

    public void searchProduct(String productName) {
        waitForElementVisibility(searchField);
        searchField.sendKeys(productName);
    }

    public void clickSearchButton() {
        waitForElementToBeClickable(searchButton);
        searchButton.click();
    }

    private boolean isElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

