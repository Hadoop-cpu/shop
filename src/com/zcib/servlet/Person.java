package com.zcib.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/person")
public class Person extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");// 处理中文乱码
        /*
         * 按照action的不同，来调用不同的处理方法
         */
        String action = request.getParameter("action");
        if ("password".equals(action)) {
            password(request, response);
        }
    }

    private void password(HttpServletRequest request,
                          HttpServletResponse response) {
        /*
         * 进行注册 1.获取表单数据，进行验证
         * 2.把表单数据封装到User对象中
         * 3.检查该用户名是否已经占用（AJAX来实现，今天不讲）
         * 4.调用Service方法regist来进行注册
         * 5.返回到index.html（防止重复提交）
         */
        // 1.获取表单数据，进行验证
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");

        System.out.println(oldPassword+newPassword+newPassword2);


    }

}