package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;

/**
 * An immutable description of a playing card.
 */
public final class Card implements Comparable<Card>, Serializable
{
	/**
	 * Represents the rank of the card.
	 */
	public enum Rank 
	{ ACE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }
	
	/**
	 * Represents the suit of the card.
	 */
	public enum Suit 
	{ CLUBS, DIAMONDS, HEARTS, SPADES }
	
	private final Rank aRank;
	
	private final Suit aSuit;
	
	/**
	 * Create a new card object. 
	 * @param pRank The rank of the card.
	 * @param pSuit The suit of the card.
	 */
	public Card(Rank pRank, Suit pSuit )
	{
		aRank = pRank;
		aSuit = pSuit;
	}
	
	/**
	 * Obtain the rank of the card.
	 * @return An object representing the rank of the card.
	 */
	public Rank getRank()
	{
		return aRank;
	}
	
	/**
	 * Obtain the suit of the card.
	 * @return An object representing the suit of the card 
	 */
	public Suit getSuit()
	{
		return aSuit;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @return See above.
	 */
	public String toString()
	{
		return aRank + " of " + aSuit;
	}

	/**
	 * Compares two cards according to gin rules (ace is low, suits 
	 * run as Spade, Hearts, Diamonds, Clubs (high to low)).
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param pCard The card to compare to
	 * @return Returns a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, or greater than pCard
	 */
	public int compareTo(Card pCard)
	{
		if(pCard == null) return -1;
		// This card's rank is lower, therefore the other card is better
		if(aRank.ordinal() < pCard.aRank.ordinal())
		{
			return -1;
		}
		
		// This card's rank is higher, therefore the other card is worse
		else if(aRank.ordinal() < pCard.aRank.ordinal()) 
		{
			return 1;
		}
		
		// If both ranks are the same, compare the suits
		// RECALL: the order of the suits in the enum type are in
		// ASCENDING ORDER
		else
		{
			if(aSuit.ordinal() < pCard.aSuit.ordinal())
			{
				return 1;
			}
			
			else if(aSuit.ordinal() > pCard.aSuit.ordinal())
			{
				return -1;
			}
			
			return 0; // The cards are the same
		}
	}

	/**
	 * Two cards are equal if they have the same suit and rank.
	 * @param pCard The card to test.
	 * @return true if the two cards are equal
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object pCard ) 
	{
		if(pCard == null) 
		{
			return false;
		}
		
		if(this.aRank.equals(((Card)pCard).aRank))
		{
			if(this.aSuit.equals(((Card)pCard).aSuit))
			{
				return true;
			}
		}
		return false;
	}

	/** 
	 * The hashcode for a card is the suit*13 + that of the rank (perfect hash).
	 * @return the hashcode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		int numRanks = Rank.values().length;
		return aSuit.ordinal() * numRanks + aRank.ordinal();
	}
}
