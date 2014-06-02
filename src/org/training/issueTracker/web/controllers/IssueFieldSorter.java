package org.training.issueTracker.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import org.training.issueTracker.service.DAO.JDBC.ConnectionDB;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.training.issueTracker.service.comparators.StringFieldIssueComparator;
import org.training.issueTracker.service.constants.RoleConst;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class StringIssueComparator
 */
@WebServlet("/IssueFieldSorter")
public class IssueFieldSorter extends HttpServlet {
	private final String ROLE = "role";
	private final String SORT_BY = "sortColumn";
	private final String  DEFECT_LIST = "defectList";
	private final String ADMIN_PAGE ="/autorizedAdminPage.jsp";
	private final String USER_PAGE ="/autorizedUserPage.jsp";
	private final String START_PAGE ="/startPage.jsp";
	private final String ERR_PAGE ="/errorLoginPage.jsp";
	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueFieldSorter() {
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
		performTask(request,response);
	}
	
	protected void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int capacity = 10;
		
		List<Issue> defectList = new ArrayList<>();
		
		HttpSession session = req.getSession();
		
		String role = (String)session.getAttribute(ROLE);

		String key = (String)req.getParameter(SORT_BY);
		
			
		
		DAOInterface defectSearcher = new DBImplDAO();
		try {
			
			
			defectList = defectSearcher.getSortedListIssue(key, capacity);
		


		} catch (DAOException | ClassNotFoundException e) {
			session.setAttribute(CAUSE, e.getMessage());
			jump(DAO_ERROR_PAGE,req,res);
			return;
		} 
						
			req.setAttribute(DEFECT_LIST, defectList);
		
	
		if (role!=null){
			if(role.equals(RoleConst.ADMIN)){	
				jump(ADMIN_PAGE, req, res);
				

			}
			if(role.equals(RoleConst.USER)){	
				jump(USER_PAGE, req, res);
					
			}
			if(role.equals(RoleConst.GUEST)){	
				jump(START_PAGE, req, res);
				
			}
		}else {
			jump(ERR_PAGE, req, res);
		}
	}
		
	protected void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}

}
