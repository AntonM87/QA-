package ru.bellintegrator;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected WebDriver chomeDriver;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY,"none");

        chomeDriver = new ChromeDriver();
        chomeDriver.manage().window().maximize();
        chomeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        chomeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after() {
        chomeDriver.quit();
    }
}