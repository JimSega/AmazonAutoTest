package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class AmazonTest {

    @BeforeEach
    void openAmazon() {
        open("https://www.amazon.com/");
        SelenideElement captcha = $(By.xpath("//div/input[@id='captchacharacters']"));
        if (captcha.is(visible)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @AfterEach
    void signOut() {
        if ($(byXpath("//div/span[contains(text(), 'Hello, first')]")).is(visible)) {
            $(byXpath("//a/span[text()='All']")).shouldBe(visible).click();
            $(byXpath("//a[text()='Sign Out']")).shouldBe(visible).click();
            assertTrue($(byXpath("//h1[contains(text(), 'Sign')]")).is(visible));
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sourceCorrect.csv")
    void signIn(String user, String password) {
        $(By.className("hm-icon-label")).shouldBe(visible).click();
        $(byXpath("//a[text()='Sign in']")).shouldBe(visible).click();
        $(byXpath("//div/input[@id='ap_email']")).shouldBe(visible).setValue(user).pressEnter();
        $(byXpath("//div/input[@id='ap_password']")).shouldBe(visible).setValue(password).pressEnter();
        if ($(byXpath("//div/input")).is(visible)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertTrue($(byXpath("//div/span[contains(text(), 'Hello, first')]")).shouldBe(visible).is(visible));
    }

    @Test
    void emptyUserName() {
        $(By.className("hm-icon-label")).shouldBe(visible).click();
        $(byXpath("//a[text()='Sign in']")).shouldBe(visible).click();
        $(byXpath("//div/input[@id='ap_email']")).shouldBe(visible).setValue("").pressEnter();
        $(byXpath("//div[contains(text(), 'Enter your email or mobile phone number')]")).shouldBe(visible);
        assertTrue($(byXpath("//div[contains(text(), 'Enter your email or mobile phone number')]")).is(visible));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wrongInput.csv")
    void wrongInput(String input, String xPath) {
        $(By.className("hm-icon-label")).shouldBe(visible).click();
        $(byXpath("//a[text()='Sign in']")).shouldBe(visible).click();
        $(byXpath("//div/input[@id='ap_email']")).shouldBe(visible).setValue(input).pressEnter();
        assertTrue($(byXpath(xPath)).shouldBe(visible).is(visible));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wrongPassword.csv")
    void wrongPassword(String name, String inputPassword, String xPath) {
        $(By.className("hm-icon-label")).shouldBe(visible).click();
        $(byXpath("//a[text()='Sign in']")).shouldBe(visible).click();
        $(byXpath("//div/input[@id='ap_email']")).shouldBe(visible).setValue(name).pressEnter();
        $(byXpath("//div/input[@id='ap_password']")).shouldBe(visible).setValue(inputPassword).pressEnter();
        if($(byXpath("//span[contains(text(), 'Solve this puzzle')]")).is(visible)) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertTrue($(byXpath(xPath)).shouldBe(visible).is(visible));
    }
}