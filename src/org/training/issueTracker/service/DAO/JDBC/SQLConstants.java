package org.training.issueTracker.service.DAO.JDBC;

public class SQLConstants {

	private SQLConstants() {
		super();
		
	}
	
	
	public final static String  SLC_ALL_BUILD = "SELECT build FROM project ORDER BY name ASC";
	public final static String  SLC_ALL_ISSUE = "SELECT * FROM issue";
	public final static String  SLC_DEFECT_CAP = " SELECT * FROM (SELECT idIssue, priority, employee.email AS assignee,"
													+ " type, status, summary FROM issue,employee"
														+ " WHERE  issue.assignee = employee.idEmail ORDER BY idIssue "
															+ "DESC FETCH NEXT ? ROWS ONLY) AS list ORDER BY idIssue ASC";
	
	
	public final static String  SLC_DEFECT_BY_ID = "SELECT idIssue,createDate,createBy,modifiedDate,modifiedBy,summary,"
											+ "issue.description AS description,status,issue.resolution AS resolution,type,priority,"
												+ "project.name AS project,buildFound,employee.email AS assignee"
													+ " FROM issue,employee,project "
														+ "WHERE idIssue= ?"
															+ "AND issue.assignee = employee.idEmail"
																+ " AND issue.project = project.idProject";
	
	public final static String  SLC_DEFECT_BY_USER ="SELECT idIssue, priority, employee.email AS assignee, type, status, summary"
															+ " FROM issue,employee"
																+ " WHERE  issue.assignee = employee.idEmail"
																	+ " AND employee.email = ? ";
	
	
	public final static String  SLC_DEFECT_BY_USER_CAP ="SELECT * FROM (SELECT idIssue, priority, employee.email AS assignee,"
											+ " type, status, summary FROM issue, employee "
												+ "WHERE  issue.assignee = employee.idEmail AND employee.email = ? "
													+ " ORDER BY idIssue DESC FETCH NEXT ? ROWS ONLY) AS list ORDER BY idIssue ASC";
	
	
	
	
	
	
	
	public final static String  EDIT_ISSUE = "UPDATE issue SET  modifiedDate =CURRENT_DATE,  modifiedBy =?,summary=?,description=?,"
												+ "status=?,type=?,priority=?,project=?,buildFound=?,assignee=? where idissue=?";
	

	public final static String ADD_ISSUE ="INSERT INTO issue (createDate,createBy,modifiedBy,summary,"
											+ "description,status,resolution,type,"
												+ "priority,project,buildFound)" 
													+"VALUES (CURRENT_DATE,?,?,?,?,?,?,?,?,?,?)";
	
	public final static String ADD_ISSUE_WITH_EMPL = "INSERT INTO issue (createDate,createBy,modifiedBy,summary,"
											+ "description,status,resolution,type,"
												+ "priority,project,buildFound,assignee)" 
													+"VALUES (CURRENT_DATE,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	
	public final static String SLC_ID_BY_EMAIL = "SELECT idEmail FROM employee WHERE email = ? ";
	
	public final static String SLC_ID_BY_PROJECT_NAME = "SELECT idProject FROM project WHERE name = ?";
	
	public final static String SLC_ALL_EMAIL = "SELECT email FROM employee  ORDER BY email ASC";
	
	public final static String SLC_EMPL_BY_EMAIL = " SELECT * FROM employee WHERE email= ?";
	
	public final static String SLC_ALL_PROJECT_NAME= " SELECT name FROM project ORDER BY name ASC";


}
