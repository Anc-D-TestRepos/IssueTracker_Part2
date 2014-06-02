package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class EmployeeSearcherDB {

	private final String FIRST_NAME= "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	private final String ROLE = "role";
	private final String PASS = "pass";
	private static final String CLS_ERR = " Can't close - ";
	private static final String CONNECT_ERR  = " Problem with  connection  to database  ";


	private Logger logger= Logger.getLogger(EmployeeSearcherDB.class);


	public List<String> findAllEmployeesMail() throws DAOException, ClassNotFoundException {
		List<String> employeesMailList = new ArrayList<String>();
		String email;
		ConnectionDB dBCon = null;
		Connection con =null;
		ResultSet rs=null;
		Statement st=null;
			
			try {
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(SQLConstants.SLC_ALL_EMAIL); 
			  
			 if (rs != null){
					
					while(rs.next()){
						email = rs.getString(EMAIL);
						
						if ((email!=null)&(!email.isEmpty())) {
							employeesMailList.add(email);
						}
						
					}
				}
			} catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			}
			finally{
				try {
					if (rs!=null){
						rs.close();
					}
					if (st!=null){
						st.close();
					}	
					if (con!=null) {
						con.close();
					} 
				}catch (SQLException e) {
					logger.error(CLS_ERR + e.getMessage());
				}
			}
						
		return employeesMailList;
	}
	
	public Employee findEmployee(String email) throws DAOException, ClassNotFoundException {
		ConnectionDB dBCon = null;
		Employee employee = null; 
		Connection con =null;
		ResultSet rs=null;
		PreparedStatement ps=null;
	
		try {
			dBCon = new ConnectionDB();
			con = dBCon.getConnection();
			 ps = con.prepareStatement(SQLConstants.SLC_EMPL_BY_EMAIL); 
			  ps.setString(1, email);
			  rs = ps.executeQuery();
		
			
			if (rs != null){
		
				while(rs.next()){
					
					employee = new Employee(
										   rs.getString(FIRST_NAME),
										   rs.getString(LAST_NAME),
										   rs.getString(EMAIL), 
										   rs.getString(ROLE),
										   rs.getString(PASS) 
										   );
				}
			}
			
		}catch (SQLException e) {
			throw new DAOException(CONNECT_ERR);
			
		}
		finally{
			try {
				if (rs!=null){
					rs.close();
				}
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
		
		return employee;
	}
}
	
	


