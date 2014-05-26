package org.training.issueTracker.service.DAO.JDBC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.training.issueTracker.service.DAO.exceptions.DAOException;

public class MD5Hashing {
	
	private static final String CONNECT_ERR  = " Can't create password ";
	
	public String  getHash(String password) throws DAOException  {
	        
	 
	        MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				throw new DAOException(CONNECT_ERR);
			}
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();

	        StringBuffer sb = new StringBuffer();
	        
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	       return sb.toString();
	
}
}