package com.ackja.swf.framework.factory;

import com.ackja.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class PageFactory
{

	@SuppressWarnings("unchecked")
	public static final <T extends AbstractPage> T createPage(final Class<T> pageClass)
	{
		return (T) ClassFactory.createClass(pageClass);
	}
}
