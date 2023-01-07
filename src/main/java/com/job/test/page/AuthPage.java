package com.job.test.page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AuthPage {

    WebDriver driver;
    String url = PropertyLoader.returnConfigValue("url.login");
    public AuthPage(){
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "kc-form-login")
    private WebElement loginForm;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "kc-login")
    private WebElement loginButton;

    @FindBy(id = "password")
    private WebElement password;

    public String correctTitle() {
        return driver.getTitle();
    }

    public AuthPage basicAuth(String user, String pass){
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        return this;
    }

    public String checkAnswerText() {
        try {
            return driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText();
        } catch (Exception e) {
            return String.format(e.getMessage());
            //or whatever xpath to displayed text after login
        }
    }

    public boolean loginDisplayed() {
        return loginForm.isDisplayed();
    }

    public int loginApi(String user, String pass) throws IOException {
        String href = loginForm.getAttribute("action");
        Response response = Request.Post(href).bodyForm(
                 Form.form().add("user", user).add("password", pass).build())
                .connectTimeout(5000)
                .socketTimeout(5000)
                .execute();
        StatusLine statusLine = response.returnResponse().getStatusLine();
        return statusLine.getStatusCode();
    }

    public void quitDriver(){
        driver.quit();
    }
}
