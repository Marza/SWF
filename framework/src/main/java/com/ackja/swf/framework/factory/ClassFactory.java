package com.ackja.swf.framework.factory;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class ClassFactory
{

	public static final Object createClass(final String className)
	{
		try
		{
			return createClass(Class.forName(className));
		}
		catch (final ClassNotFoundException e)
		{

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

		}
		catch (final IllegalAccessException e)
		{

		}

		return null;
	}
}
