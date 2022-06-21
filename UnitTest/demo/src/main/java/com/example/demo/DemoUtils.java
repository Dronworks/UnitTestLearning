package com.example.demo;

import lombok.Data;

@Data
public class DemoUtils {

    private String academy = "demoString";
    private String dupAcademy = academy;

    public boolean isGrater(int a, int b) {
        return a > b;
    }

}
