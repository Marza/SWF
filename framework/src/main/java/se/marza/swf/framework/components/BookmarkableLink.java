package se.marza.swf.framework.components;

import se.marza.swf.framework.SwfApplication;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class BookmarkableLink implements MarkupComponent
{
	private final String markupId;
	private final Class<? extends AbstractPage> page;

	public BookmarkableLink(final String markupId, final Class<? extends AbstractPage> page)
	{
		if (markupId == null || page == null)
		{
			throw new IllegalArgumentException();
		}

		this.markupId = markupId;
		this.page = page;
	}

	@Override
	public String markupId()
	{
		return this.markupId;
	}

	@Override
	public String markupTag()
	{
		return "A";
	}

	@Override
	public String markupAttribute()
	{
		return "href";
	}

	@Override
	public String markupAttributeValue()
	{
		return SwfApplication.get().getUrlForPage(this.page);
	}
}
