package ui_test.chrome_browser.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_test.chrome_browser.pages.LoginStaffPage;
import ui_test.chrome_browser.pages.MainStaffPage;
import ui_test.chrome_browser.pages.PlayersStaffPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests extends BaseTests {

    @BeforeClass
    public static void setUp() {

        System.out.println("Start tests");

        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {

            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            staffUrl = prop.getProperty("staff_site_url");
            staffLogin = prop.getProperty("my_staff_username");
            staffPassword = prop.getProperty("my_staff_password");

        } catch (IOException e) {
            System.out.println("Error: file " + PATH_TO_PROPERTIES + " not found");
            e.printStackTrace();
        }

    }


    @Before
    public void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        loginPage = PageFactory.initElements(driver, LoginStaffPage.class);
        mainPage = PageFactory.initElements(driver, MainStaffPage.class);
        playersPage = PageFactory.initElements(driver, PlayersStaffPage.class);

    }

    //@Ignore
    @Test
    public void test1_authorization() {

        driver.get(staffUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Casino", loginPage.getHeadingText());
        mainPage = loginPage.signIn(staffLogin, staffPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(true, mainPage.headerLogo.isDisplayed());
        Assert.assertEquals(true, mainPage.dashboardTickets.isDisplayed());
        Assert.assertEquals(true, mainPage.dashboardPlayersOnline.isDisplayed());
        Assert.assertEquals(true, mainPage.dashboardActiveSessions.isDisplayed());
        Assert.assertEquals(true, mainPage.dashboardWithdrawalRequests.isDisplayed());

        System.out.println("1. _Chrome_ User logged in successfully. Staff panel loaded successfully");

    }

    //@Ignore
    @Test
    public void test2_playerTableSorting() {

        driver.get(staffUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Casino", loginPage.getHeadingText());
        mainPage = loginPage.signIn(staffLogin, staffPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        playersPage = mainPage.moveToPlayersPage();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        playersPage.choosePlayersPageSize(500);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(true, playersPage.playersTable.isDisplayed());

        System.out.println("2. _Chrome_ Players table loaded successfully and displayed");

    }

    //@Ignore
    @Test
    public void test3_playersTableAscendingSort() throws InterruptedException {

        driver.get(staffUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Casino", loginPage.getHeadingText());
        mainPage = loginPage.signIn(staffLogin, staffPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        playersPage = mainPage.moveToPlayersPage();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(true, playersPage.playersTable.isDisplayed());

        // Check if ascending sort works correctly

        playersPage.choosePlayersPageSize(500);
        TimeUnit.SECONDS.sleep(15);
        playersPage.thRegistrationDate.click();
        TimeUnit.SECONDS.sleep(15);

        String firstDate = playersPage.firstRow.getText();
        String middleDate = playersPage.middleRow.getText();
        String lastDate = playersPage.lastRow.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate date1 = LocalDate.parse(firstDate, formatter);
        LocalDate date2 = LocalDate.parse(middleDate, formatter);
        LocalDate date3 = LocalDate.parse(lastDate, formatter);

        Assert.assertEquals(true, date1.isBefore(date2) && date2.isBefore(date3));


        System.out.println("3. _Chrome_ Ascending sort works correctly");

    }

    //@Ignore
    @Test
    public void test4_playersTableDescendingSort() throws InterruptedException {

        driver.get(staffUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Casino", loginPage.getHeadingText());
        mainPage = loginPage.signIn(staffLogin, staffPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        playersPage = mainPage.moveToPlayersPage();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(true, playersPage.playersTable.isDisplayed());

        // Check if descending sort works correctly

        playersPage.choosePlayersPageSize(500);
        TimeUnit.SECONDS.sleep(15);
        playersPage.thRegistrationDate.click();
        TimeUnit.SECONDS.sleep(15);
        playersPage.thRegistrationDate.click();
        TimeUnit.SECONDS.sleep(15);

        String firstDate = playersPage.firstRow.getText();
        String middleDate = playersPage.middleRow.getText();
        String lastDate = playersPage.lastRow.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate date1 = LocalDate.parse(firstDate, formatter);
        LocalDate date2 = LocalDate.parse(middleDate, formatter);
        LocalDate date3 = LocalDate.parse(lastDate, formatter);

        Assert.assertEquals(true, date1.isAfter(date2) && date2.isAfter(date3));


        System.out.println("4. _Chrome_ Descending sort works correctly");

    }


    @After
    public void finish() {

        driver.quit();

    }


    @AfterClass
    public static void tearDown() {

        System.out.println("Finish tests");

    }
}