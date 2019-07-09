package my.web.job.Interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import my.web.job.Pojo.Users;

public class EmployerInterceptor extends HandlerInterceptorAdapter {
	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		System.out.println("Inside Employer Interceptor");
		Users user = (Users) session.getAttribute("userSession");
		if(user != null && user.getUserRole().getRole().equalsIgnoreCase("ROLE_Employee")){
			return true;
		}
		
		System.out.println("no user");
		
		RequestDispatcher rd=request.getRequestDispatcher(errorPage);  
		rd.forward(request, response);
		return false;
	}

}
