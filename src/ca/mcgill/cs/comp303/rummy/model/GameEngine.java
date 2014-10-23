package ca.mcgill.cs.comp303.rummy.model;

/**
 * The heart and soul of the game. Responsible for managing the game.
 */
public final class GameEngine
{
	private static final GameEngine INSTANCE = new GameEngine();
	private GameEngine(){}
	
	/**
	 * Gets the singleton instance.
	 * @return the instance of the gameEngine
	 */
	public static GameEngine getEngine()
	{
		return INSTANCE;
	}
}
