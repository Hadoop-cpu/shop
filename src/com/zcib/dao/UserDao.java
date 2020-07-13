package com.zcib.dao;

import com.zcib.domain.User;
import com.zcib.utils.JDBCUtils;

public class UserDao {
	//增加一条记录
	public void add(User user){
		String sql = "insert into users(username,password,email,lastlogintime,enable) values(?,?,?,?,?)";
		Object params[]={
				user.getUsername(),user.getPassword(),
				user.getEmail(),new java.sql.Date(user.getLastlogintime().getTime()),
				user.getEnable()
		};
		Number id = (Number)JDBCUtils.insert(sql, params);
		user.setVipid(id.intValue());
	}
	
	public User findByName(String username){
		String sql = "select * from users where username=?";
		User user = JDBCUtils.selectToBean(User.class, sql, username);
//		List<Map<String,Object>> list = JDBCUtils.select(sql, username);
//		User user = null;
//		if(list.size()>0){
//			user = new User();
//			Map<String,Object> map = list.get(0);
//			user.setVipid(Integer.parseInt(map.get("vipid").toString()));
//			user.setUsername(username);
//			user.setSex((String)map.get("sex"));
//			user.setQuestion((String)(map.get("question")));
//			user.setPhoto((String)(map.get("photo")));
//			user.setPassword((String)(map.get("password")));
//			user.setLastlogintime((java.util.Date)(map.get("lastlogintime")));
//			user.setEnable(Integer.parseInt(map.get("enable").toString()));
//			user.setEmail((String)(map.get("email")));
//			user.setAnswer((String)(map.get("answer")));
//		}
		return user;
	}

	public void password(User user) {
		String sql = "update users set password=? where vipid=?";
		Object params[]={
				user.getPassword(),user.getVipid()
		};
		JDBCUtils.update(sql, params);
		
	}

}
