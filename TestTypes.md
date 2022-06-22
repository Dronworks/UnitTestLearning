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
- **Description:** This will check if arrays are the same. **Note:** will check exact location of items and length
- **Example:**
    ```
    String[] s = new String[]{"1", "2", "3"};
    String[] s2 = new String[]{"2", "1", "3"};
    assertArrayEquals(s, s2, "Arrays should be the same");
    ```