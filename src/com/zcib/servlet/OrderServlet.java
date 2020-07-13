package com.zcib.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcib.dao.OrderDao;
import com.zcib.domain.Address;
import com.zcib.domain.Cart;
import com.zcib.domain.CartItem;
import com.zcib.domain.Order;
import com.zcib.domain.OrderItem;
import com.zcib.domain.Product;
import com.zcib.domain.User;
import com.zcib.service.AddressService;
import com.zcib.service.OrderService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
	private AddressService addressService = new AddressService();
	private OrderService orderService = new OrderService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("add".equals(action)){
			add(request,response);
		}else if("findAll".equals(action)){
			findAll(request,response);
		}else if("findById".equals(action)){
			findById(request,response);
		}else if("paying".equals(action)){
			paying(request,response);
		}else if("pay".equals(action)){
			pay(request,response);
		}
	}

	
	private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 通过订单号，支付并跳转成功界面
		 * 0.判断用户是否登录，没有登录，转向登录页面
		 * 1.获取订单id
		 * 2.通过Service中的findById方法获取订单对象
		 * 3.把读出来的Order对象存入request中
		 * 4.重定向到订单详情paying.jsp页面
		 * */
		
		 //0.判断用户是否登录，没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		 //1.获取订单id
		String orderid = (String) request.getParameter("id");
		int vipid = user.getVipid();
		 //2.通过Service中的findById方法获取订单对象
		
		Order order = orderService.findById(orderid,vipid);
		int status = order.getStatus();
		if(status==0){
			orderService.pay(orderid,vipid);
		}
		System.out.println(order);
		 //3.把读出来的Order对象存入request中
		request.setAttribute("order", order);
		 //4.重定向到订单详情orderinfo.jsp页面
		request.getRequestDispatcher("/home/paysuccess.jsp").forward(request, response);
		
	}

	private void paying(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 通过订单号，跳转支付页面
		 * 0.判断用户是否登录，没有登录，转向登录页面
		 * 1.获取订单id
		 * 2.通过Service中的findById方法获取订单对象
		 * 3.把读出来的Order对象存入request中
		 * 4.重定向到订单详情paying.jsp页面
		 * */
		
		 //0.判断用户是否登录，没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		 //1.获取订单id
		String orderid = (String) request.getParameter("id");
		int vipid = user.getVipid();
		 //2.通过Service中的findById方法获取订单对象
		Order order = orderService.findById(orderid,vipid);
		System.out.println(order);
		 //3.把读出来的Order对象存入request中
		request.setAttribute("order", order);
		 //4.重定向到订单详情orderinfo.jsp页面
		request.getRequestDispatcher("/home/paying.jsp").forward(request, response);
		
	}

	private void findById(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 通过订单号，读取该订单
		 * 0.判断用户是否登录，没有登录，转向登录页面
		 * 1.获取订单id
		 * 2.通过Service中的findById方法获取订单对象
		 * 3.把读出来的Order对象存入request中
		 * 4.重定向到订单详情orderinfo.jsp页面
		 * */
		
		 //0.判断用户是否登录，没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		 //1.获取订单id
		String orderid = (String) request.getParameter("id");
		int vipid = user.getVipid();
		 //2.通过Service中的findById方法获取订单对象
		Order order = orderService.findById(orderid,vipid);
		System.out.println(order);
		 //3.把读出来的Order对象存入request中
		request.setAttribute("order", order);
		 //4.重定向到订单详情orderinfo.jsp页面
		request.getRequestDispatcher("/person/orderinfo.jsp").forward(request, response);
		
	}

	
	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 读取该用户的所有订单
		 * 0.判断用户是否登录，没有登录，转向登录页面
		 * 1.获取用户的vipid
		 * 2.通过Service中的findAll方法来查询所有订单
		 * 3.把订单列表存到request中
		 * 4.重定向到order.jsp页面
		 * */
		 //0.判断用户是否登录，没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		 //1.获取用户的vipid
		int vipid = user.getVipid();
		 //2.通过Service中的findAll方法来查询所有订单
		List<Order> orderlist = orderService.findAll(vipid);
		System.out.println(orderlist);
		 //3.把订单列表存到request中
		request.setAttribute("orderlist", orderlist);
		 //4.重定向到order.jsp页面
		request.getRequestDispatcher("/person/order.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取表单
		 *  1.1 获取地址id
		 *  1.2 获取备注
		 * 2.创建Order对象
		 * 3.调用Service中的add方法，把订单写到数据库中
		 * 4.从购物车中删除已购买的商品
		 * 5.清空session中的写入的数据，cartlist，total
		 * 6.把购物车重新放入session
		 * 7.转到订单的支付页面paying.jsp
		 * */
		HttpSession session = request.getSession();
		//1.1 获取地址id
		String addressid = request.getParameter("addressid");
		int id = Integer.parseInt(addressid);
		//1.2 获取备注
		String content = request.getParameter("content");
		//2.创建Order对象
		Order order = new Order();
		Address address = addressService.findById(id);
		StringBuilder addressname = new StringBuilder(address.getProvince());
		String city = address.getCity();
		if(!("市辖区".equals(city)||"县".equals(city))){
			addressname.append(" "+city);
		}
		String area = address.getArea();
		if(!("市辖区".equals(area))){
			addressname.append(" "+area);
		}
		addressname.append(" "+address.getAddressname());
		
		System.out.println(addressname);
		address.setAddressname(addressname+"");
		order.setAddress(address);
		order.setContent(content);
		String orderid = UUID.randomUUID().toString().replace("-", "");
		order.setOrderid(orderid);//订单编号
		order.setOrdertime(new Date());
		order.setStatus(0);//0表示订单未付款
		order.setTotalprice((Float) session.getAttribute("total"));
		order.setUser((User) session.getAttribute("user"));
		
		//订单条目项列表
		List<CartItem> cartlist = (List<CartItem>) session.getAttribute("cartlist");
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(int i =0;i<cartlist.size();i++){
			OrderItem orderItem = new OrderItem();
			CartItem cartItem = cartlist.get(i);
			orderItem.setBuycount(cartItem.getCount());//购买该商品数量
			Product product = cartItem.getProduct();
			orderItem.setProduct(product);//购买商品
			orderItem.setTotal(cartItem.getTotal());
			orderItem.setOrder(order);//所属订单
			orderItems.add(orderItem);//把订单条目项加到列表中
		}
		order.setOrderItems(orderItems);
				
		//3.调用Service中的add方法，把订单写到数据库中
		orderService.add(order);
		
		//4.从购物车中删除已购买的商品
		Cart cart = (Cart) session.getAttribute("cart");//取出购物车
		cart.delete(cartlist);//删除已购买的商品
		
		//5.清空session中的写入的数据，cartlist，total
		session.removeAttribute("cartlist");
		session.removeAttribute("total");
		
		//6.把购物车重新放入session
		session.setAttribute("cart", cart);

		//7.转到订单的支付页面paying.jsp
		request.setAttribute("msg", "<script>window.location.href='home/paying.jsp';</script>");
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
		
		
	}

}
