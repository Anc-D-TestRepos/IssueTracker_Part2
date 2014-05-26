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
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class AuthorizedAdmin
 */
@WebServlet("/AuthorizedAdmin")
public class AuthorizedAdmin extends HttpServlet {
	private final String  DEFECT_LIST = "defectList";
	private final String EMAIL = "email";
	private final String CAUSE = "cause";
	private final String ADMIN_PAGE ="/autorizedAdminPage.jsp";
	private final String LOGIN_ERR_PAGE ="/errorLoginPage.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizedAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request,response);
	}
	
	protected void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int capacity = 10;
		HttpSession session = req.getSession();
		
		
		DAOInterface defectSearcher = new DBImplDAO();
					
		String email = (String) session.getAttribute(EMAIL);
		
		List<Issue> defectList = new ArrayList<>();
		
		try {
			
			defectList = defectSearcher.getListIssuesbyUser(email, capacity);
		
		} catch (DAOException | SAXException e) {
			
			session.setAttribute(CAUSE, e.getMessage());
			jump(DAO_ERROR_PAGE,req,res);
			return;
		}
		
		session.setAttribute(DEFECT_LIST, defectList);
		
	
		if (email!=null){
		
			jump(ADMIN_PAGE, req, res);
		}else {
			jump(LOGIN_ERR_PAGE, req, res);
		}
	}
		
	protected void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}

}
