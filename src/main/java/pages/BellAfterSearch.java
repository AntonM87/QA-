package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BellAfterSearch extends BellBeforeSearch {
    private List<WebElement> result;
    private WebDriverWait wait;

    public BellAfterSearch(WebDriver chomeDriver) {
        super(chomeDriver);
        wait = new WebDriverWait(chomeDriver, 120);
    }

    public List<WebElement> getResult() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol[contains(@class,'search-result')]//p[contains(@class,'snippet')]")));
        result = chromeDriver.findElements(By.xpath("//ol[contains(@class,'search-result')]//p[contains(@class,'search-result__snippet')]"));
        return result;
    }
}
