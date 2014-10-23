package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * LoadException is thrown when saving
 */
public class LoadException extends Exception 
{
	public LoadException(){}
	public LoadException(String pMessage){super(pMessage);}
}
