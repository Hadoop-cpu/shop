package com.zcib.dao;

import java.util.List;
import java.util.Map;

import com.zcib.domain.Category;
import com.zcib.utils.JDBCUtils;

public class CategoryDao {
	//根据id读取记录
	public Category findById(int id){
		String sql = "select * from category where categoryid=?";
		Category category = JDBCUtils.selectToBean(Category.class, sql, id);
//		List<Map<String,Object>> list = JDBCUtils.select(sql, id);
//		Category category = null;
//		if(list.size()>0){
//			Map<String,Object> map = list.get(0);
//			category = new Category();
//			category.setCategoryid(id);
//			category.setName(map.get("name").toString());
//			category.setSort(Integer.parseInt(map.get("sort").toString()));		
//		}
		return category;
	}
	
	//获取所有记录个数
	public int findCount(){
		String sql = "select count(*) from category";
		Number count  = (Number)JDBCUtils.selectScalar(sql);
		return count.intValue();
	}
	
	//获取给定起始索引和个数的记录集合

		public List<Map<String,Object>> findAll(int startIndex,int size){
			/*
			 * 1.读取数据库，获取所有记录
			 * 2.把数据返回
			 */
			String sql = "select * from category limit ?,?";
			return JDBCUtils.select(sql,startIndex,size);
		}
	
	//获取分类列表
	public List<Map<String,Object>> findAll(){
		/*
		 * 1.读取数据库，获取所有记录
		 * 2.把数据返回
		 */
		String sql = "select * from category";
		return JDBCUtils.select(sql);
	}

	    //访问数据库，添加分类
		public void add(Category category){
			String sql = "insert into category values(null,?,?)";
			Object params[]={
					category.getName(),
					category.getSort()
			};
			JDBCUtils.insert(sql, params);
		}
		
		//根据name来读取一条记录
		public Category findByName(String name){
			Category category = null;
			String sql = "select * from category where name=?";
			category = JDBCUtils.selectToBean(Category.class, sql, name);
//			List<Map<String, Object>> list = JDBCUtils.select(sql, name);
//			if(list.size()>0){
//				//有记录
//				Map<String,Object> map = list.get(0);
//				category = new Category();
//				category.setName(name);
//				category.setCategoryid(Integer.parseInt(map.get("categoryid").toString()));
//				category.setSort(Integer.parseInt(map.get("sort").toString()));
//			}
			return category;
		}
		
		public void delete(int categoryid){
			String sql = "delete from category where categoryid=?";
			JDBCUtils.update(sql, categoryid);
		}

		//批量删除
		public void deleteMore(String[] ids) {
			//DELETE FROM category WHERE categoryid IN (21,22,23,24)
			String sql = "DELETE FROM category WHERE categoryid IN (";
			StringBuilder str = new StringBuilder("");
			//循环取出ids中要删除的id，构造sql字符串
			for(int i=1;i<=ids.length;i++){
				//DELETE FROM category WHERE categoryid IN (?,?,?,?,?)
				//要构造这个?,?,?,?,?)
				if(i == ids.length){
					//最后一个参数
					str.append("?)");
				}else{
					str.append("?,");
				}
			}
			JDBCUtils.update(sql+str.toString(), ids);
			
		}

		public void update(Category category) {
			/*
			 * 1.构造sql
			 * 2.构造参数
			 * 3.调用JDBCUtils进行修改
			 */
			String sql = "update category set name=?,sort=? where categoryid=?";
			Object params[]={
				category.getName(),
				category.getSort(),
				category.getCategoryid()
			};
			JDBCUtils.update(sql, params);
			
		}
}
