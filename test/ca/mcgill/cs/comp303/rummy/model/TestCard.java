package ca.mcgill.cs.comp303.rummy.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Nicholas Nathan Colotoutors
 *
 */
public class TestCard
{
	private Card aT1;
	private Card aT2;
	private Card aT3;
	private Card aT4;
	
	/**
	 * 
	 */
	@Before
	public void setUp()
	{
		aT1 = new Card(Card.Rank.ACE, Card.Suit.SPADES);
		aT2 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
		aT3 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS);
		aT4 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetRank()
	{
		assertTrue(aT1.getRank() == Card.Rank.ACE);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetSuit()
	{
		assertTrue(aT1.getSuit() == Card.Suit.SPADES);
	}
	
	/**
	 * 
	 */
	@Test
	public void testToString()
	{
		assertTrue(aT1.toString().equals("ACE of SPADES"));
	}
	
	/**
	 * 
	 */
	@Test
	public void testCompareTo()
	{
		assertTrue(aT1.compareTo(null) == -1);
		assertTrue(aT1.compareTo(aT1) == 0);
	}
}
