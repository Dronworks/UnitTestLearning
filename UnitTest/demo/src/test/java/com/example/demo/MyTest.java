package com.example.demo;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
public class MyTest {

    private DemoUtils demoUtils;

    @BeforeEach
    public void setUp() {
        demoUtils = new DemoUtils();
    }

    @Test
    @DisplayName("Bla")
    public void getSourch_null_exception() {
        assertTrue(true);
    }

    @Test
    public void getSourch_null_exception2() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Same or not same object")
    public void testSameAndNotSame() {
        String test = new String("demoString");
        assertSame(demoUtils.getAcademy(), demoUtils.getDupAcademy(), "Should be same");
        assertNotSame(test, demoUtils.getAcademy(), "Shouldn't be same");
    }

    @Test
    @DisplayName("True or False")
    public void testTrueAndFalse() {
        assertTrue(demoUtils.isGrater(10, 2), "Should be greater");
        assertFalse(demoUtils.isGrater(2, 10), "Should be lesser");
    }

    @Test
    public void testAssertArraysEquals() {
        String[] s = new String[]{"1", "2", "3"};
        String[] s2 = new String[]{"1", "2", "3"};
        assertArrayEquals(s, s2, "Arrays should be the same");
    }

    @Test
    public void testAssertIterableEquals() {
        List<String> s = List.of("1", "2", "3");
        List<String> s2 = List.of("1", "2", "3");
        assertIterableEquals(s, s2, "Arrays should be the same");
    }

    @Test
    public void testAssertEqualsList() {
        List<String> s = List.of("1", "2", "3");
        List<String> s2 = List.of("1", "2", "3");
        assertEquals(s, s2, "Arrays should be the same");
    }

    @Test
    public void testAssertLinesList() {
        List<String> s = List.of("1", "2", "3");
        List<String> s2 = List.of("1", "2", "3");
        assertLinesMatch(s, s2, "Arrays should be the same");
    }

}
