package org.training.issueTracker.service.DAO.DAOInterfaces;

import java.util.List;

import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.xml.sax.SAXException;

public interface DAOInterface {
	
	List<Issue> getListIssues() throws DAOException, SAXException, ClassNotFoundException;
	List<Issue> getListIssues(int capacity) throws DAOException, SAXException, ClassNotFoundException;
	List<Issue> getListIssuesbyUser(String email) throws DAOException, SAXException, ClassNotFoundException;
	List<Issue> getListIssuesbyUser(String email, int capacity) throws DAOException, SAXException, ClassNotFoundException;
	Issue getIssueById(int id) throws DAOException, ClassNotFoundException;
	List<String> getAllEmployeesMail() throws DAOException, ClassNotFoundException;
	Employee getEmployee(String email) throws DAOException, SAXException, ClassNotFoundException;
	List<String> getAllProjectName() throws DAOException, ClassNotFoundException;
	List<String> getAllBuildVersion() throws DAOException, ClassNotFoundException;
	List<Issue> getSortedListIssue (String key, int capacity) throws DAOException, ClassNotFoundException;
	List <Comment> getCommentsSortByDate(int issueId) throws ClassNotFoundException, DAOException;
	void setComment(Comment comment) throws ClassNotFoundException, DAOException;
	void setIssue(Issue issue) throws DAOException, ClassNotFoundException;
	void replaceIssue(Issue issue) throws DAOException, ClassNotFoundException;
	void replaceUserPassword(String User, String pass) throws DAOException, ClassNotFoundException;
	void replaceUserDataByUser(Employee user, String email) throws DAOException, ClassNotFoundException;
	void replaceUserDataByAdmin(Employee user) throws DAOException, ClassNotFoundException;
	
	
}
