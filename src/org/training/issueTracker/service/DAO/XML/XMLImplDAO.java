package org.training.issueTracker.service.DAO.XML;

import java.util.List;

import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.xml.sax.SAXException;



public class XMLImplDAO implements DAOInterface {

	public XMLImplDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Issue> getListIssues() throws SAXException {
		DefectSearcherXML defectSearcher = new DefectSearcherXML();
		List<Issue> defectList = defectSearcher.findDefects();
		System.out.println("Hello from XML");

		return defectList;
	}

	@Override
	public List<Issue> getListIssues(int capacity) throws SAXException {
		DefectSearcherXML defectSearcher = new DefectSearcherXML();
		List<Issue> defectList = defectSearcher.findDefects(capacity);
		System.out.println("Hello from XML");

		return defectList;
	}

	@Override
	public Employee getEmployee(String email) throws SAXException {
		
		EmployeeSearcherXML employeeSearcher = new EmployeeSearcherXML();
		System.out.println("Hello from XML");
		Employee employee  = null;
				
				
		employee = employeeSearcher.findEmployee(email);
		return employee;
	}

	

	@Override
	public List<Issue> getListIssuesbyUser(String email) throws SAXException {
		DefectSearcherXML defectSearcher  = new DefectSearcherXML();
		List<Issue> defectList = defectSearcher.findDefectsByUser(email);
		return defectList;
	}

	@Override
	public List<Issue> getListIssuesbyUser(String email, int capacity) throws SAXException {
		DefectSearcherXML defectSearcher  = new DefectSearcherXML();
		List<Issue> defectList = defectSearcher.findDefectsByUser(email, capacity);
		return defectList;
	}

	@Override
	public List<String> getAllEmployeesMail() {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<String> getAllProjectName() {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<String> getAllBuildVersion() {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void setIssue (Issue issue) {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public Issue getIssueById(int id) {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void replaceIssue(Issue issue) {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Issue> getSortedListIssue(String key, int capacity) throws DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void replaceUserPassword(String user, String pass)throws DAOException{
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void replaceUserDataByAdmin(Employee user) throws DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}
	@Override
	public void replaceUserDataByUser(Employee user, String email) throws DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Comment> getCommentsSortByDate(int issueId) {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void setComment(Comment comment) {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	
}
