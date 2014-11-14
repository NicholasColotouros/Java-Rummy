package ca.mcgill.cs.comp303.rummy.gui.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel.CompositeIcon;
import ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel.ShiftIcon;
import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * Visual representation of a deck.
 * @author Nathan
 *
 */
@SuppressWarnings("serial")
public class DeckPanel extends JPanel implements ActionListener
{
	private static final int MAX_CARDS_HIDDEN = 4;
	private static final int HIDDEN_CARD_FACTOR = 10; // adds a new card beneath the top every x cards. MUST be > 0.
	
	private static final int HORIZONTAL_OFFSET = 2;
	private static final int VERTICAL_OFFSET = 0;
	
	private static final int PREFERRED_WIDTH = VERTICAL_OFFSET * MAX_CARDS_HIDDEN + CardImages.getBack().getIconHeight();
	private static final int PREFERRED_HEIGHT = CardImages.getBack().getIconHeight();
	
	private JLabel aLabel = new JLabel();
	
	/**
	 * Constructor.
	 * @param pDeckSize the initial size of the deck.
	 */
	public DeckPanel(int pDeckSize)
	{
		setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		add(aLabel);
		paintDeck(pDeckSize);
	}
	
	/**
	 * Updates the deck according to the size.
	 * @param pDeckSize The new size of the deck.
	 */
	public void paintDeck(int pDeckSize)
	{
		CompositeIcon icon = new CompositeIcon();
	
		if(pDeckSize > 0)
		{
			int i;
			for(i = 0; i < (pDeckSize / HIDDEN_CARD_FACTOR) + 1; i++)
			{
				icon.addIcon(new ShiftIcon(i * HORIZONTAL_OFFSET, i * VERTICAL_OFFSET, CardImages.getBack()));				
			}
		}
		
		aLabel.setIcon(icon);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent pEvent)
	{
		// TODO deck clicked --> draw card
		
	}
}
