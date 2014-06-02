package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class CommentGetter {
	private final int ID = 1;
	private final int COMMENT = 2;
	private final int ADDED_BY  =3;
	private final int ADD_DATE  =4;
	private final String CONNECT_ERR  = " Problem with  connection  to database  ";
	private final String CLS_ERR = " Can't close - ";
	private Logger logger= Logger.getLogger(CommentGetter.class);
	
	
	public List<Comment> findCommentsById(int id) throws DAOException, ClassNotFoundException {
		ConnectionDB dBCon = null;
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		List<Comment> comments = new ArrayList<Comment>();
	
		try {
			dBCon = new ConnectionDB();
			con = dBCon.getConnection();
		
			
					
			 ps = con.prepareStatement( "SELECT idComment, comment, addedBy, addDate FROM comments WHERE  idComment = ? ORDER BY addDate "
						 /*SQLConstants.SLC_ALL_ISSUE*/);
			 ps.setInt(1, id);
			 rs = ps.executeQuery();
			  
	
			comments = getCommentList(rs);
		}  catch (SQLException e) {
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
		
		return comments;
	
	}	
	private List<Comment> getCommentList (ResultSet rs) throws DAOException {
		List<Comment> comments = new ArrayList<Comment>();
		
		if (rs != null){
			try {
				while(rs.next()){
					
					
					comments.add(new Comment(
										   rs.getInt(ID),
										   rs.getString(COMMENT),
										   rs.getString(ADDED_BY),
										   rs.getDate(ADD_DATE) 
										 
										   
										));
										
				
				}
			} catch (SQLException e) {
				throw new DAOException(CONNECT_ERR);
			}
		}
		return comments;
	}
}
