package se.marza.swf.web.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class IndexPage extends AbstractPage
{
	@Override
	public String response(final HttpServletRequest request, final HttpServletResponse response)
	{
		try
		{
			Parser parser = new Parser(super.response(request, response));
			NodeList nl = parser.parse(null);
			nl.visitAllNodesWith(new MyNodeVisitor(request));

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
		private final HttpServletRequest request;

		public MyNodeVisitor(final HttpServletRequest request)
		{
			this.request = request;
		}

		@Override
		public void visitTag(final Tag tag)
		{
			if (tag.getTagName().equals("A") && tag.getAttribute("swf:id").equals("other-link"))
			{
				tag.setAttribute("href", SwfApplication.get().getUrlForPage(OtherPage.class, this.request));
			}

			super.visitTag(tag);
		}
	}
}
