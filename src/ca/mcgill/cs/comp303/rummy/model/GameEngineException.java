package ca.mcgill.cs.comp303.rummy.model;

/**
 * Indicates any problem with the state of the hand.
 */
@SuppressWarnings("serial")
public class GameEngineException extends Exception
{
	/**
	 * @param pMessage The exception message.
	 * @param pException The wrapped exception.
	 */
	public GameEngineException( String pMessage, Throwable pException ) 
	{
		super( pMessage, pException );
	}

	/**
	 * @param pMessage The exception message.
	 */
	public GameEngineException( String pMessage ) 
	{
		super( pMessage );
	}
	
	/**
	 * @param pException The wrapped exception
	 */
	public GameEngineException( Throwable pException )
	{
		super( pException );
	}

	
}
