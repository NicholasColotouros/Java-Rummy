package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * Thrown when drawing from a deck is impossible.
 */
@SuppressWarnings("serial")
public class CannotDiscardException extends CannotPerformActionException
{
	/**
	 * .
	 */
	public CannotDiscardException(){}
	
	/**
	 * 
	 * @param pMessage the message to be passed.
	 */
	public CannotDiscardException(String pMessage)
	{
		super(pMessage); 
	}
}
