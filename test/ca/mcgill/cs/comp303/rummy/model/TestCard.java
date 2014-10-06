package ca.mcgill.cs.comp303.rummy.model;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 * @author Nathan
 *
 */
public class TestCard
{
	public Card aT1 = new Card(Card.Rank.ACE, Card.Suit.SPADES);
	public Card aT2 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
	public Card aT3 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS);
	public Card aT4 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS);
	
	@Test
	public void testGetRank()
	{
		assertTrue(aT1.getRank() == Card.Rank.ACE);
	}
	
	@Test
	public void testGetSuit()
	{
		assertTrue(aT1.getSuit() == Card.Suit.SPADES);
	}
	
	@Test
	public void testToString()
	{
		assertTrue(aT1.toString().equals("ACE of SPADES"));
	}
	
	@Test
	public void testCompareTo()
	{
		assertTrue(aT1.compareTo(null) == -1);
		assertTrue(aT1.compareTo(aT1) == 0);
	}
}
