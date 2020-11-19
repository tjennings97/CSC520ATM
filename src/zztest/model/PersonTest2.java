/**
 * JUnit 5 tests for class Person.java
 */
package zztest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.Main;
import model.Customer;
import model.Person;
import model.PersonType;
import model.Technician;
import ui.JFrameATM;

/**
 * @author TakeBo Bank, LLC
 *
 */
class PersonTest2 {
	
	private static final Map<Integer, Integer> IDENTIFICATION_PIN = new HashMap<>() 
	{
		private static final long serialVersionUID = -4860253226252275103L;
		{
			put(000, 0000);
			put(111, 1111);
			put(222, 2222);
			put(333, 3333);
			put(444, 4444);
			put(555, 5555);
			put(666, 6666);
			put(777, 7777);
			put(888, 8888);
			put(999, 9999);
		}
	};

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
	 * Test method for {@link model.Person#Person(int, int, model.PersonType)}.
	 */
	@Test
	final void testPerson() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			// Person is an abstract class
			// However, we can create Technician and Customer objects which are sub classes
			
			Person person = null;
			int identification = 0;
		    int pin = 0;
		    
			// Technician
		    identification = 12345;
		    pin = 4444;
			person = new Technician(identification, pin);
			
			assertEquals(person.getPersonType(), PersonType.Technician);
			assertEquals(person.getIdentification(), identification);
			assertEquals(person.getPin(), pin);
		    
			// Customer
		    identification = 67890;
		    pin = 6666;
			person = new Customer(identification, pin);
			
			assertEquals(person.getPersonType(), PersonType.Customer);
			assertEquals(person.getIdentification(), identification);
			assertEquals(person.getPin(), pin);
			
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
	 * Test method for {@link model.Person#getPerson(int)}.
	 */
	@Test
	final void testGetPerson() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			for (int identification : IDENTIFICATION_PIN.keySet())
			{
				System.out.println(MachineTest.class.getTypeName() + "."
		                + (new Throwable().getStackTrace()[0].getMethodName())
		                + ": Testing person: " 
		                + identification
		                 );
				
				Person person = Person.getPerson(identification);
				
				assertNotNull(person, "person object should not be null");
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
	 * Test method for {@link model.Person#getIdentification()}.
	 */
	@Test
	final void testGetIdentification() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			for (int identification : IDENTIFICATION_PIN.keySet())
			{
				System.out.println(MachineTest.class.getTypeName() + "."
		                + (new Throwable().getStackTrace()[0].getMethodName())
		                + ": Testing person: " 
		                + identification
		                 );
				
				Person person = Person.getPerson(identification);
				
				int personIdentification = person.getIdentification();
				
				assertEquals(personIdentification, identification);
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
	 * Test method for {@link model.Person#getPin()}.
	 */
	@Test
	final void testGetPin() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			for (int identification : IDENTIFICATION_PIN.keySet())
			{
				System.out.println(MachineTest.class.getTypeName() + "."
		                + (new Throwable().getStackTrace()[0].getMethodName())
		                + ": Testing person: " 
		                + identification
		                 );
				
				Person person = Person.getPerson(identification);
				
				int personPin = person.getPin();
				
				assertEquals(personPin, IDENTIFICATION_PIN.get(identification));
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
	 * Test method for {@link model.Person#isPinValid(int)}.
	 */
	@Test
	final void testIsPinValid() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			for (int identification : IDENTIFICATION_PIN.keySet())
			{
				System.out.println(MachineTest.class.getTypeName() + "."
		                + (new Throwable().getStackTrace()[0].getMethodName())
		                + ": Testing person: " 
		                + identification
		                 );
				
				Person person = Person.getPerson(identification);
				
				boolean valid = person.isPinValid(IDENTIFICATION_PIN.get(identification));
				
				assertTrue(valid);
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
	 * Test method for {@link model.Person#getPersonType()}.
	 */
	@Test
	final void testGetPersonType() {
		//fail("Not yet implemented"); // TODO
		
		try
		{
			for (int identification : IDENTIFICATION_PIN.keySet())
			{
				System.out.println(MachineTest.class.getTypeName() + "."
		                + (new Throwable().getStackTrace()[0].getMethodName())
		                + ": Testing person: " 
		                + identification
		                 );
				
				Person person = Person.getPerson(identification);
				
				PersonType personType = person.getPersonType();
				
				if (identification == 000)
				{
					assertEquals(personType, PersonType.Technician);
				}
				else
				{
					assertEquals(personType, PersonType.Customer);
				}
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

}
