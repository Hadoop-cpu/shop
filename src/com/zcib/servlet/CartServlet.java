package com.zcib.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcib.domain.Cart;
import com.zcib.domain.CartItem;
import com.zcib.domain.Product;
import com.zcib.service.ProductService;

public class CartServlet extends HttpServlet {
	private ProductService productService = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/*
		 * 根据action请求不同，调用不同的方法
		 */
		String action = request.getParameter("action");
		if("add".equals(action)){
			add(request,response);
		}else if("updatebuycount".equals(action)){
			updatebuycount(request,response);
		}else if("delete".equals(action)){
			delete(request,response);
		}else if("deleteAll".equals(action)){
			deleteAll(request,response);
		}else if("deleteMore".equals(action)){
			deleteMore(request,response);
		}
	}


	private void deleteMore(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 1.获取要删除的id，数组
		 * 2.获取购物车
		 * 3.调用cart的delete方法来进行删除
		 * 4.将购物车保存到Session中
		 * 5.返回购物车列表页
		 */
		//1.获取要删除的id，数组
		String ids[] = request.getParameterValues("sel");
		//2.获取购物车
		//2.1获取Session
		HttpSession session = request.getSession();
		//2.2获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		//3.调用cart的delete方法来进行删除
		cart.delete(ids);
		//4.把Cart放回Session
		session.setAttribute("cart", cart);
		//5.返回到购物车列表页
		response.sendRedirect(request.getContextPath()+"/cartlist.jsp");
	}


	private void deleteAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 1.获取购物车
		 * 2.调用购物车的clear方法，清空购物车
		 * 3.将购物车保存到Session中
		 * 4.返回购物车列表页
		 */
		//1.从Session中获取购物车
		//1.1获取Session
		HttpSession session = request.getSession();
		//1.2获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		//2.调用购物车的clear方法，清空购物车
		cart.clear();
		//3.把Cart放回Session
		session.setAttribute("cart", cart);
		//4.返回到购物车列表页
		response.sendRedirect(request.getContextPath()+"/cartlist.jsp");
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 1.获取要删除的商品id
		 * 2.从Session中获取购物车
		 * 3.调用Cart中的deleteById方法进行删除，从购物车中删除
		 * 4.把Cart放回Session
		 * 5.返回到购物车列表页
		 */
		//1.获取要删除的商品id
		String sid = request.getParameter("id");
		//2.从Session中获取购物车
		//2.1获取Session
		HttpSession session = request.getSession();
		//2.2获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		//3.调用Cart中的deleteById方法进行删除，从购物车中删除
		cart.deleteById(Integer.parseInt(sid));
		//4.把Cart放回Session
		session.setAttribute("cart", cart);
		//5.返回到购物车列表页
		response.sendRedirect(request.getContextPath()+"/cartlist.jsp");
		
	}


	private void updatebuycount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 修改购物车中给定商品的数量
		 */
		//获取Session
		HttpSession session = request.getSession();
		//获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		//获取表单数据
		String id = request.getParameter("id");
		String buycount = request.getParameter("buycount");
		int productid = Integer.parseInt(id);
		
		//调用购物车update方法来修改数量
		cart.updatebuycount(productid, Integer.parseInt(buycount));
		//将购物车放回到Session中
		session.setAttribute("cart", cart);
		//将购买该商品的总价格返回，json {"total":555,id:6}
		double total = ((CartItem)(cart.getMap().get(id))).getTotal();
		String jsonstr = "{\"total\":"+total+"}";
		response.getWriter().print(jsonstr);
		
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 1.获取商品id和数量
		 * 2.根据商品id获取商品对象
		 * 3.构造CartItem
		 * 4.获取购物车
		 * 5.将CartItem添加到购物车中
		 */
		String id = request.getParameter("productid");
		String scount = request.getParameter("count");
		Product product = productService.findById(Integer.parseInt(id));
		CartItem cartItem = new CartItem();
		cartItem.setCount(Integer.parseInt(scount));
		cartItem.setProduct(product);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
		}
		
		cart.addCart(cartItem);
		
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cartlist.jsp");
		
	}

}
