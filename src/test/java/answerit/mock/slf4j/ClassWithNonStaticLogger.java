package answerit.mock.slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ClassWithNonStaticLogger 
{
	private final Logger logger = LoggerFactory.getLogger(ClassWithNonStaticLogger.class);

	public Logger getLogger() {
		return logger;
	}

	public void debug(String msg) {
		logger.debug(msg);
	}
}
