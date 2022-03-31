import core.WebDriverManager;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Const;
import utils.Logger;
import utils.ReadFileXLSX;
import utils.WebUtils;

import java.util.ArrayList;

public class BaseTest {

    protected WebDriver driver;
    public ReadFileXLSX readFileXLSX;
    public Integer currentRow;

    @BeforeClass
    public void BeforeClassMethod() {
        Logger.logInfo("Start BeforeClass");
        driver = WebDriverManager.getDriver();
    }

    @BeforeMethod
    public void setUp() {
        Logger.logInfo("Start BeforeMethod");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(Const.SITE.getConst());
    }


    @AfterMethod
    public void siteClose() {
        Logger.logInfo("Start AfterMethod");
        WebUtils.pause(3000);
        driver.close();
    }

    @AfterClass
    public void tearDown() {
        Logger.logInfo("Start AfterClass");
    }

}
