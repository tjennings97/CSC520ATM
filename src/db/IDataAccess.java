package db;

import java.sql.*;

public interface IDataAccess {
	
	public Connection connect();
	public void disconnect();
	public boolean isConnected();
	public ResultSet executeQuery(PreparedStatement preparedStatement, Object... fields);
	public ResultSet executeQueryString(String sqlStatement, Object... params);
	public int executeUpdate(PreparedStatement preparedStatement, Object... fields);
	public int executeUpdateString(String sqlStatement, Object... params);
	public ResultSet executeCall(CallableStatement callableStatement, Object... inParams);
	public ResultSet executeCallString(String statement, Object... inParams);

}
