package se.marza.swf.framework.components;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public abstract class CSSComponent implements Component
{
	public abstract String markupId();
	public abstract String markupTag();
	public abstract String markupAttribute();
	public abstract String markupAttributeValue();
}
