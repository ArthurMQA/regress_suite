package ui_test.firefox_browser.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginStaffPage {


    private WebDriver driver;
    private WebDriverWait wait;


    public LoginStaffPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }


    @FindBy(xpath = "//*[@placeholder='Login']")
    public WebElement loginInput;

    @FindBy(xpath = "//*[@placeholder='Password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@value='Sign in']")
    public WebElement signInButton;

    @FindBy(xpath = "//*[@class='ng-binding']")
    public WebElement heading;


    public LoginStaffPage typeLogin(String login){
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.sendKeys(login);
        return this;
    }

    public LoginStaffPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
        return this;
    }

    public MainStaffPage signIn(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.sendKeys(login);
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
        return new MainStaffPage(driver);
    }

    public String  getHeadingText() {
        return heading.getText();
    }


}
