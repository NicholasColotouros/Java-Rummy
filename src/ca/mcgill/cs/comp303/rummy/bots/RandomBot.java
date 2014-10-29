package ca.mcgill.cs.comp303.rummy.bots;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Hand;

/**
 * Random bot does all random moves and knocks when possible.
 * Discards a randomly unmatched card.
 */
public class RandomBot implements RummyBot
{
	private Hand aHand;
	private Random aRandom = new Random();
	
	/**
	 * Default constructor. 
	 * @param pHand The bots hand.
	 */
	public RandomBot(Hand pHand)
	{
		aHand = pHand;
	}

	@Override
	public boolean drawFromDeck()
	{
		return aRandom.nextBoolean();
	}

	@Override
	public Card discard()
	{
		Card[] unmatched = (Card[]) aHand.getUnmatchedCards().toArray();
		if(unmatched.length == 0)
		{
			return unmatched[aRandom.nextInt() % unmatched.length];
		}
		
		return aHand.getCards().get(aRandom.nextInt() % aHand.size());
	}

	@Override
	public boolean knock()
	{
		if(aHand.score() <= 10) 
		{
			return true;
		}
		
		return false;
	}	
}
