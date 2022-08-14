package com.example.demo;

import lombok.Data;

@Data
public class DemoUtils {

    private String academy = "demoString";
    private String dupAcademy = academy;

    public boolean isGrater(int a, int b) {
        return a > b;
    }

    public String isGreaterThan0(int a) throws Exception {
        if(a < 0) {
            throw new MyException("Value should be greater or equals 0");
        }
        return "Value is greater or equals 0";
    }

    public void checkTimeout() throws InterruptedException {
        System.out.println("Going to sleep");
        Thread.sleep(2);
        System.out.println("Wake up!");
    }

}
