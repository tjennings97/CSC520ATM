package model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Main;

public class Account {
	
	private int identification = 0;
	private int accountNumber = 0;
	private AccountType accountType = null;
	private BigDecimal balance = new BigDecimal("0.00");
	
	public Account(int identification
		         , int accountNumber
		         , AccountType accountType
			     , BigDecimal balance)
	{
		this.identification = identification;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		
		// All done
		return;
	}
	
	public static ArrayList<Account> getAccounts(int identification)
	{
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		// Get all the accounts for this person from the database
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call SelectAccounts(?)}", identification);
			
			while(resultSet.next())
			{
				accounts.add(new Account(identification
	                                   , resultSet.getInt("AccountNumber")
		                               , AccountType.valueOf(resultSet.getString("AccountType"))		
	                                   , resultSet.getBigDecimal("Balance")
	                                    )
						    );
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
	    	//accounts = null;
		}
		
		// All done
		return accounts;
	}
	
	public static Account getAccount(int identification
			                       , int accountNumber)
	{
		Account account = null;
		
		// Get the account
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call SelectAccount(?, ?)}", identification, accountNumber);
			
			while(resultSet.next())
			{
				account = new Account(identification
	                                , resultSet.getInt("AccountNumber")
		                            , AccountType.valueOf(resultSet.getString("AccountType"))		
	                                , resultSet.getBigDecimal("Balance")
	                                 );
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
	    	account = null;
		}
		
		// All done
		return account;
	}
	
	public int getIdentification()
	{
		return identification;
	}
	
	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	public AccountType getAccountType()
	{
		return accountType;
	}
	
	public BigDecimal getBalance()
	{
		return balance;
	}
	
	public boolean deductFunds(BigDecimal amount)
	{
		boolean success = false;
		
		// Deduct funds
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call DeductFunds(?, ?, ?)}", identification
					                                                                                      , accountNumber
					                                                                                      , amount);
					
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
			    	success = false;
				}
			}
			
			success = true;
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Account.class as this is a static method
	    	System.err.println(Account.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	success = false;
		}
		
		// All done
		return success;
	}
	
	public boolean depositFunds(BigDecimal amount)
	{
		boolean success = false;
		
		// Deposit funds
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call DepositFunds(?, ?, ?)}", identification
					                                                                                       , accountNumber
					                                                                                       , amount);
					
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
			    	success = false;
				}
			}
			
			success = true;
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Account.class as this is a static method
	    	System.err.println(Account.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	success = false;
		}
		
		// All done
		return success;
	}
	
	public boolean transferFunds(Account targetAccount
			                   , BigDecimal amount)
	{
		boolean success = false;
		
		// Get target account number
		int targetAccountNumber = targetAccount.getAccountNumber();
		
		// Transfer funds
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
					
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call TransferFunds(?, ?, ?, ?)}", identification
					                                                                                           , accountNumber
					                                                                                           , targetAccountNumber
					                                                                                           , amount);
			
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
			    	success = false;
				}
			}
			
			success = true;
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Account.class as this is a static method
	    	System.err.println(Account.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	success = false;
		}
		
		// All done
		return success;
	}

}
