package com.ackja.swf.framework.page;

import com.ackja.swf.framework.Logger;
import com.ackja.swf.framework.response.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class AbstractPage implements Response
{
	@Override
	public final String response()
	{
		InputStream stream = null;
		try
		{
			stream = this.getClass().getResourceAsStream(this.getClass().getSimpleName() + ".html");

			if (stream == null)
			{
				Logger.info("Failed to find markup file for class: " + this.getClass().getCanonicalName());
				return null;
			}

			final int length = stream.available();
			byte[] arr = new byte[length];

			int offset = 0;
			while (stream.available() > 0)
			{
				offset += stream.read(arr, offset, length-offset);
			}

			return new String(arr);
		}
		catch (final IOException e)
		{
			// do nothing
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
}
