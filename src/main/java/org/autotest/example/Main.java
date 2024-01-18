package org.autotest.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty("webdriver.chrome.driver", "/home/ubuntu/java/AmazonTestAuto/chrome-linux64/chrome");
        WebDriver webDriver = new ChromeDriver(new ChromeDriverService.Builder().usingPort(65535).withTimeout(Duration.ofSeconds(10)).build());
        webDriver.get("https://www.amazon.com/");
        System.out.println(webDriver.getTitle());
    }
}