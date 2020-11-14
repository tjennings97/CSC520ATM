package model;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
	
	PrimarySavings("Primary Savings"),
	PrimaryChecking("Primary Checking"),
	SecondarySavings("Secondary Savings"),
	SecondaryChecking("Secondary Checking");
	 
    private String text;
	 
    // Use the enum name if specific text is not supplied
	private AccountType() {
        this.text = this.name();
    }
		 
	private AccountType(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
     
    //****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, AccountType> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(AccountType env : AccountType.values())
        {
            lookup.put(env.getText(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static AccountType get(String text) 
    {
        return lookup.get(text);
    }

}
