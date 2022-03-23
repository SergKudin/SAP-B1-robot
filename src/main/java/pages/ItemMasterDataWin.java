package pages;

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

    private static final String BUTTON_CLOSE = "//span[@id='ui-id-26']//button[@title='Close']";
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


    public ItemMasterDataWin() {
        super();
//        WebUtils.waitUntilElementVisible(homeButton);
    }

    public ItemMasterDataWin typeItemNoAndFind(String textFind) {
        findItemNo.sendKeys(textFind);
        findItemNo.sendKeys(Keys.ENTER);
        return this;
    }

    public ItemMasterDataWin optionUomGroup (String textGroup) {
        uom_Group.click();
        findUoM_Group.sendKeys(textGroup);
        findUoM_Group.sendKeys(Keys.ENTER);
        findUoM_Group.sendKeys(Keys.ENTER);

        return this;
    }

    public MainPage gotoMainPage() {
//        driver.switchTo().window(MainWindow);

        return Factory.initPage(MainPage.class);
    }


}
