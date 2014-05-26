package org.training.issueTracker.web.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class DispatcherController
 */
@WebServlet("/DispatcherController")
public class DispatcherController extends HttpServlet {

       
	private final String ADMIN = "admin";
	private final String USER = "user";
	private final String GUEST = "guest";
	private final String ADMIN_CONTROLLER = "/AuthorizedAdmin";
	private final String USER_CONTROLLER = "/AuthorizedUser";
	private final String START_PAGE = "/startPage.jsp";
	private final String LOGOUT_CONTROLLER = "/LogoutController";
	private final String ROLE = "role"; 

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherController() {
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
								
		String role = (String) session.getAttribute(ROLE);
		
	
		if (role!=null){
			
			if (role.equals(ADMIN)){
				

				jump(ADMIN_CONTROLLER, req, res);
				return;
			}
			if (role.equals(USER)){
				
				jump(USER_CONTROLLER, req, res);
				return;
			}
			if (role.equals(GUEST)){
							
				jump(START_PAGE, req, res);
				return;
			}
		}else {
			jump(LOGOUT_CONTROLLER, req, res);
		}
	}
		
	protected void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}
}
