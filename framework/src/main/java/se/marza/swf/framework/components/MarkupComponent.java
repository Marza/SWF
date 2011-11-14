package se.marza.swf.framework.components;

/**
 *
 * @author Tony Marjakangas | tony@marza.se
 */
public interface MarkupComponent extends Component
{
	String markupId();
	String markupTag();
	String markupAttribute();
	String markupAttributeValue();
}
