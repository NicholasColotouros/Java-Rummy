package ca.mcgill.cs.comp303.rummy.logging;

import java.util.logging.Level;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Player;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.Event;

/**
 * 
 */
public interface ILoggerObserver
{
	/**
	 * @param pPriority the logging priority
	 * @param pEvent the event
	 * @param pPlayer the player in the event
	 * @param pCard the card in the event
	 * 
	 
	 */ 
	void logEvent(Level pPriority, Event pEvent, Player pPlayer, Card pCard);	
//	
//	void reset();
//	
//	void discard(Player pPlayer, Card pCard);
//	
//	void drawFromDeck(Player pPlayer);
//	
	/**
	 * @param pPriority the logging priority
	 * @param pKnocker the player who knocks
	 * @param pPlayer1 the player who wins after the knock
	 * @param pPlayer2 the player who loses after the knock
	 * @param pScoreDifference the score difference between players after the knock
	 * @param pIsTied return True if it is a tie after knock
	 */
	void logKnock(Level pPriority, Player pKnocker, Player pPlayer1, Player pPlayer2, int pScoreDifference, boolean pIsTied);

}
