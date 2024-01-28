package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonJoinUserPage extends AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/input[@id='ap_email']")
    protected SelenideElement userElement;

    @FindBy(how = How.XPATH, using = "//div/input[@id='ap_password']")
    protected SelenideElement passwordElement;
}
