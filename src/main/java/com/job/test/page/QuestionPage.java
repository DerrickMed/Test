package com.job.test.page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class QuestionPage {

    WebDriver driver;
    String url = PropertyLoader.returnConfigValue("url.questions");
    Random rand = new Random();

    public QuestionPage(){
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public void quitDriver(){
        driver.quit();
    }

    //Can remove second condition and click everything if needed.
    @FindBy(xpath = "//input[contains(@name, 'questionResponseRadioButton') and contains(@value,'Y')]")
    private List<WebElement> radio;

    public void test(){
        for (WebElement e: radio) {
            if (rand.nextBoolean()) {
                e.click();
            }
        }
    }
}
