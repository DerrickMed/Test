package com.job.test;
import com.job.test.page.ListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListTest {
    ListPage listTest = new ListPage();

    @Test()
    public void chooseRightElement(){
        Assert.assertEquals(listTest.fifthElement(), "Advisory Committee",
                "Wrong text of fifth element, call Leeloo");
        listTest.quitDriver();
    }
}
