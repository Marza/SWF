package se.marza.swf.framework;

import se.marza.swf.framework.page.AbstractPage;
import se.marza.swf.framework.response.Response;
import se.marza.swf.framework.strategy.IRequestCodingStrategy;
import se.marza.swf.framework.strategy.PageRequestCodingStrategy;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class SwfApplication
{
	private final Set<IRequestCodingStrategy> strategies = new HashSet<IRequestCodingStrategy>();
	private Class<? extends AbstractPage> notFoundPageClass = null;

	public Response resolve(final HttpServletRequest request, final HttpServletResponse response)
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

	protected void mountPage(final String mountPath, final Class<? extends AbstractPage> pageClass)
	{
		this.mount(new PageRequestCodingStrategy(mountPath, pageClass));
	}

	protected void mount(final IRequestCodingStrategy strategy)
	{
		this.strategies.add(strategy);
	}

	protected void setNotFoundPageClass(final Class<? extends AbstractPage> pageClass)
	{
		this.notFoundPageClass = pageClass;
	}

	public Class<? extends AbstractPage> getNotFoundPageClass()
	{
		return this.notFoundPageClass;
	}

	public String getUrlForPage(final Class<? extends AbstractPage> pageClass, final HttpServletRequest request)
	{
		for (final IRequestCodingStrategy strategy : this.strategies)
		{
			if (strategy instanceof PageRequestCodingStrategy)
			{
				final PageRequestCodingStrategy pageStrategy = (PageRequestCodingStrategy) strategy;

				if (pageStrategy.getPageClass().equals(pageClass))
				{
					return pageStrategy.getPageURL(request);
				}
			}
		}

		return null;
	}

	public static SwfApplication get()
	{
		return SwfFilter.getApplication();
	}
}
