package com.zcib.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcib.domain.Address;
import com.zcib.domain.Cart;
import com.zcib.domain.CartItem;
import com.zcib.domain.User;
import com.zcib.service.AddressService;

public class AddressServlet extends HttpServlet {
	private AddressService addressService = new AddressService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get请求");
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//只能处理post请求中的乱码
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if("add".equals(action)){
			add(request,response);
		}else if("findAll".equals(action)){
			findAll(request,response);
		}else if("paybefore".equals(action)){
			payBefore(request,response);
		}else if("delete".equals(action)){
			delete(request,response);
		}else if("updatebefore".equals(action)){
			updatebefore(request,response);
		}else if("update".equals(action)){
			update(request,response);
		}else if("findAllPerson".equals(action)){
			findAllPerson(request,response);
		}
		
	}


	private void findAllPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.在Session中取出user,判断用户是否登录，未登录，跳转到登录页面
		 * 2.登录
		 * 3.获取vipid
		 * 4.根据vipid来获取该用户的所有地址信息
		 * 5.将信息存入request中
		 * 6.请求转发到cartlist.jsp
		 * */
		//1.判断用户是否登录，未登录，跳转到登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
//		if(user == null){
//			response.sendRedirect(request.getContextPath()+"/login.jsp");
//			return;
//		}
		//2.登录 3.获取vipid
		int vipid = user.getVipid();
		//4.根据vipid来获取该用户的所有地址信息
		List<Map<String,Object>> list = addressService.findAll(vipid);
		//5.将信息存入request中
		request.setAttribute("addresslist", list);
		//6.请求转发到cartlist.jsp
		request.getRequestDispatcher("/person/address.jsp").forward(request, response);
		
	}
	

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 
		 * 修改地址后，返回pay.jsp页面
		 * 1.获取表单数据
		 * 2.封装到Address对象中
		 * 3.调用Service中的update方法来进行修改
		 * 4.调用findAll方法
		 * */
		//1.获取表单数据
		String addressid = request.getParameter("addressid");
		int id = Integer.parseInt(addressid);
		String receiver = request.getParameter("receiver");
		String phone = request.getParameter("phone");
		String postcode = request.getParameter("postcode");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String addressname = request.getParameter("addressname");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//验证表单
		//2.封装到Address对象中
		Address address = new Address();
		address.setAddressid(id);
		address.setAddressname(addressname);
		address.setPhone(phone);
		address.setPostcode(postcode);
		
		address.setProvince(province);
		address.setCity(city);
		address.setArea(area);
		address.setReceiver(receiver);
		address.setVipid(user.getVipid());
		System.out.println(address);
		//3.调用Service中的update方法来进行修改
		addressService.update(address);
		//4.调用findAll方法
		findAll(request, response);
	}



	private void updatebefore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 修改前，需要读出数据，发给修改页面
		 * 1.获取要修改的id
		 * 2.根据id调用Service读取该地址对象
		 * 3.将地址对象存入request
		 * 4.转发给updateAddress.jsp页面
		 * */
		//1.获取要修改的id
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		//2.根据id调用Service读取该地址对象
		Address address = addressService.findById(id);
		//3.将地址对象存入request
		request.setAttribute("address", address);
		//4.转发给updateAddress.jsp页面
		request.getRequestDispatcher("/home/updateAddress.jsp").forward(request, response);
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 1.获取表单数据，要删除的id
		 * 2.调用Service中的delete方法删除
		 * 3.重新读取地址列表，返回pay.jsp（findAll）
		 * */
		//1.获取表单数据，要删除的id
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		//2.调用Service中的delete方法删除
		addressService.delete(id);
		//3.重新读取地址列表，返回pay.jsp（findAll）
		findAll(request, response);
	}


	private void payBefore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取要购买的商品id，多个
		 * 2.通过这些id，从购物车取出对应的商品，封装到一个list里
		 * 3.获取用户的id
		 * 4.通过id获取该用户的地址列表
		 * 5.将商品list存入session，将用户地址列表存入request
		 * 6.请求转发到pay.jsp页面
		 * */
		//1.获取要购买的商品id，多个
		String ids[] = request.getParameterValues("sel");
		//2.通过这些id，从购物车取出对应的商品，封装到一个list里
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> cartlist = cart.getCartItems(ids);
		//3.获取用户的id
		User user = (User) session.getAttribute("user");
		int vipid = user.getVipid();
		//4.通过id获取该用户的地址列表
		List<Map<String,Object>> addresslist = addressService.findAll(vipid);
		//5.将商品list存入session，将用户地址列表存入request
		session.setAttribute("cartlist", cartlist);
		session.setAttribute("total", cart.getCartItemsTotal(ids));
		request.setAttribute("addresslist", addresslist);
		//6.请求转发到pay.jsp页面
		request.getRequestDispatcher("/home/pay.jsp").forward(request, response);
		
		
	}


	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 1.在Session中取出user,判断用户是否登录，未登录，跳转到登录页面
		 * 2.登录
		 * 3.获取vipid
		 * 4.根据vipid来获取该用户的所有地址信息
		 * 5.将信息存入request中
		 * 6.请求转发到cartlist.jsp
		 * */
		//1.判断用户是否登录，未登录，跳转到登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
//		if(user == null){
//			response.sendRedirect(request.getContextPath()+"/login.jsp");
//			return;
//		}
		//2.登录 3.获取vipid
		int vipid = user.getVipid();
		//4.根据vipid来获取该用户的所有地址信息
		List<Map<String,Object>> list = addressService.findAll(vipid);
		//5.将信息存入request中
		request.setAttribute("addresslist", list);
		//6.请求转发到cartlist.jsp

		request.getRequestDispatcher("/home/pay.jsp").forward(request, response);
		
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//判断是否登录
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
//		//没有登录，返回到登录页面
//		if(user==null){
//			response.sendRedirect(request.getContextPath()+"/login.jsp");
//			return;
//		}
		//登录了继续
		/*
		 * 1.获取表单数据，验证
		 * 2.封装到Address对象中
		 * 3.调用service中的方法进行添加
		 * 4.返回到pay.jsp，进行显示（明天做）
		 */
		//1.获取表单数据，验证
		String receiver  = request.getParameter("receiver");
		String phone = request.getParameter("phone");
		String postcode = request.getParameter("postcode");
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String addressname = request.getParameter("addressname");
//		StringBuilder str = new StringBuilder(province);
		//2.封装到Address对象中
		int vipid = user.getVipid();//登录的用户id
		
//		if(!("市辖区".equals(city.trim()))&&!("县".equals(city.trim()))){
//			str.append(city);
//		}
//		
//		if(!("市辖区".equals(area.trim()))){
//			str.append(area);
//		}
//		str.append(addressname);
		
		Address address = new Address();
		address.setAddressname(addressname);
		address.setPhone(phone);
		address.setPostcode(postcode);
		address.setReceiver(receiver);
		address.setVipid(vipid);
		address.setProvince(province);
		address.setCity(city);
		address.setArea(area);
		//3.调用service中的方法进行添加
		addressService.add(address);
		
		//4.返回到pay.jsp，进行显示（明天做）
		request.setAttribute("msg", "<script>alert('添加地址成功!');window.location.href='addressServlet?action=findAll';</script>");
		request.getRequestDispatcher("/admin/msg.jsp").forward(request, response);
		
		
	}

}
