package com.zcib.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageUploadServlet extends HttpServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//只能防止文件名中文乱码
		String uploadpath = "/uploadimages/";
		String path = upload(uploadpath,request,response);
		request.setAttribute("path",path);
		request.getRequestDispatcher("/admin/upload.jsp").forward(request, response);
	}
	
	public String upload(String uploadpath,HttpServletRequest request, HttpServletResponse response){
		//但是在文件上传中，对普通的表单项，不起作用，必须在获取的时候进行编码
				/*
				 * 上传文件，返回路径
				 * 1.创建工厂对象
				 * 2.使用工厂对象创建解析器
				 * 3.使用解析器解析request，获得表单项
				 * 4.根据文件表单项，上传文件
				 */
				//1.创建工厂对象
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//2.使用工厂对象创建解析器
				ServletFileUpload sfu = new ServletFileUpload(factory);
				String filename = "";
				 try {
					//3.使用解析器解析request，获得表单项
					 List<FileItem> list =	sfu.parseRequest(request);
					 //3.1循环获取表单项（文件项）
					 for(FileItem item : list){
						if(!item.isFormField()){ 
							//文件表单项
							filename = item.getName();
							//提取文件名:C:/a/b/c.txt
							int index = filename.lastIndexOf("\\");
							if(index!=-1){
								filename = filename.substring(index+1);
							}
							//防止上传文件重名
							filename = UUID.randomUUID().toString().replace("-", "") + filename;
							//防止一个文件夹下上传文件过多
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
							//上传的相对路径
							filename = uploadpath + sdf.format(date).replace("-", "/") +"/"+ filename;
							//上传的实际路径，带盘符的
							String path = this.getServletContext().getRealPath(filename);
							//创建文件对象
							File file = new File(path);
							if(!file.exists()){
								file.getParentFile().mkdirs();//表示file上层文件夹如果不存在，则创建。
							}
							item.write(file);
							
						}
					 }
					 
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return filename;
	}

}
