package org.answerit.mock.slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class LoggingEventParamsMatcher<T> extends BaseMatcher<T> {

	private final Collection<Object> params;
	private final MatchAlgorithm matchAlgorithm;

	private enum MatchAlgorithm {
		INCLUSION {
			@Override
			public boolean matches(Collection<?> logParams, Collection<?> params) {
				if(logParams == null)
					logParams = new ArrayList<Object>();

				return logParams.containsAll(params);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("including ");
			}
		}, IDENTITY {
			@Override
			public boolean matches(Collection<?> logParams, Collection<?> params) {
				return Objects.equals(logParams, params);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("equal to ");
			}
		};

		abstract public boolean matches(Collection<?> logParams, Collection<?> params);
		abstract public void describeTo(Description description);
	}


	private LoggingEventParamsMatcher(MatchAlgorithm matchAlgorithm, Object ... params) {
		this.matchAlgorithm = matchAlgorithm;
		this.params = params != null ? Arrays.asList(params) : null;
	}

	private LoggingEventParamsMatcher(MatchAlgorithm matchAlgorithm, Iterable<?> params) {
		this.matchAlgorithm = matchAlgorithm;
		if(params != null) {
			this.params = new ArrayList<Object>();
			for(Object param : params) 
				this.params.add(param);
		} else {
			this.params = null;
		}

	}

	public void describeTo(Description description) {
		description.appendText("have parameters array ");
		matchAlgorithm.describeTo(description);
		description.appendValue(params);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Object actualObject) {
		if(!(actualObject instanceof LoggingEvent)) {
			return false;
		}

		final LoggingEvent loggingEvent = (LoggingEvent) actualObject;

		return matchAlgorithm.matches(loggingEvent.getCopyOfParams(), this.params);
	}

	public static <T> Matcher<T> containParams(Iterable<?> params) {
		return new LoggingEventParamsMatcher<T>(MatchAlgorithm.INCLUSION, params);
	}

	public static <T> Matcher<T> containParams(Object ... params) {
		return new LoggingEventParamsMatcher<T>(MatchAlgorithm.INCLUSION, params);
	}

	public static <T> Matcher<T> haveParams(Iterable<?> params) {
		return new LoggingEventParamsMatcher<T>(MatchAlgorithm.IDENTITY, params);
	}

	public static <T> Matcher<T> haveParams(Object ... params) {
		return new LoggingEventParamsMatcher<T>(MatchAlgorithm.IDENTITY, params);
	}

	public static <T> Matcher<T> haveParams() {
		return CoreMatchers.not(LoggingEventParamsMatcher.<T>haveNoParam());
	}

	public static <T> Matcher<T> haveNoParam() {
		return CoreMatchers.anyOf(
				new LoggingEventParamsMatcher<T>(MatchAlgorithm.IDENTITY, (Object[])null),
				new LoggingEventParamsMatcher<T>(MatchAlgorithm.IDENTITY, new ArrayList<Object>())
				);
	}
}
