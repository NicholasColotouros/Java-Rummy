package ca.mcgill.cs.comp303.rummy.logging;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Logs to console.
 */
public class ConsoleLogger implements ILoggerObserver
{
	private Logger aLogger;
	
	/**
	 * Default constructor.
	 * @param pName the name of the logger.
	 */
	public ConsoleLogger(String pName)
	{
		aLogger = Logger.getLogger(pName);
	}
	@Override
	public void updateLoggerObservers(Level pPriority, String pMessage)
	{
		aLogger.log(pPriority, pMessage);
	}
}
