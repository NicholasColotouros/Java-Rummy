package ca.mcgill.cs.comp303.rummy.model;

import java.util.logging.Level;

/**
 * 
 */
public interface ILoggerObserver
{
	/**
	 * @param pPriority priority
	 * @param pMessage the message
	 *
	 */ 
	void update(Level pPriority, String pMessage);	
}
