package ca.mcgill.cs.comp303.rummy.logging;

import ca.mcgill.cs.comp303.rummy.IJavaRummyObserver;
import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.GamePhase;
/**
 * Observer that records the game history.
 * @author Nathan
 *
 */
public class JavaRummyLogger implements IJavaRummyObserver
{
	// TODO
	
	@Override
	public void gameUpdate(GamePhase pCurrentPhase, Card pCard)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameEnded(boolean pP1Knocked, boolean pP1Won, boolean pEndByDeckShortage, int pPointsGained)
	{
		// TODO Auto-generated method stub
		
	}

}
