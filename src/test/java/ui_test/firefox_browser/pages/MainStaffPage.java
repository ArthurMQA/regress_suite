package ui_test.firefox_browser.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainStaffPage {


    private WebDriver driver;
    private WebDriverWait wait;


    public MainStaffPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }


    @FindBy(xpath = "//*[text()='Users']")
    public WebElement usersDropdownMenu;

    @FindBy(xpath = "//*[text()='Players']")
    public WebElement usersDropdownOptionPlayers;

    @FindBy(xpath = "//*[@id='header-logo']")
    public WebElement headerLogo;

    @FindBy(xpath ="//p[text()='Tickets']")
    public WebElement dashboardTickets;

    @FindBy(xpath ="//p[text()='Withdrawal requests']")
    public WebElement dashboardWithdrawalRequests;

    @FindBy(xpath ="//p[text()='Players online / total']")
    public WebElement dashboardPlayersOnline;

    @FindBy(xpath ="//p[text()='Active game sessions']")
    public WebElement dashboardActiveSessions;


    public PlayersStaffPage moveToPlayersPage() {
        wait.until(ExpectedConditions.visibilityOf(usersDropdownMenu));
        usersDropdownMenu.click();
        wait.until(ExpectedConditions.visibilityOf(usersDropdownOptionPlayers));
        usersDropdownOptionPlayers.click();
        return new PlayersStaffPage(driver);
    }


}
