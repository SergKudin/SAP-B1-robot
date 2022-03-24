package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.*;

public class MainPage  extends BasePage {

    private static final String LOGIN_PAGE = "//a[@id='topLoginLink']";
    private static final String SEARCH = "//input[@id='headerSearch']";
    private static final String BUTTON_SEARCH = "//input[@id='logon_sbo_btn']";
    private static final String STOCK = "//a[@id='tree-3072-0_anchor']";
    private static final String ITEM_MASTER_DATA = "//a[@id='tree-3073-0_anchor']";
    private static final String BUTTON_WINDOW_LOG_CLOSE = "//div[@aria-labelledby='ui-id-12']//button[@title='Close']";

    @FindBy(xpath = LOGIN_PAGE)
    private WebElement loginPage;
    @FindBy(xpath = SEARCH)
    private WebElement search;
    @FindBy(xpath = BUTTON_SEARCH)
    private WebElement buttonSearch;
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
//        clickElement(loginPage);
        return Factory.initPage(LoginPage.class);
    }

    public SearchPage search(String request) {
        Logger.logInfo("Search: " + request);
//        driver.findElement(By.xpath(xpSearch)).sendKeys(request);
//        driver.findElement(By.xpath(xpButtonSearch)).submit();
        search.sendKeys(request);
        buttonSearch.submit();
        return new SearchPage(); //return Factory.initPage(SearchPage.class);
    }

    public MainPage clickStocks() {
//        clickElement(submitButton);
        WebUtils.waitUntilElementVisible(stock);
        stock.click();
        return this;
    }
    public MainPage clickItemMasterData() {
//        clickElement(submitButton);
        WebUtils.waitUntilElementVisible(ItemMasterData);
        ItemMasterData.click();
        return this;
    }
    public MainPage closeWindowLog() {
        waitUntilPageIsLoaded();
        WebUtils.waitUntilElementVisible(windowLogClose);
        if (windowLogClose.isDisplayed()) {
            windowLogClose.click();
        }
        return this;
    }

    public ItemMasterDataWin openWindowItemMasterData() {
//        clickElement(homeButton);

        MainWindow = driver.getWindowHandle();
        closeWindowLog().clickStocks().clickItemMasterData();
        for (String windowHandle: driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        ItemMasterDataWindow = driver.getWindowHandle();
        return Factory.initPage(ItemMasterDataWin.class);
    }

    public MainPage gotoMainPage() {
//        clickElement(homeButton);
        return Factory.initPage(MainPage.class);
    }

}
