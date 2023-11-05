## Tear up and down

- To remember what annotation run before all tests VS before each test we can remember that **before all is STATIC** (same for after).

## Checking lists
There are some ways to check lists in junit.
- AssertIterableEquals
- AssertLinesMatch
- AssertEquals

To choose what do we want to use, we may decide of next factors:
- What do we want to check? Do we have personal class that implements iterable?
- How do we want to check? For example AssertEquals uses Equals method between objects. So AssertEquals on list will use `listA.equals(listB)`.
- What do we want to see if fails? Example
    ```
    Assert Equals:
    Expected :[1, 2, 3]
    Actual   :[1, 3, 4]
    ```
    ```
    Lines Match:
    org.opentest4j.AssertionFailedError: Arrays should be the same ==> expected line #2 doesn't match actual line #2
	expected: `2`
	  actual: `4`
    ```
    ```
    Iterable equals
    org.opentest4j.AssertionFailedError: Arrays should be the same ==> iterable contents differ at index [1], expected: <2> but was: <3>
    ```

## Iterable can be cast to collection
```
Iterable<SomeClass> iterable;
asseryTrue((Collection<SomeClass>) iterable).size() == 2)
```