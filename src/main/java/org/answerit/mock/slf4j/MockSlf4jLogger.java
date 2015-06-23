package org.answerit.mock.slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class MockSlf4jLogger implements Logger{

	private List<LoggingEvent> log = new Vector<LoggingEvent>();

	public MockSlf4jLogger() {
		super();
	}

	public String getName() {
		return "MockSlf4j";
	}

	public List<LoggingEvent> getLoggingEvents() {
		return Collections.unmodifiableList(log);
	}
	
	public void clear() {
		log.clear();
	}

	public boolean isTraceEnabled() {
		return true;
	}

	public boolean isTraceEnabled(Marker marker) {
		return true;
	}

	public void trace(String msg) {
		logEvent(LoggingLevel.TRACE, null, msg, null, null);
	}

	public void trace(String format, Object arg) {
		logEvent(LoggingLevel.TRACE, null, format, new Object[]{arg}, null);
	}

	public void trace(String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.TRACE, null, format, new Object[]{arg1, arg2}, null);
	}

	public void trace(String format, Object... arguments) {
		logEvent(LoggingLevel.TRACE, null, format, arguments, null);
	}

	public void trace(String msg, Throwable t) {
		logEvent(LoggingLevel.TRACE, null, msg, null, t);
	}

	public void trace(Marker marker, String msg) {
		logEvent(LoggingLevel.TRACE, marker, msg, null, null);
	}

	public void trace(Marker marker, String format, Object arg) {
		logEvent(LoggingLevel.TRACE, marker, format, new Object[]{arg}, null);
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.TRACE, marker, format, new Object[]{arg1, arg2}, null);
	}

	public void trace(Marker marker, String format, Object... argArray) {
		logEvent(LoggingLevel.TRACE, marker, format, argArray, null);
	}

	public void trace(Marker marker, String msg, Throwable t) {
		logEvent(LoggingLevel.TRACE, marker, msg, null, t);
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public boolean isDebugEnabled(Marker marker) {
		return true;
	}

	public void debug(String msg) {
		logEvent(LoggingLevel.DEBUG, null, msg, null, null);
	}

	public void debug(String format, Object arg) {
		logEvent(LoggingLevel.DEBUG, null, format, new Object[]{arg}, null);
	}

	public void debug(String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.DEBUG, null, format, new Object[]{arg1, arg2}, null);
	}

	public void debug(String format, Object... arguments) {
		logEvent(LoggingLevel.DEBUG, null, format, arguments, null);
	}

	public void debug(String msg, Throwable t) {
		logEvent(LoggingLevel.DEBUG, null, msg, null, t);
	}

	public void debug(Marker marker, String msg) {
		logEvent(LoggingLevel.DEBUG, marker, msg, null, null);
	}

	public void debug(Marker marker, String format, Object arg) {
		logEvent(LoggingLevel.DEBUG, marker, format, new Object[]{arg}, null);
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.DEBUG, marker, format, new Object[]{arg1, arg2}, null);
	}

	public void debug(Marker marker, String format, Object... arguments) {
		logEvent(LoggingLevel.DEBUG, marker, format, arguments, null);
	}

	public void debug(Marker marker, String msg, Throwable t) {
		logEvent(LoggingLevel.DEBUG, marker, msg, null, t);
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isInfoEnabled(Marker marker) {
		return true;
	}

	public void info(String msg) {
		logEvent(LoggingLevel.INFO, null, msg, null, null);
	}

	public void info(String format, Object arg) {
		logEvent(LoggingLevel.INFO, null, format, new Object[]{arg}, null);
	}

	public void info(String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.INFO, null, format, new Object[]{arg1, arg2}, null);
	}

	public void info(String format, Object... arguments) {
		logEvent(LoggingLevel.INFO, null, format, arguments, null);
	}

	public void info(String msg, Throwable t) {
		logEvent(LoggingLevel.INFO, null, msg, null, t);
	}

	public void info(Marker marker, String msg) {
		logEvent(LoggingLevel.INFO, marker, msg, null, null);
	}

	public void info(Marker marker, String format, Object arg) {
		logEvent(LoggingLevel.INFO, marker, format, new Object[]{arg}, null);
	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.INFO, marker, format, new Object[]{arg1, arg2}, null);
	}

	public void info(Marker marker, String format, Object... arguments) {
		logEvent(LoggingLevel.INFO, marker, format, arguments, null);
	}

	public void info(Marker marker, String msg, Throwable t) {
		logEvent(LoggingLevel.INFO, marker, msg, null, t);
	}
	
	public boolean isWarnEnabled() {
		return true;
	}

	public boolean isWarnEnabled(Marker marker) {
		return true;
	}

	public void warn(String msg) {
		logEvent(LoggingLevel.WARN, null, msg, null, null);
	}

	public void warn(String format, Object arg) {
		logEvent(LoggingLevel.WARN, null, format, new Object[]{arg}, null);
	}

	public void warn(String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.WARN, null, format, new Object[]{arg1, arg2}, null);
	}

	public void warn(String format, Object... arguments) {
		logEvent(LoggingLevel.WARN, null, format, arguments, null);
	}

	public void warn(String msg, Throwable t) {
		logEvent(LoggingLevel.WARN, null, msg, null, t);
	}

	public void warn(Marker marker, String msg) {
		logEvent(LoggingLevel.WARN, marker, msg, null, null);
	}

	public void warn(Marker marker, String format, Object arg) {
		logEvent(LoggingLevel.WARN, marker, format, new Object[]{arg}, null);
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.WARN, marker, format, new Object[]{arg1, arg2}, null);
	}

	public void warn(Marker marker, String format, Object... arguments) {
		logEvent(LoggingLevel.WARN, marker, format, arguments, null);
	}

	public void warn(Marker marker, String msg, Throwable t) {
		logEvent(LoggingLevel.WARN, marker, msg, null, t);
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public boolean isErrorEnabled(Marker marker) {
		return true;
	}

	public void error(String msg) {
		logEvent(LoggingLevel.ERROR, null, msg, null, null);
	}

	public void error(String format, Object arg) {
		logEvent(LoggingLevel.ERROR, null, format, new Object[]{arg}, null);
	}

	public void error(String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.ERROR, null, format, new Object[]{arg1, arg2}, null);
	}

	public void error(String format, Object... arguments) {
		logEvent(LoggingLevel.ERROR, null, format, arguments, null);
	}

	public void error(String msg, Throwable t) {
		logEvent(LoggingLevel.ERROR, null, msg, null, t);
	}

	public void error(Marker marker, String msg) {
		logEvent(LoggingLevel.ERROR, marker, msg, null, null);
	}

	public void error(Marker marker, String format, Object arg) {
		logEvent(LoggingLevel.ERROR, marker, format, new Object[]{arg}, null);
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		logEvent(LoggingLevel.ERROR, marker, format, new Object[]{arg1, arg2}, null);
	}

	public void error(Marker marker, String format, Object... arguments) {
		logEvent(LoggingLevel.ERROR, marker, format, arguments, null);
	}

	public void error(Marker marker, String msg, Throwable t) {
		logEvent(LoggingLevel.ERROR, marker, msg, null, t);
	}

	private void logEvent(LoggingLevel loggingLevel, Marker marker, String message, Object[] params, Throwable t) {
		if(!isEnabled(loggingLevel))
			return;
		//TODO: use MessageFormatter to build a FormattingTuple
		LoggingEvent loggingEvent = LoggingEvent.newBuilder()
				.setMarker(marker)
				.setLevel(loggingLevel)
				.setCause(t)
				.setParams(params != null ? Arrays.asList(params) : Collections.EMPTY_LIST)
				.setMessage(message)
				.build();
		log.add(loggingEvent);
	}

	private boolean isEnabled(LoggingLevel loggingLevel) {
		// TODO implement this
		return true;
	}
}
