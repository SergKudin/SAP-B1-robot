import core.WebDriverManager;
import org.testng.annotations.Test;
import pages.ItemMasterDataWin;
import pages.MainPage;
import utils.*;

public class MainClassTest extends BaseTest {

    @Test
    public void Test1() {
        Logger.logInfo("Start Test1");

        MainPage mainPage = Pages.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin()
                .openWindowItemMasterData()
                .logOut()
//                .typeItemNoAndFind("1FK2102-0AG00-0MA0")
//                .optionUomGroup("штука")
//                .optionUomGroupPurchasingData("шт.")
//                .optionUomGroupSalesData("шт.")
//                .findItemNo()

                .gotoMainPage();


    }

    @Test
    public void debug() {
        Logger.logInfo("debug start");
//        do {
//            try {
                readFileXLSX = Pages.initPage(ItemMasterDataWin.class).readDataFile();
                currentRow = Pages.initPage(ItemMasterDataWin.class).readStatusFile();
                Pages.initPage(MainPage.class)
                        .goToLoginPage()
                        .userLogin()
                        .openWindowItemMasterData()
                        .dataSet(readFileXLSX, currentRow);
//            } catch (Exception e) {
//                e.printStackTrace();
//                //               driver.close();
//                //              driver = WebDriverManager.getDriver();
//                //             driver.get(Const.SITE.getConst());
//            }
//        } while (currentRow == readFileXLSX.sizeRows());
    }
}
