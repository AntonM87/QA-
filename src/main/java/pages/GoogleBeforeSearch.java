package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleBeforeSearch {
    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;
    protected WebDriverWait wait;

    public GoogleBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@title='Поиск']")));
        this.searchField = this.chromeDriver.findElement(By.xpath("//textarea[@title='Поиск']"));
        this.searchButton = this.chromeDriver.findElement(By.xpath("//div[not (@jsname)]/center/*[@value='Поиск в Google']"));
    }

    public void find(String text) {
        searchField.sendKeys(text);
        chromeDriver.findElement(By.xpath("//img[@alt='Google']"));
        searchButton.click();
    }
}
