package com.job.test;
import com.job.test.page.QuestionPage;
import org.testng.annotations.Test;

public class QuestionTest {
    QuestionPage questionsPage = new QuestionPage();

    @Test()
    public void selectRandomRadioButtons(){
        questionsPage.test();
        questionsPage.quitDriver();
        System.out.println("Done");
    }
}
