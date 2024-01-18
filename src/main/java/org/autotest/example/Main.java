package org.autotest.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class Main {
    public static void main(String[] args) {
        /*System.setProperty("webdriver.chrome.driver", "/home/ubuntu/java/AmazonTestAuto/chromedriver-linux64/chromedriver");
        WebDriver webDriver = new ChromeDriver(new ChromeDriverService.Builder().build());
        webDriver.get("https://www.amazon.com/");
        System.out.println(webDriver.getTitle());
        webDriver.close();*/
        open("https://www.amazon.com/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        $(By.className("hm-icon-label")).shouldBe(visible).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        $(byXpath("//a[text()='Sign in']")).shouldBe(visible).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}