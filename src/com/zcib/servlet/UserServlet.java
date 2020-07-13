package com.zcib.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcib.domain.User;
import com.zcib.service.UserService;

public class UserServlet extends HttpServlet {
	private UserService userService = new UserService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 处理中文乱码
		/*
		 * 按照action的不同，来调用不同的处理方法
		 */
		String action = request.getParameter("action");
		if ("regist".equals(action)) {
			regist(request, response);
		}else if ("login".equals(action)) {
			login(request, response);
		}else if ("logout".equals(action)) {
			logout(request, response);
		}else if ("password".equals(action)) {
			password(request, response);
		}
	}

	private void password(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 修改密码
		 * 1.获取表单数据 
		 * 2.验证表单 
		 * 3.调用Service方法password来进行修改
		 * 4.返回到password.jsp（防止重复提交）
		 */
		//1.获取表单数据
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		
		//2.验证表单
		// 2.2验证新密码非空
		if (oldPassword == null || oldPassword.trim().isEmpty()) {
			// 密码为空
			request.setAttribute("msg", "原密码不能为空！");
			request.getRequestDispatcher("/person/password.jsp").forward(
					request, response);
			return;
		}

		// 2.2验证新密码非空
		if (newPassword == null || newPassword.trim().isEmpty()) {
			// 密码为空
			request.setAttribute("msg", "新密码不能为空！");
			request.setAttribute("oldPassword", oldPassword);
			request.getRequestDispatcher("/person/password.jsp").forward(request,
					response);
			return;
		}
		if (newPassword2 == null || newPassword2.trim().isEmpty()) {
			// 密码为空
			request.setAttribute("oldPassword", oldPassword);
			request.setAttribute("newPassword", newPassword);
			request.setAttribute("msg", "确认密码不能为空");
			request.getRequestDispatcher("/person/password.jsp").forward(request,
					response);
			return;
		}
		// 2.3验证两次密码是否一致
		if (!newPassword.trim().equals(newPassword2.trim())) {
			request.setAttribute("oldPassword", oldPassword);
			request.setAttribute("msg", "两次密码不一致！");
			request.getRequestDispatcher("/person/password.jsp").forward(request,
					response);
			return;
		}
		// 2.4验证原密码是否正确
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setPassword(oldPassword);
		// 调用Service中的方法，验证用户名和密码是否正确
		user = userService.login(user);
		if (user == null) {
			System.out.println(user);

			request.setAttribute("msg", "原密码不正确！");
			request.getRequestDispatcher("/person/password.jsp").forward(
					request, response);
			return;
		}
		// 2.3验证两次密码是否一致
		if (newPassword.trim().equals(oldPassword.trim())) {
			request.setAttribute("oldPassword", oldPassword);
			request.setAttribute("msg", "新密码不能和原密码一样！");
			request.getRequestDispatcher("/person/password.jsp").forward(
					request, response);
			return;
		}
		
		//3.调用Service方法password来进行修改
		user.setPassword(newPassword);
		userService.password(user);
		request.setAttribute("msg", "修改成功！");
//		request.getRequestDispatcher("/person/password.jsp").forward(request,response);
		
		request.setAttribute("msg", "<script>alert('密码修改成功!');window.location.href='person/password.jsp';</script>");
		request.getRequestDispatcher("/admin/msg.jsp").forward(request, response);
		
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		//清除session中保存的用户信息
		session.removeAttribute("user");
		//跳转到登陆页面
//		response.sendRedirect(request.getContextPath()+"/index.jsp");
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 1.获取表单数据
		 * 2.验证表单数据的正确性
		 * 3.调用Service中的方法，验证用户名和密码是否正确
		 * 4.登录成功，保存登录信息，转到index.jsp
		 * 5.登录失败，保存登录失败信息（显示在login页面），转到login.jsp
		 */
		//1.获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.验证表单数据的正确性
		//3.调用Service中的方法，验证用户名和密码是否正确
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		// 3.调用Service中的方法，验证用户名和密码是否正确
		user = userService.login(user);
		if(user!=null){
			//4.登录成功，保存登录信息，转到index.jsp
			HttpSession session = request.getSession();
			System.out.println(user);
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}else{
			//5.登录失败，保存登录失败信息（显示在login页面），转到login.jsp
			request.setAttribute("username", username);
			request.setAttribute("msg", "登录失败！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

	private void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 进行注册 1.获取表单数据，进行验证 
		 * 2.把表单数据封装到User对象中 
		 * 3.检查该用户名是否已经占用（AJAX来实现，今天不讲）
		 * 4.调用Service方法regist来进行注册
		 * 5.返回到index.html（防止重复提交）
		 */
		// 1.获取表单数据，进行验证
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");

		// 1.1验证用户名非空
		if (username == null || username.trim().isEmpty()) {
			// 用户名为空
			request.setAttribute("error", "用户名不能为空！");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		// 1.1.2验证用户名不能重复
		if (userService.validateName(username)) {
			// 用户名为空
			request.setAttribute("error", "该用户名已被注册！");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		// 1.2验证密码非空
		if (password == null || password.trim().isEmpty()) {
			// 密码为空
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("error", "密码不能为空！");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		if (password2 == null || password2.trim().isEmpty()) {
			// 密码为空
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("error", "确认密码不能为空");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		//1.3验证两次密码是否一致
		if(!password.trim().equals(password2.trim())){
			request.setAttribute("username", username);
			request.setAttribute("email", email);			
			request.setAttribute("error", "两次密码不一致！");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		
		// 1.4验证邮箱非空
		if (email == null || email.trim().isEmpty()) {
			// 密码为空
			request.setAttribute("username", username);
			request.setAttribute("error", "邮箱不能为空！");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		// 1.5验证邮箱格式
		String regex="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";  
		Pattern p=Pattern.compile(regex);  
		Matcher m=p.matcher(email);
		if(!m.find()){
			//m.find()验证失败，返回false
			request.setAttribute("username", username);
			request.setAttribute("error", "邮箱格式不正确！");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}
		//2.把表单数据封装到User对象中 
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setLastlogintime(new Date());
		user.setEnable(1);//后面做邮箱验证的话，须修改成0
		
		//4.调用Service方法regist来进行注册
		if(userService.regist(user)){
			//跳转到index.html（防止重复提交）
			request.setAttribute("msg", "<script>alert('注册成功!');window.location.href='"+request.getContextPath()+"/index.jsp';</script>");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			return;
			
		}else{
			//注册失败，跳转到注册页面
			request.setAttribute("error", "注册失败！");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			
		}

	}

}
