package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.awt.*;
import java.util.List;

public class BellPageFactory {
    private WebDriver chromeDriver;

    @FindBy(how = How.XPATH, using = ("//input [@id='edit-keys' and @type='search']"))
    WebElement searchField;

    @FindBy(how = How.XPATH, using = ("//input [@id='edit-submit' and @type='submit']"))
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = ("//ol[contains(@class,'search-result')]//p[contains(@class,'search-result__snippet')]"))
    List<WebElement> result;

    public BellPageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public void find(String word){
        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }

    public List<WebElement> getResult(){
        return result;
    }
}
