package ca.mcgill.cs.comp303.rummy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 */
public class CardSet implements ICardSet
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
		if (aCards.size() != 3 || aCards.size() != 4)
		{
			return false;
		} //Need to have three or more cards
		
		ArrayList<Card> aList = new ArrayList<Card>(aCards);
		
		Card.Rank rank = null; //Loop through cards, check if they have the same rank
		for(Card c1 : aList) 
		{
			if(rank == null) 
			{ 
				rank = c1.getRank(); 
			} //set the first one, then check each after
			else if( rank.ordinal() != (c1.getRank().ordinal()) ) 
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
		} //Need to have three or more cards
		
		ArrayList<Card> aList = new ArrayList<Card>(aCards);
		Collections.sort(aList, new Comparator<Card>() { //Sorts the cards, so we can check if they are sequential
			public int compare(Card pC1, Card pC2) 
			{
				if(pC1.getRank().ordinal() > pC2.getRank().ordinal()) 
				{
					return Integer.MAX_VALUE;
				}
				else if(pC1.getRank().ordinal() == pC2.getRank().ordinal()) 
				{ 
					return Integer.MIN_VALUE; 
				} 
				else 
				{
					return 0; 
				}
			};
		});
		
		Card.Suit suit = null;
		Card.Rank prevRank = null; //Loop through cards, check if each is 1 more than previous
		for(Card c1 : aList) 
		{
			if (suit == null) 
			{
				suit = c1.getSuit(); 
			}
			if(prevRank == null) 
			{ 
				prevRank = c1.getRank(); 
			} //set the first one, then check each after
			else if( (prevRank.ordinal() != (c1.getRank().ordinal() + 1)) || suit != c1.getSuit()) 
			{
					return false;
			}
		}
		return true; //there are >=3 and in order they are consecutive
	}
}