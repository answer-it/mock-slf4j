package answerit.mock.slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


final class ClassWithStaticLogger 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassWithStaticLogger.class);

	public static Logger getLogger() {
		return LOGGER;
	}

	public static void debug(String msg) {
		LOGGER.debug(msg);
	}
}
