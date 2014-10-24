package ca.mcgill.cs.comp303.rummy.exceptions;

/**
 * An exception caused by the exchange of hard drive data (saving, loading, etc)
 */
public class DataException extends JavaRummyException
{
	public DataException(){}
	public DataException(String pMessage){super(pMessage);}

}
