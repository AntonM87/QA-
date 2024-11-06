package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GoogleAfterSearch {
    private WebDriver chromeDriver;
    private WebDriverWait wait;

    public WebDriver getChromeDriver(){
        return chromeDriver;
    }
    public GoogleAfterSearch(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
    }
    public GoogleAfterSearch(WebDriver chromeDriver, String queeryString){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 10);
        chromeDriver.get("https://www.google.ru/search?q="+queeryString);
    }
    public void checkingTitleByText(String link){
        wait.until(visibilityOfElementLocated(By.xpath("//*[text()='Результаты поиска']/following-sibling::*/*//div[@data-sokoban-container or @class='g']//h3[contains(.,'"+link+"')]")));
        Assertions.assertFalse( chromeDriver.findElements(By.xpath("//*[text()='Результаты поиска']/following-sibling::*/*//div[@data-sokoban-container or @class='g']//h3[contains(.,'"+link+"')]")).size()==0,
                "Не найдено тайтла с текстом: '"+link);
    }
}
