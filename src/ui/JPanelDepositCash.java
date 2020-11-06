package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import main.Main;
import model.Account;

public class JPanelDepositCash extends JPanel implements ActionListener 
{
	private final String ACTION_ENTER = "ENTER";
	private final String ACTION_CANCEL = "CANCEL";

	JFrameATM atmFrame;
	
	JLabel lblTitle;
	JLabel lblMessage;
	JLabel lblSubTitle;
	JLabel lblCancel;
	
	KeyEventDispatcher keyEventDispatcher = null;
	
	/**
	 * Constructor for the Service panel
	 * 
	 * @param appFrame Parent frame that will hold the panels created by actions performed by this class
	 */
	public JPanelDepositCash(JFrameATM atmFrame) {
		
		// Save the application frame
		this.atmFrame = atmFrame;
		
		// Reset the frame's button action listeners
		atmFrame.setButtonActionDefaults();
		
		// Set frame background color
		this.setBackground(Main.ATM_CONTENT_PANEL_BACKGROUND);

        // Number of rows and heights
		int panelRows = 7;
        int[] rows = new int[panelRows];
        Arrays.fill(rows, (Main.ATM_PANEL_HEIGHT / panelRows));
		
		// Number of columns and widths
        int panelColumns = 2;
        int[] columns = new int[panelColumns];
        Arrays.fill(columns, (Main.ATM_PANEL_WIDTH / panelColumns));
		
		// Set layout
		GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = rows;
        gridBagLayout.columnWidths = columns;
		this.setLayout(gridBagLayout);
		
		// Set initial constraints
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,0,0,0);
		
		// Create and add components for this panel
		lblTitle = new JLabel("Please Insert Cash. ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(lblTitle, constraints);
		
		lblMessage = new JLabel(" ");
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMessage.setForeground(Color.RED);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(lblMessage, constraints);
		
		lblSubTitle = new JLabel("Press ENTER when finished.  ");
		lblSubTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(lblSubTitle, constraints);
		
		int labelWidth = 200;
		int labelHeight = 40;
		
		lblCancel = new JLabel("Cancel ", JLabel.RIGHT);
		lblCancel.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Right4(ACTION_CANCEL, this);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,10);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.EAST;
		lblCancel.setOpaque(true);
		lblCancel.setBackground(Color.BLACK);
		lblCancel.setForeground(Color.WHITE);
		lblCancel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		lblCancel.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblCancel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblCancel.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblCancel, constraints);

		
		// Setup a KeyListener
		// This code works on a panel.  
		// The issue is that the mouse pointer can be over any number of components 
		// on the panel so you would need to setup a listener on every component.
		// This code will intercept the KeyEvent before any of the other components do.
		// Setting the return to "true" tells the KeyboardFocusManager should take 
		// no further action with regard to the KeyEvent 
		keyEventDispatcher = new KeyEventDispatcher() 
		                     {
		 				        @Override
		 				        public boolean dispatchKeyEvent(KeyEvent ke) {
 			               		    

		 				        	boolean handled = false;
		 				            switch (ke.getID()) {

		 			                case KeyEvent.KEY_PRESSED:

		 			    				if (Main.DEBUG)
			 						    	System.err.println(this.getClass().getTypeName() + "."
			 								         + (new Throwable().getStackTrace()[0].getMethodName())
			 								         + "KED: Key press code: " + ke.getKeyCode()
			 								        + ", char: " + ke.getKeyChar()
			 								          );
		 			                    if (ke.getKeyCode() == KeyEvent.VK_ENTER)
		 			                    {
		 			                    	handled = true;
		 			        				ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ACTION_ENTER);
		 			        				actionPerformed(actionEvent);
		 			                    }
		 			                    else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
		 			                    {
		 			                    	handled = true;
		 			        				ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ACTION_CANCEL);
		 			        				actionPerformed(actionEvent);
		 			                    }
		 			    				break;

		 			                default:
		 			                	// No action here
		 			                	// We're just not interested in this key
		 			                    break;
		 				            }
		 				            return handled;
		 				        }
		 				    };
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

		// All done
		return;
	}
	
	public void showPanel(){
		
		// Set this panel as the frame's content panel and update the frame title
		atmFrame.setContentPanel(this);
		atmFrame.setTitle(Main.ATM_FRAME_TITLE + " - (" + this.getClass().getSimpleName() + ")");
		
		
		// Re-paint and re-validate to display the panel
		atmFrame.repaint();
		atmFrame.revalidate();

		// All done
		return;
	}
	
	/**
	 * Handles the actions that are requested by this panel
	 * 
	 * @param actionEvent An ActionEvent containing the details of the event
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		boolean error = false;

		String action = actionEvent.getActionCommand();
		
		if(Main.DEBUG)
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": Got action: : " + action
			          );
		
		switch (action) {
		
		case ACTION_ENTER:
			{

		        // Get source account
		        int identification = atmFrame.getPerson().getIdentification();
		        int sourceAccountNumber = atmFrame.getSourceAccountNumber();
		        Account sourceAccount = Account.getAccount(identification, sourceAccountNumber);
		        
				// Do the deposit
				sourceAccount.depositFunds(this.atmFrame.getTransactionAmount());
		        Account.getAccount(identification, sourceAccountNumber);
				
				JPanelDepositConfirmation depositConfirmation = new JPanelDepositConfirmation(atmFrame);
				depositConfirmation.showPanel();
				
				// Remove the Keyboard manager as we are leaving this panel
             	KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
		        
		        break;
			}
	
				
			case ACTION_CANCEL:
			{
				// Remove the Keyboard manager as we are leaving this panel
             	KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
				// Create the customer panel and place it on the frame
				JPanelCustomer customerLogon = new JPanelCustomer(atmFrame);
				customerLogon.showPanel();
				
				break;
			}
		}
		
		// All done
		return;
	}

}
