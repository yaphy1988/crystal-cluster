package com.ai.mine.crystal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AbstractBaseTest {
    @Test
    public void helloTest() {
        Assert.assertTrue(true);
        System.out.println("AbstractBaseTest.java hello!");
    }
}
