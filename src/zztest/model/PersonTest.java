/**
 * JUnit 5 tests for class Machine.java
 */
package zztest.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.Main;
import model.Person;
import model.PersonType;
import ui.JFrameATM;

/**
 * @author TaKeBo Bank, LLC
 *
 */

class PersonTest {
	private static Person person = null;
	
	@BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
        
        try
        {
	        // Create the ATM frame (and therefore the data access stuff)
			Main.atmFrame = new JFrameATM();
			
			// Get the single instance of Person
			int identification = 111;
			person = Person.getPerson(identification);
        }
		catch (Exception ex)
		{
	    	String message = (PersonTest.class.getTypeName() + "."
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
	 * Test method for {@link model.Person#Person(int, int, model.PersonType)}.
	 */
	@Test
	final void testPerson() {
		// fail("Not yet implemented"); // TODO
		try
		{
			assertNotNull(person, "person object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Person#getPerson(int)}.
	 */
	@Test
	final void testGetPerson() {
		// fail("Not yet implemented"); // TODO
		try
		{
			// The person object was obtained in the (@BeforeAll)setup method
			int id = 111;
			Person testGP = person.getPerson(id);
			assertNotNull(testGP, "person object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Person#getIdentification()}.
	 */
	@Test
	final void testGetIdentification() {
		// fail("Not yet implemented"); // TODO
		try
		{
			// The person object was obtained in the (@BeforeAll)setup method
			int testGI = person.getIdentification();
			assertNotNull(testGI, "identification object should not be null");
					
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
	}
	

	/**
	 * Test method for {@link model.Person#getPin()}.
	 */
	@Test
	final void testGetPin() {
		// fail("Not yet implemented"); // TODO
		try
		{
			// The person object was obtained in the (@BeforeAll)setup method
			int testGP = person.getPin();
			assertNotNull(testGP, "pin object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Person#isPinValid(int)}.
	 */
	@Test
	final void testIsPinValid() {
		// fail("Not yet implemented"); // TODO
		try
		{
			// The person object was obtained in the (@BeforeAll)setup method
			int pin = 1111;
			boolean testIPV = person.isPinValid(pin);
			assertNotNull(testIPV, "validated pin object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Person#getPersonType()}.
	 */
	@Test
	final void testGetPersonType() {
		// fail("Not yet implemented"); // TODO
		try
		{
			// The person object was obtained in the (@BeforeAll)setup method
			PersonType testGPT = person.getPersonType();
			assertNotNull(testGPT, "person type object should not be null");
		}
		catch (Exception ex)
		{
			fail("Exception: " + ex.getMessage());
		}
	}
}
