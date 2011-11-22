package se.marza.swf.web.pages;

import se.marza.swf.framework.components.BookmarkableLink;
import se.marza.swf.framework.page.AbstractPage;
import se.marza.swf.web.css.MyStylesheet;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class IndexPage extends AbstractPage
{
	public IndexPage()
	{
		this.add(new BookmarkableLink("other-link", OtherPage.class));
		this.add(new MyStylesheet());
	}
}
