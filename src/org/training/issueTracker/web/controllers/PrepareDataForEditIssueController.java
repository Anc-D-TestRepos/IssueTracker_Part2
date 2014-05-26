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
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

/**
 * Servlet implementation class PrepareDataForEditIssueController
 */
@WebServlet("/PrepareDataForEditIssueController")
public class PrepareDataForEditIssueController extends HttpServlet {
	private final String ID ="id";
	private final String BUILD_LIST  = "buildList";
	private final String MAIL_LIST ="mailList";
	private final String PROJECT_NAME = "projectName";
	private final String ISSUE = "issue";
	private final String CAUSE = "cause";
	private final String DEFECT_EDIT_PAGE = "/defectEditPage.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDataForEditIssueController() {
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
		
		HttpSession session = req.getSession();
				
		DAOInterface defectSearcher = new DBImplDAO();
		
		try {
			int id = Integer.parseInt(req.getParameter(ID));
			Issue issue = defectSearcher.getIssueById(id);
					
		
			List<String>  emploeesMailList = defectSearcher.getAllEmployeesMail();
			
			
			List<String>  projectNameList = defectSearcher.getAllProjectName();
			
			List<String> buildList = new ArrayList<>();
		
			buildList = defectSearcher.getAllBuildVersion();
					
			session.setAttribute(BUILD_LIST, buildList);
			session.setAttribute(MAIL_LIST, emploeesMailList);
			session.setAttribute(PROJECT_NAME, projectNameList);
			session.setAttribute(ISSUE, issue);
			
		} catch (DAOException e) {
			session.setAttribute(CAUSE, e.getMessage());
			jump(DAO_ERROR_PAGE,req,res);
			return;
		}
			jump(DEFECT_EDIT_PAGE,req,res);
		
	
		
	}
		
	private void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}
}
