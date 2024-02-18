package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;

public class AmazonJoinUserPage extends AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/input[@id='ap_email']")
    protected SelenideElement userElement;

    @FindBy(how = How.XPATH, using = "//div/input[@id='ap_password']")
    protected SelenideElement passwordElement;

    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Enter your email or mobile phone number')]")
    protected SelenideElement emptyUser;

    private final SelenideElement nameInput = $("#ap_customer_name");
    private final SelenideElement mobileOrEmailInput = $("#ap_email");
    private final SelenideElement passwordInput = $("#ap_password");
    private final SelenideElement rePasswordInput = $("#ap_password_check");
    private final SelenideElement continueButton = $("#auth-continue");




    public AmazonJoinUserPage open() {
        super.open(getBaseUrl() + "/ap/register?showRememberMe=true&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&prevRID=MY2BMNW4TZSDB5SYM7NN&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=usflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        return this;
    }

    public AmazonJoinUserPage setName(String name) {
        nameInput.val(name);
        return this;
    }

    public AmazonJoinUserPage setMobileOrEmail(String email) {
        mobileOrEmailInput.val(email);
        return this;
    }

    public AmazonJoinUserPage setPassword(String password) {
        passwordInput.val(password);
        return this;
    }

    public AmazonJoinUserPage setRePassword(String password) {
        rePasswordInput.val(password);
        return this;
    }

    public AmazonBasePage clickContinueButton() {
        continueButton.click();
        return new AmazonBasePage();
    }
}
