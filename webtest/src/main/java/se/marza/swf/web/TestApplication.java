package se.marza.swf.web;

import se.marza.swf.framework.SwfApplication;
import se.marza.swf.framework.renderer.JsoupPageRenderer;
import se.marza.swf.framework.strategy.StylesheetRequestCodingStrategy;
import se.marza.swf.web.css.MyStylesheet;
import se.marza.swf.web.pages.ErrorPage;
import se.marza.swf.web.pages.Test404Page;
import se.marza.swf.web.pages.IndexPage;
import se.marza.swf.web.pages.OtherPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class TestApplication extends SwfApplication
{
	/**
	 * Constructor.
	 */
	public TestApplication()
	{
		this.mountPage("/", IndexPage.class);
		this.mountPage("/other", OtherPage.class);

		this.mount(new StylesheetRequestCodingStrategy("/other/styles/mystyle.css", MyStylesheet.class));

		this.setNotFoundPageClass(Test404Page.class);
		this.setErrorPageClass(ErrorPage.class);

		this.setPageRenderer(JsoupPageRenderer.INSTANCE);
	}

	/**
	 * @see SwfApplication#get()
	 *
	 * @return the application.
	 */
	public static TestApplication get()
	{
		return (TestApplication) SwfApplication.get();
	}
}
