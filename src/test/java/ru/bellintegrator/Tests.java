package ru.bellintegrator;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
}
