package model;

public class Customer extends Person{
	
	public Customer(int identification
			      , int pin)
	{
		super(identification
		    , pin
		    , PersonType.Customer);
		
		// All done
		return;
	}

}
