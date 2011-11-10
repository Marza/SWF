package se.marza.swf.web;

import se.marza.swf.framework.SwfApplication;
import se.marza.swf.web.pages.Test404Page;
import se.marza.swf.web.pages.TestPage;
import se.marza.swf.web.pages.TestOtherPage;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class TestApplication extends SwfApplication
{
	public TestApplication()
	{
		this.mountPage("/", TestPage.class);
		this.mountPage("/other", TestOtherPage.class);

		this.setNotFoundPageClass(Test404Page.class);
	}

	public static TestApplication get()
	{
		return (TestApplication) SwfApplication.get();
	}
}
