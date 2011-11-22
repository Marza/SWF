package se.marza.swf.framework.renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import se.marza.swf.framework.Logger;
import se.marza.swf.framework.components.Component;
import se.marza.swf.framework.components.HeaderComponent;
import se.marza.swf.framework.components.MarkupComponent;
import se.marza.swf.framework.components.Stylesheet;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class JsoupPageRenderer implements PageRenderer
{
	public static final JsoupPageRenderer INSTANCE = new JsoupPageRenderer();

	/**
	 * Constructor.
	 */
	private JsoupPageRenderer()
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
		final Document document = Jsoup.parse(page.getMarkup(request, response));

		final Element head = document.head();

		for (final HeaderComponent component : page.getHeaders())
		{
			if (component instanceof Stylesheet)
			{
				final Stylesheet stylesheet = (Stylesheet) component;

				final Attributes attr = new Attributes();
				attr.put("rel", "stylesheet");
				attr.put("type", "style/css");
				attr.put("media", "screen");
				attr.put("href", stylesheet.url());

				Logger.info(stylesheet.url());

				final Element element = new Element(Tag.valueOf("link"), "test", attr);

				head.appendChild(element);
			}
		}

		final Element body = document.body();

		for (final Component component : page.getComponents())
		{
			if (component instanceof MarkupComponent)
			{
				final MarkupComponent markupComponent = (MarkupComponent) component;

				body.getElementById(markupComponent.markupId()).attr(markupComponent.markupAttribute(), markupComponent.markupAttributeValue());
			}
		}

		return document.outerHtml();
	}
}
