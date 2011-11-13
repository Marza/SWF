package se.marza.swf.framework.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
  private static final Pattern pattern = Pattern.compile("(\\.\\./)");

	private final String mountPath;
	private final Class<? extends AbstractPage> pageClass;

	/**
	 * Constructor.
	 *
	 * @param mountPath the mount path.
	 * @param pageClass the page class.
	 */
	public PageRequestCodingStrategy(final String mountPath, final Class<? extends AbstractPage> pageClass)
	{
		if (mountPath == null || pageClass == null)
		{
			throw new IllegalArgumentException();
		}

		this.mountPath = mountPath;
		this.pageClass = pageClass;
	}

	/**
	 * @see IRequestCodingStrategy#matches(HttpServletRequest, HttpServletResponse)
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return true if request matches otherwise false.
	 */
	@Override
	public boolean matches(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (this.mountPath.equals(request.getServletPath()))
		{
			return true;
		}

		return false;
	}

	/**
	 * @see IRequestCodingStrategy#response(HttpServletRequest, HttpServletResponse)
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the response.
	 */
	@Override
	public Response response(final HttpServletRequest request, final HttpServletResponse response)
	{
		final Response page = PageFactory.createPage(this.pageClass);

		if (page != null)
		{
			response.setHeader("Content-Type", "text/html;charset=utf-8");

			return page;
		}

		throw new RuntimeException("Failed to initialise page class");
	}

	/**
	 * @see Object#hashCode()
	 *
	 * @return the hash code.
	 */
	@Override
	public int hashCode()
	{
		return (31 + this.mountPath.hashCode()) * 31 + this.pageClass.hashCode();
	}

	/**
	 * @see Object#equals(Object)
	 *
	 * @param object the other object to check equality against.
	 * @return true if both objects are equal otherwise false.
	 */
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

	public String getMountPath()
	{
		return this.mountPath;
	}

	public String getPageURL(final HttpServletRequest request)
	{
		return createAbsoluteUrlFromRelative(request, this.mountPath);
	}

	public Class<? extends AbstractPage> getPageClass()
	{
		return this.pageClass;
	}

  /**
   * Creates an absolute URL from a relative URL.
   *
   * Steps through a relative URL and searches for ../ and modifies the <code>requestUrl</code> accordingly.
   *
   * @param request the HttpServletRequest.
   * @param relativeUrl the relative URL for the link.
   * @return the absolute URL.
   */
  public static String createAbsoluteUrlFromRelative(final HttpServletRequest request, final String relativeUrl)
  {
    String requestUrl = createRealRequestUrl(request);

    final Matcher matcher = pattern.matcher(relativeUrl);

    int index = requestUrl.lastIndexOf('/');
    if (index > 8)
    {
      requestUrl = requestUrl.substring(0, index);
    }

    while (matcher.find())
    {
      index = requestUrl.lastIndexOf('/');
      if (index > 8)
      {
        requestUrl = requestUrl.substring(0, index);
      }
    }

    /*if (requestUrl.charAt(requestUrl.length()-1) != '/')
    {
      requestUrl = requestUrl.concat("/");
    }*/

    requestUrl = requestUrl.concat(matcher.replaceAll(""));

    return requestUrl;
  }

	public static String createRealRequestUrl(final HttpServletRequest request)
	{
		final String xRequestUrl = request.getHeader("x-forwarded-url");
		if (xRequestUrl != null && !xRequestUrl.isEmpty())
		{
			return xRequestUrl;
		}
		else
		{
			return request.getRequestURL().toString();
		}
	}
}
