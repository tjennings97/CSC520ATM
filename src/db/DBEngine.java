package db;

import java.util.HashMap;
import java.util.Map;

public enum DBEngine {

	AzureSQLServer("Azure SQL Server"),
	SQLServer("SQL Server"),
	AzureCosmosDB("Azure Cosmos DB"),
	Oracle("Oracle"),
	MySQL("MySQL"),
	Sybase("Sybase");
	 
    private String text;
	 
    // Use the enum name if specific text is not supplied
	private DBEngine() {
        this.text = this.name();
    }
		 
	private DBEngine(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
     
    //****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, DBEngine> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(DBEngine env : DBEngine.values())
        {
            lookup.put(env.getText(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static DBEngine get(String text) 
    {
        return lookup.get(text);
    }
	
}
