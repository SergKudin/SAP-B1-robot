import org.testng.annotations.Test;
import pages.LoginPage;
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
                .typeItemNoAndFind("1FK2102-0AG00-0MA0")
                .optionUomGroup("штука")
                .optionUomGroupPurchasingData("шт.")
                .optionUomGroupSalesData("шт.")
//                .findItemNo()

                .gotoMainPage();


    }

    @Test
    public void debug() {
        Logger.logInfo("debug start");
        Pages.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin()
                .openWindowItemMasterData()
                .dataSet();
//                .typeItemNoAndFind("1FK2102-0AG00-0MA0")
//                .openTab("Закупки");
//        TextInputs.byLabel("Название ЕИ закупок")
//                        .setValue("шт.");
//        System.out.println();


    }
}
