package com.zcib.test;

import com.zcib.dao.ProductDao;
import com.zcib.service.ProductService;

public class TestProduct {

	
	public static void main(String[] args) {
		ProductService productService = new ProductService();
		System.out.println(productService.findAll(1,"大","c.name"));
//		System.out.println(productService.findAll(2));
		ProductDao productDao = new ProductDao();
//		System.out.println(productDao.findById(6));
//		List<Map<String,Object>> list = JDBCUtils.select("SELECT productid FROM product WHERE NAME LIKE ?", "%子%");
//		System.out.println(list);
		
		
	}

}
