package org.autotest.example.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class AmazonBasePage {
    protected SelenideElement captcha = $(By.xpath("//div/input[@id='captchacharacters']"));

    public void checkCaptcha() {
        if (captcha.is(visible)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void signOut() {
        if ($(byXpath("//div/span[contains(text(), 'Hello, first')]")).is(visible)) {
            $(byXpath("//a/span[text()='All']")).shouldBe(visible).click();
            $(byXpath("//a[text()='Sign Out']")).shouldBe(visible).click();
            assertTrue($(byXpath("//h1[contains(text(), 'Sign')]")).is(visible));
        }
    }

    public void openAmazon() {
        open("https://www.amazon.com/");
    }
}
