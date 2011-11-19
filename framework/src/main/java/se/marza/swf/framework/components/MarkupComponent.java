package se.marza.swf.framework.components;

import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class MarkupComponent implements Component
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

	public abstract String markupId();
	public abstract String markupTag();
	public abstract String markupAttribute();
	public abstract String markupAttributeValue();
}
