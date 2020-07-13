package com.zcib.test;

import com.zcib.dao.OrderDao;
import com.zcib.domain.Order;

public class TestOrder {
	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();
		Order order = orderDao.findById("226787d9455641c7bc2a8aa5c1c80400",3);
		System.out.println(order);
//		System.out.println(orderDao.findByVipId(2));
	}

}
