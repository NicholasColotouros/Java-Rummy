package ca.mcgill.cs.comp303.rummy.gui.swing.handPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import ca.mcgill.cs.comp303.rummy.exceptions.CannotDrawException;
import ca.mcgill.cs.comp303.rummy.exceptions.CannotPerformActionException;
import ca.mcgill.cs.comp303.rummy.gui.swing.JavaRummyUI;
import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;
import ca.mcgill.cs.comp303.rummy.model.GameEngine.GamePhase;

/**
 * Panel that contains available actions which are updated accordingly: discard, pass and knock. 
 * @author Nathan
 *
 */
public class ActionButtonPanel extends JPanel
{
	private static final ResourceBundle STRINGS = JavaRummyUI.STRINGS;
	private static final GameEngine INSTANCE = GameEngine.getInstance();

	private static final Dimension BUTTON_DIMENSION = new Dimension(200, 100);
	
	private static final JButton PASS_BUTTON = new JButton(STRINGS.getString("pass"));
	private static final JButton DISCARD_BUTTON = new JButton(STRINGS.getString("discard"));
	private static final JButton KNOCK_BUTTON = new JButton(STRINGS.getString("knock"));
	
	private JavaRummyUI aParentPanel;
	private GamePhase aCurrentPhase;
	private boolean aKnock = false;

	/**
	 * Default constructor.
	 * @param pParent the instance of JavaRummyUI that this belongs to.
	 */
	public ActionButtonPanel(JavaRummyUI pParent)
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
					catch (CannotDrawException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			}
		});
		
		PASS_BUTTON.setPreferredSize(BUTTON_DIMENSION);
		KNOCK_BUTTON.setPreferredSize(BUTTON_DIMENSION);
		DISCARD_BUTTON.setPreferredSize(BUTTON_DIMENSION);
				
		add(PASS_BUTTON);
		add(DISCARD_BUTTON);
		add(KNOCK_BUTTON);
		//TODO: call update method to make the right buttons visible
	}
	
	
	
	// TODO: make an observer of the GameEngine
}
