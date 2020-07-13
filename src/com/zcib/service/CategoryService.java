package com.zcib.service;

import java.util.List;
import java.util.Map;

import com.zcib.dao.CategoryDao;
import com.zcib.domain.Category;
import com.zcib.domain.Page;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	//添加分类
	public void add(Category category){
		//调用Dao层的add方法来添加分类
		categoryDao.add(category);
	}
	
	//根据id读取该条记录
	public Category findById(int id){
		//调用Dao层来读取记录
		return categoryDao.findById(id);
	}

	
	//批量删除
	public void deleteMore(String ids[]){
		categoryDao.deleteMore(ids);
	}
	
	//如果name重复，则返回true，否则返回false
	public boolean validateName(String name){
		/*
		 * 1.通过传递name给Dao的findByName，获取Category对象
		 * 2.判断Category对象中是否为空null
		 * 3.如果为空，返回false
		 * 4.如果不为空，返回true
		 */
		//1.通过传递name给Dao的findByName，获取Category对象
		Category category = categoryDao.findByName(name);
		//2.判断Category对象中是否为空null
		if(category == null){
			return false;//3.如果为空，返回false
		}
		return true;//4.如果不为空，返回true
		
	}
	
	public List<Map<String,Object>> findAll(){
		/*
		 * 1.调用Dao获取数据，并返回
		 */
		return categoryDao.findAll();
	}
	
	public Page findAll(int currentPage){
		/*
		 * 1、获取totalSize,总记录数（读数据库）
		 * 2、创建Page对象
		 * 3、从数据库中获取List，读取的起始索引值，由Page对象获取
		 * 4、将获取的list赋值给Page对象
		 * 5、返回Page对象
		 */
		//1、获取totalSize,总记录数（读数据库）
		int totalSize = categoryDao.findCount();
		//2、创建Page对象
		Page  page = new Page(currentPage,totalSize);
		// 3、从数据库中获取List，读取的起始索引值，由Page对象获取
		List<Map<String,Object>> list = categoryDao.findAll(page.getStartIndex(), page.getPageSize());
		//4、将获取的list赋值给Page对象
		page.setList(list);
		return page;
	}
	public void delete(int categoryid){
		//调用Dao方法进行删除
		categoryDao.delete(categoryid);
	}

	public void update(Category category) {
		//调用Dao方法进行修改
		categoryDao.update(category);
		
	}
	
	
}
