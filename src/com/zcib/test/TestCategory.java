package com.zcib.test;

import com.zcib.dao.CategoryDao;
import com.zcib.domain.Category;
import com.zcib.service.CategoryService;

public class TestCategory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		CategoryService categoryService = new CategoryService();
//		Category category = new Category();
//		category.setName("电脑1");
//		category.setSort(1);
//		categoryService.add(category);
		
		CategoryDao categoryDao = new CategoryDao();
		System.out.println(categoryDao.findByName("手机"));
//		System.out.println(categoryDao.findAll());
//		System.out.println(categoryDao.findById(18));
		java.util.Date date = new java.util.Date();
		java.sql.Date d = new java.sql.Date(date.getTime());
	}

}
