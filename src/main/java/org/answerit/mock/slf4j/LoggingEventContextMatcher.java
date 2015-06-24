package org.answerit.mock.slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class LoggingEventContextMatcher<T> extends BaseMatcher<T> {

	private final Map<String, String> mdc;
	private final MatchAlgorithm matchAlgorithm;

	private enum MatchAlgorithm {
		INCLUSION {
			@Override
			public boolean matches(Map<String, String> logMdc,	Map<String, String> mdc) {
				if(mdc == null || mdc.size() == 0) //any context
					return true;

				if(logMdc == null)
					logMdc = new HashMap<String, String>();

				Set<String> mdcKeySet = mdc.keySet();
				Set<String> logMdcKeySet = logMdc.keySet();

				logMdcKeySet.retainAll(mdcKeySet);
				if(!logMdcKeySet.equals(mdcKeySet))
					return false;

				for(String logMdcKey : logMdcKeySet) {
					if(!Objects.equals(logMdc.get(logMdcKey), mdc.get(logMdcKey)))
						return false;
				}

				return true;
			}
		}, IDENTITY {
			@Override
			public boolean matches(Map<String, String> logMdc, Map<String, String> mdc) {
				return Objects.equals(logMdc, mdc);
			}
		};

		abstract public boolean matches(Map<String, String> logMdc, Map<String, String> mdc);
	}


	private LoggingEventContextMatcher(MatchAlgorithm matchAlgorithm, Map<String, String> mdc) {
		this.mdc = mdc;
		this.matchAlgorithm = matchAlgorithm;
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

		return matchAlgorithm.matches(loggingEvent.getCopyOfMdc(), this.mdc);
	}

	public static <T> Matcher<T> containMDC(Map<String, String> mdc) {
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.INCLUSION, mdc);
	}

	public static <T> Matcher<T> containMDC(String key, String value) {
		final Map<String, String> mdc = new HashMap<String, String>(1);
		mdc.put(key, value);
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.INCLUSION, mdc);
	}

	public static <T> Matcher<T> containMDC(String key1, String value1, String key2, String value2) {
		final Map<String, String> mdc = new HashMap<String, String>(1);
		mdc.put(key1, value1);
		mdc.put(key2, value2);
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.INCLUSION, mdc);
	}

	public static <T> Matcher<T> containMDC(String key1, String value1, String key2, String value2, String key3, String value3) {
		final Map<String, String> mdc = new HashMap<String, String>(1);
		mdc.put(key1, value1);
		mdc.put(key2, value2);
		mdc.put(key3, value3);
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.INCLUSION, mdc);
	}

	public static <T> Matcher<T> haveMDC(Map<String, String> mdc) {
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.IDENTITY, mdc);
	}

	public static <T> Matcher<T> haveMDC(String key, String value) {
		final Map<String, String> mdc = new HashMap<String, String>(1);
		mdc.put(key, value);
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.IDENTITY, mdc);
	}

	public static <T> Matcher<T> haveMDC(String key1, String value1, String key2, String value2) {
		final Map<String, String> mdc = new HashMap<String, String>(1);
		mdc.put(key1, value1);
		mdc.put(key2, value2);
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.IDENTITY, mdc);
	}

	public static <T> Matcher<T> haveMDC(String key1, String value1, String key2, String value2, String key3, String value3) {
		final Map<String, String> mdc = new HashMap<String, String>(1);
		mdc.put(key1, value1);
		mdc.put(key2, value2);
		mdc.put(key3, value3);
		return new LoggingEventContextMatcher<T>(MatchAlgorithm.IDENTITY, mdc);
	}

	public static <T> Matcher<T> haveMDC() {
		return CoreMatchers.not(LoggingEventContextMatcher.<T>haveNoMDC());
	}

	public static <T> Matcher<T> haveNoMDC() {
		return CoreMatchers.anyOf(
				new LoggingEventContextMatcher<T>(MatchAlgorithm.IDENTITY, null),
				new LoggingEventContextMatcher<T>(MatchAlgorithm.IDENTITY, new HashMap<String, String>())
				);
	}
}
