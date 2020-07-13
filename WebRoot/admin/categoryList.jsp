<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>right</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
    
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

$(function(){
	//给复选框绑定的单击事件处理程序
	$("#all").click(function(){
		if(this.checked){//all被选中
			$(":checkbox").attr("checked",true);
		}else{
			$(":checkbox").attr("checked",false);
		}
	});
});
function del(){
	if(confirm("确定要删除吗？")){
		//单击了确定，提交表单给Servlet
		$("#form1").submit();
	}	
}
</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a>
			</li>
			<li><a href="#">分类列表</a>
			</li>
		</ul>
	</div>

	<div class="rightinfo">
		<form id="form1" action="${pageContext.request.contextPath}/categoryManageServlet" method="post">
			<input type="hidden" name="action" value="deleteMore"/>
			<input type="hidden" name="current" value="${page.currentPage}"/>
		<div class="tools">
			<ul class="toolbar">
				<li><a href="${pageContext.request.contextPath}/admin/addCategory.html"><span><img
							src="${pageContext.request.contextPath}/admin/images/t01.png" />
					</span>添加</a>
				</li>
				<li class="click"><span><img src="${pageContext.request.contextPath}/admin/images/t03.png" />
				</span><a href="javascript:del();">删除</a></li>
			</ul>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th><input id="all" type="checkbox"  />
					</th>
					<th>编号<i class="sort"><img src="${pageContext.request.contextPath}/admin/images/px.gif" />
					</i>
					</th>
					<th>分类名称</th>
					<th>分类排序</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${page.list}" var="item">
				<tr>
					<td><input name="sel" type="checkbox" value="${item.categoryid}" />
					</td>
					<td>${item.categoryid}</td>
					<td>${item.name}</td>
					<td>${item.sort}</td>
					<td><a href="${pageContext.request.contextPath}/categoryManageServlet?id=${item.categoryid}&action=updatebefore" class="tablelink"
						target="rightFrame">修改</a>
						 <a href="javascript:if(confirm('确定要删除吗？')) window.location.href='${pageContext.request.contextPath}/categoryManageServlet?action=delete&id=${item.categoryid}&current=${page.currentPage}';" class="click"> 删除</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
			
		</table>
</form>

		<%@include file="page.jsp" %>

		
	</div>

	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
