package ui_test.chrome_browser.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlayersStaffPage {


    private WebDriver driver;
    private WebDriverWait wait;


    public PlayersStaffPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }


    @FindBy(xpath = "//a[text()='Registration date']")
    public WebElement thRegistrationDate;

    @FindBy(xpath = "//*[@id='pageSizePlayers']")
    public WebElement selectorPlayersPageSizeOption;

    @FindBy(xpath = "//*[@id='pageSizePlayers']/option[1]")
    public WebElement selectPlayersPageSizeOption20;

    @FindBy(xpath = "//*[@id='pageSizePlayers']/option[2]")
    public WebElement selectPlayersPageSizeOption50;

    @FindBy(xpath = "//*[@id='pageSizePlayers']/option[3]")
    public WebElement selectPlayersPageSizeOption100;

    @FindBy(xpath = "//*[@id='pageSizePlayers']/option[4]")
    public WebElement selectPlayersPageSizeOption200;

    @FindBy(xpath = "//*[@id='pageSizePlayers']/option[5]")
    public WebElement selectPlayersPageSizeOption500;

    @FindBy(xpath = "//*[@class='table table-hover table-striped table-bordered table-condensed']")
    public WebElement playersTable;

    @FindBy(xpath = "//*[@id='payment-system-transaction-grid']/table/tbody/tr[1]/td[10]")
    public WebElement firstRow;

    @FindBy(xpath = "//*[@id='payment-system-transaction-grid']/table/tbody/tr[250]/td[10]")
    public WebElement middleRow;

    @FindBy(xpath = "//*[@id='payment-system-transaction-grid']/table/tbody/tr[500]/td[10]")
    public WebElement lastRow;


    public PlayersStaffPage choosePlayersPageSize(int numberOfRecords) {
        wait.until(ExpectedConditions.visibilityOf(selectorPlayersPageSizeOption));
        selectorPlayersPageSizeOption.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//option[text()='" + numberOfRecords + "']"))));
        driver.findElement(By.xpath("//option[text()='" + numberOfRecords + "']")).click();
        return this;
    }


}
