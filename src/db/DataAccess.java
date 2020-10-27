package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import db.SQLServer.SQLServer;

public class DataAccess implements IDataAccess {
	
	private Properties properties = null;
	private DBEngine dbEngine = null;
	
	private IDataAccess db = null;

	public DataAccess(DBEngine dbEngine, Properties properties) //throws Exception
	{
		// Save the properties
		this.properties = properties;
		
		// Validate that the requested database engine is implemented
		// and then assign an instance of the concrete class
		switch (dbEngine)
		{
			case AzureSQLServer:
			{
				// SQL Server will work for Azure SQL Server
				db = new SQLServer(properties);
				break;
			}
			
			case SQLServer:
			{
				db = new SQLServer(properties);
				break;
			}
			
		    default:
		    {
		    	String errorMessage = this.getClass().getTypeName() + "."
					                + (new Throwable().getStackTrace()[0].getMethodName())
					                + ": DBEngine (" + dbEngine+ ") not currently implemented";
		    	System.err.println(errorMessage);
		    	//throw new Exception(errorMessage);
			    break;
		    }
		}
		
		// Save the database engine
		this.dbEngine = dbEngine;
		
		// All done
		return;
	}
	
	public DBEngine getDBEngine()
	{
		// All done
		return dbEngine;
	}
	
	public Properties getProperties()
	{
		// All done
		return properties;
	}

	@Override
	public Connection connect()
	{
		// Pass through
		return db.connect();
	}

	@Override
	public void disconnect()
	{
		// Pass through
		db.disconnect();
		return;
	}

	@Override
	public boolean isConnected()
	{
		// Pass through
		return db.isConnected();
	}

	@Override
	public ResultSet executeQuery(PreparedStatement preparedStatement, Object... params)
	{
		// Pass through
		return db.executeQuery(preparedStatement, params);
	}

	@Override
	public ResultSet executeQueryString(String sqlStatement, Object... params)
	{
		// Pass through
		return db.executeQueryString(sqlStatement, params);
	}

	@Override
	public int executeUpdate(PreparedStatement preparedStatement, Object... params)
	{
		// Pass through
		return db.executeUpdate(preparedStatement, params);
	}

	@Override
	public int executeUpdateString(String sqlStatement, Object... params)
	{
		// Pass through
		return db.executeUpdateString(sqlStatement, params);
	};

	@Override
	public ResultSet executeCall(CallableStatement callableStatement, Object... inParams)
	{
		// Pass through
		return db.executeCall(callableStatement, inParams);
	}

	@Override
	public ResultSet executeCallString(String statement, Object... inParams)
	{
		// Pass through
		return db.executeCallString(statement, inParams);
	}
}
