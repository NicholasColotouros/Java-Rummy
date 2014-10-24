package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * Exception is thrown when an action cannot be performed.
 */
public class CannotPerformActionException extends JavaRummyException
{
	public CannotPerformActionException(){}
	public CannotPerformActionException(String pMessage){super(pMessage);}

}
