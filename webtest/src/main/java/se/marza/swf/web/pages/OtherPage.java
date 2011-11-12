package se.marza.swf.web.pages;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import se.marza.swf.framework.Logger;
import se.marza.swf.framework.SwfApplication;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class OtherPage extends AbstractPage
{
	@Override
	public String response()
	{
		try
		{
			Parser parser = new Parser(super.response());
			NodeList nl = parser.parse(null);
			nl.visitAllNodesWith(new MyNodeVisitor());

			return nl.toHtml();
		}
		catch (final ParserException e)
		{
			Logger.warn("Exception:", e);
		}

		return null;
	}

	private static final class MyNodeVisitor extends NodeVisitor
	{
		@Override
		public void visitTag(final Tag tag)
		{
			if (tag.getTagName().equals("A") && tag.getAttribute("swf:id").equals("index-link"))
			{
				tag.setAttribute("href", SwfApplication.get().getUrlForPage(IndexPage.class));
			}

			super.visitTag(tag);
		}
	}
}
