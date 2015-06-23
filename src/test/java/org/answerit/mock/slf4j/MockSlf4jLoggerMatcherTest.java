package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasLessEntriesThan;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasMoreEntriesThan;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.answerit.mock.slf4j.MockSlf4jLogger;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

public class MockSlf4jLoggerMatcherTest {

	private Logger logger;

	@Before
	public void before() {
		logger = new MockSlf4jLogger();
	}

	@Test
	public void test_hasEntriesCount() {
		assertThat(logger, hasEntriesCount(0));
		for (int i = 1; i <= 50; i++) {
			logger.debug("Message #" + i);
			assertThat(logger, hasEntriesCount(i));
		}
	}

	@Test
	public void test_hasEntriesCount_WithMatcher() {
		assertThat(logger, hasEntriesCount(0, anything()));
		for (int i = 1; i <= 50; i++) {
			logger.debug("Message #" + i);
			assertThat(logger, hasEntriesCount(i, anything()));
		}
	}

	@Test
	public void test_hasMoreEntriesThan() {
		assertThat(logger, not(hasMoreEntriesThan(0)));
		for (int i = 1; i <= 50; i++) {
			logger.debug("Message #" + i);
			assertThat(logger, not(hasMoreEntriesThan(i)));
			assertThat(logger, hasMoreEntriesThan(i - 1));
		}
	}

	@Test
	public void test_hasMoreEntriesThan_WithMatcher() {
		assertThat(logger, not(hasMoreEntriesThan(0, anything())));
		for (int i = 1; i <= 50; i++) {
			logger.debug("Message #" + i);
			assertThat(logger, not(hasMoreEntriesThan(i, anything())));
			assertThat(logger, hasMoreEntriesThan(i - 1, anything()));
		}
	}
	
	@Test
	public void test_hasLessEntriesThan() {
		assertThat(logger, not(hasLessEntriesThan(0)));
		for (int i = 1; i <= 50; i++) {
			logger.debug("Message #" + i);
			assertThat(logger, not(hasLessEntriesThan(i)));
			assertThat(logger, hasLessEntriesThan(i + 1));
		}
	}

	@Test
	public void test_hasLessEntriesThan_WithMatcher() {
		assertThat(logger, not(hasLessEntriesThan(0, anything())));
		for (int i = 1; i <= 50; i++) {
			logger.debug("Message #" + i);
			assertThat(logger, not(hasLessEntriesThan(i, anything())));
			assertThat(logger, hasLessEntriesThan(i + 1, anything()));
		}
	}
	
	
}
