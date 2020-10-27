package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public abstract class Person {

	private int identification = 0;
	private int pin = 0;
	private PersonType personType = null;
	
	public Person(int identification
			    , int pin
			    , PersonType personType) 
	{		
		// Save attributes
		this.identification = identification;
		this.pin = pin;
		this.personType = personType;
		
		// All done
		return;
	}
	
	public static Person getPerson(int identification)
	{
		Person person = null;
		
		// Get the person from the database
		try
		{
			// Check that we've lost the connection
			if (!Main.atmFrame.dataAccess.isConnected())
			{
				// Attempt to reconnect
				Main.atmFrame.dataAccess.connect();
			}
			
			ResultSet resultSet = Main.atmFrame.dataAccess.executeCallString("{call SelectPerson(?)}", identification);
			
			if(resultSet.next())
			{
				PersonType personType = PersonType.valueOf(resultSet.getString("PersonType"));
				switch (personType)
				{
					case Customer:
					{
						person = new Customer(identification
		                                    , resultSet.getInt("Pin"));			
						break;
					}
					case Technician:
					{
						person = new Technician(identification
	                                          , resultSet.getInt("Pin"));			
						break;
					}
					default:
					{
				    	System.err.println(Person.class.getTypeName() + "."
						         + (new Throwable().getStackTrace()[0].getMethodName())
						         + ": ERROR: PersonType (" + personType + ") not supported"
						          );
				    	person = null;
						break;
					}
				}
			}
		}
		catch (SQLException sqlEx)
		{
			// Note: the use of Person.class as this is a static method
	    	System.err.println(Person.class.getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	person = null;
		}
		
		// All Done
		return person;
	}
	
	public int getIdentification() {
		return identification;
	}
	
	public int getPin() {
		return pin;
	}
	
	public boolean isPinValid(int pin)
	{
		return (this.pin == pin);
	}
	
	public PersonType getPersonType() {
		return personType;
	}
}
