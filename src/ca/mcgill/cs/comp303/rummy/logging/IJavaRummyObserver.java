package ca.mcgill.cs.comp303.rummy.logging;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.GamePhase;

/**
 * 
 * @author Nathan
 *
 */
public interface IJavaRummyObserver
{
	/**
	 * Update method for the Observers.
	 * @param pCurrentPhase The current phase of the game.
	 * @param pCard The last visible card drawn/played. Null if drawn from deck.
	 */
	void gameUpdate(GamePhase pCurrentPhase, Card pCard);
	
	/**
	 * Update method to signal the start of the end of a round.
	 * @param pP1Knocked true if player 1 knocked to end the round.
	 * @param pP1Won true if player 1 won the round.
	 * @param pEndByDeckShortage true if game has ended due to deck not having enough cards
	 * @param pPointsGained the amount of points the winning player gained.
	 */
	void gameEnded(boolean pP1Knocked, boolean pP1Won, boolean pEndByDeckShortage, int pPointsGained);
}
