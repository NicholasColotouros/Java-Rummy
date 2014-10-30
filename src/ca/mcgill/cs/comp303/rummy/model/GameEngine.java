package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;
import java.util.ArrayList;
import java.util.logging.Level;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotKnockException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;
import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.logging.ILoggerObserver;
import ca.mcgill.cs.comp303.rummy.serialization.Serializer;

/**
 * The heart and soul of the game. Responsible for managing the game.
 */
public final class GameEngine extends Observable implements Serializable
{
	private static final long serialVersionUID = 3306656900603120034L;
	private static final int KNOCK_SCORE = 10;

	private static GameEngine aGameInstance = new GameEngine();
	private static ArrayList<ILoggerObserver> aObservers;
	
	/**
	 * Represents the state of the game.  
	 */
	private enum GamePhase
	{
		FIRSTDRAW, DRAW, DISCARD, ENDGAME
	};
	
	private GamePhase aPhase;
	private Deck aDeck;
	private Stack<Card> aDiscardPile;
	private boolean aP1IsDealer;
	
	// Player1 is the human by default
	private Player aPlayer1;
	private Player aPlayer2;
	
	private GameEngine()
	{
		reset();
	}
	
	/**
	 * Resets the game. The players stay the same and keep their scores.
	 */
	public void reset()
	{
		aDeck = new Deck();
		aDeck.shuffle();
		
		aDiscardPile = new Stack<Card>();
		aP1IsDealer = false;
		
		if(aPlayer1 != null)
		{
			aPlayer1.discardHand();
		}
		
		if(aPlayer2 != null)
		{
			aPlayer2.discardHand();
		}
		
		for(int i = 0; i < Hand.HAND_SIZE; i++)
		{
			try
			{
				aPlayer1.addCard(aDeck.draw());
				aPlayer2.addCard(aDeck.draw());
			}
			catch (CannotPerformActionException e)
			{
				logEvent(Level.SEVERE, "Draw failed during reset.");
			}
		}
		aPhase = GamePhase.FIRSTDRAW;
		logEvent(Level.INFO, "Starting new round.");	
	}
	
	/**
	 * Starts a new game.
	 * @param pP1Name Player 1s name
	 * @param pP2Name Player 2s name
	 */
	public void newGame(String pP1Name, String pP2Name)
	{
		aPlayer1 = new Player(pP1Name);
		aPlayer2 = new Player(pP2Name);
		reset();
		
		logEvent(Level.INFO, "Starting new game.");			
	}
	
	/**
	 * Discard the card and add it to the discard pile. The card is only discarded if the player has the card to discard.
	 * @param pPlayer The player to discard the card.
	 * @param pCard The card to be discarded.
	 * @throws CannotPerformActionException if the card discarded is the card that was previously drawn.
	 */
	public void discard(Player pPlayer, Card pCard) throws CannotPerformActionException
	{
		if(pPlayer.discard(pCard)) 
		{
			aDiscardPile.push(pCard);
		}
		
		// Next phase is the draw phase
		aPhase = GamePhase.DRAW;
		
		//update loggers
		String aMessage = "Player " + pPlayer.toString() + "discards" + pCard.toString();
		logEvent(Level.INFO, aMessage);	
		
	}
	
	/**
	 * Takes a card from the deck and adds it to the specified players hand.
	 * @param pPlayer the player to get the drawn card
	 * @pre GamePhase == GamePhase.DRAW
	 */
	public void drawFromDeck(Player pPlayer)
	{
		try
		{
			pPlayer.addCard(aDeck.draw());
		}
		catch (CannotPerformActionException e)
		{
			String message = new String(pPlayer.getName() + " cannot draw from the deck: added duplicate card to hand");
			logEvent(Level.SEVERE, message);
		}
		
		if(aDeck.size() == 2)
		{
			aPhase = GamePhase.ENDGAME;
		}
		aPhase = GamePhase.DISCARD;
		
		//update loggers
		String aMessage = "Player: " + pPlayer.getName() + " draws from the deck.";
		logEvent(Level.INFO, aMessage);	
	}
	
	/**
	 * Takes a card from the discard pile and adds it to the specified players hand.
	 * @param pPlayer the player to get the drawn card	 
	 * @throws CannotDrawException when the discard pile is empty
	 * @pre aPhase == GamePhase.DRAW
	 */
	public void drawFromDiscardPile(Player pPlayer) throws CannotDrawException
	{
		Card tmp;
		
		if(aDiscardPile.isEmpty()) 
		{
			String message = new String("Cannot draw from discard pile: no cards to draw.");
			logEvent(Level.SEVERE, message);
			throw new CannotDrawException(message);
		}
		try
		{
			tmp = aDiscardPile.pop();
			pPlayer.addCard(tmp);
		}
		catch (CannotPerformActionException e)
		{
			String message = new String("Cannot draw from deck: Added duplicate card to hand.");
			logEvent(Level.SEVERE, message);
			throw new CannotDrawException(message);
		}

		// Next phase is discard
		aPhase = GamePhase.DISCARD;
		
		//update loggers
		String aMessage = "Player " + pPlayer.toString() + " draws from discrad pile " + tmp.toString();
		logEvent(Level.INFO, aMessage);	
	}
	
	/**
	 * Simulates knocking, which is done AFTER discarding a card.
	 * @param pPlayer the player that knocked.
	 * @throws CannotKnockException if the player who knocked has >= 10 points of deadwood 
	 */
	public void knock(Player pPlayer) throws CannotKnockException
	{
		if(pPlayer.getHand().score() >= KNOCK_SCORE)
		{
			throw new CannotKnockException("Player " + pPlayer.getName() + " cannot knock. Too much deadwood");
		}
		
		int p1Score = 0;
		int p2Score = 0;
		// TODO: Run the new version of automatch, which calculates the optimal scores for both players
		// then based on who has the better score, update the players score appropriately
		
		aPhase = GamePhase.ENDGAME;
		
		//update loggers
		String message = "Player "+ pPlayer + " knocks.\n";
		String messsge2 = aPlayer1.getName() + " has a score of " + aPlayer1.getHand().score() + "\n";
		String message3 = aPlayer2.getName() + " has a score of " + aPlayer2.getHand().score() + "\n";
		logEvent(Level.INFO, message);	
		logEvent(Level.INFO, messsge2);
		logEvent(Level.INFO, message3);
		
		// determine winner, set the dealer for the new round, update the scores
		int scoreDifference = Math.abs(p1Score - p2Score);
		if(p1Score > p2Score)
		{
			aPlayer1.incrementScore(scoreDifference);
			aP1IsDealer = true;
			
			logEvent(Level.INFO, "Player: " + aPlayer1.getName() + " wins and gains " + scoreDifference + " points.");
		}
		else if(p1Score < p2Score)
		{
			aPlayer2.incrementScore(scoreDifference);
			aP1IsDealer = false;
			
			logEvent(Level.INFO, "Player: " + aPlayer2.getName() + " wins and gains " + scoreDifference + " points.");			
		}
		
		else
		{
			logEvent(Level.INFO, "Game tied.");
		}
	}
	
	/**
	 * Get the current game phase.
	 * @return the game phase.
	 */
	public GamePhase getPhase()
	{
		return aPhase;
	}
	
	/**
	 * Returns player 1, who is the human player in one player games.
	 * @return player 1
	 */
	public Player getPlayer1()
	{
		return aPlayer1;
	}
	
	/**
	 * Returns player 2.
	 * @return player 2
	 */
	public Player getPlayer2()
	{
		return aPlayer2;
	}
	
	/**
	 * Returns the dealer.
	 * @return the dealer
	 */
	public Player getDealer()
	{
		if(aP1IsDealer) 
		{
			return aPlayer1;
		}
		return aPlayer2;
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
	public static GameEngine getInstance()
	{
		return aGameInstance;
	}
	
	/**
	 * The number of cards left in the deck.
	 * @return the number of cards left in the deck.
	 */
	public int getDeckSize()
	{
		return aDeck.size();
	}
	/**
	 * Saves the game.
	 * @param pSerializer the format for which to implement the serializer
	 * @param pPath the full path to save the game
	 * @throws SaveException thrown when something goes wrong during saving 
	 */
	public static void save(Serializer pSerializer, String pPath) throws SaveException
	{
		try
		{
			pSerializer.save(aGameInstance, pPath);
		}
		catch(SaveException e)
		{
			logEvent(Level.SEVERE, "An error occurred while saving: " + e.getLocalizedMessage());
			throw e;
		}
		
		logEvent(Level.INFO, "Game successfully saved to: " + pPath);
	}
	
	/**
	 * Loads the game.
	 * @param pSerializer the format for which to implement the serializer
	 * @param pPath the full path to load the game
	 * @throws LoadException thrown when something goes wrong during loading 
	 */
	public static void load(Serializer pSerializer, String pPath) throws LoadException
	{
		try
		{
			aGameInstance = pSerializer.load(pPath);
		}
		catch(LoadException e)
		{
			logEvent(Level.SEVERE, "An error occurred while loading: " + e.getLocalizedMessage());
			throw e;
		}
		
		logEvent(Level.INFO, "Game successfully loaded from: " + pPath);

		// TODO: update the rest and get things going from where things were left off
	}
	
	/**
	 * Update all observers for the following actions: reset, discard, drawFromDeck,
	 * drawFromDiscardPile,knocks.
	 * @param pPriority the priority of the message
	 * @param pMessage the message
	 * 
	 */
	private static void logEvent(Level pPriority, String pMessage)
	{
		for(ILoggerObserver o: aObservers)
		{
			o.logEvent(pPriority, pMessage);
		}
	}
}
