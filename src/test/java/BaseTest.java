import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Const;
import utils.Logger;
import utils.ReadFileXLSX;

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
        driver.get(Const.SITE.getConst());
    }


    @AfterMethod
    public void tearDown() {
        Logger.logInfo("Start AfterMethod");
        driver.quit();
    }
}
