package model;

import java.awt.Color;

public class MachineStatus {

	public String displayText = null;
	public Color displayColor = null;
	
	/**
	 * 
	 * @param displayText - Text to be displayed on the status screen
	 * @param displayColor - Color of the displayed text
	 */
	public MachineStatus(String displayText, Color displayColor)
	{
		this.displayText = displayText;
		this.displayColor = displayColor;
		
		// All done
		return;
	}

}
