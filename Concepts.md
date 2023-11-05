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
- **MethodOrder.Random** - remember there is an order by default but non obvious. So this good to test that tests have no dependency on on another. 
- **MethodOrder.OrderAnnotation** - Sorts test methods numerically based on @Order. 
    - Example:
        ```
        @Order(1)
        public void testTrueAndFalse() { .. }
        ```
    - Order number CAN BE NEGATIVE!
- **More orders are available**
    - between classe
    - custom order can be defined
    - default order can be configured in properties file
    - https://junit.org/junit5/docks/current/user-guide/

# Code coverage + Maven Tests Run
It is very hard to achieve 100% coverage, mostly we would like 70-80%
## IntelliJ
- To run right click and select run with coverage (shield icon)
- After run press the `arrow exit box` icon to save and view as html
- Tests can also be saved and view as html in the test result area, `arrow exit box`
## Maven
All next items being created with `mvn test` command.
- By default Maven **will not find Junit5 tests!**
    - Use Maven **Surefire** plugin for this
        ```
        <plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-surefire-plugin</artifactId>
			<version>3.0.0-M7</version>
		</plugin>
        ```
- To generate HTML report, **Surefire-report** plugin is needed. Also need to add execution block
    ```
    <plugin>
	    <groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-surefire-report-plugin</artifactId>
		<version>3.0.0-M7</version>
		<executions>
			<execution>
				<phase>test</phase>
				<goals>
					<goal>report</goal>
				</goals>
			</execution>
		</executions>
	</plugin>
    ```
- By default the report doesn't have any css styling, run `mvn site -DgenerateReports=false` where DgenerateReports - means Don't overwrite existing reports.
- Reports are saved in **target/sire/surefire-report.html**
- By default reports will not be generated if some of the tests fail
    - Add configuration
        ```
        <configuration>
            <testFailureIgnore>true</testFailureIgnore>
        </configuration>
        ```
- By default description name will not be shown
    - Add configuration
        ```
        <configuration>
            <statelessTestsetReporter implementation="org.apache.maven.plugin.surefire.extensions.junit5.JUnit5Xml30StatelessReporter">
                <usePhrasedTestCaseMethodName>true</usePhrasedTestCaseMethodName>
            </statelessTestsetReporter>
        </configuration>
        ```
- To run code coverage in Maven we need to use JaCoCo that run during test phase
    - Add plugin
        ```
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.6</version>
            <executions>
  	            <execution>
      	            <id>jacoco-prepare</id>
                    <goals>
            		    <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>jacoco-report</id>
                    <phase>test</phase>
                    <goals>
               	        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        ```
- Reports are saved in **target/sire/jacoco/index.html**

## Conditional tests
Sometimes we want tests to run only in some cases:
- Method is broken and we wait for fix
- Run only on specific java
- Run only on specific OS
- Run only if Environment set or system property. E.g., Run only on QA/DEV/TEST environment

### Annotations - Class level OR Method level
- `@Disabled` example: `@Disabled('dont run until ...')`
- `@EnabledOnOs` example: `@EnableOnOs(OS.WINDOWS)` and `@EnableOnOs({OS.WINDOWS, OS.LINUX})`
- `@EnableOnJre` example: `@EnableOnJre(JRE.JAVA_17)`
- `@EnableForJreRange` example: `@EnableOnJreRange(min=JRE.JAVA_8,max=JRE.JAVA_11)`
- `@EnableIfSystemProperty` example: `@EnableIfSystemProperty(named="Name", matches="value")`
	- **Note:** to test system property in app variables add `-D<System Property>=<System value>`
- `@EnableIfEnvironmentVariable` example: `@EnableIfEnvironmentVariable(named="Name", matches="value")`

## Test Driven Development
- General:
	- Write test that fails
	- Write code that pass the test
 	- Refactor
- In the test create non existing class and call non existing function. Let the IntelliJ create this for you.
- Could be good practice to write expected behavior of a tested code in comments before start writing the tests, this way it will be easy to follow on progress.

## SpringBoot and Tests
- General:
  - Usually with @SpringBootTest annotation 
  - You can Autowire **ApplicationContext**
    - Usage example: `CollegeStudent student = context.getBean("studentBeanName", CollegeStudent.class);`
    - Where there is a bean 
 
              @Bean(name="studentBeanName")
              @Scope(value="prototype)
              CollegeStudent getCollegeStudent() {
                return new CollegeStudent();
              }
                              
  - SpringBootTest will be automatically found if sits under same package as the tested class
    - But what if the test in different package? Specify the springboot application class @SpringBootTest(**classes = MyApplication.class**)  

## Mock
- In pure mockito
    - **@Mock** to mock a class
    - **@InjectMocks** to inject mocked and spy dependencies into a Service
        ```
        @InjectMockes
        private MyService myService;
        ```
- `verify(service).callMethod(any())` is same as to write `verify(service, times(1)).callMethod(any())`
- **@MockBean** is a SpringBoot extension. It adds (and replaces) a bean in ApplicationContext.

## Accessing privates during test
ReflectionTestUtils
- Set private fields: `ReflectionTestUtils.setField(someInstance, "someFieldName", someValue);`
- Get private fields: `ReflectionTestUtils.getField(someInstance, "someFieldName");`
- Call private method: `ReflectionTestUtils.invokeMethod(someInstance, "someFunctionName");`

## H2 Database
If the dependence of H2 is in the dependencies. **Spring will AUTOMATICALLY** create and define a connection.

## Testing DB
When testing database:
- Each test should start from a known state.
- Before each test insert sample data.
- After each test remove all data.
- You can @Autowire **JdbcTemplate** and use it for executing jdbc operations. I.g.:
    - `jdbc.execute("insert into student(id, firstname, lastname, email) values (1, 'Eric', 'Rubi', 'email@mail.com')");`
    - `jdbc.execute("DELETE FROM student")`
- For integration testing use `spring.jpa.hibernate.ddl-auto=create-drop` - this is commonly used and will drop created tables and schemas between tests.
- Use data sql files:
    - Create in resources file: `insertData.sql`
    - Fill it with insert commands. (each line an insert command) `insert into student(id, firstname, lastname, email) values (1, 'Eric', 'Rubi', 'email@mail.com')`
    - Add `@Sql("/insertData.sql")` annotation before the test to get load the data. **NOTE:** it is executed AFTER @BeforeEach

Another great approach is to have the SQL scripts inside a properties file.
- Create in properties file the SQL script.
`sql.scripts.create.student=insert into student (id, firstname, lastname, email_address) values (1, 'Eric', 'Roby', 'eric@gnail.com')`
- Inject it using @Value
- Run it using `jdbc.execute(scriptName)`

## Testing a controller
In order to test a controller we can use MockMVC.
```
MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .post("/intents")
                        .content(MISSING_PHRASES_LIST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
```
In addition if a controller returns a view we can test it like this 
```
ModelAndView mav = result.getModelAndView();

ModelAndViewAssert.assertViewName(mav, "index");
```
Example of a **get** test
```
MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/delete/student/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();
```
- If testing a controller GET, we can test **if a data is actually in the database using assert and sql** before testing the controller itself. This may remove confusion if the controller badly written. 