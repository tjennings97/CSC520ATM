package model;

import java.util.HashMap;
import java.util.Map;

public enum PersonType {
	
	Technician("Technician"),
	Customer("Customer");
	 
    private String text;
	 
    // Use the enum name if specific text is not supplied
	private PersonType() {
        this.text = this.name();
    }
		 
	private PersonType(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
     
    //****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, PersonType> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(PersonType env : PersonType.values())
        {
            lookup.put(env.getText(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static PersonType get(String text) 
    {
        return lookup.get(text);
    }

}
