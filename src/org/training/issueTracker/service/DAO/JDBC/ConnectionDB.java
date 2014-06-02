package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import javax.sql.DataSource;



public class ConnectionDB {

	
	private static final String CONNECT_ERR  = " Problem with  connection to data base ";
	private static final String NOTE  = "\n please refresh page and try again";
	

	private  Connection connection;
	

	private DataSource ds;
	private Context ctx ;

		
	public ConnectionDB() throws ClassNotFoundException, SQLException {
	
	}
	
	public  Connection getConnection () throws SQLException{

	try {
			

			ctx = new InitialContext();
			
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/derbyDB");
			
		 
					connection = ds.getConnection();
					
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	

	
}