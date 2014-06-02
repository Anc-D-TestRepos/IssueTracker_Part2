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
import org.training.issueTracker.beans.Comment;
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
	private final String COMMENT_LIST  = "commentList";
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
		
		//HttpSession session = req.getSession();
				
		DAOInterface dataSearcher = new DBImplDAO();
		
		try {
			int id = Integer.parseInt(req.getParameter(ID));
			System.out.println("getted ID - " +id);

			Issue issue = dataSearcher.getIssueById(id);
			System.out.println(issue.toString());

			HttpSession session = req.getSession();/*!!!!!!!!!!!!!!!!!!!!!*/
			
			List<Comment> commentsList = new ArrayList<>(dataSearcher.getCommentsSortByDate(id));
			
			List<String>  emploeesMailList = dataSearcher.getAllEmployeesMail();
			
			
			List<String>  projectNameList = dataSearcher.getAllProjectName();
			
			List<String> buildList = new ArrayList<>();
		
			buildList = dataSearcher.getAllBuildVersion();
			
			session.setAttribute(COMMENT_LIST, commentsList);
			req.setAttribute(BUILD_LIST, buildList);
			req.setAttribute(MAIL_LIST, emploeesMailList);
			req.setAttribute(PROJECT_NAME, projectNameList);
			session.setAttribute(ISSUE, issue);
			
		} catch (DAOException | ClassNotFoundException e) {
			req.setAttribute(CAUSE, e.getMessage());
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
