package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.*;

public class MainPage extends BasePage {

    private static final String STOCK = "//a[@id='tree-3072-0_anchor']";
    private static final String ITEM_MASTER_DATA = "//a[@id='tree-3073-0_anchor']";
    private static final String BUTTON_WINDOW_LOG_CLOSE = "//div[@aria-labelledby='ui-id-12']//button[@title='Close']";

    @FindBy(xpath = STOCK)
    WebElement stock;
    @FindBy(xpath = ITEM_MASTER_DATA)
    WebElement ItemMasterData;
    @FindBy(xpath = BUTTON_WINDOW_LOG_CLOSE)
    WebElement windowLogClose;

    public MainPage() {
        super();
//        WebUtils.waitUntilElementVisible(homeButton);
    }

    public LoginPage goToLoginPage() {

        if (driver.findElement(By.xpath("//div[@id='main-message']")).isDisplayed()) {
            driver.findElement(By.xpath("//button[@id='details-button']")).click();
            driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
        }
        if (driver.findElement(By.xpath("//div[@id='main-message']")).isDisplayed()) {
            driver.findElement(By.xpath("//button[@id='details-button']")).click();
            driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
        }
        return Pages.initPage(LoginPage.class);
    }

    public MainPage clickStocks() {
        WebUtils.waitUntilElementVisible(stock);
        stock.click();
        return this;
    }

    public MainPage clickItemMasterData() {
        WebUtils.waitUntilElementVisible(ItemMasterData);
        ItemMasterData.click();
        return this;
    }

    public MainPage siteQuite() {
        try {
            if (stock.isDisplayed()) {
                WebUtils.waitUntilElementVisible(stock);
                stock.sendKeys(Keys.CONTROL + "q");
                Modals.clickButtons("Да");
                Logger.logInfo("Site quite");
            }
        } catch (Exception e) {
            Logger.logInfo("Error site quite");
        }
        return this;
    }

    public MainPage closeWindowLog() {
        waitUntilPageIsLoaded();
        try {
            WebUtils.waitUntilElementVisible(windowLogClose);
            if (windowLogClose.isDisplayed()) {
                windowLogClose.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public ItemMasterDataWin openWindowItemMasterData() {
        MainWindow = driver.getWindowHandle();
        WebElement sysWin = driver.findElement(By.xpath("//button[@title = 'OK']"));
        if (sysWin.isDisplayed()) {
            sysWin.sendKeys(Keys.ENTER);
        }
//        try {
//            Modals.clickButtons("Да");
//        } catch (Exception e) {}
        closeWindowLog().
                clickStocks();
        return Pages.initPage(ItemMasterDataWin.class);
    }

    public MainPage gotoMainPage() {
//        clickElement(homeButton);
        return Pages.initPage(MainPage.class);
    }

}
