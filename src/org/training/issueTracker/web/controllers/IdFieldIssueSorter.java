package org.training.issueTracker.web.controllers;

import java.io.IOException;
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


/**
 * Servlet implementation class IdIssueComparator
 */
@WebServlet("/IdIssueComparator")
public class IdFieldIssueSorter extends HttpServlet {
	
	private final String ROLE = "role";
	private final String ADMIN = "admin";
	private final String USER = "user";
	private final String GUEST = "guest";
	private final String  DEFECT_LIST = "defectList";
	private final String ADMIN_PAGE ="/autorizedAdminPage.jsp";
	private final String USER_PAGE ="/autorizedUserPage.jsp";
	private final String START_PAGE ="/startPage.jsp";
	private final String ERR_PAGE ="/errorLoginPage.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdFieldIssueSorter() {
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
						
			HttpSession session = req.getSession();
		
			String role = (String)session.getAttribute(ROLE);
			
			List<Issue> defectList = (List<Issue>)session.getAttribute(DEFECT_LIST);
			

			Collections.sort(defectList);
			
			session.setAttribute(DEFECT_LIST, defectList);
			
		
			if (role!=null){
				if(role.equals(ADMIN)){	
					jump(ADMIN_PAGE, req, res);
					}
				if(role.equals(USER)){	
					jump(USER_PAGE, req, res);
					}
				if(role.equals(GUEST)){	
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
