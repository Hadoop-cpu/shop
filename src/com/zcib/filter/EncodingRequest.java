package com.zcib.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper {
	
	private HttpServletRequest req;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		req = request;
	}
	
	@Override
	public String getParameter(String name) {
		/*
		 * 1.获取参数值
		 * 2.重新用utf-8进行编码
		 * */
		String value = req.getParameter(name);
		try {
			if(value != null)
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.getParameter(name);
	}

}
