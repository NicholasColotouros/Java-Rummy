package ca.mcgill.cs.comp303.rummy;

import ca.mcgill.cs.comp303.rummy.bots.RandomBot;
import ca.mcgill.cs.comp303.rummy.bots.RummyBot;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotDiscardException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;
import ca.mcgill.cs.comp303.rummy.model.Player;

/**
 * The driver for M2 which simulates games of Rummy with two AIs.
 */
public class M2Driver
{
	private static GameEngine aGame = GameEngine.getInstance();
	
	/**
	 * Takes as input an integer and plays that many games against the AI.
	 * @param pArgs the number of games to be played against the AI
	 */
	public static void main(String[] pArgs)
	{
		int numGames = Integer.parseInt(pArgs[0]);
		
		for(int i = 0; i < numGames; i++)
		{			
			// Start of game
			aGame.newGame("HAL 9000", "HAL 9001");
		
			RummyBot bot1 = new RandomBot(aGame.getPlayer1().getHand());
			RummyBot bot2 = new RandomBot(aGame.getPlayer2().getHand());

			
			while(aGame.getDeckSize() > 2)
			{
				// Player 1 turn, break if knocked or game over
				if(playTurn(bot1, aGame.getPlayer1()))
				{
					break;
				}
				
				// Player 2 turn
				else if(playTurn(bot2, aGame.getPlayer2()))
				{
					break;
				}
			}
			aGame.reset(); // round over, no winner
		}
	}
	
	private static boolean playTurn(RummyBot pBot, Player pPlayer)
	{
		if(pBot.drawFromDeck())
		{
			try
			{
				aGame.drawFromDeck(pPlayer);
			}
			catch (CannotDrawException e)
			{
				try
				{
					aGame.drawFromDiscardPile(pPlayer);
				}
				catch (CannotDrawException e1)
				{
					return true;
				}
			}
		}
		else
		{
			try
			{
				aGame.drawFromDeck(pPlayer);
			}
			catch (CannotDrawException e)
			{
				try
				{
					aGame.drawFromDeck(pPlayer);
				}
				catch (CannotDrawException e1)
				{
					return true;
				}
				
			}
		}
		
		while(true)
		{
			try
			{
				pPlayer.discard(pBot.discard());
			}
			catch (CannotDiscardException e)
			{
				continue;
			}
			break;
		}
		return pBot.knock();
	}
}