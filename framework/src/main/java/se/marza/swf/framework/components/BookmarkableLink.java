package se.marza.swf.framework.components;

import se.marza.swf.framework.SwfApplication;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class BookmarkableLink extends MarkupComponent
{
	private final String markupId;
	private final Class<? extends AbstractPage> page;

	/**
	 * Constructor.
	 *
	 * @param markupId the markup id for this component.
	 * @param page the page to link to.
	 */
	public BookmarkableLink(final String markupId, final Class<? extends AbstractPage> page)
	{
		if (markupId == null || page == null)
		{
			throw new IllegalArgumentException();
		}

		this.markupId = markupId;
		this.page = page;
	}

	/**
	 * @see MarkupComponent#markupId()
	 *
	 * @return the markup id.
	 */
	@Override
	public String markupId()
	{
		return this.markupId;
	}

	/**
	 * @see MarkupComponent#markupTag()
	 *
	 * @return the markup tag.
	 */
	@Override
	public String markupTag()
	{
		return "A";
	}

	/**
	 * @see MarkupComponent#markupAttribute()
	 *
	 * @return the markup attribute.
	 */
	@Override
	public String markupAttribute()
	{
		return "href";
	}

	/**
	 * @see MarkupComponent#markupAttributeValue()
	 *
	 * @return the markup attribute value.
	 */
	@Override
	public String markupAttributeValue()
	{
		return SwfApplication.get().getUrlForPage(this.getPage().getClass(), this.page);
	}
}
