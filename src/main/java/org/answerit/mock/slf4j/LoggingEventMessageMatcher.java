package org.answerit.mock.slf4j;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class LoggingEventMessageMatcher<T> extends BaseMatcher<T> {

	private final Matcher<T> matcher;

	private LoggingEventMessageMatcher(Matcher<T> matcher) {
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

		return false;
	}

	public static <T> Matcher<T> haveMessage(Matcher<T> matcher) {
		return new LoggingEventMessageMatcher<T>(matcher);
	}
}
