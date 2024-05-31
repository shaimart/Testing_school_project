package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalInfoPage {
    private WebDriver driver;
    //*[@id="edit-profile__first-name"]
    @FindBy(xpath = "//*[@id=\"edit-profile__first-name\"]")
    private WebElement changeNameButton;


    @FindBy(xpath = "//*[@id=\"edit-profile__last-name\"]")
    private WebElement changeSurnameButton;


    @FindBy(xpath = "//*[@id=\"main-content\"]/section/div/div/div[2]/div[1]/div/form/button")
    private WebElement saveChangesButton;


    public PersonalInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    changeNameButton
    private void clickChangeNameButton() {
        waitForElementVisibility(changeNameButton);
        changeNameButton.click();
    }

    //    changeSurnameButton
    private void clickChangeSurnameButton() {
        waitForElementVisibility(changeSurnameButton);
        changeSurnameButton.click();
    }

    //    saveChangesButton
    private void clickSaveChangesButton() {
        waitForElementVisibility(saveChangesButton);
        saveChangesButton.click();
    }
    public void fillPersonalInfo(String name, String surname){
        waitForElementVisibility(changeNameButton);
        waitForElementVisibility(changeSurnameButton);


        changeNameButton.sendKeys(name);
        changeSurnameButton.sendKeys(surname);


//        clickMakeOrderButton();
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

}
