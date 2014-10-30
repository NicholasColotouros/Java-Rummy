package ca.mcgill.cs.comp303.rummy.bots;

import java.util.Random;

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
		this.aHand = pHand;
	}

	@Override
	public boolean drawFromDeck()
	{
		return this.aRandom.nextBoolean();
	}

	@Override
	public Card discard()
	{
		Card[] unmatched = (Card[]) this.aHand.getUnmatchedCards().toArray();
		if(unmatched.length == 0)
		{
			return unmatched[this.aRandom.nextInt() % unmatched.length];
		}
		
		int randomIndex = this.aRandom.nextInt() % this.aHand.size();
		return this.aHand.getCards().get(randomIndex);
	}

	@Override
	public boolean knock()
	{
		if(this.aHand.score() <= 10) 
		{
			return true;
		}
		
		return false;
	}	
}
