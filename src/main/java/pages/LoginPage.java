
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Factory;
import utils.LogIn;
import utils.WebUtils;

public class LoginPage extends BasePage {

    private static final String BASE = "//section[@class='login-page has-animation']";
    private static final String LOGIN = BASE + "//input[@id='userEmail']";
    private static final String PASS = BASE + "//input[@id='userPass']";
    private static final String BUTTON = BASE + "//button[@id='se_userLogin']";
    @FindBy(xpath = LOGIN) //"//section[@class='login-page has-animation']//input[@id='userEmail']")
    private WebElement loginInput;
    @FindBy(xpath = PASS) //"//section[@class='login-page has-animation']//input[@id='userPass']")
    private WebElement passInput;
    @FindBy(xpath = BUTTON) //"//section[@class='login-page has-animation']//button[@id='se_userLogin']")
    private WebElement submitButton;
    @FindBy(xpath = BASE)
    private WebElement loginPage;

    public LoginPage() {
        super();
        WebUtils.waitUntilElementVisible(loginPage);
    }

    public LoginPage typeUserName(String userName) {
//       driver.findElement(By.xpath(XP_LOGIN)).sendKeys(userName);
        loginInput.sendKeys(userName);
        return this;
    }

    public LoginPage typePassword(String password) {
//        driver.findElement(By.xpath(XP_PASS)).sendKeys(password);
        passInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLogButton() {
//        driver.findElement(By.xpath(XP_BUTTON)).submit();
        clickElement(submitButton);
        return this;
    }

    public MyAccountPage userLogin() {
        typeUserName(LogIn.USER_NAME.getConst());
        typePassword(LogIn.PASSWORD.getConst());
        clickLogButton();
        return Factory.initPage(MyAccountPage.class);
    }
}
