package model;

import java.util.HashMap;
import java.util.Map;

public enum MachineDepositStatus {

	Ok("Ok"),
	HalfFull(">Half Full"),
	Full("Full");
	 
    private String text;
	 
    // Use the enum name if specific text is not supplied
	private MachineDepositStatus() {
        this.text = this.name();
    }
		 
	private MachineDepositStatus(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
     
    //****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, MachineDepositStatus> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(MachineDepositStatus env : MachineDepositStatus.values())
        {
            lookup.put(env.getText(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static MachineDepositStatus get(String text) 
    {
        return lookup.get(text);
    }
	
}
