package org.autotest.example.amazon;

import org.aeonbits.owner.ConfigCache;
import org.autotest.example.amazon.properties.GeneralConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class AmazonTest {

class AmazonTest extends BaseTest {

    private static String url;
    private static String userName;
    private static String password;

    @BeforeAll
    public static void read() {
        GeneralConfig generalConfig = ConfigCache.getOrCreate(GeneralConfig.class);
        url = generalConfig.url();
        userName = generalConfig.userName();
        password = System.getProperty("password");
    }


    @Test
    void signIn() {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(userName).pressEnter();
        amazonJoinUserPage.passwordElement.setValue(password).pressEnter();
        amazonJoinUserPage.checkCaptcha();
        amazonJoinUserPage.checkPuzzle();
        AmazonSignInRightPage amazonSignInRightPage = page(AmazonSignInRightPage.class);
        amazonSignInRightPage.signRight.shouldBe(visible);
        signOut(amazonSignInRightPage);

        new AmazonJoinUserPage().open()
                .setName(getUserName())
                .setMobileOrEmail("test@test.com")
                .setPassword("123456")
                .setRePassword("123456")
                .clickContinueButton()
                .shouldBeVisible();
    }

    @ParameterizedTest
    @EmptySource
    void emptyUserName(String value) {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(value).pressEnter();
        amazonJoinUserPage.emptyUser.shouldBe(visible);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1$//h4[contains(text(), 'Incorrect phone number')]",
            "0$//a[contains(text(), 'Change')]",
            "-3750000000$//h4[contains(text(), 'Incorrect phone number')]",
            "+3750000000$//h4[contains(text(), 'Incorrect phone number')]",
            "+$//h4[contains(text(), 'was a problem')]",
            "9 223 372 036 854 775 807$//h4[contains(text(), 'Incorrect phone number')]",
            "-9 223 372 036 854 775 808$//h4[contains(text(), 'Incorrect phone number')]",
            ".$//h4[contains(text(), 'was a problem')]"
    }, ignoreLeadingAndTrailingWhitespace = false,
            delimiter = '$')
    void wrongInput(String input, String xPath) {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(input).pressEnter();
        $(byXpath(xPath)).shouldBe(visible);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "~//div[contains(text(), 'Enter your password')]",
            " ~//span[contains(text(), 'password is incorrect')]",
            "!@#$$%^&*()_+|}{/*-+.<./~//span[contains(text(), 'password is incorrect')]"
    }, ignoreLeadingAndTrailingWhitespace = false,
            delimiter = '~')
    void wrongPassword(String inputPassword, String xPath) {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(userName).pressEnter();
        amazonJoinUserPage.passwordElement.setValue(inputPassword).pressEnter();
        amazonJoinUserPage.checkPuzzle();
        $(byXpath(xPath)).shouldBe(visible);
    }

    void signOut(AmazonSignInRightPage amazonSignInRightPage) {
        if (amazonSignInRightPage.signRight.is(visible)) {
            amazonSignInRightPage.all.click();
            amazonSignInRightPage.signOut.click();
            amazonSignInRightPage.signOutDone.shouldBe(visible);
        }
    }
}
