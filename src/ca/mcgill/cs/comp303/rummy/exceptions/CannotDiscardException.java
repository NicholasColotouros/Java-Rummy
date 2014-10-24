package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * Thrown when drawing from a deck is impossible.
 */
public class CannotDiscardException extends CannotPerformActionException
{
	public CannotDiscardException(){}
	public CannotDiscardException(String pMessage){super(pMessage);}
}
