package ca.mcgill.cs.comp303.rummy.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Player;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.Event;


/**
 * Logs to console.
 */
public class ConsoleLogger implements ILoggerObserver
{
	private Logger aLogger;
	
	/**
	 * Default constructor.
	 * @param pName the name of the logger.
	 */
	public ConsoleLogger(String pName)
	{
		aLogger = Logger.getLogger(pName);
		aLogger.info(pName + ": logger initialized");
	}
	
	@Override
	public void logEvent(Level pPriority, Event pEvent, Player pPlayer, Card pCard)
	{
		switch(pEvent)
		{
			case RESET:
				aLogger.log(Level.INFO, "Starting new round.");	
				break;
			case DISCARD:
				String aMessage = "Player " + pPlayer.getName() + "discards" + pCard.toString();
				aLogger.log(Level.INFO,  aMessage);
				break;
			case DRAWFROMDECK:
				String bMessage = "Player: " + pPlayer.getName() + " draws from the deck.";
				aLogger.log(Level.INFO, bMessage);	
				break;
			case DRAWFROMDISCARDPILE:
				String cMessage = "Player " + pPlayer.toString() + " draws from discard pile " + pCard.toString();
				aLogger.log(Level.INFO, cMessage);	
				break;
			default:
				aLogger.log(Level.WARNING, "No event is logged.");
			
		}
	}
	
	@Override
	public void logKnock(Level pPriority, Player pKnocker, Player pPlayer1, Player pPlayer2, int pScoreDifference, boolean pIsTied)
	{
		String message = "Player "+ pKnocker.getName() + " knocks.\n";
		String messsge2 = pPlayer1.getName() + " has a score of " + pPlayer1.getHand().score() + "\n";
		String message3 = pPlayer2.getName() + " has a score of " + pPlayer2.getHand().score() + "\n";
		aLogger.log(Level.INFO, message);	
		aLogger.log(Level.INFO, messsge2);
		aLogger.log(Level.INFO, message3);
		if (!pIsTied)
		{
			aLogger.log(Level.INFO, "Player: " + pPlayer1.getName() + " wins and gains " + pScoreDifference + " points.");
		}
		else 
		{
			aLogger.log(Level.INFO, "Game tied.");
		}
	}
}


