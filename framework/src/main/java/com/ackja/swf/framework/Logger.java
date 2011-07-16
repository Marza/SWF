package com.ackja.swf.framework;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Logger.
 *
 * @author Tony Marjakangas | tony@marza.se
 */
@SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
public final class Logger
{
	private static final String LOG_PREFIX = "SWF";

	private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("se.marza.swf");

	static
	{
		logger.setLevel(Level.INFO);
	}

	/**
	 * Prohibits instantiation.
	 */
	private Logger() {}

	/**
	 * Logs an debug message.
	 *
	 * @param message the message.
	 */
	public static void debug(final String message)
	{
		if (logger.isLoggable(Level.FINEST))
		{
			log(Level.FINEST, message, inferCaller(new Throwable().getStackTrace()), null);
		}
	}

	/**
	 * Logs an informative message.
	 *
	 * @param message the message.
	 */
	public static void info(final String message)
	{
		log(Level.INFO, message, inferCaller(new Throwable().getStackTrace()), null);
	}

	/**
	 * Logs a warning message.
	 *
	 * @param message the message.
	 */
	public static void warn(final String message)
	{
		log(Level.WARNING, message, inferCaller(new Throwable().getStackTrace()), null);
	}

	/**
	 * Logs a warning message.
	 *
	 * @param message the message.
	 * @param cause the cause.
	 */
	public static void warn(final String message, final Throwable cause)
	{
		log(Level.WARNING, message, inferCaller(new Throwable().getStackTrace()), cause);
	}

	/**
	 * Logs a severe error message.
	 *
	 * @param message the message.
	 */
	public static void severe(final String message)
	{
		log(Level.SEVERE, message, inferCaller(new Throwable().getStackTrace()), null);
	}

	/**
	 * Logs a severe error message.
	 *
	 * @param message the message.
	 * @param cause the cause.
	 */
	public static void severe(final String message, final Throwable cause)
	{
		log(Level.SEVERE, message, inferCaller(new Throwable().getStackTrace()), cause);
	}

	/**
	 * Logs an message for profiling use.
	 *
	 * <p>
	 * This will be logged only on LOCAL and INTEGRATION environments.
	 *
	 * @param message the message.
	 * @param startTime the start time in nano seconds.
	 */
	public static void profile(final String message, final long startTime)
	{
		if (logger.isLoggable(Level.FINE))
		{
			final long deltaTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

			log(Level.FINE, message + " (" + deltaTime + " ms)", null, null);
		}
	}

	/**
	 * Logs a message.
	 *
	 * @param level the message level.
	 * @param message the message.
	 * @param caller the caller's class and method names wrapped in a Pair or null.
	 * @param cause the cause or null.
	 */
	private static void log(final Level level, final String message, final String[] caller, final Throwable cause)
	{
		if (caller != null)
		{
			logger.logp(level, caller[0], caller[1], format(message, caller[0]), cause);
		}
		else
		{
			logger.logp(level, null, null, format(message), cause);
		}
	}

	/**
	 * Returns the formatted message to log.
	 *
	 * @param message the message.
	 * @param clazz the calling class.
	 * @return the formatted message to log.
	 */
	private static String format(final String message, final String clazz)
	{
		return LOG_PREFIX + ", " + clazz.substring(clazz.lastIndexOf('.') + 1) + ": " + message;
	}

	/**
	 * Returns the formatted message to log.
	 *
	 * @param message the message.
	 * @return the formatted message to log.
	 */
	private static String format(final String message)
	{
		return LOG_PREFIX + ": " + message;
	}

	/**
	 * Returns the caller's class and method names.
	 *
	 * <p>
	 * java.util.logger abuses the stack trace for this too, so let's all be evil!
	 * </p>
	 *
	 * @param stack the stack trace elements.
	 * @return the caller's class and method names wrapped in a Pair, or null if not found.
	 */
	private static String[] inferCaller(final StackTraceElement[] stack)
	{
		int index;

		// First find the called class.

		for (index = 0; index < stack.length; index++)
		{
			if (stack[index].getClassName().equals(Logger.class.getName()))
			{
				break;
			}
		}

		// Now find the calling class.

		while (++index < stack.length)
		{
			if (!stack[index].getClassName().equals(Logger.class.getName()))
			{
				return new String[]{stack[index].getClassName(), stack[index].getMethodName()};
			}
		}

		return null;
	}
}
