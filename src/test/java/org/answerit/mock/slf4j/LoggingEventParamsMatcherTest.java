package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.containParams;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveNoParam;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveParams;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggingEventParamsMatcherTest extends AbstractMockSlf4jLoggerTest{

	private static final String PARAM2 = "param2";
	private static final String PARAM1 = "param1";

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test_containsParam_Iterable() {
		logger.debug("Log with no param");
		logger.debug("Log with 2 params: {} {}", PARAM1, PARAM2);

		assertThat(logger, hasEntriesCount(2));
		assertThat(logger, hasEntriesCount(1, that(containParams(Arrays.asList(new Object[]{PARAM1})))));
		assertThat(logger, hasEntriesCount(1, that(containParams(Arrays.asList(new Object[]{PARAM2})))));
		assertThat(logger, hasEntriesCount(1, that(containParams(Arrays.asList(new Object[]{PARAM1, PARAM2})))));
	}

	@Test
	public void test_containsParam_Array() {
		logger.debug("Log with no param");
		logger.debug("Log with 2 params: {} {}", PARAM1, PARAM2);

		assertThat(logger, hasEntriesCount(2));
		assertThat(logger, hasEntriesCount(1, that(containParams(PARAM1))));
		assertThat(logger, hasEntriesCount(1, that(containParams(PARAM2))));
		assertThat(logger, hasEntriesCount(1, that(containParams(PARAM1, PARAM2))));
	}

	@Test
	public void test_hasParam_Iterable() {
		logger.debug("Log with no param");
		logger.debug("Log with 2 params: {} {}", PARAM1, PARAM2);

		assertThat(logger, hasEntriesCount(2));
		assertThat(logger, hasEntriesCount(0, that(haveParams(Arrays.asList(new Object[]{PARAM1})))));
		assertThat(logger, hasEntriesCount(0, that(haveParams(Arrays.asList(new Object[]{PARAM2})))));
		assertThat(logger, hasEntriesCount(1, that(haveParams(Arrays.asList(new Object[]{PARAM1, PARAM2})))));
	}

	@Test
	public void test_hasParam_Array() {
		logger.debug("Log with no param");
		logger.debug("Log with 2 params: {} {}", PARAM1, PARAM2);

		assertThat(logger, hasEntriesCount(2));
		assertThat(logger, hasEntriesCount(0, that(haveParams(PARAM1))));
		assertThat(logger, hasEntriesCount(0, that(haveParams(PARAM2))));
		assertThat(logger, hasEntriesCount(1, that(haveParams(PARAM1, PARAM2))));
	}

	@Test
	public void test_hasParam() {
		logger.debug("Log with no param");
		logger.debug("Log with 2 params: {} {}", PARAM1, PARAM2);

		assertThat(logger, hasEntriesCount(2));
		assertThat(logger, hasEntriesCount(1, that(haveParams())));
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test_hasNoParam() {
		logger.debug("Log with no param");
		logger.debug("Log with 2 params: {} {}", PARAM1, PARAM2);

		assertThat(logger, hasEntriesCount(2));
		assertThat(logger, hasEntriesCount(1, that(haveNoParam())));
	}

}
