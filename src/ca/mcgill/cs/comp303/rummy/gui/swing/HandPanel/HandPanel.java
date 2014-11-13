package ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.cs.comp303.rummy.gui.swing.CardImages;
import ca.mcgill.cs.comp303.rummy.model.Card;

// TODO: make this an observer to the gameEngine
// TODO: figure out how to implement this such that we have an un-interactable version for the AI
	// and one clickable version for the player with buttons for discarding and knicking (may need other classes)

/**
 * 
 * @author Nathan
 *
 */
@SuppressWarnings("serial")
public class HandPanel extends JPanel
{
	private static final int SHIFT = 30; // Horizontal shift
	private static final int HAND_SIZE = 10;
	
	private JLabel aLabel = new JLabel();
	private CompositeIcon aHand;
	
	/**
	 * Default constructor.
	 */
	public HandPanel()
	{
		add(aLabel);
		aHand = new CompositeIcon();
		for(int i = 0; i < HAND_SIZE; i++)
		{
			aHand.addIcon(new ShiftIcon(SHIFT*i, 0, CardImages.getBack()));
		}
		aLabel.setIcon(aHand);
	}
	
	// TODO update method associated with being an observer to the gameEngine
	public void update(Card[] pHand)
	{
		aHand = new CompositeIcon();
		for(int i = 0; i < pHand.length; i++)
		{
			aHand.addIcon(new ShiftIcon(SHIFT*i, 0, CardImages.getCard(pHand[i])));
		}
		aLabel.setIcon(aHand);
	}
}