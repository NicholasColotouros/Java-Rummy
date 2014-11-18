package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Set;
import java.util.Stack;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotKnockException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;
import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;
import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.model.Card.Rank;
import ca.mcgill.cs.comp303.rummy.serialization.Serializer;

/**
 * The heart and soul of the game. Responsible for managing the game.
 */
public final class GameEngine extends Observable implements Serializable
{
	private static final long serialVersionUID = 3306656900603120034L;
	
	private static final int KNOCK_SCORE = 10;
	private static final int HAND_SIZE = 10;
	
	private static GameEngine aGameInstance = new GameEngine();
	
	/**
	 * Represents the state of the game.  
	 */
	public enum GamePhase
	{
		FIRSTDRAW, DRAW, DISCARD, ENDGAME, AI_TURN
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
		newGame("Player 1", "HAL 9000");
	}
	
	/**
	 * Starts a new game.
	 * @param pP1Name Player 1s name
	 * @param pP2Name Player 2s name
	 * @pre p1 != null
	 * @pre p2 != null
	 */
	public void newGame(String pP1Name, String pP2Name)
	{
		String p1 = pP1Name;
		String p2 = pP2Name;
		
		if(p1.equals(""))
		{
			p1 = new String("Player 1");
		}
		if(p2.equals(""))
		{
			p2 = new String("HAL 9000");
		}
		
		aPlayer1 = new Player(p1);
		aPlayer2 = new Player(p2);
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
			}
		}
		aPhase = GamePhase.FIRSTDRAW;
	}
	
	/**
	 * Discard the card and add it to the discard pile. The card is only discarded if the player has the card to discard.
	 * @param pPlayer The player to discard the card.
	 * @param pCard The card to be discarded.
	 * @param pKnock whether or not a knock will happen afterwards.
	 * @throws CannotPerformActionException if the card discarded is the card that was previously drawn.
	 */
	public void discard(Player pPlayer, Card pCard, boolean pKnock) throws CannotPerformActionException
	{		
		if(pPlayer.discard(pCard)) 
		{
			aDiscardPile.push(pCard);
		}
	
		// Next phase is the draw phase
		if(aPhase == GamePhase.AI_TURN)
		{
			aPhase = GamePhase.DRAW;
		}
		else
		{
			aPhase = GamePhase.AI_TURN;
		}
		
		if(pKnock)
		{
			knock(pPlayer);
		}
	}
	
	/**
	 * Takes a card from the deck and adds it to the specified players hand.
	 * @param pPlayer the player to get the drawn card
	 */
	public void drawFromDeck(Player pPlayer)
	{		
		try
		{
			pPlayer.addCard(aDeck.draw());
		}
		catch (CannotPerformActionException e)
		{
		}
		
		if(aDeck.size() <= 2)
		{
			aPhase = GamePhase.ENDGAME;
			// TODO: notify that deck ran out
		}
		
		if(aPhase == GamePhase.DRAW)
		{
			aPhase = GamePhase.DISCARD;
		}
	}
	
	/**
	 * Takes a card from the discard pile and adds it to the specified players hand.
	 * @param pPlayer the player to get the drawn card	 
	 * @throws CannotDrawException when the discard pile is empty
	 */
	public void drawFromDiscardPile(Player pPlayer) throws CannotDrawException
	{
		Card tmp;
		
		if(aDiscardPile.isEmpty()) 
		{
			String message = new String("Cannot draw from discard pile: no cards to draw.");
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
			throw new CannotDrawException(message);
		}

		// Next phase is discard
		if(aPhase == GamePhase.DRAW)
		{
			aPhase = GamePhase.DISCARD;
		} // else it's the AIs turn
	}
	
	/**
	 * Simulates knocking, which is done AFTER discarding a card.
	 * @param pPlayer the player that knocked.
	 * @throws CannotKnockException if the player who knocked has >= 10 points of deadwood 
	 */
	private void knock(Player pPlayer) throws CannotKnockException
	{
		if(pPlayer.getHand().score() >= KNOCK_SCORE)
		{
			throw new CannotKnockException("Player " + pPlayer.getName() + " cannot knock. Too much deadwood");
		}
		aPhase = GamePhase.ENDGAME;

		
		// Find out who the other player is
		Player otherPlayer;
		if(pPlayer.equals(aPlayer1))
		{
			otherPlayer = aPlayer2;
		}
		else
		{
			otherPlayer = aPlayer1;
		}
		
		int otherScore = lastRoundMatching(pPlayer, otherPlayer);
		int knockerScore = pPlayer.getHand().score();

		// If gin => round over
		if(knockerScore == 0)
		{
			recordWin(pPlayer, otherPlayer.getHand().score(), true);
		}

		
		// if pPlayer won (the one who knocked)
		else if(otherScore < knockerScore)
		{
			recordWin(pPlayer, knockerScore - otherScore, false);
		}
		
		// if the other player won or draw
		else
		{
			recordWin(otherPlayer, otherScore - knockerScore, false);
		}

	}
	
	/**
	 * Updates the score for the winner and notifies the loggers.
	 * @param pWinner The winner of the round.
	 * @param pPointsGained The amount of points the winner gained
	 * @param pHasGin True if the winner has gin.
	 * @pre pPointsGained >= 0
	 */
	private void recordWin(Player pWinner, int pPointsGained, boolean pHasGin)
	{
		assert pPointsGained >= 0;		
		pWinner.incrementScore(pPointsGained);
	}
	
	/**
	 * Lays off the cards for the player who did not knock and calculates the new score.
	 * 
	 * @param pKnocker The player who knocked.
	 * @param pOtherPlayer The player that did NOT knock.
	 * @return The score of the player who did not knock after laying off cards.
	 */
	private static int lastRoundMatching(Player pKnocker, Player pOtherPlayer)
	{
		int otherScore = pOtherPlayer.getHand().score();
		
		// TODO: implement proper matching method, this one only lays off unmatched cards
		
		// get the unmatched cards as an array so cards can be removed without affecting the hand
		Card[] unmatchedArray = (Card[]) pOtherPlayer.getHand().getUnmatchedCards().toArray();
		Set<ICardSet> matched = pKnocker.getHand().getMatchedSets();
		
		// TODO: match the runs first
		
		// Now match the groups
		for(ICardSet set : matched)
		{
			// if the set is a group, add all cards that fit in that group
			if(set.isGroup())
			{
				// Get the rank of the set
				Rank groupRank = null;
				
				for(int i = 0; i < unmatchedArray.length; i++)
				{
					if(unmatchedArray[i] != null)
					{
						if(unmatchedArray[i].getRank() == groupRank)
						{
							// Deduct the score of the previously unmatched card, as it is no longer matched
							otherScore -= unmatchedArray[i].getRank().ordinal() + 1;
							unmatchedArray[i] = null;
						}
					}
				}
			}
		}
		return otherScore;
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
	 * Gets the size of each player's hand.
	 * @return The hand size.
	 */
	public static int getHandSize()
	{
		return HAND_SIZE;
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
			throw e;
		}		
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
			throw e;
		}		
	}	
}
