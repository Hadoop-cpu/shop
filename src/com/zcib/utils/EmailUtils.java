package com.zcib.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {
	
	public boolean sendMail(String name,String password,String host,String to,String From,String subject,String encode){
		try {
			HtmlEmail email = new HtmlEmail();
			//设置发邮件的用户名和密码（授权密码）
			email.setAuthentication("15194682162", "qq199610");
			//设置发送邮件服务器（SMTP服务器）域名
			email.setHostName("smtp.139.com");
			//收件人的邮箱
			email.addTo("979044451@qq.com");
			//发件人邮箱
			email.setFrom("15194682162@139.com");
			//邮件的主题
			email.setSubject("这是一个用于测试的邮件22！");
			//设置编码方式，防止中文乱码
			email.setCharset("GB2312");
			//邮件内容
			email.setHtmlMsg("测试邮件内容22！");
			
			//发送邮件
			email.send();
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

}
