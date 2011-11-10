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

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class SwfFilter implements Filter
{
	private static SwfApplication application = null;

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

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
	{
		Response res = null;
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse)
		{
			res = application.resolve((HttpServletRequest)request, (HttpServletResponse)response);
		}

		if (res == null && application.getNotFoundPageClass() != null)
		{
			res = PageFactory.createPage(application.getNotFoundPageClass());

			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

		if (res != null)
		{
			response.getWriter().print(res.response());
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy()
	{
		// do nothing
	}

	public static SwfApplication getApplication()
	{
		return application;
	}
}
