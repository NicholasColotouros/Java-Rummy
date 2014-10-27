package ca.mcgill.cs.comp303.rummy.model;
import ca.mcgill.cs.comp303.rummy.exceptions.*;


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
	private Card aC3;
	private Card aC4;
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
		aC3 = new Card(Card.Rank.ACE, Card.Suit.SPADES);
		aC4 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
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
	

	/**
	 * 
	 */
	@Test
	public void testDiscard()
	{	
		try
		{
			aP1.discard(aC2);	
		}
		catch(CannotDiscardException e)
		{
			fail("ttDiscard failed: " + e.getClass().toString());
		}
		finally
		{
			assertTrue(aP1.getHand().size() == 0);
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void testDiscardHand()
	{	
		try
		{
			aP1.addCard(aC2);	
			aP1.addCard(aC3);	
			aP1.addCard(aC4);	
		}
		catch(CannotPerformActionException e)
		{
			fail( "testDiscardHand failed: " + e.getClass().toString());
		}
		finally
		{
			assertTrue(aP1.getHand().size() == 0);	
		}	
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetName()
	{	
		assertTrue("SmartAss".equals(aP1.getName()));		
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetScore()
	{	
		assertTrue(aP1.getScore() == 0);		
	}
	

}
