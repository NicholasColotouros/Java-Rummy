package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Stack;

import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.serialization.Serializer;

/**
 * The heart and soul of the game. Responsible for managing the game.
 */
public final class GameEngine implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3306656900603120034L;
	private static GameEngine aGameInstance = new GameEngine();
	
	/**
	 * Represents the state of the game.  
	 */
	private enum GamePhase
	{
		UNINITIALIZED, FIRSTDRAW, 
		P1DRAW, P1DISCARD, 
		P2DRAW, P2DISCARD, 
		P1LAYOFF, P2LAYOFF,
		ENDGAME
	};
	
	private GamePhase aPhase;
	private Deck aDeck;
	private Stack<Card> aDiscardPile;
	private boolean aP1IsDealer;
	
	private Player aPlayer1;
	private Player aPlayer2;
	
	private GameEngine()
	{
		reset();
	}
	
	private void reset()
	{
		aPhase = GamePhase.UNINITIALIZED;
		aDeck = new Deck();
		aDiscardPile = new Stack<Card>();
		aP1IsDealer = false;
	}
	
	/**
	 * Starts a new game.
	 * @param pTwoAIs true means the computer will be playing against another computer, flase means player 1 is a human player.
	 * @param pP1Name Player 1s name
	 * @param pP2Name Player 2s name
	 */
	public void newGame(boolean pTwoAIs, String pP1Name, String pP2Name)
	{
		reset();
		aPlayer1 = new Player(pP1Name);
		aPlayer2 = new Player(pP2Name);
	}
	
	/**
	 * Returns the top of the discard pile. It is not taken, simply looked at.
	 * @return The top card of the discard pile
	 */
	public Card getTopOfDiscard()
	{
		return aDiscardPile.peek();
	}
	
	/**
	 * Gets the singleton instance.
	 * @return the instance of the gameEngine
	 */
	public static GameEngine getEngine()
	{
		return aGameInstance;
	}
	
	/**
	 * Saves the game.dklfnlsdknf
	 * @param pSerializer the format for which to implement the serializer
	 * @param pPath the full path to save the game
	 * @throws SaveException thrown when something goes wrong during saving 
	 */
	public void save(Serializer pSerializer, String pPath) throws SaveException
	{
		pSerializer.save(this, pPath);
	}
	
	/**
	 * Loads the game.
	 * @param pSerializer the format for which to implement the serializer
	 * @param pPath the full path to load the game
	 * @throws LoadException thrown when something goes wrong during loading 
	 */
	public void load(Serializer pSerializer, String pPath) throws LoadException
	{
		aGameInstance = pSerializer.load(pPath);
		// TODO: update the rest and get things going from where things were left off
	}
}
