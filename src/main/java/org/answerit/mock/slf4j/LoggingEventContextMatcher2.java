package org.answerit.mock.slf4j;

import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class LoggingEventContextMatcher2<T> extends BaseMatcher<T> {

	private final String key;
	private final Matcher<T> valueMatcher;

	private LoggingEventContextMatcher2(String key, Matcher<T> valueMatcher) {
		this.key = key;
		this.valueMatcher = valueMatcher;
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Object actualObject) {
		if(!(actualObject instanceof LoggingEvent)) {
			return false;
		}

		final LoggingEvent loggingEvent = (LoggingEvent) actualObject;
		Map<String, String> logMdc = loggingEvent.getCopyOfMdc();
		if(logMdc == null)
			return false;

		if(!logMdc.containsKey(key))
			return false;

		String logMdcValue = logMdc.get(key);

		return valueMatcher.matches(logMdcValue);
	}

	public static <T> Matcher<T> containsMDC(String key, Matcher<T> valueMatcher) {
		return new LoggingEventContextMatcher2<T>(key, valueMatcher);
	}
}
