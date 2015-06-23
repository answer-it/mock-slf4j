package org.answerit.mock.slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.slf4j.Marker;

final class LoggingEvent {
	private final Date timestamp;
	private final LoggingLevel level;
	private final Marker marker;
	private final String message;
	private final List<?> params;
	private final Throwable cause;
	private final String thread;
	private final Map<String, String> mdc;

	public LoggingEvent(Builder builder) {
		if(builder == null)
			builder =  newBuilder();
		this.timestamp = builder.getTimestamp();
		this.level = builder.getLevel();
		this.marker = builder.getMarker();
		this.message = builder.getMessage();
		this.params = builder.getParams();
		this.cause = builder.getCause();
		this.thread = builder.getThread();
		this.mdc = builder.getMdc();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Marker getMarker() {
		return marker;
	}

	public String getMessage() {
		return message;
	}

	public List<?> getCopyOfParams() {
		return new ArrayList<Object>(params);
	}

	public Throwable getCause() {
		return cause;
	}

	public String getThread() {
		return thread;
	}

	public Map<String, String> getCopyOfMdc() {
		return new HashMap<String, String>(mdc);
	}

	public LoggingLevel getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		return String.format(
			"%s [%s] %s %s: %s", 
			getTimestamp(),
			getThread(),
			getMarker() != null ? getMarker() : "",
			getCopyOfMdc() != null ? getCopyOfMdc() : "",
			getMessage() != null ? getMessage() : "");
	}

	static class Builder {
		private Date timestamp;
		private LoggingLevel level;
		private Marker marker;
		private String message;
		private List<?> params;
		private Throwable cause;
		private String thread;
		private Map<String, String> mdc;

		public Builder() {
			this.timestamp = new Date();
			this.thread = Thread.currentThread().getName();
			this.mdc = MDC.getCopyOfContextMap();
		}
		
		private LoggingLevel getLevel() {
			return level;
		}

		public Builder setLevel(LoggingLevel level) {
			this.level = level;
			return this;
		}

		private Marker getMarker() {
			return marker;
		}

		public Builder setMarker(Marker marker) {
			this.marker = marker;
			return this;
		}

		private Date getTimestamp() {
			return timestamp;
		}

		private String getMessage() {
			return message;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		private List<?> getParams() {
			return params;
		}

		public Builder setParams(List<?> params) {
			this.params = params;
			return this;
		}

		private Throwable getCause() {
			return cause;
		}

		public Builder setCause(Throwable cause) {
			this.cause = cause;
			return this;
		}

		private String getThread() {
			return thread;
		}

		private Map<String, String> getMdc() {
			return mdc;
		}
		
		public LoggingEvent build() {
			return new LoggingEvent(this);
		}
	
	}
}
