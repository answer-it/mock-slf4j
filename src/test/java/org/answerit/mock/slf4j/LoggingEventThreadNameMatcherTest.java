package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.fromThreadWithName;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasNoEntries;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggingEventThreadNameMatcherTest extends AbstractMockSlf4jLoggerTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() throws Exception {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread("Thread-" + i){
				@Override
				public void run() {
					logger.debug("Message from thread: " + getName());
				}
			};
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		assertThat(logger, hasNoEntries(fromThreadWithName(equalTo("main"))));
		for (int i = 0; i < threads.length; i++) {
			assertThat(logger, hasEntriesCount(1, fromThreadWithName(equalTo("Thread-" + i))));
		}
	}
}
