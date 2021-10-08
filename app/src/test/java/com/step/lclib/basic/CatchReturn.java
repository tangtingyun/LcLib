package com.step.lclib.basic;

import org.junit.Test;

public class CatchReturn {
    @Test
    public void tryTest() {

        try {
            System.out.println("normal statement");
        } catch (Exception e) {
            System.out.println("cache block");
        } finally {
            System.out.println("finally block");
        }
        System.out.println("after code ");
    }

}
