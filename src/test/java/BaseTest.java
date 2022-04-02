import core.WebDriverManager;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;
import utils.*;

import java.util.ArrayList;

public class BaseTest {

    protected WebDriver driver;
    public ReadFileXLSX readFileXLSX;
    public Integer currentRow;

    @BeforeClass
    public void BeforeClassMethod() {
        Logger.logInfo("Start BeforeClass");
        Logger.logInfo("Screenshots is Dir Empty:" + FileUtils.cleanScreenshots());
        driver = WebDriverManager.getDriver();
        driver.get(Const.SITE.getConst());
        Pages.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin();
    }

    @BeforeMethod
    public void setUp() {
        Logger.logInfo("Start BeforeMethod");
    }


    @AfterMethod
    public void siteClose() {
        Logger.logInfo("Start AfterMethod");
        Logger.log("save screenshot",false,true);
        driver.get("https://www.google.com/");
        Buttons.jsAlertOk(driver);
        WebUtils.pause(1000);
        driver.get(Const.SITE.getConst());
        Buttons.jsAlertOk(driver);
    }

    @AfterClass
    public void tearDown() {
        Logger.logInfo("Start AfterClass");
        driver.quit();
    }

}
