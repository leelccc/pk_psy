package com.lichun.demotest;

import com.lc.demostarterspringbootstarter.Digest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDegist {

@Autowired
private Digest digest;

    @Test
    public void test(){
        System.out.println(digest.digest("fffffff"));
    }
}
