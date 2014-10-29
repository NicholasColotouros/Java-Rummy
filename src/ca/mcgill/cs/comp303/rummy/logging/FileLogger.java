package ca.mcgill.cs.comp303.rummy.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ca.mcgill.cs.comp303.rummy.exceptions.DataException;

/**
 * Logs to specified file.
 */
public class FileLogger implements ILoggerObserver
{
	private Logger aLogger;
	private FileHandler aFH;

	
	/**
	 * Logs to the specified file. If the file does not exist, it will be created.
	 * If the directory does not exist, an exception is thrown.
	 * @param pPath the complete path to save the file.
	 * @param pName the name of the logger
	 * @throws DataException if the directory does not exist.
	 */
	public FileLogger(String pPath, String pName) throws DataException
	{
		try
		{
			// TODO: make sure directory is valid.
			File file = new File(pPath);
			if(file.isDirectory())
			{
				throw new DataException("Invalid file name.");
			}
			if(file.exists())
			{
				file.delete();
			}
			
			aLogger = Logger.getLogger(pName);
			aLogger.addHandler(aFH);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        aFH.setFormatter(formatter);
	        
	        aLogger.info(pName + ": logger initialized.");
		}
		catch (SecurityException e) 
		{
	        e.printStackTrace();  
	    } 
	}

	@Override
	public void logEvent(Level pPriority, String pMessage)
	{
		aLogger.log(pPriority, pMessage);
	}
}
