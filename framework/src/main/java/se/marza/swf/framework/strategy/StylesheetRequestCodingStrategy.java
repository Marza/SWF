package se.marza.swf.framework.strategy;

import se.marza.swf.framework.factory.PageFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.components.Stylesheet;
import se.marza.swf.framework.response.Response;
import se.marza.swf.framework.response.StringResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class StylesheetRequestCodingStrategy implements IRequestCodingStrategy
{
	private final String mountPath;
	private final Class<? extends Stylesheet> styleClass;

	/**
	 * Constructor.
	 *
	 * @param mountPath the mount path.
	 * @param styleClass the stylesheet class.
	 */
	public StylesheetRequestCodingStrategy(final String mountPath, final Class<? extends Stylesheet> styleClass)
	{
		if (mountPath == null || mountPath.isEmpty() || styleClass == null)
		{
			throw new IllegalArgumentException();
		}

		this.mountPath = mountPath;
		this.styleClass = styleClass;
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
		final Stylesheet page = PageFactory.createStylesheet(this.styleClass);

		if (page != null)
		{
			response.setHeader("Content-Type", "text/css;charset=utf-8");

			return new StringResponse(page.css());
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
		return (31 + this.mountPath.hashCode()) * 31 + this.styleClass.hashCode();
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
		else if (object instanceof StylesheetRequestCodingStrategy)
		{
			final StylesheetRequestCodingStrategy other = (StylesheetRequestCodingStrategy) object;
			return this.mountPath.equals(other.mountPath) && this.styleClass.equals(other.styleClass);
		}

		return false;
	}

	/**
	 * Returns the mount path.
	 *
	 * @return the mount path.
	 */
	@Override
	public String mountPath()
	{
		return this.mountPath;
	}

	/**
	 * Returns the stylesheet class.
	 *
	 * @return the stylesheet class.
	 */
	@Override
	public Class<? extends Stylesheet> clazz()
	{
		return this.styleClass;
	}
}
