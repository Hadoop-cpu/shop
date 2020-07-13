package com.zcib.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcib.domain.Page;
import com.zcib.domain.Product;
import com.zcib.service.ProductService;

public class ProductServlet extends HttpServlet {
	private ProductService productService = new ProductService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action == null){
			findIndex(request,response);
		}else if("findAll".equals(action)){
			findAll(request,response);
		}else if("findById".equals(action)){
			findById(request,response);
		}
	}


	private void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取商品的id
		 * 2.调用Service读取该id的商品信息
		 * 3.将该商品的信息保存到request中
		 * 4.请求转发到productdetails.jsp页面
		 */
		//1.获取商品的id
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		//2.调用Service读取该id的商品信息
		Product product = productService.findById(id);
		//3.将该商品的信息保存到request中
		request.setAttribute("product", product);
		//4.请求转发到productdetails.jsp页面
		request.getRequestDispatcher("/productdetails.jsp").forward(request, response);
		
		
	}


	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* 
		 * 可以根据关键字查找，按某一个关键字进行排序，获取当前页的数据，传递给productList.jsp
		 * 1.获取当前页，如果没有传递当前页码，默认是第一页
		 * 1.2 获取搜索关键字，搜索商品名称
		 * 2.调用Service中的方法，findAll(int currentPage)，获取当前页数据Page
		 * 3.把数据保存到request中
		 * 4.请求转发到productList.jsp
		 */
		//0.获取排序关键字
		String sortkey = request.getParameter("sortkey");
		String sort = request.getParameter("sort");
		//1.获取当前页，如果没有传递当前页码，默认是第一页
		String current = request.getParameter("current");
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(current);
		}catch(Exception e){
			currentPage = 1;
		}
		
		//1.2 获取搜索关键字，搜索商品名称
		String key = request.getParameter("key");
		String keytype = "name";//表示是通过商品名称进行搜索
		//2.调用Service中的方法，findAll(int currentPage)，获取当前页数据Page
		Page page = productService.findAll(currentPage,key,keytype,sort,sortkey);
		//3.把数据保存到request中
		request.setAttribute("page", page);
		request.setAttribute("key", key);
		request.setAttribute("sortkey", sortkey);
		request.setAttribute("sort", sort);
		//4.请求转发到productList.jsp
		request.getRequestDispatcher("/productList.jsp").forward(request, response);
		
	}


	private void findIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 读取数据，传给首页进行显示
		 * 1.调用Service中的方法，读取数据（12条记录）
		 * 2.把获取的数据，保存到request中
		 * 3.请求转发到myindex.jsp页面
		 */
		//1.调用Service中的方法，读取数据（12条记录）
		List<Map<String,Object>> list = productService.findIndex();
		//2.把获取的数据，保存到request中
		request.setAttribute("list",list);
		//3.请求转发到myindex.jsp页面
		request.getRequestDispatcher("/myindex.jsp").forward(request, response);
	}

}
