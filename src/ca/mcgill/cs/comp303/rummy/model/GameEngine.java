package ca.mcgill.cs.comp303.rummy.model;

import java.util.Stack;

import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.serialization.Serializer;

/**
 * The heart and soul of the game. Responsible for managing the game.
 */
public final class GameEngine
{
	private static final GameEngine INSTANCE = new GameEngine();
	
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
	
	private GamePhase aPhase = GamePhase.UNINITIALIZED;
	private Deck aDeck = new Deck();
	private Stack<Card> aDiscardPile = new Stack<Card>();
	private boolean aP1IsDealer = false;
	
	private Player aPlayer1;
	private Player aPlayer2;
	
	private GameEngine(){}
	
	
	
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
		return INSTANCE;
	}
	
	/**
	 * Saves the game.
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
		pSerializer.load(pPath);
	}
}
