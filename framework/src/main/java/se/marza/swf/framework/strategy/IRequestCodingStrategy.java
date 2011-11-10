package se.marza.swf.framework.strategy;

import se.marza.swf.framework.response.Response;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public interface IRequestCodingStrategy
{
	/**
	 * Checks if the request matches this request coding strategy.
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return true if request matches otherwise false.
	 */
	boolean matches(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Returns the response for this request.
	 *
	 * @param request the servlet request.
	 * @param response the servlet response.
	 * @return the response.
	 */
	Response response(HttpServletRequest request, HttpServletResponse response);
}
