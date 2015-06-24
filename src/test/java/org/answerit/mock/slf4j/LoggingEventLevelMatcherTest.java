package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveLevel;
import static org.answerit.mock.slf4j.LoggingLevel.DEBUG;
import static org.answerit.mock.slf4j.LoggingLevel.ERROR;
import static org.answerit.mock.slf4j.LoggingLevel.INFO;
import static org.answerit.mock.slf4j.LoggingLevel.TRACE;
import static org.answerit.mock.slf4j.LoggingLevel.WARN;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

import org.answerit.mock.slf4j.MockSlf4jLogger;
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
		assertThat(logger, hasEntriesCount(1, that(haveLevel(TRACE))));
		assertThat(logger, hasEntriesCount(1, that(haveLevel(DEBUG))));
		assertThat(logger, hasEntriesCount(1, that(haveLevel(INFO))));
		assertThat(logger, hasEntriesCount(1, that(haveLevel(WARN))));
		assertThat(logger, hasEntriesCount(1, that(haveLevel(ERROR))));
	}

}
