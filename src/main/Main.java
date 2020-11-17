package main;

import java.awt.Color;

import javax.swing.JFrame;

import ui.JFrameATM;
import ui.JPanelSplash;
import ui.JPanelWelcome;

public class Main {
	
	public static boolean DEBUG = false;
	public static boolean DECORATED = false;  // Default to show the title bar, minimize, maximize and close icons
	public static boolean NOPRINT = false;      // Default to allow printing turned on

	public static final int ATM_WIDTH = 800;
	public static final int ATM_HEIGHT = 900;
	public static final int ATM_BEZEL_THICKNESS = 100;
	public static final int ATM_CELL_WIDTH = Main.ATM_BEZEL_THICKNESS;
	public static final int ATM_CELL_HEIGHT = Main.ATM_BEZEL_THICKNESS;
	public static final int ATM_WIDTH_CELLS = ATM_WIDTH / ATM_CELL_WIDTH;
	public static final int ATM_HEIGHT_CELLS = ATM_HEIGHT / ATM_CELL_HEIGHT;
	public static final int ATM_PANEL_WIDTH = ATM_WIDTH - (2 * ATM_BEZEL_THICKNESS);
	public static final int ATM_PANEL_HEIGHT = ATM_HEIGHT - (2 * ATM_BEZEL_THICKNESS);
	public static final int ATM_PANEL_WIDTH_CELLS = ATM_PANEL_WIDTH / ATM_CELL_WIDTH;
	public static final int ATM_PANEL_HEIGHT_CELLS = ATM_PANEL_HEIGHT / ATM_CELL_HEIGHT;
	
	//public static final Color ATM_FRAME_BACKGROUND = Color.DARK_GRAY;
	public static final Color ATM_FRAME_BACKGROUND = new Color(0xC0C0C0); // Silver

	//public static final Color ATM_CONTENT_PANEL_BACKGROUND = Color.BLUE;
	//public static final Color ATM_CONTENT_PANEL_BACKGROUND = new Color(0x007FFF);
	//public static final Color ATM_CONTENT_PANEL_BACKGROUND = new Color(0x94D6E7);
	public static final Color ATM_CONTENT_PANEL_BACKGROUND = new Color(0xC6EFF7);
	
	public static final String ATM_FRAME_TITLE = "TaKeBo Bank ATM";

    public static JFrameATM atmFrame;

	/**
	 * This is the main entry point for the program
	 * 
	 * @param args The arguments passed to the program by the operating system
	 * @throws Exception 
	 */
	public static void main(String[] args)
	{
		// Process any arguments that were passed at start-up
		processArgs(args);
		
		// Set up the application frame
		atmFrame = new JFrameATM();
		atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Exit the program all together
		atmFrame.setTitle(ATM_FRAME_TITLE + " - (UndefinedPanel )");    // Set the title
		atmFrame.setSize(ATM_WIDTH, ATM_HEIGHT);                        // Set the size
		atmFrame.setLocationRelativeTo(null);                           // Center the frame on the screen
		atmFrame.setResizable(false);                                   // Don't allow frame to be resized
		atmFrame.setUndecorated(!DECORATED);                            // Sets whether the title bar, minimize, maximize and close icons are displayed
		                                                                
		// Create the splash panel and place it on the frame
		JPanelSplash panelSplash = new JPanelSplash(atmFrame);
		//panelSplash.showPanel();
		atmFrame.setContentPanel(panelSplash);
		
		// This is the first panel to be displayed
		// therefore we need to set the frame to visible here
		atmFrame.setVisible(true);
		
		try 
		{
		    Thread.sleep(2 * 1000); // Sleep for 2 seconds
		} 
		catch (InterruptedException intrrptdEx) 
		{
	    	System.err.println(Main.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": ERROR: InterruptedException: \n" 
			         + intrrptdEx.getMessage()
			          );
		}
		
		// Create the welcome panel and place it on the frame
		JPanelWelcome panelWelcome = new JPanelWelcome(atmFrame);
		panelWelcome.showPanel();
        
		// All done
	    return;
	}
	
	/**
	 * 
	 * @param args The arguments passed to the program by the operating system
	 */
	private static void processArgs(String[] args)
	{
		// Loop over the arguments and process each one
		for (String arg : args) {
			if (arg.toUpperCase().equals("DEBUG")) DEBUG = true;
			if (arg.toUpperCase().equals("DECORATED")) DECORATED = true;
			if (arg.toUpperCase().equals("NOPRINT")) NOPRINT = true;

			if (Main.DEBUG)
				System.err.println("Main.processArgs: arg: " + arg);
		}
		
		// All done
		return;
	}

}
