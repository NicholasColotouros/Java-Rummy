package ca.mcgill.cs.comp303.rummy.bots;

import java.util.Observer;

import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * The interface that the game AI will use.
 */
public interface RummyBot extends Observer
{
	/**
	 * Decides if the AI wants to draw from the deck or the discard pile .
	 * @return true to draw from the deck. False for drawing from the discard pile.
	 */
	boolean drawFromDeck();
	
	/**
	 * Decides which card to discard.
	 * @return the card to discard.
	 */
	Card discard();
	
	/**
	 * Decides if the AI wants to knock or not.
	 * @return true to knock.
	 */
	boolean knock();	
}
