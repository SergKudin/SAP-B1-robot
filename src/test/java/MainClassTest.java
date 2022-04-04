import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ItemMasterDataWin;
import pages.MainPage;
import utils.*;

public class MainClassTest extends BaseTest {


    @Test(dataProvider = "javaCode")
    public void Test1(String n1) {
        Logger.logInfo(n1);
        currentRow = Pages.initPage(ItemMasterDataWin.class).readStatusFile();
        Pages.initPage(MainPage.class)
                .openWindowItemMasterData()
                .dataSet(readFileXLSX, currentRow);                ;
        Logger.logInfo("End " + n1);
    }

    @Test
    public void debug() {
        Logger.logInfo("debug start");
        currentRow = Pages.initPage(ItemMasterDataWin.class).readStatusFile();
        Logger.logInfo("Data set starts after row " + (currentRow+1));
        Pages.initPage(MainPage.class)
                .openWindowItemMasterData()
                .dataSet(readFileXLSX, currentRow);                ;
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

