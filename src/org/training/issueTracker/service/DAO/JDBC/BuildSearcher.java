package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class BuildSearcher {

	private  final String BUILD = "build";
	private  final String RECIVE_ERR  = " Problem with  receiving  data from base  ";
	private  final String CLS_ERR = " Can't close - ";
	
	private Logger logger= Logger.getLogger(ProjectSearcherDB.class);
	
	
	public BuildSearcher() {
		super();

	}
	
	public List<String> findAllBuild() throws DAOException, ClassNotFoundException {
		List<String> buildVersionList = new ArrayList<String>();
		
		ConnectionDB dBCon = null;
		Connection con =null;
		ResultSet rs=null;
		Statement st=null;
			
			try {
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(SQLConstants.SLC_ALL_BUILD); 
			  
			 if (rs != null){
					
					while(rs.next()){
						
						buildVersionList.add(rs.getString(BUILD)) ;
						

						
					}
				}
			} catch (SQLException e) {
				throw new DAOException(RECIVE_ERR);
				
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
						
		return buildVersionList;
	}
	
	
}
