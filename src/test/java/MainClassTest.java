import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ItemMasterDataWin;
import pages.MainPage;
import utils.*;

public class MainClassTest extends BaseTest {


    @Test(dataProvider = "javaCode")
    public void Test1(String n1) {
        Logger.logInfo(n1);
        readFileXLSX = Pages.initPage(ItemMasterDataWin.class).readDataFile();
        currentRow = Pages.initPage(ItemMasterDataWin.class).readStatusFile();
        Pages.initPage(MainPage.class)
                .openWindowItemMasterData()
                .dataSet(readFileXLSX, currentRow);
        Logger.logInfo("End " + n1);
    }

    @Test
    public void debug() {
        Logger.logInfo("debug start");
        driver.get(Const.SITE.getConst());
        readFileXLSX = Pages.initPage(ItemMasterDataWin.class).readDataFile();
        currentRow = Pages.initPage(ItemMasterDataWin.class).readStatusFile();
//        WebUtils.pause(180000);
        Pages.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin()
                .openWindowItemMasterData()
                .dataSet(readFileXLSX, currentRow)
        ;
    }

    @DataProvider(name = "javaCode")
    public Object[][] createData() {
        return new Object[][]{
                {"Test1"},
                {"Test2"},
//                {"Test3"},
//                {"Test4"},
//                {"Test5"},
//                {"Test6"},
//                {"Test7"},
//                {"Test8"},
//                {"Test9"},
//                {"Test10"},
//                {"Test11"},
                {"Test12"}
        };
    }

}

