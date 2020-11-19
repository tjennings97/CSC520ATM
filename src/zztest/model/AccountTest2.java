/**
 * JUnit 5 tests for class Account.java
 */
package zztest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.Main;
import model.Account;
import model.AccountType;
import ui.JFrameATM;
import util.Misc;

/**
 * @author TakeBo Bank, LLC
 *
 */
class AccountTest2 {

	/**
	 * "setup" run before the test methods in this class
	 */
    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
        
        try
        {
	        // Create the ATM frame (and therefore the data access stuff)
			Main.atmFrame = new JFrameATM();
        }
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}
    }
    
	/**
	 * "tear" run after the test methods in this class
	 */
    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
        
        try
        {
	        // Clear the ATM frame (and therefore the data access stuff)
			Main.atmFrame = null;
        }
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}
    }

	/**
	 * Test method for {@link model.Account#Account(int, int, model.AccountType, java.math.BigDecimal)}.
	 */
	@Test
	final void testAccount() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
	        AccountType accountType = null;
		    BigDecimal balance	= null;
		    
			// Create an account of type PrimaryChecking
		    identification = 12345;
			accountNumber = 1234501;
	        accountType = AccountType.PrimaryChecking;
		    balance	= new BigDecimal("1212.12");
			account = new Account(identification, accountNumber, accountType, balance);
			
			assertEquals(account.getIdentification(), identification);
			assertEquals(account.getAccountNumber(), accountNumber);
			assertEquals(account.getAccountType(), accountType);
			assertEquals(account.getBalance(), balance);
		    
			// Create an account of type PrimarySavings
		    identification = 23456;
			accountNumber = 2345602;
	        accountType = AccountType.PrimarySavings;
		    balance	= new BigDecimal("3434.34");
			account = new Account(identification, accountNumber, accountType, balance);
			
			assertEquals(account.getIdentification(), identification);
			assertEquals(account.getAccountNumber(), accountNumber);
			assertEquals(account.getAccountType(), accountType);
			assertEquals(account.getBalance(), balance);
		    
			// Create an account of type SecondaryChecking
		    identification = 34567;
			accountNumber = 3456703;
	        accountType = AccountType.SecondaryChecking;
		    balance	= new BigDecimal("5656.56");
			account = new Account(identification, accountNumber, accountType, balance);
			
			assertEquals(account.getIdentification(), identification);
			assertEquals(account.getAccountNumber(), accountNumber);
			assertEquals(account.getAccountType(), accountType);
			assertEquals(account.getBalance(), balance);
		    
			// Create an account of type SecondarySavings
		    identification = 45678;
			accountNumber = 4567804;
	        accountType = AccountType.SecondarySavings;
		    balance	= new BigDecimal("7878.78");
			account = new Account(identification, accountNumber, accountType, balance);
			
			assertEquals(account.getIdentification(), identification);
			assertEquals(account.getAccountNumber(), accountNumber);
			assertEquals(account.getAccountType(), accountType);
			assertEquals(account.getBalance(), balance);
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#getAccounts(int)}.
	 */
	@Test
	final void testGetAccounts() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			ArrayList<Account> accounts = null;
			int identification = 0;
			
			// Get all accounts for a customer
			identification = 111;
			accounts = Account.getAccounts(identification);
			
			assertFalse(accounts.isEmpty());
			
			// Display accounts
			for (Account account : accounts)
			{
				System.out.println(MachineTest.class.getTypeName() + "."
		                + (new Throwable().getStackTrace()[0].getMethodName())
		                + ": Account" 
		                + ": Identification: " + account.getIdentification()
		                + ": AccountNumber: " + account.getAccountNumber()
		                + ": AccountType: " + account.getAccountType()
		                + ": Balance: " + Misc.roundToMoney(account.getBalance())
		                 );
			}
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#getAccount(int, int)}.
	 */
	@Test
	final void testGetAccount() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#getIdentification()}.
	 */
	@Test
	final void testGetIdentification() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
			assertEquals(account.getIdentification(), identification);
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#getAccountNumber()}.
	 */
	@Test
	final void testGetAccountNumber() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
			assertEquals(account.getAccountNumber(), accountNumber);
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#getAccountType()}.
	 */
	@Test
	final void testGetAccountType() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
			assertEquals(account.getAccountType(), AccountType.PrimarySavings);
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#getBalance()}.
	 */
	@Test
	final void testGetBalance() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
			assertTrue(account.getBalance().compareTo(BigDecimal.valueOf(0)) >= 0);
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#deductFunds(java.math.BigDecimal)}.
	 */
	@Test
	final void testDeductFunds() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			BigDecimal amount = new BigDecimal("10.00");
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
			BigDecimal oldBalance = account.getBalance();
			BigDecimal expectedBalance = oldBalance.subtract(amount);
			account.deductFunds(amount);
			BigDecimal newBalance = account.getBalance();
			
			assertEquals(newBalance, expectedBalance);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#depositFunds(java.math.BigDecimal)}.
	 */
	@Test
	final void testDepositFunds() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account account = null;
			int identification = 0;
			int accountNumber = 0;
			BigDecimal amount = new BigDecimal("10.00");
			
			// Get an account for a customer
			identification = 111;
			accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
			
			assertNotNull(account);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
			BigDecimal oldBalance = account.getBalance();
			BigDecimal expectedBalance = oldBalance.add(amount);
			account.depositFunds(amount);
			BigDecimal newBalance = account.getBalance();
			
			assertEquals(newBalance, expectedBalance);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + account.getIdentification()
	                + ": AccountNumber: " + account.getAccountNumber()
	                + ": AccountType: " + account.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(account.getBalance())
	                 );
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

	/**
	 * Test method for {@link model.Account#transferFunds(model.Account, java.math.BigDecimal)}.
	 */
	@Test
	final void testTransferFunds() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Account sourceAccount = null;
			Account targetAccount = null;
			int identification = 0;
			int accountNumber = 0;
			BigDecimal amount = new BigDecimal("10.00");
			
			// Get a source account for a customer
			identification = 111;
			accountNumber = 111101;
			sourceAccount = Account.getAccount(identification, accountNumber);
			
			assertNotNull(sourceAccount);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + sourceAccount.getIdentification()
	                + ": AccountNumber: " + sourceAccount.getAccountNumber()
	                + ": AccountType: " + sourceAccount.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(sourceAccount.getBalance())
	                 );
			
			// Get a target account for a customer
			identification = 111;
			accountNumber = 111102;
			targetAccount = Account.getAccount(identification, accountNumber);
			
			assertNotNull(targetAccount);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + targetAccount.getIdentification()
	                + ": AccountNumber: " + targetAccount.getAccountNumber()
	                + ": AccountType: " + targetAccount.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(targetAccount.getBalance())
	                 );
			
			BigDecimal oldBalanceSource = sourceAccount.getBalance();
			BigDecimal expectedBalanceSource = oldBalanceSource.subtract(amount);
			BigDecimal oldBalanceTarget = targetAccount.getBalance();
			BigDecimal expectedBalanceTarget = oldBalanceTarget.add(amount);
			sourceAccount.transferFunds(targetAccount, amount);
			BigDecimal newBalanceSource = sourceAccount.getBalance();
			BigDecimal newBalanceTarget = targetAccount.getBalance();
			
			assertEquals(newBalanceSource, expectedBalanceSource);
			
			assertEquals(newBalanceTarget, expectedBalanceTarget);
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + sourceAccount.getIdentification()
	                + ": AccountNumber: " + sourceAccount.getAccountNumber()
	                + ": AccountType: " + sourceAccount.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(sourceAccount.getBalance())
	                 );
			
			// Display account
			System.out.println(MachineTest.class.getTypeName() + "."
	                + (new Throwable().getStackTrace()[0].getMethodName())
	                + ": Account" 
	                + ": Identification: " + targetAccount.getIdentification()
	                + ": AccountNumber: " + targetAccount.getAccountNumber()
	                + ": AccountType: " + targetAccount.getAccountType()
	                + ": Balance: " + Misc.roundToMoney(targetAccount.getBalance())
	                 );
			
		}
		catch (Exception ex)
		{
	    	String message = (MachineTest.class.getTypeName() + "."
			                + (new Throwable().getStackTrace()[0].getMethodName())
			                + ": Exception: \n" 
			                + ex.getMessage()
			                 );
			fail(message);
		}

	}

}
