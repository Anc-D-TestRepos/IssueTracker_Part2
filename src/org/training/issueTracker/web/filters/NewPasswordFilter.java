package org.training.issueTracker.web.filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NewPasswordFilter
 */
@WebFilter(
		urlPatterns = { "/ChangeUserPassController" }, 
		servletNames = { "ChangeUserPassController"}
		)
public class NewPasswordFilter implements Filter {
	
	private final String NEW_PASS = "newPass";
	private final String CONFIRM_PASS = "confirmPass"; 
	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";

    /**
     * Default constructor. 
     */
    public NewPasswordFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Hello from New password checker  Filter");

		
		String newPassword;
		String confirmPassword;
		
		
		newPassword = request.getParameter(NEW_PASS);
		confirmPassword = request.getParameter(CONFIRM_PASS);
			
		
		if(!(newPassword.equals(confirmPassword))){
			
			

			request.setAttribute(CAUSE, "Entered passwords not the same ");
			
			jump(DAO_ERROR_PAGE,request,response);
			return;
			
		}
		
		if((newPassword==null)|(newPassword.isEmpty())){
			
		
			
			request.setAttribute(CAUSE, "Entered passwords is not correctly - password can't be empty");
			
			jump(DAO_ERROR_PAGE,request,response);
			return;
			
		}
		if(((newPassword.trim()).length()<5)){
			
		
			
			request.setAttribute(CAUSE, "Entered password very short - should be min 5 characters");
			
			jump(DAO_ERROR_PAGE,request,response);
			return;
			
		}
		
	 	Pattern wrongSymbol = Pattern.compile("[^A-Za-z0-9,;:.!?@%$]");
			Matcher result= wrongSymbol.matcher(newPassword);
			
			if (result.find()){ 
				System.out.println("entered password -"+newPassword+"   finded wrong symbol  "+result.group());
				request.setAttribute(CAUSE, "the password can contain only Latin letters, digits 0-9 and the character ,.;:!?");
				jump(DAO_ERROR_PAGE,request,response);
				return;

			}
				else{
				System.out.println("wrong  not found");

			
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	protected void jump( String url, ServletRequest req, ServletResponse resp) throws ServletException, IOException{
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}

}
