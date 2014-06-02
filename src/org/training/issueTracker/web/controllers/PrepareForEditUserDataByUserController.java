//package org.training.issueTracker.web.controllers;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
//import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
//import org.training.issueTracker.service.DAO.exceptions.DAOException;
//
///**
// * Servlet implementation class PrepareForEditUserDataByUserController
// */
//@WebServlet("/PrepareForEditUserDataByUserController")
//public class PrepareForEditUserDataByUserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private final String MAIL_LIST ="mailList";
//	private final String CAUSE = "cause";
//	private final String USER_EDIT_PAGE = "/changeUserDataByUserPage.jsp";
//	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public PrepareForEditUserDataByUserController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		performTask(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		performTask(request, response);
//	}
//
//	
//	protected void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		System.out.println("Hello from PrepareForEditUserDataByUserController");
//
//		HttpSession session = req.getSession();
//				
//		DAOInterface emailSearcher = new DBImplDAO();
//		
//		try {
//			
//							
//		
//			List<String>  emploeesMailList = emailSearcher.getAllEmployeesMail();
//				
//			
//			session.setAttribute(MAIL_LIST, emploeesMailList);
//			
//						
//		} catch (DAOException | ClassNotFoundException e) {
//			session.setAttribute(CAUSE, e.getMessage());
//			jump(DAO_ERROR_PAGE,req,res);
//			return;
//		}
//			jump(USER_EDIT_PAGE,req,res);
//		
//	
//		
//	}
//		
//	private void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//			
//			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
//			
//			rd.forward(req, resp);
//			
//		}
//
//}
