package ca.mcgill.cs.comp303.rummy.logging;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.mcgill.cs.comp303.rummy.exceptions.DataException;

/**
 * Logs to specified file.
 */
public class FileLogger implements ILoggerObserver
{
	Logger aLogger;
	
	/**
	 * Logs to the specified file. If the file does not exist, it will be created.
	 * If the directory does not exist, an exception is thrown.
	 * @param pPath the complete path to save the file.
	 * @throws DataException if the directory does not exist.
	 */
	public FileLogger(String pPath) throws DataException
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
		
		// TODO initialize observer
	}

	@Override
	public void updateLoggerObservers(Level pPriority, String pMessage)
	{
		aLogger.log(pPriority, pMessage);
	}
}
