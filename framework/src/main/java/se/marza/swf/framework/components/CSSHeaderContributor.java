package se.marza.swf.framework.components;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public class CSSHeaderContributor implements HeaderContributor
{
	private final String href;

	public CSSHeaderContributor(final String href)
	{
		this.href = href;
	}

	public String href()
	{
		return this.href;
	}
}
