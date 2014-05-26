package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class DefectSearcherDB {
	
	private final String ID = "idIssue";
	private final String CREATE_DATE = "createDate";
	private final String CREATE_BY = "createBy";
	private final String MODIFIED_DATE = "modifiedDate";
	private final String MODIFIED_BY= "modifiedBy";
	private final String SUMMARY = "summary";
	private final String DESCRIPTION = "description";
	private final String STATUS = "status";
	private final String RESOLUTION = "resolution";
	private final String TYPE = "type";
	private final String PRIORITY = "priority";
	private final String PROJECT = "project";
	private final String BUILD = "buildFound";
	private final String ASSIGNEE = "assignee";
	private final String CONNECT_ERR  = " Problem with  connection  to database  ";
	private final String CLS_ERR = " Can't close - ";
	private Logger logger= Logger.getLogger(DefectSearcherDB.class);
	
	
	
	public DefectSearcherDB() {
		super();
		
	}
	
	public List<Issue> findDefects() throws DAOException {
		Connection con = null;
		ResultSet rs=null;
		Statement st=null;
		List<Issue> defects = new ArrayList<Issue>();
	
		try {
			con = ConnectionPool.getConnPool().getConnection();
					
			st = con.createStatement();
			rs= st.executeQuery(SQLConstants.SLC_ALL_ISSUE);
	
			defects = getDefectList(rs);
		}  catch (SQLException e) {
			throw new DAOException(CONNECT_ERR);
			
		}
		finally{
			closeResultset(rs);
			closeStatement(st);
			closeConnection (con);
		}
		
		return defects;
	
	}	
			
	public List<Issue> findDefects(int capacity) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Issue> defects = new ArrayList<Issue>();
		
		try {

			con = ConnectionPool.getConnPool().getConnection();
			
			  ps = con.prepareStatement(SQLConstants.SLC_DEFECT_CAP); 
			  ps.setInt(1, capacity);
			  rs = ps.executeQuery();
             
			  defects=getDefectList(rs);
			
		}catch (SQLException e) {
			throw new DAOException(CONNECT_ERR);
		}
		finally{
			
			closeResultset(rs);
			closePreparedStatement(ps);
			closeConnection (con);
		}
		 return defects;
		
			
	}
	public Issue findDefectById(int id) throws DAOException{
		
		Connection con =null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		Issue issue =null;
		con = ConnectionPool.getConnPool().getConnection();
		
		 try {
			ps = con.prepareStatement(SQLConstants.SLC_DEFECT_BY_ID);
				
		
		  ps.setInt(1, id);
		  rs = ps.executeQuery();
	
		
		if (rs != null){
	
			while(rs.next()){
				
				issue = new Issue(
									   rs.getInt(ID),
									   rs.getDate(CREATE_DATE),
									   rs.getString(CREATE_BY), 
									   rs.getString(MODIFIED_DATE),
									   rs.getString(MODIFIED_BY),
									   rs.getString(SUMMARY),
									   rs.getString(DESCRIPTION),
									   rs.getString(STATUS),
									   rs.getString(RESOLUTION),
									   rs.getString(TYPE),
									   rs.getString(PRIORITY),
									   rs.getString(PROJECT),
									   rs.getString(BUILD),
									   rs.getString("ASSIGNEE")
									   );
			}
		}
		
			} catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			} 
		 finally{
				closeResultset(rs);
				closePreparedStatement(ps);
				closeConnection (con);
			}
		return issue; 
	}
	
	
	
	
	public List<Issue> findDefectsByUser(String email) throws DAOException {
		
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		List<Issue> defects = new ArrayList<Issue>();
	
		try {
			con = ConnectionPool.getConnPool().getConnection();
			  ps = con.prepareStatement(SQLConstants.SLC_DEFECT_BY_USER); 
			  
			  ps.setString(1, email);
			  rs = ps.executeQuery();
             
			defects = getDefectList(rs);
		
		} catch (SQLException e) {
			throw new DAOException(CONNECT_ERR);
		}
		finally{
			closeResultset(rs);
			closePreparedStatement(ps);
			closeConnection (con);
		}
		
		return defects;
	}
	
	public List<Issue> findDefectsByUser(String email, int capacity) throws DAOException {
		
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		List<Issue> defects = new ArrayList<Issue>();
	
		try {
			con = ConnectionPool.getConnPool().getConnection();
					
			 ps = con.prepareStatement(SQLConstants.SLC_DEFECT_BY_USER_CAP); 
			 ps.setString(1, email); 
			 ps.setInt(2, capacity);
			 rs = ps.executeQuery();
            
			 defects = getDefectList(rs);
		
		} catch (SQLException e) {
			throw new DAOException(CONNECT_ERR);
		}
		finally{
			closeResultset(rs);
			closePreparedStatement(ps);
			closeConnection (con);
		}
		
		return defects;
		}
	
	private List<Issue> getDefectList (ResultSet rs) throws DAOException {
		List<Issue> defects = new ArrayList<Issue>();
		
		if (rs != null){
			try {
				while(rs.next()){
					
					
					defects.add(new Issue(
										   rs.getInt(ID), 
										   rs.getString(PRIORITY), 
										   rs.getString(ASSIGNEE), 
										   rs.getString(TYPE),
										   rs.getString(STATUS),
										   rs.getString(SUMMARY)
										   ));
										
				
				}
			} catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			}
		}
		return defects;
	}
	private void closeResultset (ResultSet rs){
		if (rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error(CLS_ERR + e.getMessage());
			}
		}
	}
	
	
	private void closeStatement(Statement st){
		if (st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				logger.error(CLS_ERR + e.getMessage());
			}
		}
	}
	private void closePreparedStatement(PreparedStatement ps){
		if (ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(CLS_ERR + e.getMessage());
			}
		}
	}
	private void closeConnection (Connection con) throws DAOException{
		if (con!=null){
			ConnectionPool.getConnPool().releaseConnection(con);
			
		}
	}
	
}
