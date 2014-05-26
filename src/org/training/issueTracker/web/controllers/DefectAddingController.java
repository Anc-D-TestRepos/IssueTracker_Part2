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

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.CheckerAddingIssueFields;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;


/**
 * Servlet implementation class DefectAddingController
 */
@WebServlet("/DefectAddingController")
public class DefectAddingController extends HttpServlet {
	
	private final String BAD_FIELD = "badField";
	private final String CAUSE = "cause";
	private final String EMAIL = "email";
	private final String EMPTY = "";
	private final String EMPTY_FIELDS = "not filled all necessary field  :";
	private final String WRONG_FIELDS = "value fields \"Status\" and \"Assignee\" filled  incorrect";
	private final String SCSSFUL_ADING_PAGE = "/scssfulAddingData.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";
	private final String ADD_ERROR_PAGE = "/errAddingData.jsp";
	
	private final int SUMMARY     = 0;
	private final int DESCRIPTION = 1;
	private final int STATUS	= 2;
	private final int TYPE 		= 3;
	private final int PRIORITY	= 4;
	private final int PROJECT 	= 5;
	private final int BUILD 	= 6;
	private final int ASSIGNEE 	= 7;

	private final String OLD_SUMMARY     = "activeSummary";
	private final String OLD_DESCRIPTION = "activeDescription";
	private final String OLD_STATUS		= "activeStatus";
	private final String OLD_TYPE 		= "activeType";
	private final String OLD_PRIORITY	= "activePriority";
	private final String OLD_PROJECT 	= "activeProject";
	private final String OLD_BUILD 		= "activeBuild";
	private final String OLD_ASSIGNEE 	= "activeAssignee";

       	
	
	private enum IssueNecessaryAddField {
		SUMMARY,DESCRIPTION,STATUS,TYPE,PRIORITY,PROJECT,BUILD,ASSIGNEE;
		
	 }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefectAddingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String field = null;
		String parameter = null;
		boolean hasBadField = false;
		boolean hasCorrectlyField = false;
		List <String> badFields = new ArrayList<String>();
		Issue issue= new Issue();
		List <String> fields = new ArrayList<String>();
		HttpSession session = req.getSession();
		
		
		DAOInterface defectSetter = new DBImplDAO();
		
		
		for (IssueNecessaryAddField element : IssueNecessaryAddField.values()) {
			parameter = element.toString().toLowerCase();
			
			field =req.getParameter(parameter);
			
			fields.add(field);
		}
				
		badFields = CheckerAddingIssueFields.isFillingCorrectly(fields);
		
		hasBadField = !(badFields.isEmpty());
		
		if (hasBadField){
		
			setChoisenIssueField (fields, session);
		
			session.setAttribute(BAD_FIELD, badFields);
			session.setAttribute(CAUSE,EMPTY_FIELDS );
			jump(ADD_ERROR_PAGE,req,resp);
			return;
		}
		

		hasCorrectlyField = CheckerAddingIssueFields.isCorrectlySatusAndAssignee(fields);
		
		

		
		if (hasCorrectlyField){
			issue.setModifiedBy(EMPTY);
			issue.setResolution(EMPTY);
			issue.setSummary(fields.get(SUMMARY));
			issue.setDescription(fields.get(DESCRIPTION));
			issue.setStatus(fields.get(STATUS));
			issue.setType(fields.get(TYPE));
			issue.setPriority(fields.get(PRIORITY));
			issue.setProject(fields.get(PROJECT));
			issue.setBuildFound(fields.get(BUILD));
			issue.setAssignee(fields.get(ASSIGNEE));
			issue.setCreateBy((String)session.getAttribute(EMAIL));
			
			

			try {
				defectSetter.setIssue(issue);
			} catch (DAOException e) {
				session.setAttribute(CAUSE, e.getMessage());
				jump(DAO_ERROR_PAGE, req, resp);
				return;
			}
		jump(SCSSFUL_ADING_PAGE,req,resp);
		}else {
				
			setChoisenIssueField (fields, session);
						
			session.setAttribute(CAUSE,WRONG_FIELDS );
			jump(ADD_ERROR_PAGE,req,resp);
		}
		 
		
	
		
	}
		
	private void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}

	private void setChoisenIssueField (List <String> fields, HttpSession session) {
		session.setAttribute(OLD_SUMMARY, fields.get(SUMMARY));
		session.setAttribute(OLD_DESCRIPTION, fields.get(DESCRIPTION));
		session.setAttribute(OLD_STATUS, fields.get(STATUS));
		session.setAttribute(OLD_TYPE, fields.get(TYPE));
		session.setAttribute(OLD_PRIORITY, fields.get(PRIORITY));
		session.setAttribute(OLD_PROJECT, fields.get(PROJECT));
		session.setAttribute(OLD_BUILD, fields.get(BUILD));
		session.setAttribute(OLD_ASSIGNEE, fields.get(ASSIGNEE));
		
	}
	
}
