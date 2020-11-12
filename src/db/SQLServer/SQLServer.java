package db.SQLServer;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import db.IDataAccess;
import main.Main;

public class SQLServer implements IDataAccess {
	
	private Properties properties = null;
	private PreparedStatement CONNECTION_CHECK_SQL_STATEMENT = null;
	private Connection connection = null;
	
	public SQLServer(Properties properties)
	{	
		// Save the properties
		this.properties = properties;
		
		// All done
		return;
	}
	
	@Override
	public Connection connect()
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException sqlEx)
			{
		    	System.err.println(this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": SQLException: \n" 
				         + sqlEx.getMessage()
				          );
			}
		}
		
		connection = null;
		
		try
		{
			if (Main.DEBUG)
			{
				System.out.println("*************************************************************************");
		    	System.out.println(this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": Attempting to connect to the database. \n" 
				         +         this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": If the Azure database hasn't been used in 2 hours it is shutdown. \n" 
				         +         this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": In that case the connection could take upto 2 minutes to return. \n" 
				         +         this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": Be patient... " 
				          );
				System.out.println("*************************************************************************");
			}
			// Get a connection to the database
			// Note: The connection string does not contain the User and Password
			//       these are gotten from the "properties" collection
			String connectionString = properties.getProperty("url");
			connection = DriverManager.getConnection(connectionString, properties);
			
			// Reset the timeout to 30 seconds
			// Timeout is set to 120 seconds in application.properties
			// file because we're using an Azure database that is turned
			//  off after2 hours of inactivity
			connection.setNetworkTimeout(null, 30*1000);
			
			// Successful connection
			// Prepare the simple database connection check SQL statement 
			// This is used by IsConnected() to test the connection
			CONNECTION_CHECK_SQL_STATEMENT = connection.prepareStatement("SELECT 1;");

		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	connection = null;
		}
		
		// All done
		return connection;
	}

	@Override
	public void disconnect()
    {
		// Disconnect
		try
		{
			connection.close();
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
		}
		
		// Set connection to null
    	connection = null;

		// All done
		return;
	}

	@Override
	public boolean isConnected()
	{
		boolean connected = false;
		
		// Check connection
		try 
		{
			// For SQL Server run a very simple SELECT query to test the connection
			ResultSet resultSet = executeQuery(CONNECTION_CHECK_SQL_STATEMENT);
			
			if(resultSet.next())
			{
				connected = true;
			}
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	connected = false;
	    	disconnect();
		}
		
		return connected;
	}

	@Override
	public ResultSet executeQuery(PreparedStatement preparedStatement, Object... params)
	{
		ResultSet resultSet = null;
		
		// Add parameters (if any) and execute query
		try 
		{
			// Add parameters to statement
			addParams(preparedStatement, params);
			
			// Execute query statement
			resultSet = preparedStatement.executeQuery();
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	resultSet = null;
		}
		
		return resultSet;
	}

	@Override
	public ResultSet executeQueryString(String sqlStatement, Object... params)
	{
		ResultSet resultSet = null;
		
		// Prepare the statement, Add parameters (if any) and execute query
		try 
		{
			// Prepare the statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);;
	
			// Add parameters to statement
			addParams(preparedStatement, params);
			
			// Execute query statement
			resultSet = preparedStatement.executeQuery();
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	resultSet = null;
		}
		
		return resultSet;
	}

	@Override
	public int executeUpdate(PreparedStatement preparedStatement, Object... params) 
	{
		int affectedRows = 0;
		
		// Add parameters (if any) and execute query
		try 
		{		
			// Add parameters to statement
			addParams(preparedStatement, params);
			
			// Execute update statement
			affectedRows = preparedStatement.executeUpdate();
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	affectedRows = 0;
		}
		
		return affectedRows;
	}

	@Override
	public int executeUpdateString(String sqlStatement, Object... params)
	{
		int affectedRows = 0;
		
		// Prepare the statement, Add parameters (if any) and execute query
		try 
		{		
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);;
	
			// Add parameters to statement
			addParams(preparedStatement, params);
			
			// Execute query statement
			affectedRows = preparedStatement.executeUpdate();
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	affectedRows = 0;
		}
		
		return affectedRows;
	}

	@Override
	public ResultSet executeCall(CallableStatement callableStatement, Object... inParams)
	{
		ResultSet resultSet = null;

		// Cast the JDBC callable statement to SQLServer
		SQLServerCallableStatement sqlServerCallableStatement = (SQLServerCallableStatement) callableStatement;

		// Add parameters (if any) and execute query
		try 
		{
			// Add parameters to statement
			addInParams(sqlServerCallableStatement, inParams);
			
			// Execute call statement
			boolean success = sqlServerCallableStatement.execute();
			
			// Retrieve result set if successful execution
			if (success)
			{
				resultSet = sqlServerCallableStatement.getResultSet();
			}
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	resultSet = null;
		}
		
		return resultSet;
	}

	@Override
	public ResultSet executeCallString(String sqlStatement, Object... inParams)
	{
		ResultSet resultSet = null;
		
		// Prepare the statement, Add parameters (if any) and execute query
		try 
		{				
			// Create a callable statement
			// And cast the JDBC callable statement to SQLServer
			SQLServerCallableStatement sqlServerCallableStatement = (SQLServerCallableStatement) connection.prepareCall(sqlStatement);
	
			// Add parameters to statement
			addInParams(sqlServerCallableStatement, inParams);
			
			// Execute call statement
			boolean success = sqlServerCallableStatement.execute();
			
			if (success)
			{
				resultSet = sqlServerCallableStatement.getResultSet();
			}
		}
		catch (SQLException sqlEx)
		{
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": SQLException: \n" 
			         + sqlEx.getMessage()
			          );
	    	resultSet = null;
		}
		
		return resultSet;
	}

	private void addParams(PreparedStatement preparedStatement, Object... params) throws SQLException 
	{
		int paramPos = 0;
		
		// Add parameters to statement
		for(Object param : params)
		{
			// Increment the parameter position
			paramPos++;
			
			if(Main.DEBUG)
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": param.getClass().getTypeName(): " 
			         + param.getClass().getTypeName()
			          );
			switch (param.getClass().getTypeName())
			{
				case "java.lang.Integer":
				{
					if(Main.DEBUG)
						System.err.println(": param(" + paramPos + ") = " + ((int) param));
					preparedStatement.setInt(paramPos, (int) param);
					break;
				}
				case "java.lang.String":
				{
					if(Main.DEBUG)
						System.err.println(": param(" + paramPos + ") = " + ((String) param));
					preparedStatement.setString(paramPos, (String) param);
					break;
				}
				case "java.math.BigDecimal":
				{
					if(Main.DEBUG)
						System.err.println(": param(" + paramPos + ") = " + ((BigDecimal) param));
					preparedStatement.setBigDecimal(paramPos, (BigDecimal) param);
					break;
				}
				default:
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": ERROR: Type (" 
					         + param.getClass().getTypeName()
					         + ") not supported"
					          );
					break;
				}
			}
		}
		
		// All done
		return;
	}

	private void addInParams(SQLServerCallableStatement sqlServerCallableStatement, Object... params) throws SQLException 
	{
		int paramPos = 0;
		
		// Add parameters to statement
		for(Object param : params)
		{
			// Increment the parameter position
			paramPos++;
			
			if(Main.DEBUG)
		    	System.err.println(this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": param.getClass().getTypeName(): " 
				         + param.getClass().getTypeName()
				          );
			switch (param.getClass().getTypeName())
			{
				case "java.lang.Integer":
				{
					if(Main.DEBUG)
				    	System.err.println(this.getClass().getTypeName() + "."
						         + (new Throwable().getStackTrace()[0].getMethodName())
						         + ": param(" + paramPos + ") = " + ((int) param)
						          );
					sqlServerCallableStatement.setInt(paramPos, (int) param);
					break;
				}
				case "java.lang.String":
				{
					if(Main.DEBUG)
				    	System.err.println(this.getClass().getTypeName() + "."
						         + (new Throwable().getStackTrace()[0].getMethodName())
						         + ": param(" + paramPos + ") = " + ((String) param)
						          );
					sqlServerCallableStatement.setString(paramPos, (String) param);
					break;
				}
				case "java.math.BigDecimal":
				{
					if(Main.DEBUG)
				    	System.err.println(this.getClass().getTypeName() + "."
						         + (new Throwable().getStackTrace()[0].getMethodName())
						         + ": param(" + paramPos + ") = " + ((BigDecimal) param)
						          );
					sqlServerCallableStatement.setBigDecimal(paramPos, (BigDecimal) param);
					break;
				}
				default:
				{
			    	System.err.println(this.getClass().getTypeName() + "."
					         + (new Throwable().getStackTrace()[0].getMethodName())
					         + ": ERROR: Type (" 
					         + param.getClass().getTypeName()
					         + ") not supported"
					          );
					break;
				}
			}
		}
		
		// All done
		return;
	}

}
