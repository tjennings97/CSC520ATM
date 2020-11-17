/**
 * JUnit 5 tests for class Machine.java
 */
package zztest.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.Main;
import model.Account;
import model.AccountType;
import ui.JFrameATM;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author TaKeBo Bank, LLC
 *
 */

public class AccountTest {
    private static Account account = null;

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");

        try
        {
	        // Create the ATM frame (and therefore the data access stuff)
			Main.atmFrame = new JFrameATM();
			
			// Get the single instance of account
            int identification = 111;
            int accountNumber = 111101;
			account = Account.getAccount(identification, accountNumber);
        }
		catch (Exception ex)
		{
	    	String message = (AccountTest.class.getTypeName() + "."
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
			fail("Exception: " + ex.getMessage());
		} 
    }
    
    /**
     * Test method for {@link model.Account#Account(int, int, model.AccountType, BigDecimal)}
     */
    final void testAccount(){
        try{
            assertNotNull(account, "account object should not be null");
        }
        catch (Exception ex)
        {
            fail("Exception: " + ex.getMessage());
        }
    }

     /**
     * test method for {@link model.Account#getAccounts(int)}
     */
    @Test
    final void testGetAccounts() {
        try{
            int id = 111;
            ArrayList<Account> accounts = account.getAccounts(id);
            assertNotNull(accounts, "account object should not be null");
        }
        catch (Exception ex)
        {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#getAccount(int, int)}
     */
    @Test
    final void testGetAccount() {
        try{
            int id = 111; //identification
            int an = 111101; //accountNumber
            Account testGA = account.getAccount(id, an);
            assertNotNull(testGA, "account object should not be null");

        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#getIdentification()}
     */
    @Test
    final void testGetIdentification() {
        try
        {
            int testGI = account.getIdentification();
            assertNotNull(testGI, "identification object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#getAccountNumber()}
     */
    @Test
    final void testGetAccountNumber() {
        try
        {
            int testGAC = account.getAccountNumber();
            assertNotNull(testGAC, "account number object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#getAccountType()}
     */
    @Test
    final void testGetAccountType() {
        try
        {
            AccountType testGAT = account.getAccountType();
            assertNotNull(testGAT, "account type object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#getBalance()}
     */
    @Test
    final void testGetBalance() {
        try
        {
            BigDecimal testGB = account.getBalance();
            assertNotEquals(testGB, "get balance object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#deductFunds(BigDecimal)}
     */
    @Test
    final void testDeductFunds() {
        try
        {
            BigDecimal a = new BigDecimal("10.00"); // amount
            boolean testDF = account.deductFunds(a);
            assertNotNull(testDF, "deduct funds object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#depositFunds(BigDecimal)}
     */
    @Test
    final void testDepositFunds() {
        try
        {
            BigDecimal a = new BigDecimal("10.00"); // amount
            boolean testDF = account.depositFunds(a);
            assertNotNull(testDF, "deposit funds object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

    /**
     * Test method for {@link model.Account#trasnferFunds(Account, BigDecimal)}
     */
    @Test
    final void testTransferFunds() {
        try
        {
            Account acc = Account.getAccount(111, 111101);
            BigDecimal a = new BigDecimal("10.00"); // amount
            boolean testTF = account.transferFunds(acc, a);
            assertNotNull(testTF, "transfer funds object should not be null");
        }
        catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
    }

}