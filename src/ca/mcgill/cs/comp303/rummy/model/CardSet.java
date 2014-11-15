package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 */
public class CardSet implements ICardSet, Serializable
{
	private HashSet<Card> aCards;
	
	CardSet(Set<Card> pInput) 
	{
		aCards = new HashSet<Card>();
		for (Card c1 : pInput) 
		{
			Card c2 = new Card(c1.getRank(), c1.getSuit());
			aCards.add(c2);
		}
	}
	
	@Override
	public Iterator<Card> iterator()
	{
		return aCards.iterator();
	}

	@Override
	public boolean contains(Card pCard)
	{
		return aCards.contains(pCard);
	}

	@Override
	public int size()
	{
		return aCards.size();
	}

	@Override
	public boolean isGroup()
	{
		// Need to have 3 or 4 cards
		if (aCards.size() < 3 || aCards.size() > 4)
		{
			return false;
		}
		
		ArrayList<Card> aList = new ArrayList<Card>(aCards);
		
		Card.Rank rank = aList.get(0).getRank(); 
		
		//Loop through cards, check if they have the same rank
		for(Card c : aList) 
		{
			//set the first one, then check each after
			if( rank.ordinal() != (c.getRank().ordinal()) ) 
			{
					return false;
			}
		}
		return true; //returns true if they all have the same rank
	}

	@Override
	public boolean isRun()
	{
		if (aCards.size() < 3) 
		{
			return false;
		}
		
		//Need to have three or more cards
		ArrayList<Card> aList = new ArrayList<Card>(aCards);
		Collections.sort(aList);
		
		Card.Suit suit = null;
		Card.Rank prevRank = null; 
		
		//Loop through cards, check if each is 1 more than previous
		for(Card c : aList) 
		{
			if (suit == null) 
			{
				suit = c.getSuit(); 
			}
			if(prevRank == null) 
			{ 
				prevRank = c.getRank(); 
			} 
			
			else if( suit != c.getSuit() && prevRank.ordinal() >= c.getRank().ordinal()) 
			{
					return false;
			}
			prevRank = c.getRank();
		}
		return true;
	}
}