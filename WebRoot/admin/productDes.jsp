<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/admin/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/select-ui.min.js"></script>


</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a>
			</li>
			<li><a href="#">商品信息管理</a>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>商品基本信息</span>
		</div>

		<ul class="forminfo">
			<li><label>商品名称</label> <label>${product.name}</label>
			</li>
			<li><label>商品分类<b>*</b>
			</label>
				<div class="vocation">
					${product.cname}
				</div></li>
			<li><label>商品封面</label> <img src="${pageContext.request.contextPath}/${product.photo}" /></li>
			<li><label>商品价格</label> <label>￥${product.price}</label>
			</li>
			<li><label>商品市场价格</label> <label>￥${product.markprice}</label>
			</li>
			<li><label>商品数量</label> <label>${product.quality}</label>
			</li>
			<li><label>商品描述内容</label> <label>${product.content}</label></li>
		</ul>
	</div>
</body>
</html>

