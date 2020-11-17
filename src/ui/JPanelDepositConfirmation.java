package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import model.Account;
import util.Misc;

public class JPanelDepositConfirmation extends JPanel implements ActionListener
{
	private final String ACTION_PRINT = "PRINT";
	private final String ACTION_DONE = "DONE";

	JFrameATM atmFrame;
	
	JLabel lblTitle;
	JLabel lblMessage;
	JLabel lblSourceAccount;
	JLabel lblSourceAccountType;
	JLabel lblAmount;
	JLabel lblAmountValue;
	JLabel lblPrint;
	JLabel lblDone;
	
	/**
	 * Constructor for the panel
	 * 
	 * @param appFrame Parent frame that will hold the panels created by actions performed by this class
	 */
	public JPanelDepositConfirmation(JFrameATM atmFrame) {
		
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
		lblTitle = new JLabel("Deposit Successful. ");
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
		
		lblSourceAccount = new JLabel("Account: ");
		lblSourceAccount.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.EAST;
		this.add(lblSourceAccount, constraints);
		
		lblSourceAccountType = new JLabel("Undefined ");
		lblSourceAccountType.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		this.add(lblSourceAccountType, constraints);
		
		lblAmount = new JLabel("Amount: ");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.EAST;
		this.add(lblAmount, constraints);
		
		lblAmountValue = new JLabel("Undefined ");
		lblAmountValue.setFont(new Font("Tahoma", Font.BOLD, 22));
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		this.add(lblAmountValue, constraints); 
		
		int labelWidth = 185;
		int labelHeight = 40;
		
		lblPrint = new JLabel(" Print", JLabel.LEFT);
		lblPrint.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Left4(ACTION_PRINT, this);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,10,0,0);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.WEST;
		lblPrint.setOpaque(true);
		lblPrint.setBackground(Color.BLACK);
		lblPrint.setForeground(Color.WHITE);
		lblPrint.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		lblPrint.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblPrint.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblPrint.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblPrint, constraints);
		
		lblDone = new JLabel("Done ", JLabel.RIGHT);
		lblDone.setFont(new Font("Tahoma", Font.BOLD, 22));
		atmFrame.setActionListener_Right4(ACTION_DONE, this);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0,0,0,10);  //top, left, bottom, right padding
		constraints.anchor = GridBagConstraints.EAST;
		lblDone.setOpaque(true);
		lblDone.setBackground(Color.BLACK);
		lblDone.setForeground(Color.WHITE);
		lblDone.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x007FFF)));
		lblDone.setMinimumSize(new Dimension(labelWidth, labelHeight)); // Width, Height
		lblDone.setPreferredSize(new Dimension(labelWidth, labelHeight));
		lblDone.setMaximumSize(new Dimension(labelWidth, labelHeight));
		this.add(lblDone, constraints);
		
		// All done
		return;
	}
	
	public void showPanel() {
		
		// Update drawer fill percentage 
		atmFrame.getMachine().adjustDeposit(5);
		
		// Set this panel as the frame's content panel and update the frame title
		atmFrame.setContentPanel(this);
		atmFrame.setTitle(Main.ATM_FRAME_TITLE + " - (" + this.getClass().getSimpleName() + ")");

		// Get source account
		Account sourceAccount = Account.getAccount(atmFrame.getPerson().getIdentification()
                                                 , atmFrame.getSourceAccountNumber());
	
		// Display source account type
		lblSourceAccountType.setText(sourceAccount.getAccountType().name());
	
		// Display account balance
		lblAmountValue.setText("$ " + Misc.roundToMoney(atmFrame.getTransactionAmount()));
		
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
			
			case ACTION_PRINT:
			{					
				// Check paper level
				if (atmFrame.getMachine().getPaperLength() > 0)
				{
					// Create the print receipt pane and place it on the frame
					JPanelPrintReceipt panelPrintReceipt = new JPanelPrintReceipt(atmFrame);
					panelPrintReceipt.showPanel();
				}
				else
				{
					lblMessage.setText("Out of paper.");
				}

				break;
			}
			
			case ACTION_DONE:
			{
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
