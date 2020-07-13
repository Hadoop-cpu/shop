package com.zcib.service;

import java.sql.SQLException;
import java.util.List;

import com.zcib.dao.OrderDao;
import com.zcib.domain.Order;
import com.zcib.utils.JDBCUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void add(Order order){
		/*
		 * 把订单写到数据库
		 * */
		try{
			//开启事务
			JDBCUtils.beginTransaction();
			orderDao.add(order);//写订单
			orderDao.add(order.getOrderItems());//写订单列表项
			//提交事务
			JDBCUtils.CommitTransaction();
		}catch(Exception e){
			//回滚事务
			try{
				JDBCUtils.roolbackTransaction();
			}catch(SQLException e1){
				throw new RuntimeException(e);
			}
			
		}
	}

	public List<Order> findAll(int vipid) {
		
		return orderDao.findByVipId(vipid);
	}

	public Order findById(String orderid, int vipid) {
		
		return orderDao.findById(orderid, vipid);
	}

	public void pay(String orderid, int vipid) {
		orderDao.pay(orderid,vipid);
		
	}

	

}
