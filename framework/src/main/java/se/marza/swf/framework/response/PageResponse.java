package se.marza.swf.framework.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.SwfApplication;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class PageResponse implements Response
{
	private final AbstractPage page;

	/**
	 * Constructor.
	 *
	 * @param page the page.
	 */
	public PageResponse(final AbstractPage page)
	{
		if (page == null)
		{
			throw new IllegalArgumentException();
		}

		this.page = page;
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
		return SwfApplication.get().getPageRenderer().render(this.page, request, response);
	}
}
