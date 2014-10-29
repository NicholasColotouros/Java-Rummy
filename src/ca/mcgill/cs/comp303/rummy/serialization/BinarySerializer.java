package ca.mcgill.cs.comp303.rummy.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;

/**
 * Serializes and loads the game in binary.
 */
public class BinarySerializer implements Serializer
{

	@Override
	public void save(GameEngine pEngine, String pPath) throws SaveException
	{
		try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(pPath)))
		{
			// TODO: check if the file name and directory are valid
			
			// Delete the file if it already exists
			File outputFile = new File(pPath);
			if(outputFile.exists())
			{
				outputFile.delete();
			}
	
			
			outStream.writeObject(pEngine);
			outStream.close();
		}
		catch (FileNotFoundException e)
		{
			throw new SaveException("File not found.");
		}
		catch (IOException e)
		{
			throw new SaveException("Error: could not save");
		}
	}

	@Override
	public  GameEngine load(String pPath) throws LoadException
	{
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(pPath)))
		{
			// If the file does not exist, throw an exception
			if(!(new File(pPath).exists())) 
			{
				throw new LoadException("File does not exist.");
			}
			
			
			GameEngine ret = (GameEngine) in.readObject();
			return ret;
		}
		catch (IOException | ClassNotFoundException e)
		{
			throw new LoadException("Error: could not load");
		}		
	}
}
