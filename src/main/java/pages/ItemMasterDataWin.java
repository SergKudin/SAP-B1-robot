package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Factory;
import utils.WebUtils;

public class ItemMasterDataWin extends BasePage {

    Pagination pagination = new Pagination();

    private static final String FIND_ITEM_No = "//input[@class='c5']";
    private static final String BUTTON_FIND = "//div[@class='ui-dialog-inner']//button[@class='c1']";
    private static final String BUTTON_UoM_GROUP = "//button[@class='c10002059 icon-choose_icon rsrc-choose_icon']";
    private static final String FIND_UoM_GROUP = "//input[@class='c6']";
    private static final String UoM_GROUP_SYSTEM_ALERT = "//span[text()='Системное сообщение']";
    private static final String PURCHASING_DATA = "//div[@title='Закупки']";
    private static final String PURCHASING_UOM_CODE = "//input[@class='c1470002282']";
    private static final String SALES_DATA = "//div[@title='Продажи']";
    private static final String SALES_UOM_CODE = "//input[@class='c1470002273']";
//    private static final String PURCHASING_UOM_CODE_SUBMIT = "//button[@class='c1470000005']";

    private static final String MENU_FILE = "//*[@id='m_512']";
//    private static final String FILE = "//*[@id=\"m_512\"]"; //aria-activedescendant="mb_526"

    @FindBy(xpath = FIND_ITEM_No)
    private WebElement findItemNo;
    @FindBy(xpath = BUTTON_FIND)
    private WebElement buttonFind;
    @FindBy(xpath = BUTTON_UoM_GROUP)
    private WebElement uom_Group;
    @FindBy(xpath = FIND_UoM_GROUP)
    private WebElement findUoM_Group;
    @FindBy(xpath = UoM_GROUP_SYSTEM_ALERT)
    private WebElement systemAlertUoM_Group;
    @FindBy(xpath = PURCHASING_DATA)
    private WebElement purchasingData;
    @FindBy(xpath = PURCHASING_UOM_CODE)
    private WebElement purchasingUomCode;
    @FindBy(xpath = SALES_DATA)
    private WebElement salesData;
    @FindBy(xpath = SALES_UOM_CODE)
    private WebElement salesUomCode;


    public ItemMasterDataWin() {
        super();
//        WebUtils.waitUntilElementVisible(homeButton);
    }

    public ItemMasterDataWin typeItemNoAndFind(String textFind) {
        findItemNo.sendKeys(textFind);
        findItemNo.sendKeys(Keys.ENTER);
        return this;
    }

    public ItemMasterDataWin optionUomGroup(String textGroup) {
        uom_Group.click();
        findUoM_Group.sendKeys(textGroup);
        findUoM_Group.sendKeys(Keys.ENTER);
        WebUtils.waitUntilElementVisible(systemAlertUoM_Group);
        findUoM_Group.sendKeys(Keys.ENTER);
        return this;
    }

    public ItemMasterDataWin optionUomGroupPurchasingData (String textGroup) {
        purchasingData.click();
        purchasingUomCode.sendKeys(textGroup);
        purchasingUomCode.sendKeys(Keys.ENTER);
        return this;
    }

    public ItemMasterDataWin optionUomGroupSalesData (String textGrop) {
        salesData.click();
        salesUomCode.sendKeys(textGrop);
        salesUomCode.sendKeys(Keys.ENTER);
        return this;
    }

    public MainPage gotoMainPage() {
//        driver.switchTo().window(MainWindow);

        return Factory.initPage(MainPage.class);
    }


}
