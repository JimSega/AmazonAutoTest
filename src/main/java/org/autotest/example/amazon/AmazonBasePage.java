package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/input[@id='captchacharacters']")
    protected SelenideElement captcha;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Solve this puzzle')]")
    protected SelenideElement puzzle;

    @FindBy(how = How.CLASS_NAME, using = "hm-icon-label")
    protected SelenideElement navigate;

    @FindBy(how = How.XPATH, using = "//a[text()='Sign in']")
    protected SelenideElement signIn;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Sign')]")
    protected SelenideElement signOutDone;

    public AmazonJoinUserPage getJoinUserPage() {
        navigate.click();
        signIn.click();
        return page(AmazonJoinUserPage.class);
    }

    public void checkCaptcha() {
        if (captcha.is(visible)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void checkPuzzle() {
        if (puzzle.is(visible)) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
