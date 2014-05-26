package org.training.issueTracker.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.CheckerEditingIssueFields;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;


/**
 * Servlet implementation class DefectEditingController
 */
@WebServlet("/DefectEditingController")
public class DefectEditingController extends HttpServlet {
	private final String BAD_FIELD = "badField";
	private final String CAUSE = "cause";
	private final String ISSUE = "issue";
	private final String EMAIL = "email";
	private final String EMPTY_FIELDS = "not filled all necessary field  :";
	private final String WRONG_FIELDS = "value fields \"Status\" and \"Assignee\" filled  incorrect";
	private final String SCSSFUL_ADING_PAGE = "/scssfulAddingData.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";
	private final String EDIT_ERROR_PAGE = "/errEditingData.jsp";
	
	private enum IssueField {
		ID,CREATE_DATE,CREATE_BY,MODIFIED_DATE,MODIFIED_BY,SUMMARY,DESCRIPTION,STATUS,RESOLUTION,TYPE,PRIORITY,PROJECT,BUILD,ASSIGNEE;
		
	 }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefectEditingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	protected void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String field = null;
		String parameter = null;
		boolean hasBadField = false;
		boolean hasCorrectlyField = false;
		List <String> badFields = new ArrayList<String>();

		List <String> fields = new ArrayList<String>();
		HttpSession session = req.getSession();
		
		
		DAOInterface defectSetter = new DBImplDAO();
	
	
		for (IssueField element : IssueField.values()) {
			parameter = element.toString().toLowerCase();
			
			field =req.getParameter(parameter);
			
			fields.add(field);
			
		}
		
		Issue  issuefromSession = (Issue)session.getAttribute(ISSUE);
		

		
		
		
		try {
						
			badFields = CheckerEditingIssueFields.isFillingCorrectly(fields);
			
		} catch (DAOException e) {
			session.setAttribute(CAUSE, e.getMessage());
			jump(DAO_ERROR_PAGE,req,res);
			return;
		}
		
		hasBadField = !(badFields.isEmpty());
		
		if (hasBadField){

			session.setAttribute(ISSUE, setChoisenIssueField(issuefromSession,req));
	
			session.setAttribute(BAD_FIELD, badFields);
			session.setAttribute(CAUSE, EMPTY_FIELDS);
			jump(EDIT_ERROR_PAGE, req, res);
			return;
		}
		

		try {
			hasCorrectlyField = CheckerEditingIssueFields.isCorrectlySatusAndAssignee(fields);
		} catch (DAOException e) {
			session.setAttribute(CAUSE, e.getMessage());
			jump(DAO_ERROR_PAGE, req, res);
			return;
		}
		
		if (hasCorrectlyField){
			
			
			
			issuefromSession.setModifiedBy((String)session.getAttribute(EMAIL));
		
			try {
				defectSetter.replaceIssue(setChoisenIssueField(issuefromSession,req));
			} catch (DAOException e) {
				session.setAttribute(CAUSE, e.getMessage());
				jump(DAO_ERROR_PAGE, req, res);
				return;
			}
			
		jump(SCSSFUL_ADING_PAGE,req,res);
		}else {
						
			
			session.setAttribute(ISSUE, setChoisenIssueField(issuefromSession,req));
			
			
			
			session.setAttribute(CAUSE, WRONG_FIELDS);
			jump(EDIT_ERROR_PAGE, req, res);
		}
		 
		
	
		
	}
		
	private void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}

	private Issue setChoisenIssueField (Issue issue, HttpServletRequest req) {
		
		issue.setSummary(req.getParameter(IssueField.SUMMARY.toString().toLowerCase()));
		issue.setDescription(req.getParameter(IssueField.DESCRIPTION.toString().toLowerCase()));
		issue.setStatus(req.getParameter(IssueField.STATUS.toString().toLowerCase()));
		issue.setType(req.getParameter(IssueField.TYPE.toString().toLowerCase()));
		issue.setPriority(req.getParameter(IssueField.PRIORITY.toString().toLowerCase()));
		issue.setProject(req.getParameter(IssueField.PROJECT.toString().toLowerCase()));
		issue.setBuildFound(req.getParameter(IssueField.BUILD.toString().toLowerCase()));
		issue.setAssignee(req.getParameter(IssueField.ASSIGNEE.toString().toLowerCase()));
		
		return issue;
	}
}
