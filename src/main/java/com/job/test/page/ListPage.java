package com.job.test.page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ListPage {

    WebDriver driver;
    String url = PropertyLoader.returnConfigValue("url.list");
    public ListPage(){
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public void quitDriver(){
        driver.quit();
    }


    @FindBy(xpath = "//li[contains(@class, 'ui-autocomplete-list-item')]")
    private List<WebElement> webElementList;

    public String fifthElement(){
        return webElementList.get(0).getText();
    }
}
