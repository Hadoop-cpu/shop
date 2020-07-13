package com.zcib.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zcib.domain.Address;
import com.zcib.domain.Order;
import com.zcib.domain.OrderItem;
import com.zcib.domain.Product;
import com.zcib.domain.User;
import com.zcib.utils.BeanUtils;
import com.zcib.utils.JDBCUtils;

public class OrderDao {
	
	//向数据库中插入订单
	public void add(Order order){
		String sql = "insert into orders values(?,?,?,?,?,?,?,?,?,?)";
		java.sql.Timestamp time = new java.sql.Timestamp(order.getOrdertime().getTime());
		
		Object params[]={
			order.getOrderid(), time,
			order.getStatus(), order.getAddress().getAddressname(),
			order.getAddress().getPostcode(), order.getAddress().getReceiver(),
			order.getAddress().getPhone(), order.getTotalprice(),
			order.getContent(), order.getUser().getVipid()
			
		};
		JDBCUtils.insert(sql, params);
	}
	
	//向数据库中插入订单条目列表
	public void add(List<OrderItem> items){
		String sql = "insert into orderItem values(null,?,?,?,?,?,?,?)";
		Object params[][] = new Object[items.size()][];
		for(int i=0;i<items.size();i++){
			OrderItem item = items.get(i);
			params[i] = new Object[]{
					item.getBuycount(), item.getTotal(),
					item.getProduct().getProductid(), item.getProduct().getName(),
					item.getProduct().getPrice(), item.getProduct().getPhoto(),
					item.getOrder().getOrderid()
			};
		}
		
		List<Number> keys = JDBCUtils.insertbatch(sql, params);
		for(int i=0;i<keys.size();i++){
			OrderItem item = items.get(i);
			item.setId(keys.get(i).intValue());
		}
				
		
	}
	
	//通过订单id查找订单
	public Order findById(String orderid, int vipid){
		String sql = "select * from orders where orderid=? and vipid=?";
		Map<String,Object> map = (JDBCUtils.select(sql, orderid, vipid)).get(0);
		Order order = null;
		try {
			order = BeanUtils.toBean(map, Order.class);
			Address address = BeanUtils.toBean(map, Address.class);
			order.setAddress(address);
			User user = BeanUtils.toBean(map, User.class);
			order.setUser(user);
			
			//获取订单条目项
			sql = "select * from orderitem where orderid=?";
			List<Map<String,Object>> list = JDBCUtils.select(sql, orderid);
			List<OrderItem> orderitemlist = new ArrayList<OrderItem>();
			for(int i=0;i<list.size();i++){
				Map<String,Object> item = list.get(i);//读出第i条
				OrderItem orderitem = BeanUtils.toBean(item, OrderItem.class);
				Product product = BeanUtils.toBean(item, Product.class);
				orderitem.setProduct(product);
				orderitem.setOrder(order);
				orderitemlist.add(orderitem);
			}
			order.setOrderItems(orderitemlist);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return order;
	}
	
	//通过用户的vipid来查询所有订单
	public List<Order> findByVipId(int vipid){
		String sql = "select * from orders where vipid=?";
		List<Map<String,Object>> olist = JDBCUtils.select(sql, vipid);
		List<Order> orderlist = new ArrayList<Order>();
		for(int j=0;j<olist.size();j++){
			Map<String,Object> map = olist.get(j);
			Order order = null;
			try {
				order = BeanUtils.toBean(map, Order.class);
				Address address = BeanUtils.toBean(map, Address.class);
				order.setAddress(address);
				User user = BeanUtils.toBean(map, User.class);
				order.setUser(user);
				
				//获取订单条目项
				sql = "select * from orderitem where orderid=?";
				List<Map<String,Object>> list = JDBCUtils.select(sql, order.getOrderid());
				List<OrderItem> orderitemlist = new ArrayList<OrderItem>();
				for(int i=0;i<list.size();i++){
					Map<String,Object> item = list.get(i);//读出第i条
					OrderItem orderitem = BeanUtils.toBean(item, OrderItem.class);
					Product product = BeanUtils.toBean(item, Product.class);
					orderitem.setProduct(product);
					orderitem.setOrder(order);
					orderitemlist.add(orderitem);
				}
				order.setOrderItems(orderitemlist);
				orderlist.add(order);
			} catch (Exception e) {
				throw new RuntimeException();
			}
			
		}
		
		return orderlist;
	}

	public void pay(String orderid, int vipid) {
		String sql = "update orders set status=1 where orderid=? and vipid=?";
		JDBCUtils.update(sql, orderid, vipid);
		
	}
	

}
