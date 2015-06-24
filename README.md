# mock-slf4j

##About mock-slf4j
This library was born from my own needs. I would like to share it with you hopping that it will help you, too. I have been searching a while for the best approach on how to mock and query `static` and `final` loggers. The first thing that can be done is to make use of [PowerMock](https://code.google.com/p/powermock/). That powerful indeed library helps you to mock `static` and `final` fields. That's easy. The hard work begins after. You need to collect logged data and to query for what you want to test, most commonly for the message and the logging level. The things get more complicated when you want to take into consideration for your test the mapped diagnostic context (MDC), the markers or the message format parameters. More data has to be collected and queried. This is boring and error prone job. Here this library comes into the picture. Its goal is to help you to get rid of the hard work and let you focus on testing the logged data, which should be your main goal.

##How to use

###Mocking static logger

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClassWithStaticLogger 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassWithStaticLogger.class);
	...
}

```

```java
public class MockSlf4jTest 
{
	...
	
	@Test
	public void test_mockStatic()
	{
		Logger mockedLogger = MockSlf4j.mockStatic(ClassWithStaticLogger.class, "LOGGER");
		...
	}
}
```

###Mocking non-static logger

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ClassWithNonStaticLogger 
{
	private final Logger logger = LoggerFactory.getLogger(ClassWithNonStaticLogger.class);
	...
}

```

```java
public class MockSlf4jTest 
{
	...

	@Test
	public void test_mock()
	{
		ClassWithNonStaticLogger instance = new ClassWithNonStaticLogger();
		Logger mockedLogger = MockSlf4j.mock(instance, "logger");
		...
	}
}
```
###Testing logged data

####Test log entries count

```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.junit.Assert.assertThat;

//Asserts that exactly 5 entries have been logged 
assertThat(mockedLogger, hasEntriesCount(5));
```

####Test message content
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasMessage;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasMessageThat;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasAtLeastOneEntryThat;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

....
//Asserts that at least one entry with 'a message' message has been logged 
assertThat(mockedLogger, hasAtLeastOneEntryThat(hasMessage("a message")));

//Asserts that at least one message that starts with 'Hello' has been logged
assertThat(mockedLogger, hasAtLeastOneEntryThat(hasMessageThat(startsWith("Hello"))));
```

###Test message format parameters
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasNoParam;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasParams;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containsParams;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

assertThat(mockedLogger, hasEntriesCount(1, that(hasParams())));
assertThat(mockedLogger, hasEntriesCount(1, that(hasNoParam())));

//Asserts that there is exactly one log entry with format parameters array that includes p1 and p2
assertThat(mockedLogger, hasEntriesCount(1, that(containsParams(p1, p2))));

//Asserts that there is exactly one log entry with format parameters array equals to {p1,p2,p3}
assertThat(mockedLogger, hasEntriesCount(1, that(hasParams(p1, p2, p3))));
```

####Test logging level
```java
import static org.answerit.mock.slf4j.LoggingEventLevelMatcher.hasLevel;
import static org.answerit.mock.slf4j.LoggingLevel.DEBUG;
import static org.answerit.mock.slf4j.LoggingLevel.ERROR;
import static org.answerit.mock.slf4j.LoggingLevel.INFO;
import static org.answerit.mock.slf4j.LoggingLevel.TRACE;
import static org.answerit.mock.slf4j.LoggingLevel.WARN;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

assertThat(mockedLogger, hasEntriesCount(1, that(hasLevel(TRACE))));
assertThat(mockedLogger, hasEntriesCount(1, that(hasLevel(DEBUG))));
assertThat(mockedLogger, hasEntriesCount(1, that(hasLevel(INFO))));
assertThat(mockedLogger, hasEntriesCount(1, that(hasLevel(WARN))));
assertThat(mockedLogger, hasEntriesCount(1, that(hasLevel(ERROR))));
```

####Test mapped diagnostic context (MDC)
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containsMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertThat;

assertThat(logger, hasEntriesCount(2, that(containsMDC("key1", "val1"))));
assertThat(logger, hasEntriesCount(1, that(hasMDC("key1","val1","key2","val2","key3","val3"))));
```

####Test markers
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containsAtLeastOneMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containsMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containsNoMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

assertThat(logger, hasEntriesCount(1, that(containsNoMarker())));
assertThat(logger, hasEntriesCount(2, that(containsMarker("MARKER_NAME"))));
```

####More complex test
```java
assertThat(mockedLogger, hasAtLeastOneEntryThat(allOf(
	hasLevel(LoggingLevel.ERROR),
	containsMDC("authenticationToken", nullValue()),
	containsMarker("SECURITY_ALERT"),
	hasMessageThat(allOf(
		containsString("Invalid"),
		containsString("username")
		)))
	));
```