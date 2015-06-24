package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveCause;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggingEventCauseMatcherTest extends AbstractMockSlf4jLoggerTest {

	protected Throwable cause;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		Throwable e1 = new NullPointerException("Level1");
		Throwable e2 = new IllegalStateException("Level2", e1);
		Throwable e3 = new IllegalArgumentException("Level3", e2);
		cause = new RuntimeException("Level4", e3);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		logger.error("Error", cause);
		assertThat(logger, hasEntriesCount(1, that(haveCause(instanceOf(NullPointerException.class)))));
		assertThat(logger, hasEntriesCount(1, that(haveCause(instanceOf(IllegalStateException.class)))));
		assertThat(logger, hasEntriesCount(1, that(haveCause(instanceOf(IllegalArgumentException.class)))));
		assertThat(logger, hasEntriesCount(1, that(haveCause(instanceOf(RuntimeException.class)))));
		assertThat(logger, hasEntriesCount(0, that(haveCause(instanceOf(IOException.class)))));
	}

}
