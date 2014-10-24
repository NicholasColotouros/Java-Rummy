package ca.mcgill.cs.comp303.rummy.serialization;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.cs.comp303.rummy.model.GameEngine;

/**
 * 
 */
public class TestBinarySerializer
{
	private GameEngine aEngine;
	
	/**
	 * Setup the engine before each test.
	 */
	@Before
	public void setUp()
	{
		aEngine = GameEngine.getInstance();
	}
	
	/**
	 *
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testLoad()
	{
		fail("Not yet implemented");
	}

}
