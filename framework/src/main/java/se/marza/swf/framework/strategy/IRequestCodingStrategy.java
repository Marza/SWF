package se.marza.swf.framework.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.marza.swf.framework.response.Response;

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

	/**
	 * Returns the mount path.
	 *
	 * @return the mount path.
	 */
	String mountPath();

	Class<?> clazz();
}
