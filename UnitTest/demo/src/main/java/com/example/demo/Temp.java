package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Temp {

    public static void main(String[] pArgs) throws IOException {
        String fileName1 = "c:\\temp\\new.tx";
        String fileName2 = "c:\\temp\\old.tx";

        File file1 = new File(fileName1);
        File file2 = new File(fileName2);


        try (Stream<String> linesStream = Files.lines(file1.toPath())) {
            System.out.println(linesStream.filter(l -> l.contains("\"id\":")).filter(line -> {
                String[] split = line.split(":");
                String trim = split[1].replaceAll("\"", "").replaceAll(",", "").trim();
                try {
                    UUID.fromString(trim);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }).count());
        }
        try (Stream<String> linesStream = Files.lines(file2.toPath())) {
            System.out.println(linesStream.filter(l -> l.contains("\"id\":")).filter(line -> {
                String[] split = line.split(":");
                String trim = split[1].replaceAll("\"", "").replaceAll(",", "").trim();
                try {
                    UUID.fromString(trim);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }).count());
        }
        List<String> collect = new ArrayList<>();
        try (Stream<String> linesStream = Files.lines(file1.toPath())) {
            collect = linesStream.filter(l -> l.contains("\"id\":")).map(line -> {
                String[] split = line.split(":");
                String trim = split[1].replaceAll("\"", "").replaceAll(",", "").trim();
                try {
                    UUID.fromString(trim);
                    return trim;
                } catch (Exception e) {
                    return null;
                }
            }).filter(Objects::nonNull).sorted().collect(Collectors.toList());
        }
        List<String> collect2 = new ArrayList<>();
        try (Stream<String> linesStream = Files.lines(file2.toPath())) {
            collect2 = linesStream.filter(l -> l.contains("\"id\":")).map(line -> {
                String[] split = line.split(":");
                String trim = split[1].replaceAll("\"", "").replaceAll(",", "").trim();
                try {
                    UUID.fromString(trim);
                    return trim;
                } catch (Exception e) {
                    return null;
                }
            }).filter(Objects::nonNull).sorted().collect(Collectors.toList());
        }
        collect.removeAll(collect2);
        collect.forEach(System.out::println);
    }

}
