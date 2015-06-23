package org.answerit.mock.slf4j;

import org.answerit.mock.slf4j.MockSlf4jLogger;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;

abstract class AbstractMockSlf4jLoggerTest {

	protected Logger logger;

	@Before
	public void setUp() throws Exception {
		logger = new MockSlf4jLogger();
	}

	@After
	public void tearDown() throws Exception {
		((MockSlf4jLogger)logger).clear();
	}
}
