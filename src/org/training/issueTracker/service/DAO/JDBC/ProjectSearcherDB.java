package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class ProjectSearcherDB {
	private final String NAME = "name";
	private static final String CLS_ERR = " Can't close - ";
	private static final String CONNECT_ERR  = " Problem with  connection  to database  ";
	private Logger logger= Logger.getLogger(ProjectSearcherDB.class);
	
	
	public ProjectSearcherDB() {
		super();
		
	}
	
	
	
	public List<String> findAllProjectName() throws DAOException {
		List<String> projectNameList = new ArrayList<String>();
		
		Connection con =null;
		ResultSet rs=null;
		Statement st=null;
			
			try {
				con = ConnectionPool.getConnPool().getConnection();
			
			st = con.createStatement();
			 rs = st.executeQuery(SQLConstants.SLC_ALL_PROJECT_NAME); 
			  
			 if (rs != null){
					
					while(rs.next()){
						
						projectNameList.add(rs.getString(NAME)) ;
										
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
						ConnectionPool.getConnPool().releaseConnection(con);
					} 
				}catch (SQLException e) {
					logger.error(CLS_ERR + e.getMessage());
				}
			}
						
		return projectNameList;
	}
	
}
