package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Iterator;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;

/**
 * Represents a player that plays the game.
 */
public class Player implements Serializable
{
	private static final long serialVersionUID = 6763429439067353883L;
	private final String aName;
	private int aScore;
	private Hand aHand;
	private Card aLastCardDrawn;
	
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
	 * Adds a card to the players hand.
	 * @param pCard the card to be added
	 */
	public void addCard(Card pCard)
	{
		aHand.add(pCard);
		aLastCardDrawn = pCard;
	}
	
	/**
	 * Discards the specified card from the hand if it exists. 
	 * @param pCard the card to discard.
	 * @return true if the card was successfully removed. False if the card does not exist in the hand
	 * @throws CannotPerformActionException if the card that is going to be discarded was the last card to be drawn
	 */
	public boolean discard(Card pCard) throws CannotPerformActionException
	{
		if(pCard.equals(aLastCardDrawn)) 
		{
			throw new CannotPerformActionException("Cannot discard the last card drawn.");
		}
		if(aHand.contains(pCard))
		{
			aHand.remove(pCard);
			return true;
		}
		return false;
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
	 * Returns the players score.
	 * @return the players score.
	 */
	public int getScore()
	{
		return aScore;
	}
	
	/**
	 * Gets the hand contained in the player.
	 * @return a copy of the players hand.
	 */
	public Hand getHand()
	{
		return new Hand(aHand);
	}
}
