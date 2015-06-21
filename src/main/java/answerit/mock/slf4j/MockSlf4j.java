package answerit.mock.slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MockSlf4j {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockSlf4j.class);

	private MockSlf4j() {
		//hide me
	}

	public static Logger mockStatic(Class<?> klass, String loggerName) {
		return mock(null, klass, loggerName);
	}

	public static Logger mock(Object instance, String loggerName) {
		Objects.requireNonNull(instance, "Expected not null object");
		return mock(instance, instance.getClass(), loggerName);
	}

	private static Logger mock(Object instance, Class<?> klass, String loggerName) {
		
		if(klass != null)
			try {
				Class.forName(klass.getName());
				//MockSlf4j.class.getClassLoader().loadClass(klass.getName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		MockSlf4jLogger result = new MockSlf4jLogger();

		Objects.requireNonNull(klass, "Expected not null class object");
		Objects.requireNonNull(loggerName, "Expected not null loggerName");

		Field field = null;
		Field modifiersField = null;
		Integer modifiers = null;
		Boolean fieldAccessible = null;
		Boolean modifiersFieldAccessible = null;
		try {
			field = klass.getDeclaredField(loggerName);
			modifiersField = Field.class.getDeclaredField("modifiers");

			fieldAccessible = field.isAccessible();
			field.setAccessible(true);

			modifiersFieldAccessible = Boolean.valueOf(modifiersField.isAccessible());
			modifiersField.setAccessible(true);

			modifiers = Integer.valueOf(field.getModifiers());
			modifiersField.setInt(field, modifiers.intValue() & ~Modifier.FINAL);
			field.set(instance, result);
			return result;
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} finally {
			if(field != null && modifiersField != null) {
				
				if(modifiers != null) {
					try {
						modifiersField.setInt(field, modifiers.intValue());
					} catch (IllegalArgumentException e) {
						LOGGER.warn("Error while attemting to restore initial state of mocked field: " + field, e);
					} catch (IllegalAccessException e) {
						LOGGER.warn("Error while attemting to restore initial state of mocked field: " + field, e);
					}
				}
				if(fieldAccessible != null) {
					field.setAccessible(fieldAccessible.booleanValue());
				}
				if(modifiersFieldAccessible != null) {
					modifiersField.setAccessible(modifiersFieldAccessible.booleanValue());
				}
			}
		}
	}

}
