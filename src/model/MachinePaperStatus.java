package model;

import java.util.HashMap;
import java.util.Map;

public enum MachinePaperStatus {

	Ok("Ok"),
	Low("Low"),
	Empty("Empty");
	 
    private String text;
	 
    // Use the enum name if specific text is not supplied
	private MachinePaperStatus() {
        this.text = this.name();
    }
		 
	private MachinePaperStatus(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
     
    //****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, MachinePaperStatus> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(MachinePaperStatus env : MachinePaperStatus.values())
        {
            lookup.put(env.getText(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static MachinePaperStatus get(String text) 
    {
        return lookup.get(text);
    }
	
}
