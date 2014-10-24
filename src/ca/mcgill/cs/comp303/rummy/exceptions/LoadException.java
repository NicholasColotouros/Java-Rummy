package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * LoadException is thrown when loading.
 */
public class LoadException extends DataException 
{
	public LoadException(){}
	public LoadException(String pMessage){super(pMessage);}
}
