package answerit.mock.slf4j;

import static answerit.mock.slf4j.MockSlf4jMatchers.containsAtLeastOneMarker;
import static answerit.mock.slf4j.MockSlf4jMatchers.containsMarker;
import static answerit.mock.slf4j.MockSlf4jMatchers.containsNoMarker;
import static answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LoggingEventMarkerMatcherTest extends AbstractMockSlf4jLoggerTest {

	private Marker parentMarker;
	private Marker childMarker;
	private Marker orphanMarker;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		parentMarker = MarkerFactory.getMarker("PARENT");
		childMarker = MarkerFactory.getMarker("CHILD");
		parentMarker.add(childMarker);
		orphanMarker = MarkerFactory.getMarker("ORPHAN");
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test_containsMarker() {
		logger.debug(parentMarker, "Parent marker");
		logger.debug(childMarker, "Child marker");

		assertThat(logger, hasEntriesCount(0, that(containsMarker(orphanMarker.getName()))));
		assertThat(logger, hasEntriesCount(1, that(containsMarker(parentMarker.getName()))));
		assertThat(logger, hasEntriesCount(2, that(containsMarker(childMarker.getName()))));		
	}

	@Test
	public void test_containsNoMarker() {
		logger.debug("No marker");
		logger.debug(parentMarker, "Parent marker");
		logger.debug(childMarker, "Child marker");

		assertThat(logger, hasEntriesCount(1, that(containsNoMarker())));
	}

	@Test
	public void test_containsAtLeastOneMarker() {
		logger.debug("No marker");
		logger.debug(parentMarker, "Parent marker");
		logger.debug(childMarker, "Child marker");

		assertThat(logger, hasEntriesCount(2, that(containsAtLeastOneMarker())));
	}

}
