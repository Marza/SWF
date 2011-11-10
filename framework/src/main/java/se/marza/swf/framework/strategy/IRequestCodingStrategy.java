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
	boolean matches(HttpServletRequest request, HttpServletResponse response);
	Response response(HttpServletRequest request, HttpServletResponse response);
}
