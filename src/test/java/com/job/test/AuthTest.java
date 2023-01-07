package com.job.test;
import com.job.test.page.AuthPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuthTest {
    private static final ThreadLocal<AuthPage> basicAuthPages = new ThreadLocal<>();
     /**
     * Creating new instance of page for each running test in parallel.
     */
    @BeforeMethod
    private void resetDriver(){
        basicAuthPages.set(new AuthPage());
    }

    @AfterMethod
    private void quitDriver(){
        basicAuthPages.get().quitDriver();
    }

    @Test()
    public void successfulLogin(){
        String answer = basicAuthPages.get().basicAuth("xxx", "yyy").checkAnswerText();
        Assert.assertEquals(answer, "success", "Incorrect answer, or failed login");
    }

    @Test()
    public void unSuccessfulLogin(){
        String answer = basicAuthPages.get().basicAuth("wrong", "yyy").checkAnswerText();
        Assert.assertEquals(answer, "invalid email", "Incorrect answer, or failed login");
    }

    @Test()
    public void unSuccessfulLogin2(){
        String answer = basicAuthPages.get().basicAuth("incorrect", "yyy").checkAnswerText();
        Assert.assertEquals(answer, "enter correct email", "Incorrect answer, or failed login");
    }

    @Test()
    public void validatePage(){
        Assert.assertEquals(basicAuthPages.get().correctTitle(), "Log in to VMD-SERAPIS-01", "Incorrect status page");
        Assert.assertTrue(basicAuthPages.get().loginDisplayed(), "Login form not displayed");
    }

    @Test()
    public void successLoginApi(){
        try {
            Assert.assertEquals(basicAuthPages.get().loginApi("xxx", "yyy"), 200, "Incorrect status code");
        } catch (IOException exception){
            throw new AssertionError(exception.getMessage());
        }
    }
}
