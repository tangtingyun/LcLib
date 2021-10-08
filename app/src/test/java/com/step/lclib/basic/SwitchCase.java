package com.step.lclib.basic;

import org.junit.Test;

public class SwitchCase {
    @Test
    public void trySwitch() {
        String name = null;
        switch (name) {
            case "bbb":
                System.out.println("bbb");
                break;
            case "ccc":
                System.out.println("ccc");
                break;
            case "DDD":
                System.out.println("DDD");
                break;
            default:
                System.out.println("default");

        }
    }

}
