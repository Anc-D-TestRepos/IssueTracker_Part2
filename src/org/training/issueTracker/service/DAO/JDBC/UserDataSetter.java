package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class UserDataSetter {
	
	private  final String ID_EMAIL = "idEmail";
	private  final String CLS_ERR = " Can't close - ";
	private  final String CONNECT_ERR  = " Can't write issue problem with  connection  to database  ";
	private Logger logger= Logger.getLogger(UserDataSetter.class);
	
	
	
	public UserDataSetter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void editPassword(String user, String pass) throws DAOException, ClassNotFoundException {
		
		Object lock = new Object();
		ConnectionDB dBCon = null;
		Connection con =null;
		PreparedStatement ps=null;
			
			try {
				
			
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				
				synchronized (lock) {
					

					 ps = con.prepareStatement( "UPDATE employee SET pass =? WHERE email =?"/*SQLConstants.EDIT_ISSUE*/);

					
					 ps.setString(1,pass);
					 ps.setString(2,user);
					 
					 ps.executeUpdate();

				}
				
		
			}  catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			}
			finally{
				try {
					if (ps!=null){
						ps.close();
					}	
					if (con!=null) {
						con.close();
					} 
				}catch (SQLException e) {
					logger.error(CLS_ERR + e.getMessage());
				}
			}
					
	
	}
	public void editUserDataByUser(Employee user, String email) throws DAOException, ClassNotFoundException {
		
		Object lock = new Object();
		ConnectionDB dBCon = null;
		Connection con =null;
		PreparedStatement ps=null;
			
			try {
				
			
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				
				synchronized (lock) {
					
					 
					 ps = con.prepareStatement( "UPDATE employee SET email = ? ,firstName =?, lastName =?  WHERE email =?"/*SQLConstants.EDIT_ISSUE*/);
	
					
					 ps.setString(1,user.getEmail());
					 ps.setString(2,user.getFirstName());
					 ps.setString(3,user.getLastName());
					 ps.setString(4,email);
					 ps.executeUpdate();
	
				}
				
		
			}  catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			}
			finally{
				try {
					if (ps!=null){
						ps.close();
					}	
					if (con!=null) {
						con.close();
					} 
				}catch (SQLException e) {
					logger.error(CLS_ERR + e.getMessage());
				}
			}
					
	
	}
	public void editUserDataByAdmin(Employee user) throws DAOException, ClassNotFoundException {
		
		Object lock = new Object();
		ConnectionDB dBCon = null;
		Connection con =null;
		PreparedStatement ps=null;
			
			try {
				System.out.println(user.toString());
			
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				
				synchronized (lock) {
					
					 
					 ps = con.prepareStatement("UPDATE employee SET firstName =?, lastName =?, role =?   WHERE email =?"/*SQLConstants.EDIT_ISSUE*/);
	
					 ps.setString(1,user.getFirstName());
					 ps.setString(2,user.getLastName());
					 ps.setString(3,user.getRole());
					 ps.setString(4,user.getEmail());
					 ps.executeUpdate();
	
				}
				
		
			}  catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			}
			finally{
				try {
					if (ps!=null){
						ps.close();
					}	
					if (con!=null) {
						con.close();
					} 
				}catch (SQLException e) {
					logger.error(CLS_ERR + e.getMessage());
				}
			}
					
	
	}
}



