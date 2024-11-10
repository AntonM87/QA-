package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.List;
import java.util.Map;

public class Tests extends  BaseTests {

    @Feature("Проверка тайтла")
    @DisplayName("Проверка тайтла сайта")
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
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска напрямую")
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

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью PageObject")
    @ParameterizedTest(name="{displayName}:{arguments}")
    @CsvSource({"Минаев,руководитель","Филенков,руководитель","Результаты проектов,плохо"})
    public void testPO(String word, String result){
        chomeDriver.get("https://bellintegrator.ru/search/node");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chomeDriver);
        bellBeforeSearch.find(word);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chomeDriver);
        Assertions.assertTrue(bellAfterSearch.getResult().stream().anyMatch(x->x.getText().contains(result)),
                "Статьи содержимое: \"" + result + " \" не найдены для поискового слова " + word);
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью PageFabric")
    @ParameterizedTest(name="{displayName}:{arguments}")
    @CsvSource({"Минаев,руководитель","Филенков,руководитель","Результаты проектов,плохо"})
    public void testPageFactory(String word, String result){
        chomeDriver.get("https://bellintegrator.ru/search/node");
        BellPageFactory bellPageFactory = PageFactory.initElements(chomeDriver,BellPageFactory.class);
        bellPageFactory.find(word);
        Assertions.assertTrue(bellPageFactory.getResult().stream().anyMatch(x->x.getText().contains(result)),
                "Статьи содержимое: \"" + result + "\" не найдены для поискового слова " + word);
    }
    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют, базовая")
    @ParameterizedTest(name = "{displayName}:{arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingMoney")
    public void openList(String searchQuery, String title, String money){
        chomeDriver.get("https://google.ru/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chomeDriver);
        googleBeforeSearch.find(searchQuery);
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(chomeDriver);
        googleAfterSearch.goPageByLinkName(title);
        OpenMainPage openMainPage = new OpenMainPage(chomeDriver);
        openMainPage.goExchangeRates();//происходит нажание все курсы
        OpenAllExchangeRates openAllExchangeRates = new OpenAllExchangeRates(chomeDriver);
        List<Map<String,String>> collectionsExchangeRates = openAllExchangeRates.getCollectionExchangeRates();
        System.out.println(collectionsExchangeRates.size());
        System.out.println(collectionsExchangeRates.stream()
                .filter(x->x.get("Валюта обмена").contains(money))
                .findFirst()
                .get().get("Банк покупает"));
        Assertions.assertTrue(
                Double.parseDouble(
                        collectionsExchangeRates.stream()
                                .filter(x->x.get("Валюта обмена").contains(money))
                                .findFirst()
                                .get().get("Банк покупает").replace(",",".")
                )
                        <
                        Double.parseDouble(
                                collectionsExchangeRates.stream()
                                        .filter(x->x.get("Валюта обмена").contains(money))
                                        .findFirst()
                                        .get().get("Банк продаёт").replace(",",".")
                        ),
                "Курс покупки для "+money+" больше курса продажи "+money
        );
    }
}
