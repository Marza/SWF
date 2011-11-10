package se.marza.swf.framework.factory;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class ClassFactory
{
	private ClassFactory() {}

	public static final Object createClass(final String className)
	{
		try
		{
			return createClass(Class.forName(className));
		}
		catch (final ClassNotFoundException e)
		{
			// do nothing
		}

		return null;
	}

	public static final Object createClass(final Class<?> clazz)
	{
		try
		{
			return clazz.newInstance();
		}
		catch (final InstantiationException e)
		{
			// do nothing
		}
		catch (final IllegalAccessException e)
		{
			// do nothing
		}

		return null;
	}
}
