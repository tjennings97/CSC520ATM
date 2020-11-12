package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Main;
import model.Account;
import model.AccountType;

public class JPanelSelectAccount extends JPanel implements ActionListener 
{
	// A unique ID required because this class is serializable
	// This can be auto generated by hovering over the warning on the class name
	private static final long serialVersionUID = -8150660236285636871L;
	
	private final String ACTION_PRIMARY_SAVINGS = "PRIMARY_SAVINGS";
	private final String ACTION_PRIMARY_CHECKING = "PRIMARY_CHECKING";
	private final String ACTION_SECONDARY_SAVINGS = "SECONDARY_SAVINGS";
	private final String ACTION_SECONDARY_CHECKING = "SECONDARY_CHECKING";
	private final String ACTION_CANCEL = "CANCEL";

	JFrameATM atmFrame;
	boolean isDeposit;
	
	JLabel lblTitle;
	JLabel lblMessage;
	JLabel lblPrimarySavings;
	JLabel lblPrimaryChecking;
	JLabel lblSecondarySavings;
	JLabel lblSecondaryChecking;
	JLabel lblCancel;
	
	/**
	 * Constructor for the select account panel
	 * 
	 * @param appFrame Parent frame that will hold the panels created by actions performed by this class
	 */
	public JPanelSelectAccount(JFrameATM atmFrame, boolean isDeposit) {
		
		// Save the application frame
		this.atmFrame = atmFrame;
		this.isDeposit = isDeposit;
		
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
		
		if (atmFrame.getTransactionType().equals("DEPOSIT")) {
			lblTitle = new JLabel("Please select the account to deposit into. ");
		} else {
			lblTitle = new JLabel("Please select the Source Account. "); 
		}
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.NORTH;
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
		
		int labelWidth = 240;
		int labelHeight = 40;
		
		lblPrimarySavings = new JLabel(" " + AccountType.PrimarySavings.toString(), JLabel.LEFT);
		lblPrimarySavings.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Left1(ACTION_PRIMARY_SAVINGS, this);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		lblPrimarySavings.setOpaque(true);
		lblPrimarySavings.setBackground(Color.BLACK);
		lblPrimarySavings.setForeground(Color.WHITE);
		//lblblPrimarySavingslBalance.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		lblPrimarySavings.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		//lblPrimarySavings.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblPrimarySavings.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblPrimarySavings.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblPrimarySavings.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblPrimarySavings, constraints);
		
		lblPrimaryChecking = new JLabel(" " + AccountType.PrimaryChecking.toString(), JLabel.LEFT);
		lblPrimaryChecking.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Left2(ACTION_PRIMARY_CHECKING, this);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		lblPrimaryChecking.setOpaque(true);
		lblPrimaryChecking.setBackground(Color.BLACK);
		lblPrimaryChecking.setForeground(Color.WHITE);
		//lblPrimaryChecking.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		lblPrimaryChecking.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		//lblPrimaryChecking.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblPrimaryChecking.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblPrimaryChecking.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblPrimaryChecking.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblPrimaryChecking, constraints);
		
		lblSecondarySavings = new JLabel(" " + AccountType.SecondarySavings.toString(), JLabel.LEFT);
		lblSecondarySavings.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Left3(ACTION_SECONDARY_SAVINGS, this);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		lblSecondarySavings.setOpaque(true);
		lblSecondarySavings.setBackground(Color.BLACK);
		lblSecondarySavings.setForeground(Color.WHITE);
		//lblSecondarySavings.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		lblSecondarySavings.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		//lblSecondarySavings.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblSecondarySavings.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblSecondarySavings.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblSecondarySavings.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblSecondarySavings, constraints);
		
		lblSecondaryChecking = new JLabel(" " + AccountType.SecondaryChecking.toString(), JLabel.LEFT);
		lblSecondaryChecking.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Left4(ACTION_SECONDARY_CHECKING, this);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		lblSecondaryChecking.setOpaque(true);
		lblSecondaryChecking.setBackground(Color.BLACK);
		lblSecondaryChecking.setForeground(Color.WHITE);
		//lblSecondaryChecking.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		lblSecondaryChecking.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		//lblSecondaryChecking.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblSecondaryChecking.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblSecondaryChecking.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblSecondaryChecking.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblSecondaryChecking, constraints);
		
		labelWidth = 200;
		labelHeight = 40;
		
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
		//lblCancel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		lblCancel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		//lblCancel.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblCancel.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblCancel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblCancel.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblCancel, constraints);
			
		/*
		// Set initial component focus
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Doesn't work when you just want to press the enter key and not click
				//boolean focused = btnDone.requestFocusInWindow();
				//if (Main.DEBUG)
			    //	System.err.println(this.getClass().getTypeName() + "."
				//	         + (new Throwable().getStackTrace()[0].getMethodName())
				//	         + ": focused: >" + focused + "<"
				//	          );
				
				// Use this when you just want to press the enter key and not have to click the button
				atmFrame.getRootPane().setDefaultButton( btnDone );
				if (Main.DEBUG)
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": DefaultButton set >" + btnDone.getText() + "<"
					          );
			}
		});
		*/

		// All done
		return;
	}
	
	public void showPanel(){
		
		// Set this panel as the frame's content panel and update the frame title
		atmFrame.setContentPanel(this);
		atmFrame.setTitle(Main.ATM_FRAME_TITLE + " - (" + this.getClass().getSimpleName() + ")");
		
		// Actions prior to display
		
		// Reset the different account labels
		lblPrimarySavings.setVisible(false);
		lblPrimarySavings.setName("");
		atmFrame.setActionListener_Left1("", null);
		lblPrimaryChecking.setVisible(false);
		lblPrimaryChecking.setName("");
		atmFrame.setActionListener_Left2("", null);
		lblSecondarySavings.setVisible(false);
		lblSecondarySavings.setName("");
		atmFrame.setActionListener_Left3("", null);
		lblSecondaryChecking.setVisible(false);
		lblSecondaryChecking.setName("");
		atmFrame.setActionListener_Left4("", null);
		
		// Change the caption if we're looking for the "target" account (second time through here) 
		if (atmFrame.getSourceAccountNumber() != 0) lblTitle.setText("Please select the Target Account. ");
		
		// Get the account associated with this customer
		ArrayList<Account> accounts = Account.getAccounts(atmFrame.getPerson().getIdentification());
		
		// Activate available accounts
		for (Account account : accounts)
		{
			// Check if we're looking for the "target" account (second time through here)
			// Don't enable the "source" account when looking for the "target"
			if (account.getAccountNumber() == atmFrame.getSourceAccountNumber()) continue;
			
			switch (account.getAccountType())
			{
				case PrimarySavings:
				{
					lblPrimarySavings.setVisible(true);
					lblPrimarySavings.setName(Integer.toString(account.getAccountNumber()));
					atmFrame.setActionListener_Left1(ACTION_PRIMARY_SAVINGS, this);

					break;
				}
				
				case PrimaryChecking:
				{
					lblPrimaryChecking.setVisible(true);
					lblPrimaryChecking.setName(Integer.toString(account.getAccountNumber()));
					atmFrame.setActionListener_Left2(ACTION_PRIMARY_CHECKING, this);

					break;
				}
				
				case SecondarySavings:
				{
					lblSecondarySavings.setVisible(true);
					lblSecondarySavings.setName(Integer.toString(account.getAccountNumber()));
					atmFrame.setActionListener_Left3(ACTION_SECONDARY_SAVINGS, this);

					break;
				}
				
				case SecondaryChecking:
				{
					lblSecondaryChecking.setVisible(true);
					lblSecondaryChecking.setName(Integer.toString(account.getAccountNumber()));
					atmFrame.setActionListener_Left4(ACTION_SECONDARY_CHECKING, this);

					break;
				}
				
				default:
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": ERROR: Invalid AccountType: >" + account.getAccountType() + "<"
					          );
					break;
				}
			}
		}
		
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

		String action = actionEvent.getActionCommand();
		
		if(Main.DEBUG)
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": Got action: : " + action
			          );
		
		switch (action) {
		
			case ACTION_PRIMARY_SAVINGS:
			{
				 // Save the account number
				 if (atmFrame.getSourceAccountNumber() == 0)
				 {
					 atmFrame.setSourceAccountNumber(Integer.parseInt(lblPrimarySavings.getName()));
				 }
				 else
				 {
					 atmFrame.setTargetAccountNumber(Integer.parseInt(lblPrimarySavings.getName()));
				 }
				
				break;
			}
			
			case ACTION_PRIMARY_CHECKING:
			{				 
				// Save the account number
				if (atmFrame.getSourceAccountNumber() == 0)
				{
					atmFrame.setSourceAccountNumber(Integer.parseInt(lblPrimaryChecking.getName()));
				}
				else
				{
					atmFrame.setTargetAccountNumber(Integer.parseInt(lblPrimaryChecking.getName()));
				}
				
				break;
			}
				
			case ACTION_SECONDARY_SAVINGS:
			{			 
				// Save the account number
				if (atmFrame.getSourceAccountNumber() == 0)
				{
					atmFrame.setSourceAccountNumber(Integer.parseInt(lblSecondarySavings.getName()));
				}
				else
				{
					atmFrame.setTargetAccountNumber(Integer.parseInt(lblSecondarySavings.getName()));
				}
				
				break;
			}
				
			case ACTION_SECONDARY_CHECKING:
			{			 
				// Save the account number
				if (atmFrame.getSourceAccountNumber() == 0)
				{
					atmFrame.setSourceAccountNumber(Integer.parseInt(lblSecondaryChecking.getName()));
				}
				else
				{
					atmFrame.setTargetAccountNumber(Integer.parseInt(lblSecondaryChecking.getName()));
				}
				
				break;
			}
				
			case ACTION_CANCEL:
			{
				// Create the customer panel and place it on the frame
				JPanelCustomer panelCustomer = new JPanelCustomer(atmFrame);
				panelCustomer.showPanel();
				
				return;
			}
		
		}
		
		// Get the target account if this is a transfer and we don't have it
		if (atmFrame.getTransactionType().equals("TRANSFER") && atmFrame.getTargetAccountNumber() == 0) 
		{
			showPanel();
			return;
		}
		
		// Get transaction accounts (right now only used for debugging)
		Account sourceAccount = null;
		if (atmFrame.getSourceAccountNumber() != 0)
		{
		    sourceAccount = Account.getAccount(atmFrame.getPerson().getIdentification()
                                             , atmFrame.getSourceAccountNumber());
		}
		
		Account targetAccount = null;
		if (atmFrame.getTargetAccountNumber() != 0)
		{
			targetAccount = Account.getAccount(atmFrame.getPerson().getIdentification()
                                             , atmFrame.getTargetAccountNumber());
		}

		// Display next panel based on the transaction type
		switch (atmFrame.getTransactionType())
		{
			case "BALANCE":
			{			 		        
				// Create the check balance panel and place it on the frame
				JPanelCheckBalance panelCheckBalance = new JPanelCheckBalance(atmFrame);
				panelCheckBalance.showPanel();

				break;
			}
			
			case "WITHDRAWAL":
			{
						
				// Create the withdrawal panel and place it on the frame
				JPanelWithdraw panelWithdraw = new JPanelWithdraw(atmFrame);
				panelWithdraw.showPanel();

				break;
			}
			
			case "TRANSFER":
			{
				// Create the transfer panel and place it on the frame
				JPanelTransfer panelTransfer = new JPanelTransfer(atmFrame);
				panelTransfer.showPanel();

				break;
			}
			
			case "DEPOSIT":
			{	
				// Create the deposit panel and place it on the frame
				JPanelDeposit panelDeposit = new JPanelDeposit(atmFrame);
				panelDeposit.showPanel();

				break;
				
			}
		
			default:
			{
		    	System.err.println(this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": ERROR: Invalid TransactionType: >" + atmFrame.getTransactionType() + "<"
				          );
				break;
			}
		}
		
		// All done
		return;
	}

}
