package ca.mcgill.cs.comp303.rummy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Nicholas
 */
public class CardSet implements ICardSet
{
	private ArrayList<Card> aCardSet;
	
	/**
	 * Default constructor.
	 */
	public CardSet()
	{
		aCardSet = new ArrayList<Card>();
	}
	
	/**
	 * Adds a card to the set. Nothing will happen if the card is already in the set.
	 * @param pCard The card to be added to the set
	 */
	public void add(Card pCard)
	{
		aCardSet.add(pCard);			
	}
	
	@Override
	public Iterator<Card> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Card pCard)
	{
		if(aCardSet.isEmpty()) 
		{
			return false;
		}

		if(aCardSet.contains(pCard)) 
		{
			return true;
		}
		
		return false;
	}

	@Override
	public int size()
	{
		return aCardSet.size();
	}

	@Override
	public boolean isGroup()
	{
		if(aCardSet.size() != 3 || aCardSet.size() != 4)
		{
			return false;
		}

		for(int i = 1; i < aCardSet.size(); i++)
		{
			Card.Rank firstRank = aCardSet.get(i-1).getRank();
			Card.Rank secondRank = aCardSet.get(i).getRank();
			if(firstRank != secondRank)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isRun()
	{
		if(aCardSet.size() < 3)
		{
			return false;
		}
		
		Collections.sort(aCardSet);
		
		// If it is a valid run, then each rank < next rank
		for(int i = 1; i < aCardSet.size(); i++)
		{
			Card.Rank previous = aCardSet.get(i-1).getRank();
			Card.Rank current = aCardSet.get(i).getRank();
			
			if(previous.ordinal() >= current.ordinal())
			{
				return false;
			}
		}
		
		return true;
	}

}
