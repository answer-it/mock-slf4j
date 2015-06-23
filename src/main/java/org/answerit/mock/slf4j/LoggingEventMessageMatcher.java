package org.answerit.mock.slf4j;

import java.util.Objects;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class LoggingEventMessageMatcher<T> extends BaseMatcher<T> {

	private final String message;
	private final Matcher<T> matcher;

	private LoggingEventMessageMatcher(String message, Matcher<T> matcher) {
		this.message = message;
		this.matcher = matcher;
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

		String logMessage = loggingEvent.getMessage();
		if(this.matcher != null)
			return this.matcher.matches(logMessage);
		return Objects.equals(logMessage, message);
	}

	public static <T> Matcher<T> hasMessage(String message) {
		return new LoggingEventMessageMatcher<T>(message, null);
	}
	
	public static <T> Matcher<T> hasMessageThat(Matcher<T> matcher) {
		return new LoggingEventMessageMatcher<T>(null, matcher);
	}
}
