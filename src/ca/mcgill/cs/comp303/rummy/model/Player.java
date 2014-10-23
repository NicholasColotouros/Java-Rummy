package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Represents a player that plays the game.
 */
public class Player implements Serializable, Iterable
{
	private final String aName;
	private int aScore;
	private Hand aHand;
	
	/**
	 * Default constructor.
	 * @param pName name of the player
	 */
	public Player(String pName)
	{
		aName = pName;
		aScore = 0;
		aHand = new Hand();
	}
	
	/**
	 * Gets the name of the player.
	 * @return the name of the player.
	 */
	public String getName()
	{
		return aName;
	}
	
	/**
	 * Adds a card to the players hand.
	 * @param pCard the card to be added
	 */
	public void addCard(Card pCard)
	{
		aHand.add(pCard);
	}
	
	/**
	 * Discards the specified card from the hand if it exists. 
	 * @param pCard the card to discard.
	 * @return true if the card was successfully removed. False if the card does not exist in the hand
	 */
	public boolean discard(Card pCard)
	{
		if(aHand.contains(pCard))
		{
			aHand.remove(pCard);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the players score.
	 * @return the players score.
	 */
	public int getScore()
	{
		return aScore;
	}

	@Override
	public Iterator iterator()
	{
		// TODO: what to iterator over (specify the generic)?
		return aHand.iterator();
	}
}
