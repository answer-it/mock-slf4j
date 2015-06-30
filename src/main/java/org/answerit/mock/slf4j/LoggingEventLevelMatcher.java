package org.answerit.mock.slf4j;

import java.util.Objects;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class LoggingEventLevelMatcher<T> extends BaseMatcher<T> {

	private final LoggingLevel level;
	
	private LoggingEventLevelMatcher(LoggingLevel level) {
		this.level = level;
	}

	public void describeTo(Description description) {
		description.appendText("have logging level ").appendValue(level);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Object actualObject) {
		if(!(actualObject instanceof LoggingEvent)) {
			return false;
		}

		final LoggingEvent loggingEvent = (LoggingEvent) actualObject;

		return Objects.equals(loggingEvent.getLevel(), this.level);
	}

	public static <T> Matcher<T> haveLevel(LoggingLevel level) {
		return new LoggingEventLevelMatcher<T>(level);
	}
}
