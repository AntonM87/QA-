package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenMainPage {
    private String buttonClose = "//span[text()='Закрыть']/ancestor::button";
    private String allClose = "//a[text()='Все курсы']";
    private WebDriverWait wait;
    private WebDriver chromeDriver;

    public OpenMainPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonClose)));
        this.chromeDriver.findElement(By.xpath(buttonClose)).click();
    }
    public void goExchangeRates(){
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(allClose))).click();
         chromeDriver.findElement(By.xpath(allClose)).click();
    }
}
