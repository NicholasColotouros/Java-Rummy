package ca.mcgill.cs.comp303.rummy.bots;

import java.util.Random;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;
import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.GamePhase;
import ca.mcgill.cs.comp303.rummy.model.Hand;
import ca.mcgill.cs.comp303.rummy.model.Player;

/**
 * Random bot does all random moves and knocks when possible.
 * Discards a randomly unmatched card.
 */
public class RandomBot implements RummyBot
{
	private Hand aHand;
	private Random aRandom = new Random();
	private boolean aFirstCall = false;
	private Player aSelf;
	
	/**
	 * Default constructor. 
	 * @param pHand The bots hand.
	 */
	public RandomBot(Hand pHand)
	{
		this.aHand = pHand;
		this.aSelf = GameEngine.getInstance().getPlayer2();
	}

	@Override
	public void gameUpdate(GamePhase pCurrentPhase, Card pCard)
	{
		// If the AIs turn just started.
		if(pCurrentPhase.equals(GamePhase.AI_TURN) && !aFirstCall)
		{
			playTurn();
		}
		
		// Turn just started
		if(pCurrentPhase.equals(GamePhase.FIRSTDRAW))
		{
			aSelf = GameEngine.getInstance().getPlayer2();
			aHand = aSelf.getHand();
		}
	}

	@Override
	public void gameEnded(boolean pP1Knocked, boolean pP1Won, boolean pEndByDeckShortage, int pPointsGained)
	{
		// do nothing if the game ended
	}

	@Override
	public void playTurn()
	{
		// Draw randomly if the deck size doesn't end in game over
		if(GameEngine.getInstance().getDeckSize() > 2 && aRandom.nextBoolean())
		{
			GameEngine.getInstance().drawFromDeck(aSelf);
		}
		else
		{
			try
			{
				GameEngine.getInstance().drawFromDiscardPile(aSelf);
			}
			catch (CannotDrawException e)
			{				
				GameEngine.getInstance().drawFromDeck(aSelf);
			}
		}
		
		// Discard randomly and knock
		Card[] unmatched = (Card[]) this.aHand.getUnmatchedCards().toArray();
		Card discardCard;		
		if(unmatched.length == 0)
		{
			discardCard = unmatched[this.aRandom.nextInt() % unmatched.length];
		}
		else
		{
			int randomIndex = this.aRandom.nextInt() % this.aHand.size();
			discardCard = this.aHand.getCards().get(randomIndex);			
		}
		
		boolean canKnock = false;
		if(this.aHand.score() <= GameEngine.HAND_SIZE) 
		{
			canKnock = true;
		}
		
		// now do the actual draw action
		try
		{
			GameEngine.getInstance().discard(aSelf, discardCard, canKnock);
		}
		catch (CannotPerformActionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
