package org.answerit.mock.slf4j;

import static org.answerit.mock.slf4j.MockSlf4jMatchers.containMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.hasEntriesCount;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.haveNoMDC;
import static org.answerit.mock.slf4j.MockSlf4jMatchers.that;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.MDC;

public class LoggingEventContextMatcherTest extends AbstractMockSlf4jLoggerTest{


	private static final String KEY_1 = "Key1";
	private static final String VALUE_1 = "Value1";
	private static final String KEY_2 = "Key2";
	private static final String VALUE_2 = "Value2";
	private static final String KEY_3 = "Key3";
	private static final String VALUE_3 = "Value3";
	private static final String KEY_4 = "Key4";
	private static final String VALUE_4 = "Value4";

	private static final Map<String, String> MDC1 = new HashMap<String, String>();
	private static final Map<String, String> MDC2 = new HashMap<String, String>();
	private static final Map<String, String> MDC3 = new HashMap<String, String>();
	private static final Map<String, String> MDC4 = new HashMap<String, String>();
	private static final Map<String, String> MDC5 = new HashMap<String, String>();
	private static final Map<String, String> EMPTY_MDC = Collections.emptyMap();

	@BeforeClass
	public static void setUpBeforeClass() {
		MDC1.put(KEY_1, VALUE_1);
		MDC1.put(KEY_2, VALUE_2);
		MDC1.put(KEY_3, VALUE_3);
		MDC1.put(KEY_4, VALUE_4);

		//Subset of MDC1
		MDC2.put(KEY_1, VALUE_1);
		MDC2.put(KEY_3, VALUE_3);

		MDC3.put(KEY_2, VALUE_1);
		MDC3.put(KEY_4, VALUE_3);

		MDC4.put(KEY_1, VALUE_2);

		MDC5.put(KEY_1, VALUE_3);
		MDC5.put(KEY_2, VALUE_4);
		MDC5.put(KEY_3, VALUE_1);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();

		logger.trace("log0");

		MDC.setContextMap(MDC2);
		logger.debug("log2");

		MDC.setContextMap(MDC3);
		logger.info("log3");

		MDC.setContextMap(MDC1);
		logger.warn("log1");

		MDC.setContextMap(MDC4);
		logger.error("log4");

		MDC.setContextMap(MDC5);
		logger.error("log5");
	}

	@After
	public void tearDown() {
		MDC.setContextMap(Collections.<String, String>emptyMap());
	}

	@Test
	public void test_containsMDC_Map() {
		assertThat(logger, hasEntriesCount(6, that(containMDC(EMPTY_MDC))));
		assertThat(logger, hasEntriesCount(6, that(containMDC(null))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(MDC1))));
		assertThat(logger, hasEntriesCount(2, that(containMDC(MDC2))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(MDC3))));
	}

	@Test
	public void test_containsMDC_One_Pair() {
		assertThat(logger, hasEntriesCount(2, that(containMDC(KEY_1, VALUE_1))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_2, VALUE_2))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_2, VALUE_1))));
		assertThat(logger, hasEntriesCount(2, that(containMDC(KEY_3, VALUE_3))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_4, VALUE_4))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_4, VALUE_3))));
		assertThat(logger, hasEntriesCount(0, that(containMDC(KEY_4, VALUE_2))));
	}

	@Test
	public void test_containsMDC_Two_Pairs() {
		assertThat(logger, hasEntriesCount(2, that(containMDC(KEY_1, VALUE_1, KEY_3, VALUE_3))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_2, VALUE_2, KEY_4, VALUE_4))));
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_2, VALUE_1, KEY_4, VALUE_3))));
	}

	@Test
	public void test_containsMDC_Three_Pairs() {
		assertThat(logger, hasEntriesCount(1, that(containMDC(KEY_1, VALUE_1, KEY_2, VALUE_2, KEY_3, VALUE_3))));
	}

	@Test
	public void test_hasMDC_Map() {
		assertThat(logger, hasEntriesCount(1, that(anyOf(haveMDC(EMPTY_MDC), haveMDC(null)))));
		assertThat(logger, hasEntriesCount(1, that(haveMDC(MDC1))));
		assertThat(logger, hasEntriesCount(1, that(haveMDC(MDC2))));
		assertThat(logger, hasEntriesCount(1, that(haveMDC(MDC3))));
	}

	@Test
	public void test_hasMDC_One_Pair() {
		assertThat(logger, hasEntriesCount(0, that(haveMDC(KEY_1, VALUE_1))));
		assertThat(logger, hasEntriesCount(1, that(haveMDC(KEY_1, VALUE_2))));
	}

	@Test
	public void test_hasMDC_Two_Pairs() {
		assertThat(logger, hasEntriesCount(0, that(haveMDC(KEY_1, VALUE_1, KEY_2, VALUE_2))));
		assertThat(logger, hasEntriesCount(1, that(haveMDC(KEY_1, VALUE_1, KEY_3, VALUE_3))));
	}

	@Test
	public void test_hasMDC_Three_Pairs() {
		assertThat(logger, hasEntriesCount(0, that(haveMDC(KEY_1, VALUE_1, KEY_2, VALUE_2, KEY_3, VALUE_3))));
		assertThat(logger, hasEntriesCount(1, that(haveMDC(KEY_1, VALUE_3, KEY_2, VALUE_4, KEY_3, VALUE_1))));
	}

	@Test
	public void test_hasMDC() {
		assertThat(logger, hasEntriesCount(5, that(haveMDC())));
	}

	@Test
	public void test_hasNoMDC() {
		assertThat(logger, hasEntriesCount(1, that(haveNoMDC())));
	}
}
