package ca.mcgill.cs.comp303.rummy.serialization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;

import com.google.gson.Gson;

/**
 * Serializes the game to and from JSon.
 */
public class JSonSerializer implements Serializer
{

	@Override
	public void save(GameEngine pEngine, String pPath) throws SaveException
	{
		try
		{
			File file = new File(pPath);
			if(file.exists())  
			{
				file.delete();
			}
			
			Gson gsonSaver = new Gson();

			BufferedWriter out = new BufferedWriter(new FileWriter(pPath));
			out.write(gsonSaver.toJson(pEngine));
			out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public GameEngine load(String pPath) throws LoadException
	{
		if(!(new File(pPath).exists()))
		{
			throw new LoadException("Unable to load game: File does not exist.");
		}
		
		BufferedReader in = new BufferedReader();
		// TODO test
		return null;
	}
	
}
