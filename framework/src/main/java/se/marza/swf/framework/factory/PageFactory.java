package se.marza.swf.framework.factory;

import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class PageFactory
{
	private PageFactory() {}

	@SuppressWarnings("unchecked")
	public static <T extends AbstractPage> T createPage(final Class<T> pageClass)
	{
		return (T) ClassFactory.createClass(pageClass);
	}
}
