package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotKnockException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;
import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.serialization.Serializer;

/**
 * The heart and soul of the game. Responsible for managing the game.
 */
public final class GameEngine extends Observable implements Serializable
{
	private static final long serialVersionUID = 3306656900603120034L;
	private static GameEngine aGameInstance = new GameEngine();
	
	/**
	 * Represents the state of the game.  
	 */
	private enum GamePhase
	{
		UNINITIALIZED, FIRSTDRAW, 
		DRAW, DISCARD,
		ENDGAME
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
		aPhase = GamePhase.UNINITIALIZED;
		aDeck = new Deck();
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
	}
	
	/**
	 * Starts a new game.
	 * @param pP1Name Player 1s name
	 * @param pP2Name Player 2s name
	 */
	public void newGame(String pP1Name, String pP2Name)
	{
		reset();
		aPlayer1 = new Player(pP1Name);
		aPlayer2 = new Player(pP2Name);
		aPhase = GamePhase.FIRSTDRAW;
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
	}
	
	/**
	 * Takes a card from the deck and adds it to the specified players hand.
	 * @param pPlayer the player to get the drawn card
	 * @throws CannotDrawException when there are less than two cards in the deck
	 */
	public void drawFromDeck(Player pPlayer) throws CannotDrawException
	{
		if(aDeck.size() < 2)
		{
			throw new CannotDrawException("Cannot draw from deck: less than 2 cards left in deck.");
		}
		try
		{
			pPlayer.addCard(aDeck.draw());
		}
		catch (CannotPerformActionException e)
		{
			throw new CannotDrawException("Cannot draw from deck: Added duplicate card to hand.");
		}
		
		// Next phase is discard
		aPhase = GamePhase.DISCARD;
	}
	
	/**
	 * Takes a card from the discard pile and adds it to the specified players hand.
	 * @param pPlayer the player to get the drawn card	 
	 * @throws CannotDrawException when the discard pile is empty
	 */
	public void drawFromDiscardPile(Player pPlayer) throws CannotDrawException
	{
		if(aDiscardPile.isEmpty()) 
		{
			throw new CannotDrawException("Cannot draw from discard pile: no cards to draw.");
		}
		try
		{
			pPlayer.addCard(aDiscardPile.pop());
		}
		catch (CannotPerformActionException e)
		{
			throw new CannotDrawException("Cannot draw from deck: Added duplicate card to hand.");
		}

		// Next phase is discard
		aPhase = GamePhase.DISCARD;
	}
	
	/**
	 * Simulates knocking, which is done AFTER discarding a card.
	 * @param pPlayer the player that knocked.
	 * @throws CannotKnockException if the player who knocked has >= 10 points of deadwood 
	 */
	public void knock(Player pPlayer) throws CannotKnockException
	{
		if(pPlayer.getHand().score() >= 10)
		{
			throw new CannotKnockException("Player " + pPlayer.getName() + " cannot knock. Too much deadwood");
		}
		
		// TODO: Run the new version of automatch, which calculates the optimal scores for both players,
		// then based on who has the better score, update the players score appropriately
		
		aPhase = GamePhase.ENDGAME;
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
	 * Saves the game.
	 * @param pSerializer the format for which to implement the serializer
	 * @param pPath the full path to save the game
	 * @throws SaveException thrown when something goes wrong during saving 
	 */
	public static void save(Serializer pSerializer, String pPath) throws SaveException
	{
		pSerializer.save(aGameInstance, pPath);
	}
	
	/**
	 * Loads the game.
	 * @param pSerializer the format for which to implement the serializer
	 * @param pPath the full path to load the game
	 * @throws LoadException thrown when something goes wrong during loading 
	 */
	public static void load(Serializer pSerializer, String pPath) throws LoadException
	{
		aGameInstance = pSerializer.load(pPath);
		// TODO: update the rest and get things going from where things were left off
	}
}
