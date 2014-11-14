package ca.mcgill.cs.comp303.rummy.gui.swing.HandPanel;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 * 
 * @author Nicholas Nathan Colotoutors
 *
 */
public class CompositeIcon implements Icon
{
	private int aHeight;
	private int aWidth;
	
	private ArrayList<Icon> aIcons;
	
	/**
	 * Default constructor.
	 */
	public CompositeIcon()
	{
		aHeight = 0;
		aWidth = 0;
		aIcons = new ArrayList<Icon>();
	}
	
	/**
	 * Adds an icon.
	 * @param pIcon an Icon to add.
	 */
	public void addIcon(Icon pIcon)
	{
		aIcons.add(pIcon);
		if(aHeight < pIcon.getIconHeight())
		{
			aHeight = pIcon.getIconHeight();
		}
		if(aWidth < pIcon.getIconWidth())
		{
			aWidth = pIcon.getIconWidth();
		}
	}
	
	@Override
	public int getIconHeight()
	{
		return aHeight;
	}

	@Override
	public int getIconWidth()
	{
		return aWidth;
	}

	@Override
	public void paintIcon(Component pComponent, Graphics pGraphics, int pX, int pY)
	{
		for(Icon i : aIcons)
		{
			i.paintIcon(pComponent, pGraphics, pX, pY);
		}
	}

}
