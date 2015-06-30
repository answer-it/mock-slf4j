package org.answerit.mock.slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class MockSlf4jLoggerMatcher<T> extends BaseMatcher<T> {

	private final Integer count;
	private final Comparator comparator;
	private final Matcher<?> matcher;

	private final List<LoggingEvent> matchers = new ArrayList<LoggingEvent>();



	private enum Comparator {
		LT {
			@Override
			public boolean compare(int left, int right) {
				return left < right;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("less than ");
			}
		}, LTE {
			@Override
			public boolean compare(int left, int right) {
				return left <= right;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("less than or exactly ");
			}
		}, EQ {
			@Override
			public boolean compare(int left, int right) {
				return left == right;
			}

			@Override
			public void describeTo(Description description) {
				//nothing to do
			}
		}, GTE {
			@Override
			public boolean compare(int left, int right) {
				return left >= right;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("more than or exactly ");
			}
		}, GT {
			@Override
			public boolean compare(int left, int right) {
				return left > right;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("more than ");
			}
		};

		public abstract boolean compare(int left, int right);
		public abstract void describeTo(Description description);
	}

	private MockSlf4jLoggerMatcher(Comparator comparator, Integer count, Matcher<?> matcher) {
		this.comparator = comparator;
		this.count = count;
		this.matcher = matcher;
	}

	public void describeTo(Description description) {
		comparator.describeTo(description);
		description.appendValue(count).appendText(" entries ");
		if(matcher != null) {
			matcher.describeTo(description);
		}
	}
	
	@Override
	public void describeMismatch(Object item, Description description) {
		if(!(item instanceof MockSlf4jLogger)) {
			description.appendText("not an instance of ").appendValue(MockSlf4jLogger.class.getName());
			return;
		}
		
		final MockSlf4jLogger logger = (MockSlf4jLogger) item;
		description
			.appendText(" found ")
			.appendValue(matchers.size());
			
		if(matcher != null) {
			description
			.appendText(" out of ")
			.appendValue(logger.getLoggingEvents().size())
			.appendText(" entries ")
			.appendText("matching the given condition");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Object actualObject) {
		if(!(actualObject instanceof MockSlf4jLogger)) {
			return false;
		}

		final MockSlf4jLogger logger = (MockSlf4jLogger) actualObject;
		final Collection<LoggingEvent> loggingEvents;

		loggingEvents = logger.getLoggingEvents();

		if(loggingEvents == null) {
			return false;
		}

		for(LoggingEvent loggingEvent : loggingEvents) {
			if(loggingEvent == null)
				continue;

			if(matcher == null || matcher.matches(loggingEvent)) {
				matchers.add(loggingEvent);
			}
		}

		return 
				(count == null && matchers.size() == loggingEvents.size()) ||
				(count != null && comparator.compare(matchers.size(), count.intValue()));
	}

	public static <T> Matcher<T> hasEntriesCount(int count) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, Integer.valueOf(count), null);
	}

	public static <T> Matcher<T> hasEntriesCount(int count, Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, Integer.valueOf(count), matcher);
	}

	public static <T> Matcher<T> hasMoreEntriesThan(int count) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.GT, Integer.valueOf(count), null);
	}

	public static <T> Matcher<T> hasMoreEntriesThan(int count, Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.GT, Integer.valueOf(count), matcher);
	}

	public static <T> Matcher<T> hasLessEntriesThan(int count) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.LT, Integer.valueOf(count), null);
	}

	public static <T> Matcher<T> hasLessEntriesThan(int count, Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.LT, Integer.valueOf(count), matcher);
	}

	public static <T> Matcher<T> hasAllEntries(Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, null, matcher);
	}

	public static <T> Matcher<T> hasAtLeastOneEntry(Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.GTE, Integer.valueOf(1), matcher);
	}

	public static <T> Matcher<T> hasNoEntries() {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, Integer.valueOf(0), null);
	}

	public static <T> Matcher<T> hasNoEntries(Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, Integer.valueOf(0), matcher);
	}
}
