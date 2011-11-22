package se.marza.swf.framework.components;

import se.marza.swf.framework.SwfApplication;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class Stylesheet implements HeaderComponent
{
	private AbstractPage page;

	/**
	 * Sets the page this component is attached to.
	 *
	 * @param page the page class.
	 */
	public void setPage(final AbstractPage page)
	{
		this.page = page;
	}

	/**
	 * Returns the page this component is attached to.
	 *
	 * @return the page class.
	 */
	public AbstractPage getPage()
	{
		return this.page;
	}

	public abstract String css();

	public String url()
	{
		return SwfApplication.get().getUrlForResource(this.page.getClass(), this.getClass());
	}
}
