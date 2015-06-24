package org.answerit.mock.slf4j;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.slf4j.Marker;


public class LoggingEventMarkerMatcher<T> extends BaseMatcher<T> {

	private final String name;

	private LoggingEventMarkerMatcher(String name) {
		this.name = name;
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

		Marker logMarker = loggingEvent.getMarker();
		if (logMarker == null && this.name == null)
			return true;

		if(logMarker == null) // ==> marker != null
			return false;

		//org.slf4j.Marker.contains(String) states that for null name false is returned,
		//but some implementations (i.e. log4j) may throw exception.
		return name != null ? logMarker.contains(name) : false;
	}

	public static <T> Matcher<T> containMarker(String name) {
		return new LoggingEventMarkerMatcher<T>(name);
	}
	
	public static <T> Matcher<T> containNoMarker() {
		return new LoggingEventMarkerMatcher<T>(null);
	}
	
	public static <T> Matcher<T> containAtLeastOneMarker() {
		return CoreMatchers.not(new LoggingEventMarkerMatcher<T>(null));
	}
}
