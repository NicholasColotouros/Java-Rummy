package ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import ca.mcgill.cs.comp303.rummy.gui.swing.CardImages;
import ca.mcgill.cs.comp303.rummy.model.Card;

/**
 * Supports displaying cards and selecting one. Also a nice demonstration
 * of the Composite and Decorator design patterns in action.
 * 
 * @author Martin P. Robillard McGill University 3 November 2014
 * 
 * Modified by @author Nicholas Nathan Colotouros to remove default color setting and
 * added getter method to get the selected card.
 */
@SuppressWarnings("serial")
public class CardSelectionPanel extends JPanel implements ActionListener
{
	private static final int H_SHIFT = 30;
	private static final int V_SHIFT = 20;
	private static final int CARD_HEIGHT = CardImages.getBack().getIconHeight();
	private static final int CARD_WIDTH = CardImages.getBack().getIconWidth();
	private static final int PREFERRED_HEIGHT = CardImages.getBack().getIconHeight() + V_SHIFT;
	
	private Card[] aCards; 
	private JLabel aLabel = new JLabel();
	private int aHovered = -1;
	private int aSelected = -1;
	
	/**
	 * Creates an empty hand panel.
	 * @param pMaxCards The maximum number of cards that will ever be displayed.
	 */
	public CardSelectionPanel(int pMaxCards)
	{
		aCards = new Card[pMaxCards];
		setPreferredSize(new Dimension((pMaxCards -1) * H_SHIFT + CARD_WIDTH, PREFERRED_HEIGHT));
		add(aLabel);
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent pEvent)
			{
				aHovered = getSelectedCardIndex(pEvent.getX(), pEvent.getY());
				paintCards();
			}
		});
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent pEvent)
			{
				int selection = getSelectedCardIndex(pEvent.getX(), pEvent.getY());
				if( aSelected == selection )
				{
					aSelected = -1;
				}
				else
				{
					aSelected = selection;
				}
				paintCards();
			}
		});
	}
	
	/**
	 * Loads the cards and shows them on the panel.
	 * @param pCards The cards to load into the panel.
	 */
	public void loadCards(Card[] pCards)
	{
		aCards = Arrays.copyOf(pCards, aCards.length);
		paintCards();
	}
	
	private void paintCards()
	{
		CompositeIcon icon = new CompositeIcon();
		for( int i = 0; i < aCards.length; i++ )
		{
			if( aCards[i] == null )
			{
				continue;
			}
			int upShift = V_SHIFT;
			if( aHovered == i || aSelected == i)
			{
				upShift = 0;
			}
			icon.addIcon(new ShiftIcon( i * H_SHIFT, upShift, CardImages.getCard(aCards[i])));
		}
		aLabel.setIcon(icon);
		repaint();
	}
	
	/**
	 * 
	 * @return the selected card.
	 */
	public Card getSelectedCard()
	{
		return aCards[aSelected];
	}
	
	private int getSelectedCardIndex(int pX, int pY)
	{
		if( pY < aLabel.getY() || pY >= aLabel.getY() + CARD_HEIGHT)
		{
			return -1;
		}
		int trueX = pX - aLabel.getX();
		if( trueX < 0 || trueX >= aLabel.getWidth())
		{
			return -1;
		}
		return Math.min(trueX / H_SHIFT, aCards.length-1);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		((JButton)e.getSource()).setEnabled(false);
		startAnimation();
	}
	
	private void startAnimation()
	{
		new Timer(150, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				aHovered = (aHovered+1)%aCards.length;
				CardSelectionPanel.this.paintCards();
			}
		}).start();
	}
}