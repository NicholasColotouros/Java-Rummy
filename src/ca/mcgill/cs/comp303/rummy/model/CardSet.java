package ca.mcgill.cs.comp303.rummy.model;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Nicholas
 */
public class CardSet implements ICardSet
{
	private HashSet<Card> aCardSet;
	
	/**
	 * Default constructor.
	 */
	public CardSet()
	{
		aCardSet = new HashSet<Card>();
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
		
		for(Card c : aCardSet)
		{
			if(c.equals(pCard))
			{
				return true;
			}
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRun()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
