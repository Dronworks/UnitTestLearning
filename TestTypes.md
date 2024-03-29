## AssertSame/NotSame
- **Description:** This will check if **object** is the same.
- **Example:**
    - `assertSame(stringA, stringA, "Objects should refer to same object");`
    - `assertNotSame(stringB, stringA, "Objects should not refer to same object");`

## AssertTrue/False
- **Description:** This will check **boolean** expression.
- **Example:**
    - `assertTrue(isGrater(big, small), "This shoud be true");`
    - `assertFalse(isGrater(small, big), "This should be false");`

## AssertArrayEquals 
- **Description:** This will check if arrays are the same. **Note:** will check exact location of items and length.
- **Example:**
    ```
    String[] s = new String[]{"1", "2", "3"};
    String[] s2 = new String[]{"1", "2", "3"};
    assertArrayEquals(s, s2, "Arrays should be the same");
    ```

## AssertIterableEquals 
- **Description:** This will check if any implementation of iterable is equals. **Note:** will check exact location of items and length. **Note2:** can check different types (ArrayList and LinkedList).
- **Example:**
    ```
    List<String> s = List.of("1", "2", "3");
    List<String> s2 = List.of("1", "2", "3");
    assertIterableEquals(s, s2, "Arrays should be the same");
    ```

## AssertLinesMatch
- **Description:** Explicitly checks lists. 
- **Example:**
    ```
    List<String> expected = Arrays.asList("String A", "String B");
    List<String> actual = Arrays.asList("String B", "String A");
    assertLinesMatch("List equality without order", actual, containsInAnyOrder(expected.toArray()));
    ```

## Check list OrderLESS 
- **Description:** Example of checking two lists ignoring order. **Note:** `org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder`
- **Example:**
    ```
    List<String> expected = Arrays.asList("String A", "String B");
    List<String> actual = Arrays.asList("String B", "String A");
    assertThat("List equality without order", actual, containsInAnyOrder(expected.toArray()));
    ```

## Assert Throws/DoesNotThrow
- **Description:** Check if exception thrown or doesn't.
- **Example:**
    - `assertThrows(MyException.class, () -> demoUtils.isGreaterThan0(-1))`;
    - `assertDoesNotThrow(() -> demoUtils.isGreaterThan0(1));`

## Assert TimeoutPreemptively
- **Description:** Assert if timeout not happens during time given. **Note:** test WILL NOT wait for actual duration BUT FAIL after the Duration requested.
- **Example:** In the next example the function runs 2 seconds, so test shuold pass, if Duration was 1 second it would fail. 
    - `assertTimeoutPreemptively(Duration.ofSeconds(3), () -> demoUtils.checkTimeout());`

## AssertAll
- **Description:** We can do multiple asserts in one block.
- **Example:** 
    
  ```
  assertAll("Testing all methods for true",
      () -> assertEquals(323.2, studentGrades.calculate()),
      () -> assertEquals(100, studentGrades.max()));
  ```
    
## Nice test code
- `fail("message")` - this will just fail the test. Good for starting.

## ParameterizedTest
### Types
- `@ValueSource` - Array of values: Strings, ints...
- `@CsvSource` - Array of csv
- `@CsvFileSource` - CSV values from file
- `@EnumSource` - Enum constant values
- `@MethodSource` - Custom method that return values
### Display name per test
To display names by value: `@ParameterizedTest(name="value={0}, expected={1}")`. This will do output similar to 
```
V Testing FizzBull                   (origin test name)
    V value=1, expected=1            (test name per value 1)
    V value=3, expected=Fizz          (test name per value 2)
```
