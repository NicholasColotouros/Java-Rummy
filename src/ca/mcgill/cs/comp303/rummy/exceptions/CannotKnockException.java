package ca.mcgill.cs.comp303.rummy.exceptions;

public class CannotKnockException extends CannotPerformActionException
{
	public CannotKnockException(){}
	public CannotKnockException(String pMessage){super(pMessage);}
}
