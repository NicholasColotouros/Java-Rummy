package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * Thrown when the user cannot draw from a deck (either there are less than 2 cards left in the deck
 * or an empty discard pile.
 */
public class CannotDrawException extends CannotPerformActionException
{
	public CannotDrawException(){}
	public CannotDrawException(String pMessage){super(pMessage);}
}
