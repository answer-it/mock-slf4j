package answerit.mock.slf4j;

import static answerit.mock.slf4j.LoggingEventLevelMatcher.hasLevel;
import static answerit.mock.slf4j.LoggingLevel.DEBUG;
import static answerit.mock.slf4j.LoggingLevel.ERROR;
import static answerit.mock.slf4j.LoggingLevel.INFO;
import static answerit.mock.slf4j.LoggingLevel.TRACE;
import static answerit.mock.slf4j.LoggingLevel.WARN;
import static answerit.mock.slf4j.MockSlf4jLoggerMatcher.hasEntriesCount;
import static answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

public class LoggingEventLevelMatcherTest {

	private Logger logger;

	@Before
	public void setUp() throws Exception {
		logger = new MockSlf4jLogger();
	}

	@Test
	public void test_hasLevel() {
		logger.trace("Trace");
		logger.debug("Debug");
		logger.info("Info");
		logger.warn("Warn");
		logger.error("Error");

		assertThat(logger, hasEntriesCount(5));
		assertThat(logger, hasEntriesCount(1, that(hasLevel(TRACE))));
		assertThat(logger, hasEntriesCount(1, that(hasLevel(DEBUG))));
		assertThat(logger, hasEntriesCount(1, that(hasLevel(INFO))));
		assertThat(logger, hasEntriesCount(1, that(hasLevel(WARN))));
		assertThat(logger, hasEntriesCount(1, that(hasLevel(ERROR))));
	}

}
