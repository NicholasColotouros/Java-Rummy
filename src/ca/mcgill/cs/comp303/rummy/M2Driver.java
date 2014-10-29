package ca.mcgill.cs.comp303.rummy;

import ca.mcgill.cs.comp303.rummy.bots.RandomBot;
import ca.mcgill.cs.comp303.rummy.bots.RummyBot;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;
import ca.mcgill.cs.comp303.rummy.model.Player;

/**
 * The driver for M2 which simulates games of Rummy with two AIs.
 */
public class M2Driver
{
	/**
	 * Takes as input an integer and plays that many games against the AI.
	 * @param pArgs the number of games to be played against the AI
	 */
	public static void main(String[] pArgs)
	{
		int numGames = Integer.parseInt(pArgs[0]);
		GameEngine game = GameEngine.getInstance();
		
		for(int i = 0; i < numGames; i++)
		{			
			// Start of game
			game.newGame("HAL 9000", "HAL 9001");
		
			RummyBot bot1 = new RandomBot(game.getPlayer1().getHand());
			RummyBot bot2 = new RandomBot(game.getPlayer2().getHand());

			
			while(game.getDeckSize() > 2)
			{
				// Player 1 turn
				if(playTurn(bot1, game.getPlayer1()))
				{
					break;
				}
				
				// Player 2 turn
				else if(playTurn(bot2, game.getPlayer2()))
				{
					break;
				}
			}
			
			
			// TODO: play the games
			// When drawing, add the last card to the end of the hand, and then sort only AFTER discard
		}
	}
	
	private static boolean playTurn(RummyBot pBot, Player pPlayer)
	{
		// TODO
		return false;
	}
}
