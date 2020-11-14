package model;

import java.util.HashMap;
import java.util.Map;

public enum MachineCashStatus {

	Ok("Ok"),
	Low("Low"),
	Empty("Empty");
	 
    private String text;
	 
    // Use the enum name if specific text is not supplied
	private MachineCashStatus() {
        this.text = this.name();
    }
		 
	private MachineCashStatus(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
     
    //****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, MachineCashStatus> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(MachineCashStatus env : MachineCashStatus.values())
        {
            lookup.put(env.getText(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static MachineCashStatus get(String text) 
    {
        return lookup.get(text);
    }
	
}
