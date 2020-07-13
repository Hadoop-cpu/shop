package com.zcib.service;

import com.zcib.dao.UserDao;
import com.zcib.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	//注册成功返回true，失败返回false
	public boolean regist(User user){
		//调用Dao的add方法来进行注册
		userDao.add(user);
		return (user.getVipid()>0);
	}
	
	public User login(User user){
		/*
		 * 1.调用Dao通过用户名来查询该用户，保存到User中，有，存入User，没有，null
		 * 2.通过判断User对象，为null，没有该用户，返回false
		 * 3.User存在，比较密码是否一致，一致，则登录成功，返回true
		 * 4.User存在，比较密码是否一致，不一致，密码错误，返回false
		 */
		//1.调用Dao通过用户名来查询该用户，保存到User中，有，存入User，没有，null
		User user1 = userDao.findByName(user.getUsername());
		//3.User存在，比较密码是否一致，一致，则登录成功，返回true
		if(user1!=null&&user.getPassword().equals(user1.getPassword())){
			return user1;
		}
		return null;
	}

	public boolean validateName(String username) {
		/*
		 * 1.通过传递username给Dao的findByName，获取User对象
		 * 2.判断User对象中是否为空null
		 * 3.如果为空，返回false
		 * 4.如果不为空，返回true
		 */
		//1.通过传递username给Dao的findByName，获取User对象
		User user = userDao.findByName(username);
		//2.判断User对象中是否为空null
		if (user == null) {
			return false;// 3.如果为空，返回false
		}
		return true;//4.如果不为空，返回true
	}

	public void password(User user) {
		userDao.password(user);
		
	}

}
