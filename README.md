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
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasAtLeastOneEntryThat;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveMessage;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

....
//Asserts that at least one entry with 'a message' message has been logged 
assertThat(logger, hasAtLeastOneEntry(that(haveMessage(equalTo("Message")))));

//Asserts that at least one message that starts with 'Hello' has been logged
assertThat(logger, hasAtLeastOneEntry(that(haveMessage(that(startsWith("Hello"))))));
```

###Test message format parameters
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveNoParam;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveParams;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containParams;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

assertThat(mockedLogger, hasEntriesCount(1, that(haveParams())));
assertThat(mockedLogger, hasEntriesCount(1, that(haveNoParam())));

//Asserts that there is exactly one log entry with format parameters array that includes p1 and p2
assertThat(mockedLogger, hasEntriesCount(1, that(containParams(p1, p2))));

//Asserts that there is exactly one log entry with format parameters array equals to {p1,p2,p3}
assertThat(mockedLogger, hasEntriesCount(1, that(haveParams(p1, p2, p3))));
```

####Test logging level
```java
import static org.answerit.mock.slf4j.LoggingEventLevelMatcher.haveLevel;
import static org.answerit.mock.slf4j.LoggingLevel.DEBUG;
import static org.answerit.mock.slf4j.LoggingLevel.ERROR;
import static org.answerit.mock.slf4j.LoggingLevel.INFO;
import static org.answerit.mock.slf4j.LoggingLevel.TRACE;
import static org.answerit.mock.slf4j.LoggingLevel.WARN;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

assertThat(mockedLogger, hasEntriesCount(6, that(haveLevel(TRACE))));
assertThat(mockedLogger, hasEntriesCount(5, that(haveLevel(DEBUG))));
assertThat(mockedLogger, hasEntriesCount(4, that(haveLevel(INFO))));
assertThat(mockedLogger, hasEntriesCount(3, that(haveLevel(WARN))));
assertThat(mockedLogger, hasEntriesCount(2, that(haveLevel(ERROR))));
```

####Test mapped diagnostic context (MDC)
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertThat;

assertThat(logger, hasEntriesCount(2, that(containMDC("key1", "val1"))));
assertThat(logger, hasEntriesCount(1, that(haveMDC("key1","val1","key2","val2","key3","val3"))));
```

####Test markers
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containAtLeastOneMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containNoMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

assertThat(logger, hasEntriesCount(1, that(containNoMarker())));
assertThat(logger, hasEntriesCount(2, that(containMarker("MARKER_NAME"))));
```

####Test cause
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveCause;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveCauseMessage;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

//Checks the cause recursively
assertThat(logger, hasEntriesCount(1, that(haveCause(instanceOf(NullPointerException.class)))));

//Checks recursively for a cause with the given message
assertThat(logger, hasEntriesCount(1, that(haveCauseMessage(that(containsString("Invalid parameter"))))));
```

####Test thread
```java
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasAtLeastOneEntry;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.fromThreadWithName;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

assertThat(logger, hasAtLeastOneEntry(fromThreadWithName(equalTo("main"))));
```

####More complex test
```java
Map<String, String> contextMap = new HashMap<String, String>();
contextMap.put("authenticationToken", null);
contextMap.put("ipAddress", "192.168.254.254");
contextMap.put("method", "GET");
contextMap.put("request", "/users");
MDC.setContextMap(contextMap);

Marker securityAlertMarker = MarkerFactory.getDetachedMarker("SECURITY_ALERT");

logger.error(securityAlertMarker, "User not currently logged in");

assertThat(logger, hasAtLeastOneEntry(that(allOf(
	haveLevel(LoggingLevel.ERROR),
	containMDC("authenticationToken", nullValue()),
	containMDC("request", anything()),
	containMarker("SECURITY_ALERT"),
	haveMessage(allOf(
		containsString("not"),
		containsString("logged")
		)))
	)));
```