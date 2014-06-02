package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class CommentSetter {
	private final int ID = 1;
	private final int ADDED_BY  = 2;
	private final int COMMENT = 3;
	
	private final String CONNECT_ERR  = " Problem with  connection  to database  ";
	private final String CLS_ERR = " Can't close - ";
	private Logger logger= Logger.getLogger(CommentSetter.class);
	public void setComment(Comment comment) throws DAOException, ClassNotFoundException {
		
		
		Object lock = new Object();
		ConnectionDB dBCon = null;
		Connection con =null;
		PreparedStatement ps=null;
			
			try {
			
				dBCon = new ConnectionDB();
				con = dBCon.getConnection();
				
				synchronized (lock) {
					
						if (comment!=null){
							

							 ps = con.prepareStatement("INSERT INTO comments (idComment,addDate,addedBy,comment)" 
											+"VALUES (?,CURRENT_DATE,?,?)"/*SQLConstants.ADD_ISSUE*/); 
							
							 ps.setInt(ID,comment.getId());
							 ps.setString(ADDED_BY,comment.getAddedBy());
							 ps.setString(COMMENT,comment.getComment());
							
							
							 ps.executeUpdate();
						}

				}
				
		
			}  catch (SQLException e) {
				e.printStackTrace();
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
