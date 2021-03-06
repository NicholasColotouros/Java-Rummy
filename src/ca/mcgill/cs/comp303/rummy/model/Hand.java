package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import ca.mcgill.cs.comp303.rummy.model.Card.Rank;

/**
 * Models a hand of 10 cards. The hand is not sorted. Not threadsafe.
 * The hand is a set: adding the same card twice will not add duplicates
 * of the card.
 * @inv size() > 0
 * @inv size() <= HAND_SIZE
 */
public class Hand implements Serializable
{
	public static final int HAND_SIZE = GameEngine.HAND_SIZE;
	private ArrayList<Card> aCards;
	private ArrayList<Card> aUnmatched;
	private ArrayList<ICardSet> aRuns;
	private ArrayList<ICardSet> aGroups;
	
	/**
	 * Creates a new, empty hand.
	 */
	public Hand()
	{
		aCards = new ArrayList<Card>();
		aUnmatched = new ArrayList<Card>();
		aRuns = new ArrayList<ICardSet>();
		aGroups = new ArrayList<ICardSet>();
	}
	
	/**
	 * Adds pCard to the list of unmatched cards.
	 * If the card is already in the hand, it is not added.
	 * @param pCard The card to add.
	 * @pre pCard != null
	 */
	public void add( Card pCard )
	{
		aCards.add(pCard);
		aUnmatched.add(pCard);
	}
	
	/**
	 * Remove pCard from the hand and break any matched set
	 * that the card is part of. Does nothing if
	 * pCard is not in the hand.
	 * @param pCard The card to remove.
	 * @pre pCard != null
	 */
	public void remove( Card pCard )
	{
		aCards.remove(pCard);
		aUnmatched.remove(pCard);
		
		// Remove any runs containing the card to remove.
		ArrayList<ICardSet> runsToRemove = new ArrayList<ICardSet>();
		for (ICardSet run : aRuns) 
		{
			if (run.contains(pCard)) 
			{
				runsToRemove.add(run);	
			}
		}
		for (ICardSet run : runsToRemove)
		{
			aRuns.remove(run);
		}
		
		// Remove any groups containing the card to remove.
		ArrayList<ICardSet> groupsToRemove = new ArrayList<ICardSet>();
		for (ICardSet group : aGroups) 
		{
			if (group.contains(pCard))
			{
				groupsToRemove.add(group);
			}
		}
		for (ICardSet group : groupsToRemove)
		{
			aGroups.remove(group);
		}
	}
	
	/**
	 * @return True if the hand is complete.
	 */
	public boolean isComplete()
	{
		if (this.size() == HAND_SIZE) 
		{
			return true;
		}
		return false; 
	}
	
	/**
	 * Removes all the cards from the hand.
	 */
	public void clear()
	{
		aCards = new ArrayList<Card>();
		aUnmatched = new ArrayList<Card>();
		aRuns = new ArrayList<ICardSet>();
		aGroups = new ArrayList<ICardSet>();
	}
	
	/**
	 * @return A copy of the set of matched sets
	 */
	public Set<ICardSet> getMatchedSets()
	{
		HashSet<ICardSet> matched = new HashSet<ICardSet>(aGroups);
		matched.addAll(aRuns);
		return matched;
	}
	
	/**
	 * @return A copy of the set of unmatched cards.
	 */
	public Set<Card> getUnmatchedCards()
	{
		return new HashSet<Card>(aUnmatched);
	}
	
	/**
	 * @return The number of cards in the hand.
	 */
	public int size()
	{
		return aCards.size(); 
	}
	
	/**
	 * Determines if pCard is already in the hand, either as an
	 * unmatched card or as part of a set.
	 * @param pCard The card to check.
	 * @return true if the card is already in the hand.
	 * @pre pCard != null 
	 */
	public boolean contains( Card pCard )
	{
		return aCards.contains(pCard);
	}
	
	/**
	 * @return The total point value of the unmatched cards in this hand.
	 */
	public int score()
	{
		int score = 0;
		for (Card c : this.aUnmatched)
		{
			int cardPoints = c.getRank().ordinal() + 1;
			if (cardPoints > HAND_SIZE)
			{
				cardPoints = HAND_SIZE;
			}
			score += cardPoints;
		}
		return score; 
	}
	
	
	/**
	 * Calculates the matching of cards into groups and runs that
	 * results in the lowest amount of points for unmatched cards.
	 */
	public void autoMatch()
	{
		HashSet<CardSet> sets = new HashSet<CardSet>();
		
		// TODO: current implementation just makes groups
		
		// all cards are considered unmatched
		aRuns = new ArrayList<ICardSet>();
		aGroups = new ArrayList<ICardSet>();
		
		// copy the cards to the unmatched card set
		aUnmatched = new ArrayList<Card>();
		for(Card c : aCards)
		{
			aUnmatched.add(c);
		}
		
		Collections.sort(aUnmatched);
		
		// Make groups by starting at a card and checking at most the next 4
		for(int i = 0; i < aCards.size() - 1; i++)
		{
			HashSet<Card> possibleGroup = new HashSet<Card>();
			possibleGroup.add(aCards.get(i));
			Rank groupRank = aCards.get(i).getRank();
			
			for(int j = i + 1; j <= i + 4; j++)
			{
				// if the rank of the next card is the same as the rank of the group,
				// add it to the group and remove it from the unmatched cards
				Rank currentRank = aCards.get(j).getRank();
				if(currentRank == groupRank)
				{
					possibleGroup.add(aCards.get(j));
					aUnmatched.remove(aCards.get(j));
				}
			}
			
			// if it's a group, add it to the set of all groups
			CardSet possibleSet = new CardSet(possibleGroup);
			if(possibleSet.isGroup())
			{
				aGroups.add(possibleSet);
			}
		}

	}
	
	/**
	 * Gets the cards in the hand.
	 * @return a copy of the cards in the hand
	 */
	public ArrayList<Card> getCards()
	{
		ArrayList<Card> ret = new ArrayList<Card>();
		for(Card c : aCards)
		{
			ret.add(c);
		}
		return ret;
	}
}