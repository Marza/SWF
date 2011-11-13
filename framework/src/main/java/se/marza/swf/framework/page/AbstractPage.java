package se.marza.swf.framework.page;

import se.marza.swf.framework.Logger;
import se.marza.swf.framework.response.Response;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class AbstractPage implements Response
{
	/**
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @see Response#response()
	 *
	 * @return the response.
	 */
	@Override
	public String response(final HttpServletRequest request, final HttpServletResponse response)
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
