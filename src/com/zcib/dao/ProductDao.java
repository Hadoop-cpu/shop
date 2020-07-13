package com.zcib.dao;

import java.util.List;
import java.util.Map;

import com.zcib.domain.Product;
import com.zcib.utils.JDBCUtils;

public class ProductDao {
	//根据传递的product对象来添加一条记录
	public void add(Product product){
		String sql = "insert into product values(null,?,?,?,?,?,?,?,?,?)";
		java.util.Date date = product.getTime();
		java.sql.Date d = new java.sql.Date(date.getTime());
		Object params[]={
				product.getName(),product.getPrice(),product.getMarkprice(),
				product.getQuality(),product.getHit(),d,
				product.getPhoto(),product.getContent(),product.getCategoryid()
		};
		Number id = (Number)JDBCUtils.insert(sql, params);
		product.setProductid(id.intValue());
	}
	
	//获取表中所有记录个数
	public int count(){
		String sql = "select count(*) from product";
		return ((Number)JDBCUtils.selectScalar(sql)).intValue();
	}

	//获取product表中包含了key的所有记录个数
	public int count(String key,String keytype){
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product AS p,category AS c WHERE p.categoryid=c.categoryid ");
		
		if((keytype!=null)&&(key!=null)&&(!keytype.trim().isEmpty())&&(!key.trim().isEmpty())){
			if("category".equals(keytype))
				keytype = "c.name";
			else if("name".equals(keytype)){
				keytype = "p.name";
			}
			sql.append(" and "+keytype+" like ?");
			//select count(*) from product  where name  like ?
			return ((Number)JDBCUtils.selectScalar(sql.toString(),"%"+key+"%")).intValue();
		}
		return ((Number)JDBCUtils.selectScalar(sql.toString())).intValue();
	}
	
	//读取当前页包含了key的所有记录list，给出起始索引，读取多少条
	public List<Map<String, Object>> findAll(String key ,String keytype,int startIndex, int pageSize) {
		StringBuilder sql = new StringBuilder("SELECT p.name AS pname,p.productid,p.price,p.markprice,p.quality,p.hit,p.time,p.photo,p.categoryid,c.name AS cname FROM category AS c,product AS p  WHERE p.categoryid=c.categoryid ");
		if((keytype!=null)&&(key!=null)&&(!keytype.trim().isEmpty())&&(!key.trim().isEmpty())){
			if("category".equals(keytype))
				keytype = "c.name";
			else if("name".equals(keytype)){
				keytype = "p.name";
			}	
			
			sql.append(" and " + keytype +" like ?");
			sql.append(" limit ?,?");
			//select * from product  where name  like ?
			return JDBCUtils.select(sql.toString(),"%"+key+"%", startIndex,pageSize);
		}
		sql.append(" limit ?,?");
		
		return JDBCUtils.select(sql.toString(), startIndex,pageSize);
	}
	
	public List<Map<String, Object>> findAll(String key ,String keytype,int startIndex, int pageSize,String sort,String sortkey) {
		StringBuilder sql = new StringBuilder("SELECT p.name AS pname,p.productid,p.price,p.markprice,p.quality,p.hit,p.time,p.photo,p.categoryid,c.name AS cname FROM category AS c,product AS p  WHERE p.categoryid=c.categoryid ");
		StringBuilder sql1 = new StringBuilder("");
		if((sortkey!=null)&&(sort!=null)&&(!sortkey.trim().isEmpty())&&(!sort.trim().isEmpty())){
			// ORDER BY TIME ASC 
			sql1.append(" ORDER BY ");
			sql1.append(sortkey);
			sql1.append(" ");
			sql1.append(sort);
		}
		if((keytype!=null)&&(key!=null)&&(!keytype.trim().isEmpty())&&(!key.trim().isEmpty())){
			if("category".equals(keytype))
				keytype = "c.name";
			else if("name".equals(keytype)){
				keytype = "p.name";
			}	
			
			sql.append(" and " + keytype +" like ? ");
			sql.append(sql1.toString());
			
			sql.append(" limit ?,?");
			//select * from product  where name  like ?
			return JDBCUtils.select(sql.toString(),"%"+key+"%", startIndex,pageSize);
		}
		sql.append(sql1.toString());
		sql.append(" limit ?,?");
		
		return JDBCUtils.select(sql.toString(), startIndex,pageSize);
	}
	public List<Map<String, Object>> findAll(int startIndex, int pageSize) {
		String sql = "SELECT p.name as pname,p.productid,p.price,p.markprice,p.quality,p.hit,p.time,p.photo,p.categoryid,c.name AS cname FROM category AS c,product AS p  WHERE p.categoryid=c.categoryid limit ?,?";
		return JDBCUtils.select(sql, startIndex,pageSize);
	}

	public void delete(int productid) {
		String sql = "delete from product where productid = ?";
		JDBCUtils.update(sql, productid);
		
	}
	
	public Product findById(int productid){
		Product product = null;
		String sql = "SELECT p.name AS name,p.productid,p.price,p.markprice,p.quality,p.hit,p.time,p.photo,p.categoryid,p.content ,c.name AS cname FROM category AS c,product AS p  WHERE p.categoryid=c.categoryid and productid=?";
		product = JDBCUtils.selectToBean(Product.class, sql, productid);
//		List<Map<String,Object>> list = JDBCUtils.select(sql, productid);
//		if(list.size()>0){
//			product = new Product();
//			Map<String,Object> map = list.get(0);
//			product.setProductid(productid);
//			product.setCategoryid(Integer.parseInt((map.get("categoryid").toString())));
//			product.setContent(map.get("content").toString());
//			product.setHit(Integer.parseInt((map.get("hit").toString())));
//			product.setMarkprice(Float.parseFloat(map.get("markprice").toString()));
//			product.setName(map.get("pname").toString());
//			product.setPhoto(map.get("photo").toString());
//			product.setPrice(Float.parseFloat(map.get("price").toString()));
//			product.setQuality(Integer.parseInt((map.get("quality").toString())));
//			product.setTime(Date.valueOf((map.get("time").toString())));
//			product.setCname(map.get("cname").toString());
//		}
		return product;
	}

	public void update(Product product) {
		String sql = "UPDATE product SET NAME=?,price=?,markprice=?,quality=?,hit=?,TIME=?, photo=?,content=?,categoryid=? WHERE productid=?";
		Object params[]={
			product.getName(),product.getPrice(),product.getMarkprice(),
			product.getQuality(),product.getHit(),new java.sql.Date(product.getTime().getTime()),
			product.getPhoto(),product.getContent(),product.getCategoryid(),
			product.getProductid(),
			};
		JDBCUtils.update(sql, params);
		
	}
}
