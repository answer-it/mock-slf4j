package answerit.mock.slf4j;

import static org.hamcrest.CoreMatchers.anything;

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



	private enum Comparator {
		LT {
			@Override
			public boolean compare(int left, int right) {
				return left < right;
			}
		}, LTE {
			@Override
			public boolean compare(int left, int right) {
				return left <= right;
			}
		}, EQ {
			@Override
			public boolean compare(int left, int right) {
				return left == right;
			}
		}, GTE {
			@Override
			public boolean compare(int left, int right) {
				return left >= right;
			}
		}, GT {
			@Override
			public boolean compare(int left, int right) {
				return left > right;
			}
		};

		public abstract boolean compare(int left, int right);
	}

	private MockSlf4jLoggerMatcher(Comparator comparator, Integer count, Matcher<?> matcher) {
		this.comparator = comparator;
		this.count = count;
		this.matcher = matcher;
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub
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

		final List<LoggingEvent> matchers = new ArrayList<LoggingEvent>();
		for(LoggingEvent loggingEvent : loggingEvents) {
			if(loggingEvent == null)
				continue;

			if(matcher.matches(loggingEvent)) {
				matchers.add(loggingEvent);
			}
		}

		return 
				(count == null && matchers.size() == loggingEvents.size()) ||
				(count != null && comparator.compare(matchers.size(), count.intValue()));
	}

	public static <T> Matcher<T> hasEntriesCount(int count) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, Integer.valueOf(count), anything());
	}

	public static <T> Matcher<T> hasEntriesCount(int count, Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, Integer.valueOf(count), matcher);
	}

	public static <T> Matcher<T> hasMoreEntriesThan(int count) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.GT, Integer.valueOf(count), anything());
	}

	public static <T> Matcher<T> hasMoreEntriesThan(int count, Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.GT, Integer.valueOf(count), matcher);
	}

	public static <T> Matcher<T> hasLessEntriesThan(int count) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.LT, Integer.valueOf(count), anything());
	}

	public static <T> Matcher<T> hasLessEntriesThan(int count, Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.LT, Integer.valueOf(count), matcher);
	}

	public static <T> Matcher<T> hasAllEntriesThat(Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.EQ, null, matcher);
	}

	public static <T> Matcher<T> hasAtLeastOneEntryThat(Matcher<?> matcher) {
		return new MockSlf4jLoggerMatcher<T>(Comparator.GTE, Integer.valueOf(1), matcher);
	}
}
