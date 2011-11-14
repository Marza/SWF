package se.marza.swf.web.pages;

import se.marza.swf.framework.components.BookmarkableLink;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class OtherPage extends AbstractPage
{
	public OtherPage()
	{
		this.add(new BookmarkableLink("index-link", IndexPage.class));
	}
}
