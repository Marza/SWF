package se.marza.swf.framework.page;

import se.marza.swf.framework.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.components.Component;
import se.marza.swf.framework.components.HeaderComponent;
import se.marza.swf.framework.components.MarkupComponent;
import se.marza.swf.framework.components.Stylesheet;
import se.marza.swf.framework.response.Response;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class AbstractPage
{
	private final Set<Component> components = new HashSet<Component>();
	private final Set<HeaderComponent> headers = new HashSet<HeaderComponent>();

	/**
	 * Adds a component to the page.
	 *
	 * @param component the component to add.
	 * @return this page.
	 */
	protected final AbstractPage add(final MarkupComponent component)
	{
		this.add((Component)component);

		component.setPage(this);

		return this;
	}

	/**
	 * Adds a component to the page.
	 *
	 * @param component the component to add.
	 * @return this page.
	 */
	protected final AbstractPage add(final Component component)
	{
		if (component == null)
		{
			throw new IllegalArgumentException("Component cannot be null.");
		}

		this.components.add(component);

		return this;
	}

	/**
	 * Adds a header component to the page.
	 *
	 * @param component the header component to add.
	 * @return this page.
	 */
	protected final AbstractPage add(final Stylesheet component)
	{
		if (component == null)
		{
			throw new IllegalArgumentException("Component cannot be null.");
		}

		component.setPage(this);

		this.headers.add(component);

		return this;
	}

	/**
	 * Returns the components.
	 *
	 * @return the components.
	 */
	public final Set<Component> getComponents()
	{
		return Collections.unmodifiableSet(this.components);
	}

	/**
	 * Returns the header components.
	 *
	 * @return the header components.
	 */
	public final Set<HeaderComponent> getHeaders()
	{
		return Collections.unmodifiableSet(this.headers);
	}

	/**
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @see Response#response()
	 *
	 * @return the response.
	 */
	public String getMarkup(final HttpServletRequest request, final HttpServletResponse response)
	{
		InputStream stream = null;
		try
		{
			stream = this.getMarkupStream();

			if (stream == null)
			{
				return null;
			}

			final int length = stream.available();
			final byte[] arr = new byte[length];

			int offset = 0;
			while (stream.available() > 0)
			{
				offset += stream.read(arr, offset, length - offset);
			}

			return new String(arr);
		}
		catch (final IOException e)
		{
			Logger.warn("IOException thrown while trying to read markup file.", e);
		}
		finally
		{
			try
			{
				if (stream != null)
				{
					stream.close();
				}
			}
			catch (final IOException e)
			{
				Logger.warn("Failed to close input stream.", e);
			}
		}

		return null;
	}

	/**
	 * Returns the markup stream.
	 *
	 * @return the markup stream.
	 */
	protected InputStream getMarkupStream()
	{
		final InputStream stream = this.getClass().getResourceAsStream(this.getClass().getSimpleName() + ".html");

		if (stream == null)
		{
			Logger.warn("Failed to find markup file for class: " + this.getClass().getCanonicalName());
			return null;
		}

		return stream;
	}
}
