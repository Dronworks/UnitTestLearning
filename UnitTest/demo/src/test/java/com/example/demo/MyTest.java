package com.example.demo;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
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
    @Order(1)
    public void testTrueAndFalse() {
        assertTrue(demoUtils.isGrater(10, 2), "Should be greater");
        assertFalse(demoUtils.isGrater(2, 10), "Should be lesser");
    }

    @Test
    @DisplayName("Throws and doesn't")
    public void testThrows() {
        assertThrows(MyException.class, () -> demoUtils.isGreaterThan0(-1));
        assertDoesNotThrow(() -> demoUtils.isGreaterThan0(1));
    }

    @Test
    @DisplayName("Test not oversleeps")
    public void testTimeout() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> demoUtils.checkTimeout());
    }
}
