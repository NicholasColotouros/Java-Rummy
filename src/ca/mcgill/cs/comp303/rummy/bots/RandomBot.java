package ca.mcgill.cs.comp303.rummy.bots;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Hand;

/**
 * Random bot does all random moves and knocks when possible.
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
	
	/**
	 * Updates the hand at the start of the turn.
	 * @param pModel the model
	 * @param pArgument the hand
	 */
	public void update(Observable pModel, Object pArgument)
	{
		if(pArgument instanceof Hand)
		{
			aHand = (Hand) pArgument;
		}
	}

	@Override
	public boolean drawFromDeck()
	{
		return aRandom.nextBoolean();
	}

	@Override
	public Card discard()
	{
		ArrayList<Card> cards = aHand.getCards();
		return cards.get(aRandom.nextInt() % cards.size() - 1);
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
