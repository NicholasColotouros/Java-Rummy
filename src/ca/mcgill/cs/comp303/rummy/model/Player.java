package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;

/**
 * Represents a player that plays the game.
 */
public class Player implements Serializable
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
	 * Returns the players score.
	 * @return the players score.
	 */
	public int getScore()
	{
		return aScore;
	}
}
