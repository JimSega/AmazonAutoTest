package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AmazonSignInRightPage extends AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/span[contains(text(), 'Hello, first')]")
    protected SelenideElement signRight;

    @FindBy(how = How.XPATH, using = "//a/span[text()='All']")
    protected SelenideElement all;

    @FindBy(how = How.XPATH, using = "//a[text()='Sign Out']")
    protected SelenideElement signOut;
}
