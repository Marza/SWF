package se.marza.swf.web;

import se.marza.swf.framework.SwfApplication;
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

		this.setNotFoundPageClass(Test404Page.class);
		this.setErrorPageClass(ErrorPage.class);
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
