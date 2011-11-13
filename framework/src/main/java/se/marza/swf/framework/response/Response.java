package se.marza.swf.framework.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public interface Response
{
	/**
	 * Returns the response.
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the response.
	 */
	String response(HttpServletRequest request, HttpServletResponse response);
}
