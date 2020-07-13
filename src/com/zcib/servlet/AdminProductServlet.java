package com.zcib.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcib.domain.Page;
import com.zcib.domain.Product;
import com.zcib.service.CategoryService;
import com.zcib.service.ProductService;

public class AdminProductServlet extends HttpServlet {
	private ProductService productService = new ProductService();
	private CategoryService categoryService = new CategoryService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//根据action进行不同的操作
		String action = request.getParameter("action");
		if("add".equals(action)){
			add(request,response);
		}else if("findAllPage".equals(action)){
			findByKey(request,response);
		}else if("delete".equals(action)){
			delete(request,response);
		}else if("findById".equals(action)){
			findById(request,response);
		}else if("updatebefore".equals(action)){
			updateBefore(request,response);
		}else if("update".equals(action)){
			update(request,response);
		}else if("findByKey".equals(action)){
			findByKey(request,response);
		}
		
	}


	private void findByKey(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取表单数据，查询的关键字key，按什么查询keytype，当前页码
		 * 2.根据key,keytype,当前页码，调用Service中的方法进行查询，获取的是一个Page
		 * 3.将查询结果存入request
		 * 4.请求转发到list页面进行显示
		 */
		//1.获取表单数据，查询的关键字key，按什么查询keytype，当前页码
		String key = request.getParameter("key");
		String keytype = request.getParameter("keytype");
		System.out.println("key:"+key);
		System.out.println("keytype:"+keytype);
		String current = request.getParameter("current");
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(current);
		}catch(Exception e){
			currentPage = 1;
		}
		
		//2.根据key,keytype,当前页码，调用Service中的方法进行查询，获取的是一个Page
		Page page = productService.findAll(currentPage, key, keytype);
		//3.将查询结果存入request
		request.setAttribute("page", page);
		request.setAttribute("key",key);
		request.setAttribute("keytype", keytype);
		String url = request.getContextPath()+"/adminProductServlet?action=findByKey";
		request.setAttribute("url", url);
		//4.请求转发到list页面进行显示
		request.getRequestDispatcher("/admin/productList.jsp").forward(request, response);
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取所有的表单数据
		 * 2.封装到product对象中
		 * 3.调用Service中的方法进行修改
		 * 4.返回到productList页面
		 */
		//1.获取所有的表单数据
		String productid = request.getParameter("productid");
		String name = request.getParameter("name");
		String categoryid = request.getParameter("categoryid");
		String filepath = request.getParameter("filepath");
		String price = request.getParameter("price");
		String markprice = request.getParameter("markprice");
		String quality = request.getParameter("quality");
		String hit = request.getParameter("hit");
		String uploadtime = request.getParameter("uploadtime");
		String content = request.getParameter("content");
		
		//2.封装到product对象中
		Product product = new Product();
		product.setCategoryid(Integer.parseInt(categoryid));
		product.setProductid(Integer.parseInt(productid));
		product.setHit(Integer.parseInt(hit));
		product.setQuality(Integer.parseInt(quality));
		product.setMarkprice(Float.parseFloat(markprice));
		product.setPrice(Float.parseFloat(price));
		product.setName(name);
		product.setContent(content);
		product.setPhoto(filepath);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			product.setTime(sdf.parse(uploadtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//3.调用Service中的方法进行修改
		productService.update(product);
		//4.返回到productList页面
		findAllPage(request, response);
		
		
	}


	private void updateBefore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取要修改的商品id
		 * 2.通过id调用Service方法来获取该商品的所有信息
		 * 3.调用Service来读取所有的分类信息（要显示在页面的下拉列表框中）
		 * 4.把该商品信息存入request中
		 * 5.请求转发到updateProduct.jsp页面
		 */
		//1.获取要修改的商品id
		String pid = request.getParameter("id");
		int id = Integer.parseInt(pid);
		//2.通过id调用Service方法来获取该商品的所有信息
		Product product = productService.findById(id);
		// 3.调用CategoryService来读取所有的分类信息（要显示在页面的下拉列表框中）
		List<Map<String,Object>> list = categoryService.findAll();
		//4.把该商品信息存入request中
		request.setAttribute("product", product);
		request.setAttribute("list", list);
		//5.请求转发到updateProduct.jsp页面
		request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
	}


	private void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取表单数据，商品的id
		 * 2.根据id来获取该条记录，通过调用Service来实现
		 * 3.把该条信息保存到request
		 * 4.请求转发productDes.jsp
		 */
		//1.获取表单数据，商品的id
		String pid = request.getParameter("id");
		int id = Integer.parseInt(pid);
		//2.根据id来获取该条记录，通过调用Service来实现，3.把该条信息保存到request
		request.setAttribute("product",productService.findById(id));
		//4.请求转发productDes.jsp
		request.getRequestDispatcher("/admin/productDes.jsp").forward(request, response);
		
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取表单数据，要删除的商品id
		 * 2.调用Service中的方法进行删除delete
		 * 3.返回到列表页
		 */
		//1.获取表单数据，要删除的商品id
		String id = request.getParameter("id");
		int productid = Integer.parseInt(id);
		//2.调用Service中的方法进行删除delete
		productService.delete(productid);
		//3.返回到列表页
		findAllPage(request,response);
			
	}


	private void findAllPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取当前页，如果没有当前页信息，默认是当前页是第一页
		 * 2.根据当前页获取这一页的所有记录（调用Service来实现）
		 * 3.把记录集保存到request对象中
		 * 4.请求转发到productList.jsp
		 */
		String current = request.getParameter("current");
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(current);
		}catch(Exception e){
			currentPage = 1;
		}
		//2.根据当前页获取这一页的所有记录（调用Service来实现）
		Page page = productService.findAll(currentPage);
		//3.把记录集保存到request对象中
		request.setAttribute("page", page);
		// 4.请求转发到productList.jsp
		request.getRequestDispatcher("/admin/productList.jsp").forward(request, response);
		
		
		
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取表单数据
		 * 2.验证表单数据的正确性
		 * 3.把表单数据封装到Product对象中
		 * 4.调用Service方法进行添加该对象
		 * 5.返回到addProduct.jsp
		 */
		//1.获取表单数据
		String name = request.getParameter("name");
		String cid = request.getParameter("categoryid");
		String path = request.getParameter("filepath");
		String price = request.getParameter("price");
		String markprice = request.getParameter("markprice");
		String quality = request.getParameter("quality");
		String hit = request.getParameter("hit");
		String date = request.getParameter("uploadtime");
		String content = request.getParameter("content");
		
		//3.把表单数据封装到Product对象中
		Product product = new Product();
		product.setName(name);
		product.setPhoto(path);
		product.setContent(content);
		try{
			product.setCategoryid(Integer.parseInt(cid));
			product.setHit(Integer.parseInt(hit));
			product.setPrice(Float.parseFloat(price));
			product.setMarkprice(Float.parseFloat(markprice));
			product.setQuality(Integer.parseInt(quality));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			product.setTime(sdf.parse(date));
		}catch(Exception e){
			e.printStackTrace();
		}
		//4.调用Service方法进行添加该对象
		productService.add(product);
		
		//5.返回到addProduct.jsp
		request.setAttribute("msg", "<script>alert('添加商品成功!');window.location.href='admin/addProduct.jsp';</script>");
		request.getRequestDispatcher("/admin/msg.jsp").forward(request, response);
		
	}

}
