package se.marza.swf.web.pages;

import se.marza.swf.framework.components.BookmarkableLink;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class IndexPage extends AbstractPage
{
	public IndexPage()
	{
		this.add(new BookmarkableLink("other-link", OtherPage.class));
	}
}
