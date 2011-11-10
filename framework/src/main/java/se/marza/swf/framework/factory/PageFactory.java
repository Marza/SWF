package se.marza.swf.framework.factory;

import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class PageFactory
{
	/**
	 * Prohibits instantiation.
	 */
	private PageFactory() {}

	/**
	 * Creates an instance of a page.
	 *
	 * @param <T> the generic type for the page class.
	 * @param pageClass the page class to instantiate.
	 * @return an instance of the class or null if failed to instantiate.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends AbstractPage> T createPage(final Class<T> pageClass)
	{
		return (T) ClassFactory.createClass(pageClass);
	}
}
