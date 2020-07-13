package com.zcib.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcib.domain.User;


@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
		, urlPatterns = { "/home/*", "/person/*" }, servletNames = { "AddressServlet" })
public class LoginFilter implements Filter {

    public LoginFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 对会员登录进行登录
		 * 1.获取Session
		 * 2.获取User对象
		 * 3.User对象为空，去登录
		 * 4.不为空，放行
		 * */
		//1.获取Session
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		//2.获取User对象
		User user = (User) session.getAttribute("user");
		//3.User对象为空，去登录
		if(user == null){
//			session.setAttribute("msg", "请登录后再操作！");
			res.sendRedirect(req.getContextPath()+"/login.jsp");
		}else{
			chain.doFilter(request, response);//4.不为空，放行
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
