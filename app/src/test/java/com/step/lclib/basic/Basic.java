package com.step.lclib.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Basic {

    @Test
    public void strTest() {
        String a = "hello";
        process(a);
        System.out.println(a);
    }

    private void process(String a) {
        a = a + "bbb";
    }
}
