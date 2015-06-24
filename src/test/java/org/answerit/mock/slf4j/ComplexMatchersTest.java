package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.containMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.containMarker;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasAtLeastOneEntry;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveLevel;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveMessage;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class ComplexMatchersTest extends AbstractMockSlf4jLoggerTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		Map<String, String> contextMap = new HashMap<String, String>();
		contextMap.put("authenticationToken", null);
		contextMap.put("ipAddress", "192.168.254.254");
		contextMap.put("method", "GET");
		contextMap.put("request", "/users");
		MDC.setContextMap(contextMap);

		Marker securityAlertMarker = MarkerFactory.getDetachedMarker("SECURITY_ALERT");

		logger.error(securityAlertMarker, "Usuer not currently logged in");

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
	}

}
