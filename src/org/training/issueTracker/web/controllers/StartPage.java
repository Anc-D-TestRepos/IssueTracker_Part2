package org.training.issueTracker.web.controllers;


import java.io.IOException;
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
import org.training.issueTracker.service.DAO.JDBC.ConnectionPool;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.xml.sax.SAXException;


/**
 * Servlet implementation class StartPage
 */
@WebServlet("/StartPage")
public class StartPage extends HttpServlet {
	private final String  DEFECT_LIST = "defectList";
	private final String  START_PAGE = "/startPage.jsp";
	private final String  ROLE = "role";
	private final String  GUEST = "guest";
	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartPage() {
        super();
       
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
		String  role = GUEST;
		HttpSession session = req.getSession();
		ConnectionPool.addURL(req.getServletContext().getRealPath("\\"));
		DAOInterface defectSearcher = new DBImplDAO();
		try {
			
			
			List<Issue> defectList = defectSearcher.getListIssues(capacity);
			
			
			session.setAttribute(ROLE, role);
			session.setAttribute(DEFECT_LIST, defectList);
	
			
			jump(START_PAGE, req, res);
		
		} catch (DAOException | SAXException e) {
			session.setAttribute(CAUSE, e.getMessage());
			jump(DAO_ERROR_PAGE,req,res);
			return;
		} 
	}
		
	protected void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}
				

}


