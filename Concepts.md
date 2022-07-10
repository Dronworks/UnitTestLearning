# Display Name
## Display name annotation

- We can use **`@DisplayName`**`("Some explanatory name here that will be displayed at test run")` in order to set better explanation for our test.
- Or we can use default Generators

## Generators
- **Simple** - Removes trailing paretheses from test method name.
    - @DisplayNameGeneration(DisplayNameGenerator.**Simple**.class)
    - `getSourch_null_exception`**`()`** -> `getSourch_null_exception`
- **ReplaceUnderscores** - Replaces underscores in test method name with spaces.
    - @DisplayNameGeneration(DisplayNameGenerator.**ReplaceUnderscores**.class)
    - `getSourch_null_exception`**`()`** -> `getSourch null exception`
- **IndicativeSentences** - Generate sentence based on test class name and test method name.
    - @DisplayNameGeneration(DisplayNameGenerator.**IndicativeSentences**.class)
    - `getSourch_null_exception`**`()`** -> `MyTest, getSourch_null_exception()`
- **NOTE:** @DisplayName on method takes priority.

Generators annotation can be added to a class.

# Tests Order
* By default, test classes and methods will be **ordered** using an algorithm that is **deterministic** but **intentionally non obvious.**
* To order use class annotation **@TestMethodOrder(MethodOrder.{someOrder})**

## Order types:
- **MethodOrder.DisplayName**
- **MethodOrder.MethodName** - Ignores the display name and only orders by method name.
- **MethodOrder.Random** - remember there is an orderm but non obvious.
- **MethodOrder.OrderAnnotation** - Sorts test methods numerically based on @Order.