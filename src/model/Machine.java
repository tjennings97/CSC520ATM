package model;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import event.CashStatusEvent;
import event.PaperStatusEvent;
import event.DepositStatusEvent;
import event.CashStatusEvent.IObserverCashStatus;
import event.PaperStatusEvent.IObserverPaperStatus;
import event.DepositStatusEvent.IObserverDepositStatus;

public class Machine 
{
	/**
	 * Create the singleton instance of this class
	 */
	private static final Machine SINGLETON_INSTANCE = new Machine();

	public static final int MACHINE_ID = 123456789;
			
	private String cashStatus = null;
	private Color cashStatusColor = null;
	private BigDecimal cashAmount = null;
	private String paperStatus = null;
	private Color paperStatusColor = null;
	private int paperLength = 0;
	private String depositStatus = null;
	private Color depositStatusColor = null;
	private int depositFillPercent = 100;
	
	private CashStatusEvent cashStatusEvent = null;
	private PaperStatusEvent paperStatusEvent = null;
	private DepositStatusEvent depositStatusEvent = null;
	
	/**
	 * Hide the constructor to the outside world
	 */
	private Machine() 
	{
		// Create the status events
		cashStatusEvent = new CashStatusEvent();
		paperStatusEvent = new PaperStatusEvent();
		depositStatusEvent = new DepositStatusEvent();
		
		// Get the current database values
		getCurrentMachineStatus();
		
		// Cash event code
		IObserverCashStatus updateCashStatus = (machineStatus -> {cashStatus = machineStatus.displayText;
		                                                          cashStatusColor = machineStatus.displayColor;
		                                                          return;
		                                                          }
		                                       );
		// Subscribe to the cash event
		cashStatusEvent.addObserver(updateCashStatus);
		
		// Paper event code
		IObserverPaperStatus updatePaperStatus = (machineStatus -> {paperStatus = machineStatus.displayText;
		                                                            paperStatusColor = machineStatus.displayColor;
		                                                            return;
		                                                            }
		                                         );
		// Subscribe to the paper event
		paperStatusEvent.addObserver(updatePaperStatus);
		
		// Deposit event code
		IObserverDepositStatus updateDepositStatus = (machineStatus -> {depositStatus = machineStatus.displayText;
		                                                                depositStatusColor = machineStatus.displayColor;
		                                                                return;
		                                                                }
		                                             );
		// Subscribe to the deposit event
		depositStatusEvent.addObserver(updateDepositStatus);
		
		// All done
		return;
	};
	
	/**
	 * @return - The singleton instance of this class
	 */
	public static Machine getInstance()
	{
		// All done
		return SINGLETON_INSTANCE;
	}
	
	/**
	 * Reset the machine by retrieving the current database values
	 */
	public void getCurrentMachineStatus()
	{
		// Get Machine status from the database
		// Get the person from the database
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
			
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call SelectMachine(?)}", MACHINE_ID);
			
			if(resultSet.next())
			{
				try
				{
					cashStatus = MachineCashStatus.valueOf(resultSet.getString("CashStatus")).getText();
					cashStatusColor = (Color)(Color.class.getField(resultSet.getString("CashStatusColor"))).get(null);
					cashStatusColor = cashStatusColor.darker();
					MachineStatus machineCashStatus = new MachineStatus(cashStatus, cashStatusColor);
					cashStatusEvent.updateStatus(machineCashStatus);
					cashAmount = resultSet.getBigDecimal("CashAmount");					
					
					paperStatus = MachinePaperStatus.valueOf(resultSet.getString("PaperStatus")).getText();
					paperStatusColor = (Color)(Color.class.getField(resultSet.getString("PaperStatusColor"))).get(null);
					paperStatusColor = paperStatusColor.darker();
					MachineStatus machinePaperStatus = new MachineStatus(paperStatus, paperStatusColor);
					paperStatusEvent.updateStatus(machinePaperStatus);
					paperLength = resultSet.getInt("PaperLength");
					
					depositStatus = MachineDepositStatus.valueOf(resultSet.getString("DepositStatus")).getText();
					depositStatusColor = (Color)(Color.class.getField(resultSet.getString("DepositStatusColor"))).get(null);
					depositStatusColor = depositStatusColor.darker();
					MachineStatus machineDepositStatus = new MachineStatus(depositStatus, depositStatusColor);
					depositStatusEvent.updateStatus(machineDepositStatus);
					depositFillPercent = resultSet.getInt("DepositFillPercent");
				}
				catch (Exception ex)
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": Exception: \n" 
					         + ex.getMessage()
					          );
				}
			}
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
		}
		catch (Exception ex)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": Exception: \n" 
			         + ex.getMessage()
			          );
		}

		// All done
		return;
	}

	/**
	 * 
	 * @param cashAdjustment the amount of cash withdrawn
	 */
	public void adjustCash(BigDecimal cashAdjustment)
	{
		// Adjust the cash amount
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call AdjustMachineCash(?, ?)}"
					                                                       , MACHINE_ID, cashAdjustment);
			
			// No result set is returned so need the null check first
			if(resultSet != null && resultSet.next())
			{
				try
				{
					// No result set is returned
				}
				catch (Exception ex)
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": Exception: \n" 
					         + ex.getMessage()
					          );
				}
			}
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Account.class as this is a static method
	    	System.err.println(Account.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
		}
		
		// Get the current machine status
		getCurrentMachineStatus();
		
		// All done
		return;
	}
	
	/**
	 * 
	 * @param paperAdjustment the amount of paper used to print the receipt
	 */
	public void adjustPaper(int paperAdjustment)
	{
		// Adjust the paper amount
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call AdjustMachinePaper(?, ?)}"
					                                                       , MACHINE_ID, paperAdjustment);
			
			// No result set is returned so need the null check first
			if(resultSet != null && resultSet.next())
			{
				try
				{
					// No result set is returned
				}
				catch (Exception ex)
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": Exception: \n" 
					         + ex.getMessage()
					          );
				}
			}
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Account.class as this is a static method
	    	System.err.println(Account.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
		}
		
		// Get the current machine status
		getCurrentMachineStatus();
		
		// All done
		return;
	}

	/**
	 * 
	 * @param depositAdjustment the percent of deposit drawer space used
	 */
	public void adjustDeposit(int depositAdjustment)
	{
		// Adjust the deposit amount (drawer space fill percent)
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call AdjustMachineDeposit(?, ?)}"
					                                                       , MACHINE_ID, depositAdjustment);
			
			// No result set is returned so need the null check first
			if(resultSet != null && resultSet.next())
			{
				try
				{
					// No result set is returned
				}
				catch (Exception ex)
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": Exception: \n" 
					         + ex.getMessage()
					          );
				}
			}
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Account.class as this is a static method
	    	System.err.println(Account.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
		}
		
		// Get the current machine status
		getCurrentMachineStatus();
		
		// All done
		return;
	}

	
	/**
	 * 
	 * @return - A CashStatusEvent object
	 */
	public CashStatusEvent getCashStatusEvent()
	{
		// all done
		return cashStatusEvent;
	}
	
	/**
	 * 
	 * @return - A PaperStatusEvent object
	 */
	public PaperStatusEvent getPaperStatusEvent()
	{
		// all done
		return paperStatusEvent;
	}
	
	/**
	 * 
	 * @return - A PaperStatusEvent object
	 */
	public DepositStatusEvent getDepositStatusEvent()
	{
		// all done
		return depositStatusEvent;
	}
	
	/**
	 * 
	 * @return - The cash status text
	 */
	public String getCashStatus()
	{
		// All done
		return cashStatus;
	}
	
	/**
	 * 
	 * @return - The cash status color
	 */
	public Color getCashStatusColor()
	{
		// All done
		return cashStatusColor;
	}
	
	/**
	 * 
	 * @return - The cash amount
	 */
	public BigDecimal getCashAmount()
	{
		// All done
		return cashAmount;
	}
	
	/**
	 * 
	 * @return - The paper status text
	 */
	public String getPaperStatus()
	{
		// All done
		return paperStatus;
	}
	
	/**
	 * 
	 * @return - The paper status color
	 */
	public Color getPaperStatusColor()
	{
		// All done
		return paperStatusColor;
	}
	
	/**
	 * 
	 * @return - The paper length
	 */
	public int getPaperLength()
	{
		// All done
		return paperLength;
	}
	
	/**
	 * 
	 * @return - The deposit status text
	 */
	public String getDepositStatus()
	{
		// All done
		return depositStatus;
	}
	
	/**
	 * 
	 * @return - The deposit status color
	 */
	public Color getDepositStatusColor()
	{
		// All done
		return depositStatusColor;
	}
	
	/**
	 * 
	 * @return - The deposit fill percent
	 */
	public int getDepositFillPercent()
	{
		// All done
		return depositFillPercent;
	}
}
