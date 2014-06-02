package org.training.issueTracker.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.training.issueTracker.service.DAO.exceptions.DAOException;


/**
 * Servlet Filter implementation class ChangeUserDataByAdminFilter
 */

@WebFilter(urlPatterns = { "/ChangeUserDataByAdmin" }, servletNames = { "ChangeUserDataByAdmin" })
public class ChangeUserDataByAdminFilter implements Filter {
	private final String EMAIL = "email";
	private final String RETURN_PAGE = "page"; 
	private final String CAUSE = "cause";
	private final String BAD_FIELD = "badField";
	private final String ERR_REQ = "wrong request data from previous page";
	private final String EMPTY_FIELDS = "not filled all necessary field  :";
	private final String ADD_ERROR_PAGE = "/errEditingData.jsp";
	
	
	private enum EmployeeFields {
		FIRST_NAME,LAST_NAME,EMAIL,ROLE;
		
	 }

    /**
     * Default constructor. 
     */
    public ChangeUserDataByAdminFilter() {
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
		System.out.println("Hello from new user data from admin checker  Filter");

		
		HttpServletRequest httpReq = (HttpServletRequest)request;

		

		
		
		List <String> badFields = new ArrayList<String>();

		List <String> fields = new ArrayList<String>();
		String field = null;
		String parameter = null;
		for (EmployeeFields element : EmployeeFields.values()) {
			parameter = element.toString().toLowerCase();
			//System.out.println("getted = "+parameter);

			field =request.getParameter(parameter);
			
			fields.add(field);
			
		}
		
		badFields = isNullOrEmpty(fields);
		
		
			
		if(!badFields.isEmpty()){
			
			request.setAttribute(BAD_FIELD, badFields);
			request.setAttribute(CAUSE,EMPTY_FIELDS );
			request.setAttribute(RETURN_PAGE,"/changeUserDataByAdminPage.jsp" );
			setChoisenEmployeeField(fields,httpReq);
			RequestDispatcher rd = request.getRequestDispatcher(ADD_ERROR_PAGE);
			rd.forward(request, response);
			
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private List <String> isNullOrEmpty (List <String> fields){
		
		List <String> badFields = new ArrayList<>();
		String field =null;
		
		for (int i = 0; i < 4; i++) {
		
			field = fields.get(i);
			
			if ((field==null) || (field.trim().isEmpty()) ){
			
				System.out.println("bad field added -> "+getFieldName(i)+"  value= ["+field.trim().isEmpty()+"]");

				badFields.add(getFieldName(i));
			
			}
		}
		
		return badFields;
		
	}

			

	private String getFieldName (int index){
		switch (index) {
		case 0:
			return  "First name";
		case 1:
			return 	"Last name";
		case 2:
			return  "Email";
		case 3:
			return  "Role";
					
		default:
			return  "";
		
		
		}		
	}
	
	private void setChoisenEmployeeField(List <String> fields, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.setAttribute("choisenFirstName", fields.get(0));
		session.setAttribute("choisenLastName", fields.get(1));
		session.setAttribute("choisenEmail", fields.get(2));
		session.setAttribute("choisenRole", fields.get(3));
		
	}
}
