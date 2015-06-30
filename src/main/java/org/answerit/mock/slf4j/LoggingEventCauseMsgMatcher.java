package org.answerit.mock.slf4j;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class LoggingEventCauseMsgMatcher<T> extends BaseMatcher<T> {

	private final Matcher<T> matcher;

	private LoggingEventCauseMsgMatcher(Matcher<T> matcher) {
		this.matcher = matcher;
	}

	public void describeTo(Description description) {
		description.appendText("have cause with message ");
		matcher.describeTo(description);
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

				if (this.matcher.matches(cause.getMessage()))
					return true;
				cause = cause.getCause();
			}
		}

		return false;
	}

	public static <T> Matcher<T> haveCauseMessage(Matcher<T> matcher) {
		return new LoggingEventCauseMsgMatcher<T>(matcher);
	}
}
