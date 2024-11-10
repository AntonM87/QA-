package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAllExchangeRates {
    private String selectorExchange = "//selection[not(@style='display:none;')]//table[@class='card-rates-table cards']";
    private String selectorTableHeaders = ".//thead//th";
    private String selectorTableRows = ".//tbody/tr";
    private WebElement exchangeRates;
    private WebDriver chromeDriver;
    private WebDriverWait wait;
    private List<Map<String, String>> collectionExchangeRates = new ArrayList<>();

    public OpenAllExchangeRates(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 10);
    }

    public List<Map<String, String>> getCollectionExchangeRates() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorExchange)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorTableHeaders)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorTableRows)));

        exchangeRates = chromeDriver.findElement(By.xpath(selectorExchange));
        List<WebElement> tableHaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        List<WebElement> tableRows = exchangeRates.findElements(By.xpath(selectorTableRows));

        for (int i = 0; i < tableRows.size(); i++){
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < tableRows.size(); j++){
                collectRow.put(
                        tableHaders.get(j).getText(),
                        tableRows.get(j).findElement(By.xpath("\"./td[\"+(j+1)+\"]\"")).getText()
                );
            }
            collectionExchangeRates.add(collectRow);
        }
        return this.collectionExchangeRates;
    }
}
