package ca.mcgill.cs.comp303.rummy.serialization;

import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;

/**
 * Serializes the game to and from JSon.
 */
public class JSonSerializer implements Serializer
{

	@Override
	public void save(GameEngine pEngine, String pPath) throws SaveException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameEngine load(String pPath) throws LoadException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
