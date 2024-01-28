package org.autotest.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AmazonBasePage {
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
}
