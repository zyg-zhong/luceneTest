package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Test
    public void test() {
        System.out.println("ok.");
    }
}
