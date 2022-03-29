
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.*;

import java.io.IOException;

public class LoginPage extends BasePage {

    //    private static final String BASE = "//section[@class='login-page has-animation']";
    private static final String COMPANY = "//input[@id='sbo_custom_company']";
    private static final String LOGIN = "//input[@id='sbo_user']";
    private static final String PASS = "//input[@id='sbo_password']";
    private static final String BUTTON = "//input[@id='logon_sbo_btn']";
    private static final String LOG_ON_BY_DOMAIN = "//input[@id='logon_by_domain']";
    @FindBy(xpath = COMPANY)
    private WebElement loginCompany;
    @FindBy(xpath = LOGIN)
    private WebElement loginInput;
    @FindBy(xpath = PASS)
    private WebElement passInput;
    @FindBy(xpath = BUTTON)
    private WebElement submitButton;
    @FindBy(xpath = LOG_ON_BY_DOMAIN)
    private WebElement LogOnByDomain;

    public LoginPage() {
        super();
        WebUtils.waitUntilElementVisible(submitButton);
    }

    public LoginPage typeCompany(String company) {
        loginCompany.sendKeys(company);
        return this;
    }

    public LoginPage typeUserName(String userName) {
        loginInput.sendKeys(userName);
        return this;
    }

    public LoginPage typePassword(String password) {
        passInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLogButton() {
//        clickElement(submitButton);
        submitButton.click();
        if (Modals.isOpened()) {
            Modals.clickButton("Да");
        }
        if (Modals.isOpened()) {
            Modals.clickButton("OK");
        }
        return this;
    }

    public LoginPage turnOffLogOnByDomain() {
        if (LogOnByDomain.isSelected()) {
            LogOnByDomain.click();
        }
        return this;
    }

    public MainPage userLogin() {
        ReadFile readFile = new ReadFile();
        readFile.ReadFileToList();
        turnOffLogOnByDomain();
        typeCompany(readFile.getData(0));
        typeUserName(readFile.getData(1));
        typePassword(readFile.getData(2));
        clickLogButton();
        return Pages.initPage(MainPage.class);
    }

}
