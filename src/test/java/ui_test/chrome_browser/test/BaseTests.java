package ui_test.chrome_browser.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_test.chrome_browser.pages.LoginStaffPage;
import ui_test.chrome_browser.pages.MainStaffPage;
import ui_test.chrome_browser.pages.PlayersStaffPage;

public abstract class BaseTests {

    // Config data.
    protected final static String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    protected static String staffUrl;
    protected static String staffLogin;
    protected static String staffPassword;

    // Page object variables.
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static LoginStaffPage loginPage;
    protected static MainStaffPage mainPage;
    protected static PlayersStaffPage playersPage;



}
