package com.zcib.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcib.domain.Category;
import com.zcib.domain.Page;
import com.zcib.service.CategoryService;

public class CategoryManageServlet extends HttpServlet {
	private CategoryService categoryService = new CategoryService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//防止中文乱码
		String action = request.getParameter("action");
		if("add".equals(action)){
			add(request,response);
		}else if("findAll".equals(action)){
			findAll(request,response);
		}else if("delete".equals(action)){
			delete(request,response);
		}else if("deleteMore".equals(action)){
			deleteMore(request,response);
		}else if("updatebefore".equals(action)){
			updatebefore(request,response);
		}else if("update".equals(action)){
			update(request,response);
		}else if("findAllNo".equals(action)){
			findAllNo(request,response);
		}
		
	}
	
	private void findAllNo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.调用Service中的方法，读取数据
		List<Map<String,Object>> list =  categoryService.findAll();
		//2.把数据存储在request中
		request.setAttribute("list", list);
		//3.请求转发到categoryList页面
		request.getRequestDispatcher("/admin/addProduct.jsp").forward(request, response);
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 添加分类信息
		 * 1.获取表单数据
		 * 2.对表单进行验证，分类名称不能为重复
		 * 3.将数据封装到一个对象中（分类对象）
		 * 4.调用Service层中的方法来进行添加（add()）
		 * 5.返回添加分类页（后面还需改）
		 */
		//1.获取表单数据
		String strid = request.getParameter("id");
		String name = request.getParameter("name");//输入框分类名称
		String sort = request.getParameter("sort");//输入框分类排序号
		//2.对表单进行验证
		//分类名称是否为空
		if(name == null || name.trim().isEmpty()){
			//为空的话，返回页面，提示用户输入
			request.setAttribute("msg", "分类名称不能为空！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		//分类排序号是否为空
		if(sort == null || sort.trim().isEmpty()){
		//为空的话，返回页面，提示用户输入
			request.setAttribute("msg", "分类排序号不能为空！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		String regex="^[1-9]+[0-9]*$";  
		Pattern p=Pattern.compile(regex);  
		Matcher m=p.matcher(sort);
		//返回true表示符合规则，false表示不符合规则
		if(!m.find()){//false表示不符合规则
			request.setAttribute("msg", "分类排序号必须为正整数！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		
		//2.2 分类名称不能重复
		if(categoryService.validateName(name)){
			request.setAttribute("msg", "分类名称不能重复！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		int id = 0;
		try{
			id = Integer.parseInt(strid);
		}catch(Exception e){
			
		}
		
		//3.将数据封装到一个对象中（分类对象）
		Category category = new Category();
		category.setCategoryid(id);
		category.setName(name);
		category.setSort(Integer.parseInt(sort));
		//4.调用Service层中的方法来进行修改
		categoryService.update(category);
		
		//5.返回添加分类页（后面还需改）
		findAll(request,response);
		
	}

	private void updatebefore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 根据id读取该条记录
		 * 1.获取id
		 * 2.根据id读取该条记录，需要调用Service
		 * 3.将该条记录存入request中
		 * 4.返回到updateCategory.jsp
		 */
		//1.获取id
		String strid = request.getParameter("id");
		int id = 0;
		try{
			id = Integer.parseInt(strid);
		}catch(Exception e){
			
		}
		//2.根据id读取该条记录，需要调用Service
		Category category = categoryService.findById(id);
		// 3.将该条记录存入request中
		request.setAttribute("category", category);
		//4.返回到updateCategory.jsp
		request.getRequestDispatcher("/admin/updateCategory.jsp").forward(request, response);
		
	}

	private void deleteMore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取要删除的所有被选中的id
		 * 2.调用Service方法来删除
		 * 3.返回到列表页
		 */
		//1.获取要删除的所有被选中的id
		String ids[] = request.getParameterValues("sel");
		//2.调用Service方法来删除
		categoryService.deleteMore(ids);
		//3.返回到列表页
		findAll(request,response);
		
	}

	//删除一条记录
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取表单数据，要删除的id
		 * 2.验证
		 * 3.调用Service中的方法，进行删除
		 * 4.返回到List页面
		 */
		//1.获取表单数据，要删除的id
		String id = request.getParameter("id");
		int categoryid = Integer.parseInt(id);
		// 3.调用Service中的方法，进行删除
		categoryService.delete(categoryid);
		//4.返回到List页面
		findAll(request,response);
	}

	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 0.获取当前页码
		 * 1.调用Service中的方法，读取数据
		 * 2.把数据存储在request中
		 * 3.请求转发到categoryList页面
		 */
		//0.获取当前页码
		String c= request.getParameter("current");
		int currentPage = 1;
		try{
			currentPage =Integer.parseInt(c);
		}catch(Exception e){
			currentPage = 1;
		}
		// 1.调用Service中的方法，读取数据
		Page page = categoryService.findAll(currentPage);
		//2.把数据存储在request中
		request.setAttribute("page", page);
		request.setAttribute("current", currentPage);
		String url = request.getContextPath() + "/categoryManageServlet?action=findAll";
		request.setAttribute("url",url);
		//3.请求转发到categoryList页面
		request.getRequestDispatcher("/admin/categoryList.jsp").forward(request, response);
//		//1.调用Service中的方法，读取数据
//		List<Map<String,Object>> list =  categoryService.findAll();
//		//2.把数据存储在request中
//		request.setAttribute("list", list);
//		//3.请求转发到categoryList页面
//		request.getRequestDispatcher("/admin/categoryList.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 添加分类信息
		 * 1.获取表单数据
		 * 2.对表单进行验证，分类名称不能为重复
		 * 3.将数据封装到一个对象中（分类对象）
		 * 4.调用Service层中的方法来进行添加（add()）
		 * 5.返回添加分类页（后面还需改）
		 */
		//1.获取表单数据
		String name = request.getParameter("name");//输入框分类名称
		String sort = request.getParameter("sort");//输入框分类排序号
		//2.对表单进行验证
		//分类名称是否为空
		if(name == null || name.trim().isEmpty()){
			//为空的话，返回页面，提示用户输入
			request.setAttribute("msg", "分类名称不能为空！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		//分类排序号是否为空
		if(sort == null || sort.trim().isEmpty()){
		//为空的话，返回页面，提示用户输入
			request.setAttribute("msg", "分类排序号不能为空！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		String regex="^[1-9]+[0-9]*$";  
		Pattern p=Pattern.compile(regex);  
		Matcher m=p.matcher(sort);
		//返回true表示符合规则，false表示不符合规则
		if(!m.find()){//false表示不符合规则
			request.setAttribute("msg", "分类排序号必须为正整数！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		
		//2.2 分类名称不能重复
		if(categoryService.validateName(name)){
			request.setAttribute("msg", "分类名称不能重复！");
			request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
			return;
		}
		
		//3.将数据封装到一个对象中（分类对象）
		Category category = new Category();
		category.setName(name);
		category.setSort(Integer.parseInt(sort));
		//4.调用Service层中的方法来进行添加（add()）
		categoryService.add(category);
		
		//5.返回添加分类页（后面还需改）
		request.setAttribute("msg", "<script>alert('添加分类成功!');window.location.href='admin/addCategory.jsp';</script>");
		request.getRequestDispatcher("admin/msg.jsp").forward(request, response);
		
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

}
