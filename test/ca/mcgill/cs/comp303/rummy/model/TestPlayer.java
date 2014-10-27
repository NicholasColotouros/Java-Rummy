package ca.mcgill.cs.comp303.rummy.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;


/**
 * 
 * @author Kristine
 *
 */
public class TestPlayer
{
	private Player aP1;
	private Card aC1;
	private Card aC2;
	private Hand aH1;
	/**
	 * 
	 */
	@Before
	public void setUp()
	{
		aP1 = new Player("Smart Ass");
		aC1 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS);
		aC2 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS);
		aH1.add(aC1);
				
	}
	
	/**
	 * 
	 */
	@Test
	public void testAddCard()
	{	
		try
		{
			aP1.addCard(aC2);	
		}
		catch(CannotPerformActionException e)
		{
			fail("testAddCard failed: " + e.getClass().toString());
		}
		finally
		{
			assertTrue(aP1.getHand().equals(aH1));
		}
	}
	
}
