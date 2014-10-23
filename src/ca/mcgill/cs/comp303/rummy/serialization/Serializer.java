package ca.mcgill.cs.comp303.rummy.serialization;

import ca.mcgill.cs.comp303.rummy.exceptions.SaveException;
import ca.mcgill.cs.comp303.rummy.exceptions.LoadException;

import ca.mcgill.cs.comp303.rummy.model.GameEngine;

/**
 * Interface used to save and load the game.
 */
public interface Serializer
{
	/**
	 * Saved the game.
	 * @param pEngine the instance of the gameEngine
	 * @param pPath the path to save the game
	 * @throws SaveException thrown when something goes wrong with saving like having an invalid path
	 */
	void save(GameEngine pEngine, String pPath) throws SaveException;
	
	/**
	 * Loads the game from pre-existing file.
	 * @param pPath the full location file to be loaded
	 * @throws LoadException is thrown if something goes wrong like corrupted save or an invalid path
	 * @return the loaded GameEngine
	 */
	GameEngine load(String pPath) throws LoadException;
}
