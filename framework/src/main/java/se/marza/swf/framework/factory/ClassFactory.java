package se.marza.swf.framework.factory;

import se.marza.swf.framework.Logger;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class ClassFactory
{
	/**
	 * Prohibits instantiation.
	 */
	private ClassFactory() {}

	/**
	 * Creates an instance of a class.
	 *
	 * @param className the name of the class to instantiate.
	 * @return an instance of the class or null if failed to instantiate.
	 */
	public static Object createClass(final String className)
	{
		try
		{
			return createClass(ClassFactory.class.getClassLoader().loadClass(className));
		}
		catch (final ClassNotFoundException e)
		{
			Logger.warn("Class [" + className +  " not found.", e);
		}

		return null;
	}

	/**
	 * Creates and instance of a class.
	 *
	 * @param clazz the class to instantiate.
	 * @return an instance of the class or null if failed to instantiate.
	 */
	public static Object createClass(final Class<?> clazz)
	{
		try
		{
			return clazz.newInstance();
		}
		catch (final InstantiationException e)
		{
			Logger.warn("Cannot instantiate [" + clazz.getName() + "]", e);
		}
		catch (final IllegalAccessException e)
		{
			Logger.warn("Not allowed to create instance of [" + clazz.getName() +"]", e);
		}

		return null;
	}
}
