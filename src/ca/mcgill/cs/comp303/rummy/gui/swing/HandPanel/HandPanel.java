package ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.cs.comp303.rummy.gui.swing.CardImages;
import ca.mcgill.cs.comp303.rummy.model.Card;

// TODO: make this an observer to the gameEngine
/**
 * The handpanel for the AI.
 * @author Nicholas Nathan Colotoutors
 *
 */
@SuppressWarnings("serial")
public class HandPanel extends JPanel
{
	private static final int SHIFT = 30; // Horizontal shift
	
	private static final int CARD_WIDTH = CardImages.getBack().getIconWidth();
	private static final int PREFERRED_HEIGHT = CardImages.getBack().getIconHeight();

	private final int aHandSize;

	private JLabel aLabel = new JLabel();
		
	/**
	 * Default constructor.
	 * @param pMaxCards The maximum number of cards that can be held
	 */
	public HandPanel(int pMaxCards)
	{
		aHandSize = pMaxCards;
		
		add(aLabel);
		setPreferredSize(new Dimension((aHandSize -1) * SHIFT + CARD_WIDTH, PREFERRED_HEIGHT));
	}
	
	/**
	 * Updates the cards in the Hand.
	 * @param pHand The set of cards to be shown.
	 */
	public void updateHand(Card[] pHand)
	{
		CompositeIcon hand = new CompositeIcon();
		for(int i = 0; i < pHand.length; i++)
		{
			hand.addIcon(new ShiftIcon(SHIFT*i, 0, CardImages.getCard(pHand[i])));
		}
		aLabel.setIcon(hand);
	}
}