## Display name annotation

- We can use **`@DisplayName`**`("Some explanatory name here that will be displayed at test run")` in order to set better explanation for our test.
- Or we can use default Generators

# Generators
- **Simple** - Removes trailing paretheses from test method name . `getSourch_null_exception`**`()`** -> `getSourch_null_exception`
- **ReplaceUnderscores** - Replaces underscores in test method name with spaces.
- **IndicativeSentences** - Generate sentence based on test class name and test method name.

Generators annotation can be added to a class.
