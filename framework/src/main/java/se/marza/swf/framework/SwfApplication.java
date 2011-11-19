package se.marza.swf.framework;

import se.marza.swf.framework.page.AbstractPage;
import se.marza.swf.framework.response.Response;
import se.marza.swf.framework.strategy.IRequestCodingStrategy;
import se.marza.swf.framework.strategy.PageRequestCodingStrategy;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.renderer.HTMLParserPageRenderer;
import se.marza.swf.framework.renderer.PageRenderer;
import se.marza.swf.framework.util.NetUtil;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class SwfApplication
{
	private final Set<IRequestCodingStrategy> strategies = new HashSet<IRequestCodingStrategy>();
	private Class<? extends AbstractPage> notFoundPageClass = null;
	private Class<? extends AbstractPage> errorPageClass = null;
	private PageRenderer pageRenderer = HTMLParserPageRenderer.INSTANCE;

	/**
	 * Resolves a request.
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the response.
	 */
	public final Response resolve(final HttpServletRequest request, final HttpServletResponse response)
	{
		for (final IRequestCodingStrategy strategy : this.strategies)
		{
			if (strategy.matches(request, response))
			{
				return strategy.response(request, response);
			}
		}

		return null;
	}

	/**
	 * Mounts a page.
	 *
	 * @param mountPath the mount path.
	 * @param pageClass the page class.
	 */
	protected final void mountPage(final String mountPath, final Class<? extends AbstractPage> pageClass)
	{
		if (mountPath == null || mountPath.isEmpty() || pageClass == null)
		{
			throw new IllegalArgumentException();
		}

		this.mount(new PageRequestCodingStrategy(mountPath, pageClass));
	}

	/**
	 * Mounts a request strategy.
	 *
	 * @param strategy the request strategy.
	 */
	protected final void mount(final IRequestCodingStrategy strategy)
	{
		if (strategy == null)
		{
			throw new IllegalArgumentException("strategy cannot be null.");
		}

		this.strategies.add(strategy);
	}

	/**
	 * Sets the 404 page.
	 *
	 * @param pageClass the page class.
	 */
	protected final void setNotFoundPageClass(final Class<? extends AbstractPage> pageClass)
	{
		this.notFoundPageClass = pageClass;
	}

	/**
	 * Returns the 404 page class.
	 *
	 * @return the page class.
	 */
	public final Class<? extends AbstractPage> getNotFoundPageClass()
	{
		return this.notFoundPageClass;
	}

	/**
	 * Sets the error page class.
	 *
	 * @param pageClass the page class.
	 */
	protected final void setErrorPageClass(final Class<? extends AbstractPage> pageClass)
	{
		this.errorPageClass = pageClass;
	}

	/**
	 * Returns the error page class.
	 *
	 * @return the page class.
	 */
	public final Class<? extends AbstractPage> getErrorPageClass()
	{
		return this.errorPageClass;
	}

	/**
	 * Returns the relative URL for a page based on current URL.
	 *
	 * @param currentPage the current page class.
	 * @param pageClass the page class to get the URL for.
	 * @return the relative URL.
	 */
	public String getUrlForPage(final Class<? extends AbstractPage> currentPage, final Class<? extends AbstractPage> pageClass)
	{
		String currentPath = null;
		String pagePath = null;

		for (final IRequestCodingStrategy strategy : this.strategies)
		{
			if (strategy instanceof PageRequestCodingStrategy)
			{
				final PageRequestCodingStrategy pageStrategy = (PageRequestCodingStrategy) strategy;

				if (pageStrategy.pageClass().equals(pageClass))
				{
					pagePath = pageStrategy.mountPath();
				}
				if (pageStrategy.pageClass().equals(currentPage))
				{
					currentPath = pageStrategy.mountPath();
				}
			}
		}

		if (currentPath != null && pagePath != null)
		{
			return NetUtil.linkUrl(currentPath, pagePath);
		}

		return null;
	}

	/**
	 * @see SwfApplication#get()
	 *
	 * @return the application.
	 */
	public static SwfApplication get()
	{
		return SwfFilter.getApplication();
	}

	/**
	 * Returns the page renderer.
	 *
	 * @return the page renderer.
	 */
	public final PageRenderer getPageRenderer()
	{
		return this.pageRenderer;
	}

	/**
	 * Sets the page renderer.
	 *
	 * @param renderer the page renderer.
	 */
	public void setPageRenderer(final PageRenderer renderer)
	{
		if (renderer == null)
		{
			throw new IllegalArgumentException("renderer cannot be null.");
		}

		this.pageRenderer = renderer;
	}
}
