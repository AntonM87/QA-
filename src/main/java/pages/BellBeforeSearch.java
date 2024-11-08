package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BellBeforeSearch {

    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public BellBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = this.chromeDriver.findElement(By.xpath("//input [@id='edit-keys' and @type='search']"));
        this.searchButton = this.chromeDriver.findElement(By.xpath("//input [@id='edit-submit' and @type='submit']"));
    }

    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }
}
