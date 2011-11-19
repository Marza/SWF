package se.marza.swf.framework.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class NetUtil
{
	private static final Pattern slashPattern = Pattern.compile("/");

	private NetUtil() {}

	/**
	 * Returns the link URL.
	 *
	 * @param currentPath the current path.
	 * @param toPath the path to go to.
	 * @return the link URL.
	 */
	public static String linkUrl(final String currentPath, final String toPath)
	{
		if (currentPath == null || toPath == null)
		{
			throw new IllegalArgumentException();
		}

		final List<String> currentList = splitPath(currentPath);
		final List<String> toList = splitPath(toPath);

		if (currentList.size() == toList.size() + 1)
		{
			return "./";
		}

		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < (currentList.size() - toList.size() - 1); i++)
		{
			builder.append("../");
		}

		if (toList.size() > 0)
		{
			for (int i = 0; i < toList.size(); i++)
			{
				final String pagePart = toList.get(i);
				if (currentList.size() <= i || !pagePart.equals(currentList.get(i)))
				{
					builder.append(pagePart);
					builder.append('/');
				}
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	/**
	 * Splits a path and returns all parts in a list.
	 *
	 * @param path the path to split.
	 * @return a list of parts of the path.
	 */
	private static List<String> splitPath(final String path)
	{
		final String[] splitted = slashPattern.split(path);

		final List<String> list = new ArrayList<String>(splitted.length);

		for (final String part : splitted)
		{
			if (part != null && !part.isEmpty())
			{
				list.add(part);
			}
		}

		return list;
	}
}
