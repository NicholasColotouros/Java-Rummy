package ca.mcgill.cs.comp303.rummy;

import ca.mcgill.cs.comp303.rummy.model.GameEngine;

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
			
			// TODO: play the games
			// When drawing, add the last card to the end of the hand, and then sort only AFTER discard
		}
	}
}
