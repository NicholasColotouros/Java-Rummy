package ca.mcgill.cs.comp303.rummy.gui.swing;

import ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel.HandPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Nathan
 *
 */
@SuppressWarnings("serial")
public class JavaRummyUI extends JFrame
{
	public static final String RESOURCE_BUNDLE_NAME = "JavaRummyUIBundle";
	public static final Locale LOCALE = Locale.CANADA;
	public static final ResourceBundle STRINGS = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, LOCALE);
	
	public static final String TITLE = STRINGS.getString("title");
	
	public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	
	/**
	 * Default constructor.
	 */
	public JavaRummyUI()
	{
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_SIZE);
		
		setJMenuBar(createMenuBar());
		setLayout(new BorderLayout());

		add(new HandPanel(), BorderLayout.NORTH);
	}
	
	private JMenuBar createMenuBar()
	{
		// construct the structure of the menu bar
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu(STRINGS.getString("file"));
		JMenu helpMenu = new JMenu(STRINGS.getString("help"));
		
		JMenuItem saveMenuItem = new JMenuItem(STRINGS.getString("save"));
		JMenuItem loadMenuItem = new JMenuItem(STRINGS.getString("load"));
		JMenuItem exitMenuItem = new JMenuItem(STRINGS.getString("exit"));
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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() 
			{
				JavaRummyUI mainFrame = new JavaRummyUI();
				mainFrame.setVisible(true);
			}
		});
	}
}
