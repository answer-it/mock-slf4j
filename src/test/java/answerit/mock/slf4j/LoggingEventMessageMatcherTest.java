package answerit.mock.slf4j;

import static answerit.mock.slf4j.LoggingEventMessageMatcher.hasMessage;
import static answerit.mock.slf4j.LoggingEventMessageMatcher.hasMessageThat;
import static answerit.mock.slf4j.MockSlf4jLoggerMatcher.hasAtLeastOneEntryThat;
import static answerit.mock.slf4j.MockSlf4jLoggerMatcher.hasEntriesCount;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

public class LoggingEventMessageMatcherTest {

	private Logger logger;
	private static final String MESSAGE = "May the force be with you!";

	@Before
	public void before() {
		logger = new MockSlf4jLogger();
	}

	@Test
	public void test_hasMessage() {
		logger.debug(MESSAGE);

		Assert.assertThat(logger, hasAtLeastOneEntryThat(hasMessage(MESSAGE)));
	}

	@Test
	public void test_hasMessage_WithMatcher() {
		logger.debug(MESSAGE);
		
		assertThat(logger, hasEntriesCount(1));
		Assert.assertThat(logger, hasAtLeastOneEntryThat(hasMessageThat(startsWith("May"))));
		Assert.assertThat(logger, not(hasAtLeastOneEntryThat(hasMessageThat(startsWith("may")))));
	}

}
