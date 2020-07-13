<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0 ,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<title>结算页面</title>

	<link href="<c:url value='/AmazeUI-2.4.2/assets/css/amazeui.css'/>" rel="stylesheet"
		type="text/css" />

	<link href="<c:url value='/basic/css/demo.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/css/cartstyle.css'/>" rel="stylesheet" type="text/css" />

	<link href="<c:url value='/css/jsstyle.css'/>" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<c:url value='/js/address.js'/>"></script>
<script type="text/javascript">
		$(function(){
			//页面加载后，从后台传入数据
			$.post(
					"${pageContext.request.contextPath}/pCAServlet",
					{
						"action":"getprovinces"
					},
					function(data){
						//获取省份数据，将它们绑定到id:pro
						
						$.each(data,function(index,content){
							$("#pro").append("<option value='"+content.provinceid+"'>"+content.province+"</option>");
						});
						var val = $("#pro_text").val();
						$('#pro option').filter(function(){return $(this).text()==val;}).attr("selected",true);
						$("#pro").trigger("change");
						
					},
					"json"
			);
			
			$("#pro").change(function(){
				var pro_text = $("#pro option:selected").text();
				$("#pro_text").val(pro_text);
				
				$.post(
					"${pageContext.request.contextPath}/pCAServlet",
					{
						"action":"getcities",
						"provinceid":$(this).val()
					},
					function(data){
						//1.清空市下拉列表中的数据
						$("#ci").empty();
						//获取市数据，将它们绑定到id:ci
						$("#ci").append("<option>--请选择--</option>");
						$.each(data,function(index,content){
							$("#ci").append("<option value='"+content.cityid+"'>"+content.city+"</option>");
						});
						var val = $("#ci_text").val();
						$('#ci option').filter(function(){return $(this).text()==val;}).attr("selected",true);
						$("#ci").trigger("change");
					},
					"json"
				);
			});
			
			
			$("#ci").change(function(){
				var ci_text = $("#ci option:selected").text();
				$("#ci_text").val(ci_text);
				$.post(
					"${pageContext.request.contextPath}/pCAServlet",
					{
						"action":"getareas",
						"cityid":$(this).val()
					},
					function(data){
						//1.清空区下拉列表中的数据
						$("#ar").empty();
						//获取区数据，将它们绑定到id:ci
						$("#ar").append("<option>--请选择--</option>");
						$.each(data,function(index,content){
							$("#ar").append("<option value='"+content.areaid+"'>"+content.area+"</option>");
						});
						var val = $("#ar_text").val();
						$('#ar option').filter(function(){return $(this).text()==val;}).attr("selected",true);
						$("#ar").trigger("change");
					},
					"json"
				);
			});
			
			$("#ar").change(function(){
				var ar_text = $("#ar option:selected").text();
				
				$("#ar_text").val(ar_text);
			});
			
			$("#sub_edit").click(function(){
				$("#form1").submit();
			});
			
			
			
			
			
		});
	</script>
</head>

<body>

<!-- 编辑地址 -->

	
	<div class="atheme-popover" id="b">

		<!--标题 -->
		<div class="am-cf am-padding">
			<div class="am-fl am-cf">
				<strong class="am-text-danger am-text-lg">编辑地址</strong> / <small>Add
					address</small>
			</div>
		</div>
		<hr />

		<div class="am-u-md-12">
			<form method="post" class="am-form am-form-horizontal" id="form1" action="${pageContext.request.contextPath}/addressServlet">
				<input type="hidden" name="action" value="update"/>
				<input type="hidden" name="addressid" value="${address.addressid}"/>
				<div class="am-form-group">
					<label for="user-name" class="am-form-label">收货人</label>
					<div class="am-form-content">
						<input type="text" id="user-name" name="receiver" placeholder="收货人" value="${address.receiver }">
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-phone" class="am-form-label">手机号码</label>
					<div class="am-form-content">
						<input id="user-phone" name="phone" placeholder="手机号必填" type="text" value="${address.phone}">
					</div>
				</div>
				
				<div class="am-form-group">
					<label for="user-phone" class="am-form-label">邮编</label>
					<div class="am-form-content">
						<input id="user-email" name="postcode" placeholder="邮编必填" type="text" value="${address.postcode}">
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-phone" class="am-form-label">所在地</label>
					<div class="am-form-content address">
						<input type="hidden" name="province" id="pro_text" value="${address.province }"/>
						<input type="hidden" name="city" id="ci_text"  value="${address.city}"/>
						<input type="hidden" name="area" id="ar_text"  value="${address.area}"/>
						<select data-am-selected id="pro">
							<option>--请选择--</option>
						</select> <select data-am-selected id="ci">
						<option>--请选择--</option>
						</select> <select data-am-selected id="ar">
						<option>--请选择--</option>
						</select>
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-intro" class="am-form-label">详细地址</label>
					<div class="am-form-content">
						<textarea name="addressname" class="" rows="3" id="user-intro" placeholder="输入详细地址">${address.addressname}</textarea>
						<small> 100字以内写出你的详细地址...</small>
					</div>
				</div>

				<div class="am-form-group theme-poptit">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<div class="am-btn am-btn-danger" id="sub_edit">编辑</div>
						<div class="am-btn am-btn-danger close">取消</div>
					</div>
				</div>
			</form>
		</div>

	</div>
	
	<div class="clear"></div>
	<!-- 编辑地址 end-->
	</body>

</html>