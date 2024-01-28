package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class AmazonNavigatePage extends AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/span[contains(text(), 'Hello, first')]")
    protected SelenideElement signRight;

    @FindBy(how = How.XPATH, using = "//a/span[text()='All']")
    protected SelenideElement all;

    @FindBy(how = How.XPATH, using = "//a[text()='Sign Out']")
    protected SelenideElement signOut;

    /*public void signOut() {

        if (signRight.is(visible)) {
            all.click();
            signOut.click();
            //assertTrue($(byXpath("//h1[contains(text(), 'Sign')]")).is(visible));
        }
    }*/
}
