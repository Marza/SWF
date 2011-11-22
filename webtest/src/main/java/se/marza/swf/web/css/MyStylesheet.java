package se.marza.swf.web.css;

import se.marza.swf.framework.components.Stylesheet;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class MyStylesheet extends Stylesheet
{

	@Override
	public String css()
	{
		return "* {" +
		"margin: 0;" +
		"padding: 0;" +
		"border: 0;" +
	"}"+
	"body, html {"+
		"text-align: center;"+
		"height: 100%;"+
		"width: 100%;"+
	"}"+
	"body, a {"+
		"background: #232323;"+
		"color: #fff;"+
	"}";
	}
}
