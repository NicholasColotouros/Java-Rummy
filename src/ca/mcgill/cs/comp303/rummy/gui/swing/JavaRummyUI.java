package ca.mcgill.cs.comp303.rummy.gui.swing;

import ca.mcgill.cs.comp303.rummy.bots.RandomBot;
import ca.mcgill.cs.comp303.rummy.bots.RummyBot;
import ca.mcgill.cs.comp303.rummy.gui.swing.handPanel.CardSelectionPanel;
import ca.mcgill.cs.comp303.rummy.gui.swing.handPanel.HandPanel;
import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.Deck;
import ca.mcgill.cs.comp303.rummy.model.GameEngine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Nicholas Nathan Colotouros
 *
 */
@SuppressWarnings("serial")
public class JavaRummyUI extends JFrame
{
	// Instance of Game Engine
	public static final GameEngine INSTANCE = GameEngine.getInstance(); 
	public static final int HAND_SIZE = GameEngine.HAND_SIZE;
	
	// Getting the strings to be used
	public static final String RESOURCE_BUNDLE_NAME = "JavaRummyUIBundle";
	public static final Locale LOCALE = Locale.CANADA;
	public static final ResourceBundle STRINGS = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, LOCALE);
	
	public static final String TITLE = STRINGS.getString("title");	
	
	private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private static final int VERTICAL_GAP = 5;
		
	// The background color to be used
	private static final int RED = 0;
	private static final int GREEN = 102;
	private static final int BLUE = 0;
	private static final Color BG_COLOR = new Color(RED, GREEN, BLUE);

	// The panels used
	private static final CardSelectionPanel PLAYER1_PANEL = new CardSelectionPanel(HAND_SIZE);
	private static final HandPanel PLAYER2_PANEL = new HandPanel(HAND_SIZE);
	private final ActionButtonPanel aActionPanel;
	
	private final DeckPanel aDeckPanel;
	private final DeckPanel aDiscardPanel;
	
	/**
	 * Default constructor.
	 * @param pP1Name The name for Player 1
	 * @param pP2Name The name for Player 2
	 */
	public JavaRummyUI(String pP1Name, String pP2Name)
	{
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_SIZE);
		
		setJMenuBar(createMenuBar());
		setLayout(new BorderLayout());
		
		INSTANCE.newGame(STRINGS.getString("player1"), "");
		
		// TODO remove this randomly generated hand code: is only for testing purposes since the GE is borked
		Card[] p1Cards = new Card[HAND_SIZE];
		Card[] p2Cards = new Card[HAND_SIZE];
		Deck deck = new Deck();
		for(int i = 0; i < HAND_SIZE; i++)
		{
			p1Cards[i] = deck.draw();
			p2Cards[i] = deck.draw();
		}
	
		aActionPanel = new ActionButtonPanel(this, INSTANCE.getPhase());
		
		aDeckPanel = new DeckPanel(deck.size(), null);
		aDiscardPanel = new DeckPanel(1, deck.draw()); // TODO change -- debugging only
		addDeckActionListeners();
		
		JPanel centerPanel = new JPanel();

		centerPanel.add(aDeckPanel);
		centerPanel.add(aDiscardPanel);
		
		PLAYER1_PANEL.loadCards(p1Cards);
		PLAYER2_PANEL.updateHand(p2Cards);
		
		// make the bottom panel
		// TODO: fix alignment
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(PLAYER1_PANEL);
		bottomPanel.add(aActionPanel);
		
		
		// add everything to the window
		add(PLAYER2_PANEL, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// set the background colors
		bottomPanel.setBackground(BG_COLOR);
		aActionPanel.setBackground(BG_COLOR);
		PLAYER1_PANEL.setBackground(BG_COLOR);
		PLAYER2_PANEL.setBackground(BG_COLOR);
		aDeckPanel.setBackground(BG_COLOR);
		aDiscardPanel.setBackground(BG_COLOR);
		centerPanel.setBackground(BG_COLOR);
		setBackground(BG_COLOR);
		
		INSTANCE.newGame(pP1Name, pP2Name);
	}
	
	/**
	 * @return the card that has been selected by the player.
	 */
	public Card getSelectedCard()
	{
		return PLAYER1_PANEL.getSelectedCard();
	}

	private void addDeckActionListeners()
	{
		aDeckPanel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent pEvent)
			{
				// TODO: draw card from deck
			}
		});
		
		aDiscardPanel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent pEvent)
			{
				// TODO: draw card from discard panel
			}
		});
	}
	
	private JMenuBar createMenuBar()
	{
		// construct the structure of the menu bar
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu(STRINGS.getString("file"));
		JMenu helpMenu = new JMenu(STRINGS.getString("help"));
		
		JMenuItem newGameMenuItem = new JMenuItem(STRINGS.getString("new_game"));
		JMenuItem saveMenuItem = new JMenuItem(STRINGS.getString("save"));
		JMenuItem loadMenuItem = new JMenuItem(STRINGS.getString("load"));
		JMenuItem exitMenuItem = new JMenuItem(STRINGS.getString("exit"));
		fileMenu.add(newGameMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem(STRINGS.getString("about"));
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		// Action listeners
		
		// Action listeners for the fileMenu
		newGameMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				INSTANCE.newGame(STRINGS.getString("player1"), "");
			}
		});
		
		saveMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				// TODO: pop up an explorer window
				// TODO: call save at that location
			}
		});
		
		loadMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				// TODO: pop up an explorer window
				// TODO: call load at that location
			}
		});
		
		exitMenuItem.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				System.exit(0);
			}
		});
		
		
		// helpmenu actionListeners
		aboutMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				JOptionPane.showMessageDialog(null, STRINGS.getString("aboutMessage"));
			}
		});
		
		return menuBar;
	}
	
	public static void main(String[] pArgs)
	{
//		RummyBot computer = new RandomBot(GameEngine.getInstance().getPlayer2().getHand());
//		GameEngine.getInstance().addObserver(computer);

		SwingUtilities.invokeLater(new Runnable() 
		{			
			@Override
			public void run() 
			{
				JavaRummyUI mainFrame = new JavaRummyUI("asdf", "fasdf");
				mainFrame.setVisible(true);
			}
		});
	}
}
