package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveCauseMessage;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggingEventCauseMsgMatcherTest extends LoggingEventCauseMatcherTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		logger.error("Error", cause);
		assertThat(logger, hasEntriesCount(1, that(haveCauseMessage(that(containsString("Level1"))))));
		assertThat(logger, hasEntriesCount(1, that(haveCauseMessage(that(containsString("Level2"))))));
		assertThat(logger, hasEntriesCount(1, that(haveCauseMessage(that(is(equalTo("Level3")))))));
		assertThat(logger, hasEntriesCount(1, that(haveCauseMessage(that(containsString("Level4"))))));
		assertThat(logger, hasEntriesCount(0, that(haveCauseMessage(that(containsString("Level5"))))));		
	}

}
