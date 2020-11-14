/**
 * JUnit 5 tests for class Machine.java
 */
package zztest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import event.CashStatusEvent;
import event.DepositStatusEvent;
import event.PaperStatusEvent;
import main.Main;
import model.Machine;
import model.MachineCashStatus;
import model.MachineDepositStatus;
import model.MachinePaperStatus;
import ui.JFrameATM;

/**
 * @author TaKeBo Bank, LLC
 *
 */
class MachineTest {
	
	private static Machine machine = null;
    
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
			
			// Get the single instance of the Machine object
			machine = Machine.getInstance();
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
	 * Test method for {@link model.Machine#getInstance()}.
	 */
	@Test
	final void testGetInstance() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			// The machine object was obtained in the (@BeforeAll)setup method
			assertNotNull(machine, "machine object should not be null");
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
	 * Test method for {@link model.Machine#getCurrentMachineStatus()}.
	 */
	@Test
	final void testGetCurrentMachineStatus() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			// This method returns void so only test is 
			// that it didn't cause an exception
			machine.getCurrentMachineStatus();
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
		
	}

	/**
	 * Test method for {@link model.Machine#adjustCash(java.math.BigDecimal)}.
	 */
	@Test
	final void testAdjustCash() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			BigDecimal cashAmountStart = machine.getCashAmount();
			BigDecimal cashAmountDelta = new BigDecimal("5.00");
			machine.adjustCash(cashAmountDelta);
			BigDecimal cashAmountFinish = machine.getCashAmount();
			
			BigDecimal cashAmountExpected = cashAmountStart.add(cashAmountDelta);
			
			assertEquals(cashAmountFinish, cashAmountExpected);
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
		
	}

	/**
	 * Test method for {@link model.Machine#adjustPaper(int)}.
	 */
	@Test
	final void testAdjustPaper() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			int paperAmountStart = machine.getPaperLength();
			int paperAmountDelta = 5;
			machine.adjustPaper(paperAmountDelta);
			int paperAmountFinish= machine.getPaperLength();
			
			int paperAmountExpected = paperAmountStart + paperAmountDelta;
			
			assertEquals(paperAmountFinish, paperAmountExpected);
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
		
	}

	/**
	 * Test method for {@link model.Machine#adjustDeposit(int)}.
	 */
	@Test
	final void testAdjustDeposit() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			int depositAmountStart = machine.getDepositFillPercent();
			int depositAmountDelta = 5;
			machine.adjustDeposit(depositAmountDelta);
			int depositAmountFinish= machine.getDepositFillPercent();
			
			int depositAmountExpected = depositAmountStart + depositAmountDelta;
			
			assertEquals(depositAmountFinish, depositAmountExpected);
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
		
	}

	/**
	 * Test method for {@link model.Machine#getCashStatusEvent()}.
	 */
	@Test
	final void testGetCashStatusEvent() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			CashStatusEvent cashStatusEvent = machine.getCashStatusEvent();
			
			assertNotNull(cashStatusEvent, "cashStatusEvent object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getPaperStatusEvent()}.
	 */
	@Test
	final void testGetPaperStatusEvent() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			PaperStatusEvent paperStatusEvent = machine.getPaperStatusEvent();
			
			assertNotNull(paperStatusEvent, "paperStatusEvent object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getDepositStatusEvent()}.
	 */
	@Test
	final void testGetDepositStatusEvent() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			DepositStatusEvent depositStatusEvent = machine.getDepositStatusEvent();
			
			assertNotNull(depositStatusEvent, "depositStatusEvent object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getCashStatus()}.
	 */
	@Test
	final void testGetCashStatus() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			String cashStatus = machine.getCashStatus();
			MachineCashStatus machineCashStatus = MachineCashStatus.get(cashStatus);
			
			assertNotNull(machineCashStatus, "machineCashStatus object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getCashStatusColor()}.
	 */
	@Test
	final void testGetCashStatusColor() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Color cashStatusColor = machine.getCashStatusColor();
			
			assertNotNull(cashStatusColor, "cashStatusColor object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getCashAmount()}.
	 */
	@Test
	final void testGetCashAmount() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			BigDecimal cashAmount = machine.getCashAmount();
			
			assertNotNull(cashAmount, "cashAmount object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getPaperStatus()}.
	 */
	@Test
	final void testGetPaperStatus() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			String paperStatus = machine.getPaperStatus();
			MachinePaperStatus machinePaperStatus = MachinePaperStatus.get(paperStatus);
			
			assertNotNull(machinePaperStatus, "machinePaperStatus object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getPaperStatusColor()}.
	 */
	@Test
	final void testGetPaperStatusColor() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Color paperStatusColor = machine.getPaperStatusColor();
			
			assertNotNull(paperStatusColor, "paperStatusColor object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getPaperLength()}.
	 */
	@Test
	final void testGetPaperLength() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			int paperLength = machine.getPaperLength();
			
			assertTrue((paperLength >= 0), "paperLength >= 0");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getDepositStatus()}.
	 */
	@Test
	final void testGetDepositStatus() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			String depositStatus = machine.getDepositStatus();
			MachineDepositStatus machineDepositStatus = MachineDepositStatus.get(depositStatus);
			
			assertNotNull(machineDepositStatus, "machineDepositStatus object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getDepositStatusColor()}.
	 */
	@Test
	final void testGetDepositStatusColor() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			Color depositStatusColor = machine.getDepositStatusColor();
			
			assertNotNull(depositStatusColor, "depositStatusColor object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

	/**
	 * Test method for {@link model.Machine#getDepositFillPercent()}.
	 */
	@Test
	final void testGetDepositFillPercent() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			int depositFillPercent = machine.getDepositFillPercent();
			
			assertTrue((depositFillPercent <= 100), "depositFillPercent <= 100");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}

	}

}
