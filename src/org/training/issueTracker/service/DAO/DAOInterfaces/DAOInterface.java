package org.training.issueTracker.service.DAO.DAOInterfaces;

import java.util.List;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.xml.sax.SAXException;

public interface DAOInterface {
	
	List<Issue> getListIssues() throws DAOException, SAXException;
	List<Issue> getListIssues(int capacity) throws DAOException, SAXException;
	List<Issue> getListIssuesbyUser(String email) throws DAOException, SAXException;
	List<Issue> getListIssuesbyUser(String email, int capacity) throws DAOException, SAXException;
	List<String> getAllEmployeesMail() throws DAOException;
	Employee getEmployee(String email) throws DAOException, SAXException;
	List<String> getAllProjectName() throws DAOException;
	List<String> getAllBuildVersion() throws DAOException;
	Issue getIssueById(int id) throws DAOException;
	void setIssue(Issue issue) throws DAOException;
	void replaceIssue(Issue issue) throws DAOException;
}
