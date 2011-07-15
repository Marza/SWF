package com.ackja.swf.framework.strategy;

import com.ackja.swf.framework.response.Response;
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
