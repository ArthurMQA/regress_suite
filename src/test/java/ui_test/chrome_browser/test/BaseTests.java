package ui_test.chrome_browser.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_test.chrome_browser.pages.LoginStaffPage;
import ui_test.chrome_browser.pages.MainStaffPage;
import ui_test.chrome_browser.pages.PlayersStaffPage;

public abstract class BaseTests {

    // Config data.
    protected final static String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    protected final String STAFF_SITE_URL = "http://service.com/test/login";

    // Test admin user credentials
    protected final String USER_LOGIN = "user1";
    protected final String USER_PASSWORD = "newpassword123";

    // Page object and driver variables
    protected static WebDriver driver;
    protected static LoginStaffPage loginPage;
    protected static MainStaffPage mainPage;
    protected static PlayersStaffPage playersPage;
    protected static WebDriverWait wait;


}
