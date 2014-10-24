package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * Exception caused by saving.
 */
public class SaveException extends DataException
{
	public SaveException(){}
	public SaveException(String pMessage){super(pMessage);}
}
