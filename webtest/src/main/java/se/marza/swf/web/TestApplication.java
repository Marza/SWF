package se.marza.swf.web;

import se.marza.swf.framework.SwfApplication;
import se.marza.swf.web.pages.Test404Page;
import se.marza.swf.web.pages.IndexPage;
import se.marza.swf.web.pages.OtherPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class TestApplication extends SwfApplication
{
	/**
	 * Constructor.
	 */
	public TestApplication()
	{
		this.mountPage("/", IndexPage.class);
		this.mountPage("/other", OtherPage.class);

		this.setNotFoundPageClass(Test404Page.class);
	}

	public static TestApplication get()
	{
		return (TestApplication) SwfApplication.get();
	}
}
