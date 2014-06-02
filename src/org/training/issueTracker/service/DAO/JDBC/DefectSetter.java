package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class DefectSetter {
	
	private  final String ID_EMAIL = "idEmail";
	private  final String CLS_ERR = " Can't close - ";
	private  final String CONNECT_ERR  = " Can't write issue problem with  connection  to database  ";
	private Logger logger= Logger.getLogger(DefectSetter.class);
	
	
	public DefectSetter() {
		super();
		
	}
	
	
public void editIssue(Issue issue) throws DAOException, ClassNotFoundException {
		
		Object lock = new Object();
		ConnectionDB dBCon = null;
		Connection con =null;
		PreparedStatement ps=null;
			
			try {
				
				int employeeId =  getEmployeeId(issue.getAssignee());			
				int projectId = getProjectId(issue.getProject());
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				
				synchronized (lock) {
					

					 ps = con.prepareStatement(SQLConstants.EDIT_ISSUE);

					
					 ps.setString(1,issue.getModifiedBy());
					 ps.setString(2,issue.getSummary());
					 ps.setString(3,issue.getDescription());
					 ps.setString(4,issue.getStatus());
					 ps.setString(5,issue.getType());
					 ps.setString(6,issue.getPriority());
					 ps.setInt(7,projectId);
					 ps.setString(8,issue.getBuildFound());
					 ps.setInt(9,employeeId);
					 ps.setInt(10,issue.getId());
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

	
	
	public void addIssue(Issue issue) throws DAOException, ClassNotFoundException {
		
		Object lock = new Object();
		ConnectionDB dBCon = null;
		Connection con =null;
		PreparedStatement ps=null;
			
			try {
				Integer employeeId =  getEmployeeId(issue.getAssignee());			
				int projectId = getProjectId(issue.getProject());
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				
				synchronized (lock) {
					
						if (employeeId==null){
							 ps = con.prepareStatement(SQLConstants.ADD_ISSUE); 
							
							 ps.setString(1,issue.getCreateBy());
							 ps.setString(2,issue.getModifiedBy());
							 ps.setString(3,issue.getSummary());
							 ps.setString(4,issue.getDescription());
							 ps.setString(5,issue.getStatus());
							 ps.setString(6,issue.getResolution());
							 ps.setString(7,issue.getType());
							 ps.setString(8,issue.getPriority());
							 ps.setInt(9,projectId);
							 ps.setString(10,issue.getBuildFound());
							 
							 ps.executeUpdate();
						}else {
							ps = con.prepareStatement(SQLConstants.ADD_ISSUE_WITH_EMPL); 

							ps.setString(1,issue.getCreateBy());
							ps.setString(2,issue.getModifiedBy());
							ps.setString(3,issue.getSummary());
							ps.setString(4,issue.getDescription());
							ps.setString(5,issue.getStatus());
							ps.setString(6,issue.getResolution());
							ps.setString(7,issue.getType());
							ps.setString(8,issue.getPriority());
							ps.setInt(9,projectId);
							ps.setString(10,issue.getBuildFound());
							ps.setInt(11,employeeId);
							ps.executeUpdate();
						}

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



	private Integer getEmployeeId(String assignee) throws DAOException, ClassNotFoundException {
			ConnectionDB dBCon = null;
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			String result= "" ;
		
			try {
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				ps = con.prepareStatement(SQLConstants.SLC_ID_BY_EMAIL); 
				ps.setString(1, assignee);
				rs = ps.executeQuery();
			
				
				if (rs != null){
					while (rs.next()){
					
					result = rs.getString(ID_EMAIL);
					
					}
				}else {
					throw new DAOException(CONNECT_ERR);
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
			
			 if ((result!=null) & (!result.isEmpty())) {
				 return Integer.parseInt(result);
				 }else {
					 return null ;
				 }
		
	}



	private int getProjectId(String projectName) throws DAOException, ClassNotFoundException {
		ConnectionDB dBCon = null;
		Connection con =null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		String result ="";
	
		try {
			dBCon = new ConnectionDB();
			con = dBCon.getConnection();
			ps = con.prepareStatement(SQLConstants.SLC_ID_BY_PROJECT_NAME); 
			ps.setString(1, projectName);
			rs = ps.executeQuery();
		
			
			if (rs != null){
				while (rs.next()){
					
					result = rs.getString("idProject");
					
				}
				
			}else {
				throw new DAOException(CONNECT_ERR);
			}
			
		}catch (SQLException e) {
			
			logger.error(CONNECT_ERR + e.getMessage());
			
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
		
	return Integer.parseInt(result);
	}
}
