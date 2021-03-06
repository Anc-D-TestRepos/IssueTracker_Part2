package org.training.issueTracker.service.DAO.JDBC;

import java.util.ArrayList;
import java.util.List;

import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.exceptions.DAOException;


public class DBImplDAO implements DAOInterface {

	
	public DBImplDAO() {
		super();
		
	}
	
	@Override
	public List<Issue> getListIssues() throws DAOException, ClassNotFoundException {
		DefectSearcherDB defectSearcher = new DefectSearcherDB();
		
		List<Issue> defectList = new ArrayList<Issue>();
		defectList = defectSearcher.findDefects();
		
		return defectList;
	}
	
	@Override
	public List<Issue> getListIssues(int capacity) throws DAOException, ClassNotFoundException {
	
		DefectSearcherDB defectSearcher = new DefectSearcherDB();
		List<Issue> defectList = new ArrayList<Issue>();
		defectList = defectSearcher.findDefects(capacity);;
		
		return defectList;
	
	}
	@Override
	public Employee getEmployee(String email) throws DAOException, ClassNotFoundException{
		EmployeeSearcherDB employeeSearcher = new EmployeeSearcherDB();
		Employee employee  = null;
				
		employee = employeeSearcher.findEmployee(email);
		
		
		return employee;
		
	}

	@Override
	public List<Issue> getListIssuesbyUser(String email) throws DAOException, ClassNotFoundException {
		DefectSearcherDB defectSearcher = new DefectSearcherDB();
		List<Issue> defectList = new ArrayList<Issue>();
		defectList = defectSearcher.findDefectsByUser( email );
		return defectList;
	}
	
	@Override
	public List<Issue> getListIssuesbyUser(String email, int capacity) throws DAOException, ClassNotFoundException {
		
		DefectSearcherDB defectSearcher = new DefectSearcherDB();
		List<Issue> defectList = new ArrayList<Issue>();
		defectList = defectSearcher.findDefectsByUser( email, capacity);
		return defectList;
	}


	@Override
	public List<String> getAllEmployeesMail() throws DAOException, ClassNotFoundException {
		EmployeeSearcherDB employeeSearcher = new EmployeeSearcherDB();
		return employeeSearcher.findAllEmployeesMail();
	}


	@Override
	public List<String> getAllProjectName() throws DAOException, ClassNotFoundException {
		ProjectSearcherDB projectSearcher = new ProjectSearcherDB();
		return projectSearcher.findAllProjectName();
	}


	@Override
	public List<String> getAllBuildVersion() throws DAOException, ClassNotFoundException  {
		BuildSearcher buildSearcher = new BuildSearcher();
		
		return buildSearcher.findAllBuild();
	}


	@Override
	public void setIssue(Issue issue) throws DAOException, ClassNotFoundException {
		DefectSetter defectSetter = new DefectSetter();
		defectSetter.addIssue(issue);
		
	}


	@Override
	public Issue getIssueById(int id) throws DAOException, ClassNotFoundException {
		DefectSearcherDB defectSearcher = new DefectSearcherDB();
		
		return defectSearcher.findDefectById( id );
		 
	}


	@Override
	public void replaceIssue(Issue issue) throws DAOException, ClassNotFoundException {
		DefectSetter defectSetter = new DefectSetter();
		defectSetter.editIssue(issue);
	}

	@Override
	public List<Issue> getSortedListIssue(String key, int capacity) throws DAOException, ClassNotFoundException {

		DefectSearcherDB defectSearcher = new DefectSearcherDB();
			
		return defectSearcher.findDefectsSortedByKey( key, capacity );
	}

	@Override
	public void replaceUserPassword(String user, String pass)throws DAOException, ClassNotFoundException{
			UserDataSetter setter = new UserDataSetter();
			
			setter.editPassword(user, pass);
		
	}

	@Override
	public void replaceUserDataByAdmin(Employee user) throws DAOException, ClassNotFoundException {
		UserDataSetter setter = new UserDataSetter();
		
		setter.editUserDataByAdmin(user);
		
	}
	@Override
	public void replaceUserDataByUser(Employee user, String email) throws DAOException, ClassNotFoundException {
		UserDataSetter setter = new UserDataSetter();
		
		setter.editUserDataByUser(user, email);
		
	}

	@Override
	public List<Comment> getCommentsSortByDate(int issueId) throws ClassNotFoundException, DAOException {
		CommentGetter getter = new CommentGetter();
		return getter.findCommentsById(issueId);
	}

	@Override
	public void setComment(Comment comment) throws ClassNotFoundException, DAOException {
		CommentSetter setter = new CommentSetter();
		setter.setComment(comment);
		
	}

}
