package ru.bellintegrator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.BellAfterSearch;
import pages.BellBeforeSearch;
import pages.BellPageFactory;

import java.util.List;

public class Tests extends  BaseTests {

    @Test
    public void test() {
        chomeDriver.get("https://bellintegrator.ru/search/node");
        String title = chomeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell integrator"), "Тайтл " + title + "На сайте не содержит bellintegrator");
        //input [@id='edit-keys' and @type='search']
        //input [@id='edit-submit' and @type='submit']
        //ol[contains(@class, 'search-result')]//p[contains(@class,'snippet')]
    }

    @Test
    public void testFind(){
        chomeDriver.get("https://bellintegrator.ru/search/node");
        WebElement searchField = chomeDriver.findElement(By.xpath("//input [@id='edit-keys' and @type='search']"));
        WebElement searchButton = chomeDriver.findElement(By.xpath("//input [@id='edit-submit' and @type='submit']"));

        searchField.click();
        searchField.sendKeys("Филенков");
        searchButton.click();

        List<WebElement> resultSearch = chomeDriver.findElements(By.xpath("//ol[contains(@class, 'search-result')]//p[contains(@class,'snippet')]"));
        System.out.println(resultSearch.size());
        System.out.println(resultSearch);
        resultSearch.forEach(list -> System.out.println(list.getText()));
        Assertions.assertTrue(resultSearch.stream().anyMatch(list-> list.getText().contains("руководитель")));
    }

    @ParameterizedTest
    @CsvSource({"Минаев,руководитель","Филенков,руководитель","Результаты проектов,плохо"})
    public void testPO(String word, String result){
        chomeDriver.get("https://bellintegrator.ru/search/node");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chomeDriver);
        bellBeforeSearch.find(word);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chomeDriver);
        Assertions.assertTrue(bellAfterSearch.getResult().stream().anyMatch(x->x.getText().contains(result)),
                "Статьи содержимое" + result + "не найдены для поискового слова " + word);
    }

    @ParameterizedTest
    @CsvSource({"Филенков,руководитель"})
    public void testPageFactory(String word, String result){
        chomeDriver.get("https://bellintegrator.ru/search/node");
        BellPageFactory bellPageFactory = PageFactory.initElements(chomeDriver,BellPageFactory.class);
        bellPageFactory.find(word);
        Assertions.assertTrue(bellPageFactory.getResult().stream().anyMatch(x->x.getText().contains(result)),
                "Статьи содержимое" + result + "не найдены для поискового слова " + word);
    }
}
