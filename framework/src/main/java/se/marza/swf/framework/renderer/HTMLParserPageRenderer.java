package se.marza.swf.framework.renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import se.marza.swf.framework.Logger;
import se.marza.swf.framework.components.Component;
import se.marza.swf.framework.components.MarkupComponent;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class HTMLParserPageRenderer implements PageRenderer
{
	public static final HTMLParserPageRenderer INSTANCE = new HTMLParserPageRenderer();

	/**
	 * Constructor.
	 */
	private HTMLParserPageRenderer()
	{

	}

	/**
	 * @see PageRenderer#render(AbstractPage, HttpServletRequest, HttpServletResponse)
	 *
	 * @param page the page.
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the rendered page.
	 */
	@Override
	public String render(final AbstractPage page, final HttpServletRequest request, final HttpServletResponse response)
	{
		try
		{
			final Parser parser = new Parser(page.getMarkup(request, response));
			final NodeList root = parser.parse(null);

			for (final Component component : page.getComponents())
			{
				if (component instanceof MarkupComponent)
				{
					final MarkupComponent markupComponent = (MarkupComponent) component;

					root.visitAllNodesWith(newNodeVisitor(markupComponent));
				}
			}

			return root.toHtml();
		}
		catch (final ParserException e)
		{
			Logger.warn("ParserException:", e);
		}

		return null;
	}

	/**
	 * Creates a new node visitor of a markup component.
	 *
	 * @param component the markup component.
	 * @return a node visitor.
	 */
	private static NodeVisitor newNodeVisitor(final MarkupComponent component)
	{
		return new NodeVisitor()
		{
			@Override
			public void visitTag(final Tag tag)
			{
				if (tag.getTagName().equals(component.markupTag()) && tag.getAttribute("id").equals(component.markupId()))
				{
					tag.setAttribute(component.markupAttribute(), component.markupAttributeValue());
				}

				super.visitTag(tag);
			}
		};
	}
}
