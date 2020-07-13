package com.zcib.service;

import java.util.List;
import java.util.Map;

import com.zcib.dao.ProductDao;
import com.zcib.domain.Page;
import com.zcib.domain.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	//添加一条商品记录
	public void add(Product product){
		productDao.add(product);
	}
	
	//重载
	public Page findAll(int currentPage,String key,String keytype) {
		/*
		 * 0.获取product表中包含了key的所有记录个数
		 * 1.创建Page对象
		 * 2.读取当前页的所有记录list，给出起始索引，读取多少条
		 * 3.把list存入Page对象中
		 * 4.返回Page对象
		 */
		//0.获取product表中包含了key的所有记录个数
		int count = productDao.count(key,keytype);
		// 1.创建Page对象
		Page page = new Page(currentPage,count);
		//2.读取当前页包含了key的所有记录list，给出起始索引，读取多少条
		List<Map<String,Object>> list = productDao.findAll(key,keytype,page.getStartIndex(),page.getPageSize());
		//3.把list存入Page对象中
		page.setList(list);
		//4.返回Page对象
		return page;
	}
	
	//重载
		public Page findAll(int currentPage,String key,String keytype,String sort,String sortkey) {
			/*
			 * 0.获取product表中包含了key的所有记录个数
			 * 1.创建Page对象
			 * 2.读取当前页的所有记录list，给出起始索引，读取多少条
			 * 3.把list存入Page对象中
			 * 4.返回Page对象
			 */
			//0.获取product表中包含了key的所有记录个数
			int count = productDao.count(key,keytype);
			// 1.创建Page对象
			Page page = new Page(currentPage,count);
			//2.读取当前页包含了key的所有记录list，给出起始索引，读取多少条
			List<Map<String,Object>> list = productDao.findAll(key,keytype,page.getStartIndex(),page.getPageSize(),sort,sortkey);
			//3.把list存入Page对象中
			page.setList(list);
			//4.返回Page对象
			return page;
		}
	public Page findAll(int currentPage) {
		/*
		 * 0.获取product表中所有记录个数
		 * 1.创建Page对象
		 * 2.读取当前页的所有记录list，给出起始索引，读取多少条
		 * 3.把list存入Page对象中
		 * 4.返回Page对象
		 */
		//0.获取product表中所有记录个数
		int count = productDao.count();
		// 1.创建Page对象
		Page page = new Page(currentPage,count);
		//2.读取当前页的所有记录list，给出起始索引，读取多少条
		List<Map<String,Object>> list = productDao.findAll(page.getStartIndex(),page.getPageSize());
		//3.把list存入Page对象中
		page.setList(list);
		//4.返回Page对象
		return page;
	}
	public void delete(int productid) {
		productDao.delete(productid);
		
	}
	//根据id读取该商品
	public Product findById(int productid){
		return productDao.findById(productid);
	}
	
	//根据id进行修改
	public void update(Product product){
		productDao.update(product);
	}
	
	//读取要显示在首页的数据
	public List<Map<String,Object>> findIndex(){
		//调用dao来读取，从0开始的12条记录
		return productDao.findAll(0, 12);
	}
	
}
