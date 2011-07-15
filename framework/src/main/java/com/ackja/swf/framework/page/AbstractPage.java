package com.ackja.swf.framework.page;

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
			e.printStackTrace();
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
				System.out.println("Failed to close stream.");
			}
		}

		return null;
	}
}
