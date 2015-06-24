package org.answerit.mock.slf4j;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class LoggingEventThreadNameMatcher<T> extends BaseMatcher<T> {

	private final Matcher<T> matcher;

	private LoggingEventThreadNameMatcher(Matcher<T> matcher) {
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

		if(this.matcher == null)
			return false;
		
		return this.matcher.matches(loggingEvent.getThread());
	}

	public static <T> Matcher<T> fromThreadWithName(Matcher<T> matcher) {
		return new LoggingEventThreadNameMatcher<T>(matcher);
	}
}
