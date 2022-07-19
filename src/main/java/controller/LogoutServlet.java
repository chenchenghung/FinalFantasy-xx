package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.CustomerBean;
import model.CustomerService;
@WebServlet(
		urlPatterns={"/secure/logout.controller"}		
)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerService customerService;
	@Override
	public void init() throws ServletException {
//		customerService = new CustomerService();
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application.getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		customerService = context.getBean("customerService", CustomerService.class);
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
		  	session.removeAttribute("user");
		}
	    session.invalidate();
	    response.sendRedirect(request.getContextPath() +"/secure/login.view");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
