package se.marza.swf.framework.strategy;

import se.marza.swf.framework.page.AbstractPage;
import se.marza.swf.framework.factory.PageFactory;
import se.marza.swf.framework.response.Response;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class PageRequestCodingStrategy implements IRequestCodingStrategy
{
	private final String mountPath;
	private final Class<? extends AbstractPage> pageClass;

	public PageRequestCodingStrategy(final String mountPath, final Class<? extends AbstractPage> pageClass)
	{
		if (mountPath == null || pageClass == null)
		{
			throw new IllegalArgumentException();
		}

		this.mountPath = mountPath;
		this.pageClass = pageClass;
	}

	@Override
	public boolean matches(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (this.mountPath.equals(request.getServletPath()))
		{
			return true;
		}

		return false;
	}

	@Override
	public Response response(final HttpServletRequest request, final HttpServletResponse response)
	{
		final Response res = PageFactory.createPage(this.pageClass);

		if (res != null)
		{
			return res;
		}

		throw new RuntimeException("Failed to initialise page class");
	}

	@Override
	public int hashCode()
	{
		return (31 + this.mountPath.hashCode()) * 31 + this.pageClass.hashCode();
	}

	@Override
	public boolean equals(final Object object)
	{
		if (this == object)
		{
			return true;
		}
		else if (object instanceof PageRequestCodingStrategy)
		{
			final PageRequestCodingStrategy other = (PageRequestCodingStrategy) object;
			return this.mountPath.equals(other.mountPath) && this.pageClass.equals(other.pageClass);
		}

		return false;
	}
}
