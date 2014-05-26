package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.training.issueTracker.service.DAO.exceptions.DAOException;


public class ConnectionPool {
	private  final String DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private  static String CONECT_URL_BEGIN = "jdbc:derby:";
	private  static String CONECT_URL_END = "resources\\issueDB;create=false;user=admin;password=admin";
	private  static String CONECT_URL ;
	private  final String USER = "admin";
	private  final String PASSW = "admin";	
	private static final String CONNECT_ERR  = " Problem with  connection to data base ";
	private static ConnectionPool pool;
	private int maxConn=10;
	private Connection[] ConnArray = new Connection[maxConn];
	private int withdrewed=0;
	private  Connection baseConnection;
	
	
	private  Connection getBaseConnection () throws SQLException, ClassNotFoundException{
		
		Class.forName(DRIVER_CLASS);
			
		baseConnection = DriverManager.getConnection(CONECT_URL  , USER , PASSW);
		
		return baseConnection;
	}
	
		
	private ConnectionPool() throws ClassNotFoundException, SQLException {
		for (int i = 0; i < maxConn; i++){
			
			ConnArray[i] = getBaseConnection();
			
		}
	}

	static public ConnectionPool getConnPool( ) throws DAOException {
		
		ConnectionPool conPool=null;
			
		try {
			if (pool == null){
				conPool=new ConnectionPool();
			}else {
				conPool = pool;
			}
						
			pool = conPool;
		} catch (ClassNotFoundException e) {
			
			throw new DAOException(CONNECT_ERR+e.getMessage());
		} catch (SQLException e) {
			
			throw new DAOException(CONNECT_ERR+e.getMessage());
		}
		
		return conPool;
	}
	
	/**
	 * @param cOONECT_STATIC_URL the cOONECT_STATIC_URL to set
	 */
	public static void setURL(String URL) {
		CONECT_URL = URL;
	}
	public static void addURL(String URL) {
		CONECT_URL = CONECT_URL_BEGIN + URL + CONECT_URL_END;
	}


	synchronized public Connection getConnection() throws DAOException{
		Connection conn=null;

		while (withdrewed==10){
			try {

				wait();
			} catch (InterruptedException e) { 
				
				throw new DAOException(CONNECT_ERR);
			}
		}

		withdrewed++;
		
		for (int i = 0; i < maxConn; i++){
			if (ConnArray[i] != null) {
				
				conn = ConnArray[i];
				ConnArray[i] = null;
				break;
			}
		}

		notify(); 

		return conn;
	}

	synchronized public void releaseConnection(Connection conn) throws DAOException{
		withdrewed--;

		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DAOException(CONNECT_ERR);
		}
		for (int i = 0; i<maxConn; i++){
			if (ConnArray[i] == null){
				ConnArray[i] = conn;
				break;
			}
		}
		notify();

	}



	synchronized public void closeAll() throws DAOException{
		for (int i = 0; i<maxConn; i++){
			if (ConnArray[i] != null){
				try{
					ConnArray[i].rollback();
					ConnArray[i].close();
				} catch (SQLException e) {
					throw new DAOException(CONNECT_ERR);
				}
			}
				
		}
		ConnArray = null;
	}
}