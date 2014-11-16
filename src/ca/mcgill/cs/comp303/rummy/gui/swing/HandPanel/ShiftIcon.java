package ca.mcgill.cs.comp303.rummy.gui.swing.handPanel;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * 
 * @author Nicholas Nathan Colotoutors
 *
 */
public class ShiftIcon implements Icon
{
	private int aXShift;
	private int aYShift;
	private Icon aIcon;
	
	/**
	 * Default constructor.
	 * @param pXShift The amount to shift the icon along the x axis.
	 * @param pYShift The amount to shift the icon along the y axis.
	 * @param pIcon The icon to shift.
	 */
	public ShiftIcon(int pXShift, int pYShift, Icon pIcon)
	{
		aXShift = pXShift;
		aYShift = pYShift;
		aIcon = pIcon;
	}

	@Override
	public int getIconHeight()
	{
		return aIcon.getIconHeight() + aYShift;
	}

	@Override
	public int getIconWidth()
	{
		return aIcon.getIconWidth() + aXShift;
	}

	@Override
	public void paintIcon(Component pArg0, Graphics pArg1, int pArg2, int pArg3)
	{
		aIcon.paintIcon(pArg0, pArg1, pArg2 + aXShift, pArg3 + aYShift);
	}
}
