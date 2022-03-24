package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Factory;
import utils.Logger;
import utils.WebUtils;
import org.openqa.selenium.support.ui.Select;

public class ItemMasterDataWin extends BasePage {

    Pagination pagination = new Pagination();


    private static final String BUTTON_OK = "//button[@class='c1']";
    private static final String FIND = "//a[@title='Найти']";
    private static final String FIND_ITEM_No = "//input[@class='c5']";
    private static final String BUTTON_FIND = "//div[@class='ui-dialog-inner']" + BUTTON_OK;
    private static final String BUTTON_UoM_GROUP = "//div[@class='ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable ui-resizable win-fm-message']" + BUTTON_OK;
    private static final String FIND_UoM_GROUP = "//input[@class='c6']";
    private static final String SELECT_UoM_GROUP = "//select[@class='c10002056']";
    private static final String SELECT_UoM_GROUP_LABEL = "//label[contains(@id, '10002056')]";
    private static final String UoM_GROUP_SYSTEM_ALERT = "//span[text()='Системное сообщение']";
    private static final String PURCHASING_DATA = "//div[@title='Закупки']";
    private static final String PURCHASING_UOM_CODE = "//input[@class='c1470002282']";
    private static final String SALES_DATA = "//div[@title='Продажи']";
    private static final String SALES_UOM_CODE = "//input[@class='c1470002273']";
    private static final String BUTTON_SUBMIT = "//div[@class='ui-dialog-content ui-widget-content fi1 f150']" + BUTTON_OK;
    private static final String BUTTON_CLOSE = "//div[@class='ui-dialog-titlebar ui-corner-all ui-widget-header ui-helper-clearfix']//button[@title='Close']";

    @FindBy(xpath = FIND)
    private WebElement find;
    @FindBy(xpath = FIND_ITEM_No)
    private WebElement findItemNo;
    @FindBy(xpath = BUTTON_FIND)
    private WebElement buttonFind;
    @FindBy(xpath = BUTTON_UoM_GROUP)
    private WebElement uom_Group;
    @FindBy(xpath = FIND_UoM_GROUP)
    private WebElement findUoM_Group;
    @FindBy(xpath = SELECT_UoM_GROUP)
    private WebElement selectUoM_Group;
    @FindBy(xpath = SELECT_UoM_GROUP_LABEL)
    private WebElement selectUoM_GroupLabel;
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
    @FindBy(xpath = BUTTON_SUBMIT)
    private WebElement buttonSubmit;
    @FindBy(xpath = BUTTON_CLOSE)
    private WebElement buttonClose;


    public ItemMasterDataWin() {
        super();
//        WebUtils.waitUntilElementVisible(homeButton);
    }

    public ItemMasterDataWin findItemNo() {
        find.click();
        return this;
    }

    public ItemMasterDataWin typeItemNoAndFind(String textFind) {
        findItemNo.sendKeys(textFind);
        findItemNo.sendKeys(Keys.ENTER);
        Logger.logInfo("ItemNo set = " + textFind + "; ItemNo read = " + findItemNo.getText());

        return this;
    }

    public ItemMasterDataWin optionUomGroup(String textGroup) {
        System.out.println(selectUoM_Group.getAttribute("value"));
        System.out.println(textGroup);
        if (selectUoM_Group.getText().equals(textGroup)) {
            Logger.logInfo("UoM Group read = UoM Group set");
        } else {
            Logger.logInfo("UoM Group read NO EQUALS UoM Group set");
            Select selectUomGroup = new Select(selectUoM_Group);
            selectUomGroup.selectByVisibleText(textGroup);
            WebUtils.pause(100);
            if (uom_Group.isDisplayed()) {
                uom_Group.sendKeys(Keys.ENTER);
            }
        }
        return this;
    }

    public ItemMasterDataWin optionUomGroupPurchasingData(String textGroup) {
        purchasingData.click();
        Logger.logInfo("Purchasing UoM Group read = " + purchasingUomCode.getText());
        purchasingUomCode.sendKeys(textGroup);
        purchasingUomCode.sendKeys(Keys.ENTER);
        Logger.logInfo("Purchasing UoM Group set = " + textGroup + "; Purchasing UoM Group read = " + purchasingUomCode.getText());
        if (purchasingUomCode.getText().equals(textGroup)) {
            Logger.logInfo("Purchasing UoM Group read = Purchasing UoM Group set");
        }

        return this;
    }

    public ItemMasterDataWin optionUomGroupSalesData(String textGroup) {
        salesData.click();
        Logger.logInfo("Sales UoM Group read = " + salesUomCode.getText());
        salesUomCode.sendKeys(textGroup);
        salesUomCode.sendKeys(Keys.ENTER);
        Logger.logInfo("Sales UoM Group set = " + textGroup + "; Sales UoM Group read = " + salesUomCode.getText());
        if (salesUomCode.getText().equals(textGroup)) {
            Logger.logInfo("Sales UoM Group read = Sales UoM Group set");
        }

        return this;
    }

    public MainPage gotoMainPage() {
//        driver.switchTo().window(MainWindow);

        return Factory.initPage(MainPage.class);
    }


}
