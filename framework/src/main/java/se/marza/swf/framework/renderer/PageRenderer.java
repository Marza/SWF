package se.marza.swf.framework.renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.page.AbstractPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public interface PageRenderer
{
	/**
	 * Renders a web page.
	 *
	 * @param page the page class.
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the rendered page.
	 */
	String render(AbstractPage page, HttpServletRequest request, HttpServletResponse response);
}
