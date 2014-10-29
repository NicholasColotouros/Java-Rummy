package ca.mcgill.cs.comp303.rummy.serialization;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;

/**
 * .
 */
public class TestSerializer
{
	private GameEngine aEngine = GameEngine.getInstance();
	private String aBinaryPath = new String("savegame.dat");
	private String aJSonPath = new String("savegame.json");
	
	/**
	 * .
	 */
	@Before
	public void setUp()
	{
		aEngine.newGame("Jack", "Jill");
	}
	
	/**
	 * .
	 */
	@Test
	public void testBinarySerializer()
	{
		BinarySerializer saver = new BinarySerializer();

		try
		{
			saver.save(aEngine, aBinaryPath);
		}
		catch (SaveException e)
		{
			e.printStackTrace();
			fail("Exception thrown while saving in binary");
		}
		
		try
		{
			GameEngine loadedEngine = saver.load(aBinaryPath);
			// TODO: compare the engines
		}
		catch (LoadException e)
		{
			e.printStackTrace();
			fail("Exception thrown while loading in binary");
		}
	}
	
	/**
	 * .
	 */
	@Test
	public void testJSonSerializer()
	{
		JSonSerializer saver = new JSonSerializer();

		try
		{
			saver.save(aEngine, aJSonPath);
		}
		catch (SaveException e)
		{
			e.printStackTrace();
			fail("JSon: Exception thrown while saving in json");
		}
		
		try
		{
			GameEngine loadedEngine = saver.load(aJSonPath);
		}
		catch (LoadException e)
		{
			e.printStackTrace();
			fail("Exception thrown while loading in json");
		}
		
	}
	
	/**
	 * .
	 */
	@After
	public void cleanUp()
	{
		File binFile = new File(aBinaryPath);
		File jsonFile = new File(aJSonPath);
		
		if(binFile.exists())
		{
			binFile.delete();
		}
		if(jsonFile.exists())
		{
			jsonFile.delete();
		}
	}
}
