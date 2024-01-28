package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AmazonJoinUserPage extends AmazonBasePage {

    @FindBy(how = How.XPATH, using = "//div/input[@id='ap_email']")
    protected SelenideElement userElement;

    @FindBy(how = How.XPATH, using = "//div/input[@id='ap_password']")
    protected SelenideElement passwordElement;

    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Enter your email or mobile phone number')]")
    protected SelenideElement emptyUser;
}
