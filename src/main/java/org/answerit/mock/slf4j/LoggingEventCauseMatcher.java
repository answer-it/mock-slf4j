package org.answerit.mock.slf4j;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class LoggingEventCauseMatcher<T> extends BaseMatcher<T> {

	private final Matcher<T> matcher;

	private LoggingEventCauseMatcher(Matcher<T> matcher) {
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

		if(this.matcher != null) {
			Throwable cause = loggingEvent.getCause();
			for(;;) {
				if(cause == null)
					break;

				if (this.matcher.matches(cause))
					return true;
				cause = cause.getCause();
			}
		}

		return false;
	}

	public static <T> Matcher<T> haveCause(Matcher<T> matcher) {
		return new LoggingEventCauseMatcher<T>(matcher);
	}
}
