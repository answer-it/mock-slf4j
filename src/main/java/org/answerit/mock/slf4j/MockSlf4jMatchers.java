package org.answerit.mock.slf4j;

import java.util.Map;

import org.hamcrest.Matcher;

public final class MockSlf4jMatchers {

	private MockSlf4jMatchers() {
		// hide me
	}

	public static <T> org.hamcrest.Matcher<T> that(org.hamcrest.Matcher<T> matcher) {
		return org.hamcrest.core.Is.<T>is(matcher);
	}

	public static <T> org.hamcrest.Matcher<T> which(org.hamcrest.Matcher<T> matcher) {
		return org.hamcrest.core.Is.<T>is(matcher);
	}

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

	public static <T> org.hamcrest.Matcher<T> containsMDC(Map<String, String> mdc) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containsMDC(mdc);
	}

	public static <T> org.hamcrest.Matcher<T> containsMDC(String key, String value) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containsMDC(key, value);
	}

	public static <T> org.hamcrest.Matcher<T> containsMDC(String key1, String value1, String key2, String value2) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containsMDC(key1, value1, key2, value2);
	}

	public static <T> org.hamcrest.Matcher<T> containsMDC(String key1, String value1, String key2, String value2, String key3, String value3) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>containsMDC(key1, value1, key2, value2, key3, value3);
	}

	public static <T> org.hamcrest.Matcher<T> hasMDC(Map<String, String> mdc) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>hasMDC(mdc);
	}

	public static <T> org.hamcrest.Matcher<T> hasMDC(String key, String value) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>hasMDC(key, value);
	}

	public static <T> org.hamcrest.Matcher<T> hasMDC(String key1, String value1, String key2, String value2) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>hasMDC(key1, value1, key2, value2);
	}

	public static <T> org.hamcrest.Matcher<T> hasMDC(String key1, String value1, String key2, String value2, String key3, String value3) {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>hasMDC(key1, value1, key2, value2, key3, value3);
	}

	public static <T> org.hamcrest.Matcher<T> hasMDC() {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>hasMDC();
	}

	public static <T> org.hamcrest.Matcher<T> hasNoMDC() {
		return org.answerit.mock.slf4j.LoggingEventContextMatcher.<T>hasNoMDC();
	}

	public static <T> org.hamcrest.Matcher<T> containsParams(Iterable<?> params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>containsParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> containsParams(Object ... params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>containsParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> hasParams(Iterable<?> params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>hasParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> hasParams(Object ... params) {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>hasParams(params);
	}

	public static <T> org.hamcrest.Matcher<T> hasParams() {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>hasParams();
	}

	public static <T> org.hamcrest.Matcher<T> hasNoParam() {
		return org.answerit.mock.slf4j.LoggingEventParamsMatcher.<T>hasNoParam();
	}

	public static <T> org.hamcrest.Matcher<T> containsMarker(String name)  {
		return org.answerit.mock.slf4j.LoggingEventMarkerMatcher.<T>containsMarker(name) ;
	}

	public static <T> org.hamcrest.Matcher<T> containsNoMarker()  {
		return org.answerit.mock.slf4j.LoggingEventMarkerMatcher.<T>containsNoMarker() ;
	}

	public static <T> org.hamcrest.Matcher<T> containsAtLeastOneMarker()  {
		return org.answerit.mock.slf4j.LoggingEventMarkerMatcher.<T>containsAtLeastOneMarker() ;
	}

	public static <T> org.hamcrest.Matcher<T> hasMessage(String message)  {
		return org.answerit.mock.slf4j.LoggingEventMessageMatcher.<T>hasMessage(message) ;
	}

	public static <T> org.hamcrest.Matcher<T> hasMessageThat(Matcher<T> matcher)  {
		return org.answerit.mock.slf4j.LoggingEventMessageMatcher.<T>hasMessageThat(matcher) ;
	}
}