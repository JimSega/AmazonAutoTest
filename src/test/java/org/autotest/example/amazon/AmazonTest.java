package org.autotest.example.amazon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

class AmazonTest {
    private static final String url = "https://www.amazon.com/";


    @ParameterizedTest
    @CsvFileSource(resources = "/sourceCorrect.csv")
    void signIn(String user, String password) {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(user).pressEnter();
        amazonJoinUserPage.passwordElement.setValue(password).pressEnter();
        amazonJoinUserPage.checkCaptcha();
        amazonJoinUserPage.checkPuzzle();
        AmazonSignInRightPage amazonSignInRightPage = page(AmazonSignInRightPage.class);
        amazonSignInRightPage.signRight.shouldBe(visible);
        signOut(amazonSignInRightPage);
    }

    @Test
    void emptyUserName() {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue("").pressEnter();
        amazonJoinUserPage.emptyUser.shouldBe(visible);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wrongInput.csv")
    void wrongInput(String input, String xPath) {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(input).pressEnter();
        $(byXpath(xPath)).shouldBe(visible);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wrongPassword.csv")
    void wrongPassword(String name, String inputPassword, String xPath) {
        AmazonBasePage basePage = open(url, AmazonBasePage.class);
        basePage.checkCaptcha();
        AmazonJoinUserPage amazonJoinUserPage = basePage.getJoinUserPage();
        amazonJoinUserPage.userElement.setValue(name).pressEnter();
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
