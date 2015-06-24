package org.answerit.mock.slf4j;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slf4j.Logger;

public class MockSlf4jTest 
{
	@Test
	public void test_mockStatic()
	{
		Logger originalLogger = ClassWithStaticLogger.getLogger();
		Logger mockedLogger = MockSlf4j.mockStatic(ClassWithStaticLogger.class, "LOGGER");

		assertThat(originalLogger, not(instanceOf(MockSlf4jLogger.class)));
		assertThat(originalLogger, not(sameInstance(mockedLogger)));
		assertThat(mockedLogger, sameInstance(ClassWithStaticLogger.getLogger()));
		assertThat(mockedLogger, instanceOf(MockSlf4jLogger.class));
	}

	@Test
	public void test_mock()
	{
		ClassWithNonStaticLogger instance = new ClassWithNonStaticLogger();

		Logger originalLogger = ClassWithStaticLogger.getLogger();
		Logger mockedLogger = MockSlf4j.mock(instance, "logger");

		assertThat(originalLogger, not(instanceOf(MockSlf4jLogger.class)));
		assertThat(originalLogger, not(sameInstance(mockedLogger)));
		assertThat(mockedLogger, sameInstance(instance.getLogger()));
		assertThat(mockedLogger, instanceOf(MockSlf4jLogger.class));
	}
}
