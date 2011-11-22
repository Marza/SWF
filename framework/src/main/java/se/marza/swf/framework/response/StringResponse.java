package se.marza.swf.framework.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class StringResponse implements Response
{
	private final String string;

	/**
	 * Constructor.
	 *
	 * @param string the string to return.
	 */
	public StringResponse(final String string)
	{
		if (string == null)
		{
			throw new IllegalArgumentException();
		}

		this.string = string;
	}

	/**
	 * @see Response#response(HttpServletRequest, HttpServletResponse)
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the response.
	 */
	@Override
	public String response(final HttpServletRequest request, final HttpServletResponse response)
	{
		return this.string;
	}
}
