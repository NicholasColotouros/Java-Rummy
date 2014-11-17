package ca.mcgill.cs.comp303.rummy.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;
import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.GamePhase;

/**
 * Panel that contains available actions which are updated accordingly: discard, pass and knock. 
 * @author Nathan
 *
 */
@SuppressWarnings("serial")
public class ActionButtonPanel extends JPanel
{
	private static final ResourceBundle STRINGS = JavaRummyUI.STRINGS;
	private static final GameEngine INSTANCE = GameEngine.getInstance();

	private static final JButton PASS_BUTTON = new JButton(STRINGS.getString("pass"));
	private static final JButton DISCARD_BUTTON = new JButton(STRINGS.getString("discard"));
	private static final JButton KNOCK_BUTTON = new JButton(STRINGS.getString("knock"));
	
	private JavaRummyUI aParentPanel;
	private GamePhase aCurrentPhase;
	private boolean aKnock = false;

	/**
	 * Default constructor.
	 * @param pParent the instance of JavaRummyUI that this belongs to.
	 * @param pCurrentPhase the current phase of the game.
	 */
	public ActionButtonPanel(JavaRummyUI pParent, GamePhase pCurrentPhase)
	{
		aParentPanel = pParent;
		
		// Action Listeners
		
		PASS_BUTTON.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				if(aCurrentPhase == GamePhase.FIRSTDRAW)
				{
					try
					{
						INSTANCE.drawFromDiscardPile(INSTANCE.getPlayer1());
					}
					catch (CannotDrawException e){}
				}
			}
		});
		
		DISCARD_BUTTON.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				if(aCurrentPhase == GamePhase.DISCARD)
				{
					Card selected = aParentPanel.getSelectedCard();
					if(selected != null)
					{
						try
						{
							INSTANCE.discard(INSTANCE.getPlayer1(), selected, aKnock);
						}
						catch (CannotPerformActionException e){}
					}
				}
			}
		});
		
		KNOCK_BUTTON.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				// TODO: toggle knock and button
				// TODO: make sure this is reset upon turn ending
				// TODO: make the button faded when it's impossible to knock
			}
		});
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(PASS_BUTTON);
		add(DISCARD_BUTTON);
		add(KNOCK_BUTTON);

		update(pCurrentPhase);
	}
	
	/**
	 * Update method to be called by parent window.
	 * @param pCurrentPhase the turn's current phase.
	 */
	public void update(GamePhase pCurrentPhase)
	{
		// TODO: make sure knock is de-toggled
		if(pCurrentPhase == GamePhase.FIRSTDRAW)
		{
			PASS_BUTTON.setVisible(true);
			KNOCK_BUTTON.setVisible(false);
			// TODO: make sure discard button is disabled
		}
		else
		{
			PASS_BUTTON.setVisible(false);
			KNOCK_BUTTON.setVisible(true);
			// TODO: make sure discard button is enabled
		}
		aCurrentPhase = pCurrentPhase;
	}
}