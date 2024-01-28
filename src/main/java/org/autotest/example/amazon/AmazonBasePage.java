package org.autotest.example.amazon;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/input[@id='captchacharacters']")
    protected SelenideElement captcha;

    @FindBy(how = How.CLASS_NAME, using = "hm-icon-label")
    protected SelenideElement navigate;

    @FindBy(how = How.XPATH, using = "//a[text()='Sign in']")
    protected SelenideElement signIn;

    public AmazonJoinUserPage getJoinUserPage() {
        navigate.click();
        signIn.click();
        return page(AmazonJoinUserPage.class);
    }

    public void checkCaptcha() {
        if (this.captcha.is(visible)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
