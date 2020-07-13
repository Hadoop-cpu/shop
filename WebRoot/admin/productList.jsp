<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>productList</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/admin/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/select-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 280
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>

</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a>
			</li>
			<li><a href="#">商品列表</a>
			</li>
		</ul>
	</div>

	<div class="rightinfo">
	
		<div class="tools">
			<ul class="toolbar">
				<li><a href="addProduct.html"><span><img
							src="${pageContext.request.contextPath}/admin/images/t01.png" />
					</span>添加</a>
				</li>
				<li class="click"><span><img src="${pageContext.request.contextPath}/admin/images/t03.png" />
				</span>删除</li>
			</ul>
			
			<form action="${pageContext.request.contextPath}/adminProductServlet" method="post">
				<input type="hidden" name="action" value="findByKey"/>
				<ul class="seachform">
					<li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							按关键字&nbsp;&nbsp;</label><input name="key" type="text" class="scinput" />
					</li>
					<li><label>查询&nbsp;&nbsp;按</label>
						<div class="vocation">
							<select class="select3" name="keytype">
								<option value="category">分类</option>
								<option value="productid">id</option>
								<option value="name">名称</option>
							</select>
						</div></li>
					<li><label>&nbsp;</label><input name="" type="submit"
						class="scbtn" value="查询" />
					</li>
				</ul>
			</form>
			
		</div>

		<table class="tablelist" >
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" />
					</th>
					<th>编号<i class="sort"><img src="${pageContext.request.contextPath}/admin/images/px.gif" />
					</i>
					</th>
					<th>商品名称</th>
					<th>价格</th>
					<th>市场价格</th>
					<th>库存量</th>
					<th>商品种类</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${page.list}">

				<tr>
					<td><input name="" type="checkbox" value="" />
					</td>
					<td>${item.productid}</td>
					<td>${item.pname}</td>
					<td>${item.price}</td>
					<td>${item.markprice}</td>
					<td>${item.quality}</td>
					<td>${item.cname}</td>
					<td>${item.time}</td>
					<td><a href="${pageContext.request.contextPath}/adminProductServlet?action=findById&id=${item.productid}" class="tablelink">查看</a> 
					<a href="${pageContext.request.contextPath}/adminProductServlet?action=updatebefore&id=${item.productid}"
						class="tablelink"> 修改</a> <a href="javascript:if(confirm('确定要删除吗？'))window.location.href='${pageContext.request.contextPath}/adminProductServlet?action=delete&id=${item.productid}&current=${page.currentPage}';" class="click"> 删除</a>
					</td>
				</tr>
			</c:forEach>
				

			</tbody>
		</table>


		<%@include file="page.jsp" %>


	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
