package se.marza.swf.framework.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * TODO: double check that all these are links generated are valid.
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public final class NetUtilTest
{
	@Test
	public void testLinkUrl()
	{
		assertEquals("a", NetUtil.linkUrl("/", "/a"));
		assertEquals("a/b", NetUtil.linkUrl("/", "/a/b"));
		assertEquals("a/b/c", NetUtil.linkUrl("/a", "/a/b/c")); // valid.

		assertEquals("../", NetUtil.linkUrl("/a/b", "/"));
		assertEquals("../a2", NetUtil.linkUrl("/a/b", "/a2"));
		assertEquals("../b2", NetUtil.linkUrl("/a/b/c", "/a/b2"));
		assertEquals("../../a2/b2", NetUtil.linkUrl("/a/b/c", "/a2/b2"));

		assertEquals("./", NetUtil.linkUrl("/a", "/"));
		assertEquals("./c2", NetUtil.linkUrl("/a/b/c", "/a/b/c2"));
	}
}
