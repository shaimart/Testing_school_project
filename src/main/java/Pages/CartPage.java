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

public class CartPage {
    private WebDriver driver;

    @FindBy(css = "//*[@id=\"app\"]/main/div/div[2]/article/section[1]/div/section/article/div[2]/div[1]/a/span")
    private List<WebElement> cartProducts;

    @FindBy(xpath = "//*[@id=\"app\"]/header/nav/a")
    private WebElement goToHomePage;

    @FindBy(xpath = "//*[@id=\"app\"]/header/nav/section/div[2]")
    private WebElement goToUcet;

    @FindBy(xpath = "//*[@id=\"app\"]/header/nav/div/a")
    private WebElement continueOrderButton;

    @FindBy(xpath = "//*[@id=\"app\"]/header/div[1]/nav[1]/div[4]/div[5]/a/div/span")
    private WebElement myCart;
    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div[2]/article/section[1]/div/section/article/div[2]/div[4]/div/div/button[2]/span")
    private WebElement addOneMore;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div[2]/article/section[1]/div/section/article/div[2]/div[4]/div/div/button[1]")
    private WebElement deleteOne;
    @FindBy(xpath = "//*[@id=\"app\"]/header/nav/div/a")
    private WebElement continueOrderButton2;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div[2]/article/section[1]/div/section/p[1]")
    private WebElement emptyCart;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div[2]/article/section[2]/div[1]/div/span/div[6]/div/button")
    private WebElement choiceDelivery;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }
    public boolean verifyCartProducts(String expectedProductName) {
        for (WebElement result : cartProducts) {
            if (result.getText().contains(expectedProductName)) {
                return true;
            }
        }
        return false;
    }
    public void clickContinueOrderButton(){
        waitForElementVisibility(continueOrderButton);
        continueOrderButton.click();
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickDeleteOneButton() {
        waitForElementVisibility(deleteOne);
        deleteOne.click();
    }

    public void clickGoToMyCart() {
        waitForElementVisibility(myCart);
        myCart.click();
    }
    public void clickAddOneMoreButton() {
        waitForElementVisibility(addOneMore);
        addOneMore.click();
    }

    public boolean isCartEmpty() {
        try {
            waitForElementVisibility(emptyCart);
            return emptyCart.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
