package com.zcib.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.zcib.service.PCAService;

public class PCAServlet extends HttpServlet {
	private PCAService pcaService = new PCAService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//防止中文乱码
		request.setCharacterEncoding("utf-8");
		//防止输出有中文乱码
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		if("getprovinces".equals(action)){
			getprovinces(request,response);
		}else if("getcities".equals(action)){
			getcities(request,response);
		}else if("getareas".equals(action)){
			getareas(request,response);
		}
		
	}


	private void getareas(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 1.获取市id
		 * 2.通过市id调用Service来获取区列表
		 * 3.转换成JSONArray
		 * 4.输出
		 */
		//1.获取市id
		String cityid = request.getParameter("cityid");
		//2.通过省份id调用Service来获取市列表
		List<Map<String,Object>> list = pcaService.getareas(cityid);
		//3.转换成JSONArray
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		//4.输出
		response.getWriter().print(jsonArray.toString());
		
	}


	private void getcities(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 1.获取省份id
		 * 2.通过省份id调用Service来获取市列表
		 * 3.转换成JSONArray
		 * 4.输出
		 */
		//1.获取省份id
		String provinceid = request.getParameter("provinceid");
		//2.通过省份id调用Service来获取市列表
		List<Map<String,Object>> list = pcaService.getCities(provinceid);
		//3.转换成JSONArray
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		//4.输出
		response.getWriter().print(jsonArray.toString());
		
	}


	private void getprovinces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 1.调用Service来获取省份列表
		 * 2.将列表转换成JSON
		 * 3.输出
		 */
		//1.调用Service来获取省份列表
		List<Map<String,Object>> list = pcaService.getprovinces();
		//2.将列表转换成JSON
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		//3.输出
		response.getWriter().print(jsonArray.toString());
		
	}

}
