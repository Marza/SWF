package se.marza.swf.framework;

import se.marza.swf.framework.factory.ClassFactory;
import se.marza.swf.framework.factory.PageFactory;
import se.marza.swf.framework.response.Response;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.response.PageResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class SwfFilter implements Filter
{
	private static SwfApplication application = null;

	/**
	 * Initializes the servlet filter.
	 *
	 * @param filterConfig the filter configuration.
	 * @throws ServletException thrown if failed to instantiate the application class.
	 */
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{
		final String applicationClassName = filterConfig.getInitParameter("applicationClassName");

		application = (SwfApplication) ClassFactory.createClass(applicationClassName);

		if (application == null)
		{
			throw new ServletException("Failed to initialise application.");
		}
	}

	/**
	 * Handles a request to the filter.
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @param chain the filter chain.
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
	{
		Response res = null;
		try
		{
			if (request instanceof HttpServletRequest && response instanceof HttpServletResponse)
			{
				res = application.resolve((HttpServletRequest)request, (HttpServletResponse)response);
			}

			if (res == null && application.getNotFoundPageClass() != null)
			{
				res = new PageResponse(PageFactory.createPage(application.getNotFoundPageClass()));

				final HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		catch (final RuntimeException e)
		{
			if (application.getErrorPageClass() != null)
			{
				res = new PageResponse(PageFactory.createPage(application.getErrorPageClass()));
			}
			else
			{
				throw e;
			}
		}

		if (res != null)
		{
			response.getWriter().print(res.response((HttpServletRequest)request, (HttpServletResponse)response));
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy()
	{
		// do nothing
	}

	/**
	 * Returns the current application instance.
	 *
	 * @return the current application instance.
	 */
	public static SwfApplication getApplication()
	{
		return application;
	}
}
