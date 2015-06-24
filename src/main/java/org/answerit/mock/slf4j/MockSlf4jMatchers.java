package org.answerit.mock.slf4j;

import java.util.Map;

import org.hamcrest.Matcher;

public final class MockSlf4jMatchers {

	private MockSlf4jMatchers() {
		// hide me
	}

	//---------------------------------------------------------
	// Sugar
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> that(org.hamcrest.Matcher<T> matcher) {
		return org.hamcrest.core.Is.<T>is(matcher);
	}

	public static <T> org.hamcrest.Matcher<T> which(org.hamcrest.Matcher<T> matcher) {
		return org.hamcrest.core.Is.<T>is(matcher);
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.MockSlf4jLoggerMatcher
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> hasEntriesCount(int count) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasEntriesCount(count);
	}

	public static <T> org.hamcrest.Matcher<T> hasEntriesCount(int count, Matcher<?> matcher) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasEntriesCount(count, matcher);
	}

	public static <T> org.hamcrest.Matcher<T> hasMoreEntriesThan(int count) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasMoreEntriesThan(count);
	}

	public static <T> org.hamcrest.Matcher<T> hasMoreEntriesThan(int count, Matcher<?> matcher) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasMoreEntriesThan(count, matcher);
	}

	public static <T> org.hamcrest.Matcher<T> hasLessEntriesThan(int count) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasLessEntriesThan(count);
	}

	public static <T> org.hamcrest.Matcher<T> hasLessEntriesThan(int count, Matcher<?> matcher) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasLessEntriesThan(count, matcher);
	}

	public static <T> org.hamcrest.Matcher<T> hasAllEntriesThat(Matcher<?> matcher) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasAllEntriesThat(matcher);
	}

	public static <T> org.hamcrest.Matcher<T> hasAtLeastOneEntryThat(Matcher<?> matcher) {
		return org.answerit.mock.slf4j.MockSlf4jLoggerMatcher.<T>hasAtLeastOneEntryThat(matcher);
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.LoggingEventContextMatcher2
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> containMDC(String key, Matcher<T> valueMatcher) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher2.<T>containMDC(key, valueMatcher);
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.LoggingEventContextMatcher
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> containMDC(Map<String, String> mdc) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containMDC(mdc);
	}

	public static <T> org.hamcrest.Matcher<T> containMDC(String key, String value) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containMDC(key, value);
	}

	public static <T> org.hamcrest.Matcher<T> containMDC(String key1, String value1, String key2, String value2) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containMDC(key1, value1, key2, value2);
	}

	public static <T> org.hamcrest.Matcher<T> containMDC(String key1, String value1, String key2, String value2, String key3, String value3) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containMDC(key1, value1, key2, value2, key3, value3);
	}

	public static <T> org.hamcrest.Matcher<T> haveMDC(Map<String, String> mdc) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>haveMDC(mdc);
	}

	public static <T> org.hamcrest.Matcher<T> haveMDC(String key, String value) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>haveMDC(key, value);
	}

	public static <T> org.hamcrest.Matcher<T> haveMDC(String key1, String value1, String key2, String value2) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>haveMDC(key1, value1, key2, value2);
	}

	public static <T> org.hamcrest.Matcher<T> haveMDC(String key1, String value1, String key2, String value2, String key3, String value3) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>haveMDC(key1, value1, key2, value2, key3, value3);
	}

	public static <T> org.hamcrest.Matcher<T> haveMDC() {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>haveMDC();
	}

	public static <T> org.hamcrest.Matcher<T> haveNoMDC() {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>haveNoMDC();
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.LoggingEventParamsMatcher
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> containParams(Iterable<?> params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>containParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> containParams(Object ... params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>containParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> haveParams(Iterable<?> params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>haveParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> haveParams(Object ... params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>haveParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> haveParams() {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>haveParams();
	}

	public static <T> org.hamcrest.Matcher<T> haveNoParam() {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>haveNoParam();
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.LoggingEventLevelMatcher
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> haveLevel(LoggingLevel level)  {
		return org.answerit.mock.slf4j.LoggingEventLevelMatcher.<T>haveLevel(level) ;
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.LoggingEventMarkerMatcher
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> containMarker(String name)  {
		return org.answerit.mock.slf4j.LoggingEventMarkerMatcher.<T>containMarker(name) ;
	}

	public static <T> org.hamcrest.Matcher<T> containNoMarker()  {
		return org.answerit.mock.slf4j.LoggingEventMarkerMatcher.<T>containNoMarker() ;
	}

	public static <T> org.hamcrest.Matcher<T> containAtLeastOneMarker()  {
		return org.answerit.mock.slf4j.LoggingEventMarkerMatcher.<T>containAtLeastOneMarker() ;
	}

	//---------------------------------------------------------
	// org.answerit.mock.slf4j.LoggingEventMessageMatcher
	//---------------------------------------------------------
	public static <T> org.hamcrest.Matcher<T> haveMessage(String message)  {
		return org.answerit.mock.slf4j.LoggingEventMessageMatcher.<T>haveMessage(message) ;
	}

	public static <T> org.hamcrest.Matcher<T> haveMessageThat(Matcher<T> matcher)  {
		return org.answerit.mock.slf4j.LoggingEventMessageMatcher.<T>haveMessageThat(matcher) ;
	}
}
