package ca.mcgill.cs.comp303.rummy.bots;

import java.util.Observer;

import ca.mcgill.cs.comp303.rummy.IJavaRummyObserver;
import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * The interface that the game AI will use.
 */
public interface RummyBot extends IJavaRummyObserver
{
	/**
	 * Is notified when the round starts and plays its turn.
	 */
	void playTurn();
}
