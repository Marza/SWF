package com.ackja.swf.web;

import com.ackja.swf.web.pages.Test404Page;
import com.ackja.swf.web.pages.TestPage;
import com.ackja.swf.web.pages.TestOtherPage;
import com.ackja.swf.framework.SwfApplication;

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

	public String other()
	{
		return "OTHER";
	}
}
