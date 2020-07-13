<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<title>购物车页面</title>

	<link
		href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/amazeui.css"
		rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/basic/css/demo.css"
		rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/cartstyle.css"
		rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/optstyle.css"
		rel="stylesheet" type="text/css" />

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".increase").click(function() {
				var qinput = $(this).siblings(":text");
				qinput.val(eval(qinput.val()) + 1);
				var id = qinput.attr("productid");
				$.post("${pageContext.request.contextPath}/cartServlet", {
					"action" : "updatebuycount",
					"id" : id,
					"buycount" : qinput.val()
				}, function(data) {
					$("#total" + id).text(data.total);
					$("#J_Total").text(computeTotal());
				}, "json");
			});
			$(".decrease").click(function() {
				var qinput = $(this).siblings(":text");
				var count = eval(qinput.val()) - 1;
				if (1 > count) {
					alert("商品不能再减少了！");
					return;
				}
				qinput.val(eval(qinput.val()) - 1);
				var id = qinput.attr("productid");
				$.post("${pageContext.request.contextPath}/cartServlet", {
					"action" : "updatebuycount",
					"id" : id,
					"buycount" : qinput.val()
				}, function(data) {
					$("#total" + id).text(data.total);
					$("#J_Total").text(computeTotal());
				}, "json");
			});

			$("#J_SelectAllCbx2").click(function() {
				if (this.checked) {
					$(":checkbox").attr("checked", true);
				} else {
					$(":checkbox").attr("checked", false);
				}
				$("#J_Total").text(computeTotal());
			});

			$(".check").click(function() {
					$("#J_Total").text(computeTotal());
			});
			
			$("#deleteAll").click(function() {
				if(confirm("确定要清空购物车吗？")){
					window.location.href="${pageContext.request.contextPath}/cartServlet?action=deleteAll";
				}
			});
			
			
			$("#deleteMore").click(function() {
				if(confirm("确定要删除吗？")){
					form1.submit();
				}
			});
			
			$("#J_Go").click(function(){
				//修改form表单的action
				//修改hidden的action
				$("#form1").attr("action","${pageContext.request.contextPath}/addressServlet");
				$("input[name='action']").val("paybefore");
				//提交表单
				$("#form1").submit();
			});
			
		});

		function computeTotal() {
			var total = 0;
			$(".check").each(function() {
				if (!$("#J_SelectAllCbx2").is(this)) {
					if (this.checked) {
						var id = $(this).attr("id");
						var stotal = $("#total" + id).text();
						total = eval(stotal) + eval(total);
					}
				}
			});
			return total;
		}
		</script>
</head>

<body>
	<!--顶部导航条 -->
	<!--悬浮搜索框-->
	<%@include file="/top.jsp" %>

	<div class="clear"></div>

	
	<!--购物车 -->
	<div class="concent">
	<c:choose>
			<c:when test="${empty(cart.map) }">
			<a href="${pageContext.request.contextPath}/productServlet" >
			<img src="${pageContext.request.contextPath}/images/nocart.jpg" alt="" href="${pageContext.request.contextPath}/productServlet"/>
			购物车没有东西！
			快去首页看看你喜欢的东西吧！</a>
			</c:when>
			<c:otherwise>
		<div id="cartTable">
			<div class="cart-table-th">
				<div class="wp">
					<div class="th th-chk">
						<div id="J_SelectAll1" class="select-all J_SelectAll"></div>
					</div>
					<div class="th th-item">
						<div class="td-inner">商品信息</div>
					</div>
					<div class="th th-price">
						<div class="td-inner">单价</div>
					</div>
					<div class="th th-amount">
						<div class="td-inner">数量</div>
					</div>
					<div class="th th-sum">
						<div class="td-inner">金额</div>
					</div>
					<div class="th th-op">
						<div class="td-inner">操作</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<form action="${pageContext.request.contextPath}/cartServlet" method="post" id="form1">
			<input type="hidden" name="action" value="deleteMore"/>
			
			
			<c:forEach var="item" items="${cart.cartItems}">
				<tr class="item-list">
					<div class="bundle  bundle-last ">

						<div class="bundle-main">
							<ul class="item-content clearfix">
								<li class="td td-chk">
									<div class="cart-checkbox ">
										<input class="check" id="${item.product.productid}"
											name="sel" value="${item.product.productid}" type="checkbox">
											<label for="J_CheckBox_170037950254"></label>
									</div></li>
								<li class="td td-item">
									<div class="item-pic">
										<a href="#" target="_blank" data-title="${item.product.name}"
											class="J_MakePoint" data-point="tbcart.8.12">
											<img src="${pageContext.request.contextPath}/${item.product.photo}"
											class="itempic J_ItemImg" width="80px">
										</a>
									</div>
									<div class="item-info">
										<div class="item-basic-info">
											<a href="#" target="_blank" title="${item.product.name}"
												class="item-title J_MakePoint" data-point="tbcart.8.11">${item.product.name}</a>
										</div>
									</div></li>

								<li class="td td-price">
									<div class="item-price price-promo-promo">
										<div class="price-content">
											<div class="price-line">
												<em class="price-original">${item.product.markprice}</em>
											</div>
											<div class="price-line">
												<em class="J_Price price-now" tabindex="0">${item.product.price}</em>
											</div>
										</div>
									</div></li>
								<li class="td td-amount">
									<div class="amount-wrapper ">
										<div class="item-amount ">
											<div class="sl">
												<input class="decrease" name="" type="button" value="-" />
												<input class="text_box"
													productid="${item.product.productid}" name="buycount"
													type="text" value="${item.count}" style="width:30px;" /> <input
													class="increase" type="button" value="+" />
											</div>
										</div>
									</div></li>
								<li class="td td-sum">
									<div class="td-inner">
										<span tabindex="0" id="total${item.product.productid}"
											class="J_ItemSum number">${item.total}</span>
									</div></li>
								<li class="td td-op">
									<div class="td-inner">

										<a href="${pageContext.request.contextPath}/cartServlet?action=delete&id=${item.product.productid}" data-point-url="#" class="delete" >
											删除</a>
									</div></li>
							</ul>


						</div>
					</div>
				</tr>
				<div class="clear"></div>
			</c:forEach>
			
		</form>
		</div>
		<div class="clear"></div>

		<div class="float-bar-wrapper">
			<div id="J_SelectAll2" class="select-all J_SelectAll">
				<div class="cart-checkbox">
					<input class="check-all check" id="J_SelectAllCbx2"
						name="select-all" value="true" type="checkbox"> <label
						for="J_SelectAllCbx2"></label>
				</div>
				<span id="J_SelectAllCbx2">全选</span>
			</div>
			<div class="operations">
				<a id="deleteMore" hidefocus="true" class="deleteAll">删除</a>
				<a id="deleteAll" hidefocus="true" class="deleteAll">清空购物车</a>
			</div>
			<div class="float-bar-right">
				<div class="amount-sum">
					<span class="txt">已选商品</span> <em id="J_SelectedItemsCount">1</em><span
						class="txt">件</span>
					<div class="arrow-box">
						<span class="selected-items-arrow"></span> <span class="arrow"></span>
					</div>
				</div>
				<div class="price-sum">
					<span class="txt">合计:</span> <strong class="price">¥<em
						id="J_Total">0</em>
					</strong>
				</div>
				<div class="btn-area">
					<a  id="J_Go" class="submit-btn submit-btn-disabled"
						aria-label="请注意如果没有选择宝贝，将无法结算"> <span>结&nbsp;算</span>
					</a>
				</div>
			</div>

		</div>

		<div class="footer">

			<div class="footer-bd">
				<p>
					<a href="#">关于恒望</a> <a href="#">合作伙伴</a> <a href="#">联系我们</a> <a
						href="#">网站地图</a> <em>© 2015-2025 版权所有. </em>
				</p>
			</div>
		</div>
		</c:otherwise>
		</c:choose>
	</div>

	<!--操作页面-->

	<div class="theme-popover-mask"></div>

	<div class="theme-popover">
		<div class="theme-span"></div>
		<div class="theme-poptit h-title">
			<a href="javascript:;" title="关闭" class="close">×</a>
		</div>
		<div class="theme-popbod dform">
			<form class="theme-signin" name="loginform" action="" method="post">

				<div class="theme-signin-left">

					<li class="theme-options">
						<div class="cart-title">颜色：</div>
						<ul>
							<li class="sku-line selected">12#川南玛瑙<i></i>
							</li>
							<li class="sku-line">10#蜜橘色+17#樱花粉<i></i>
							</li>
						</ul></li>
					<li class="theme-options">
						<div class="cart-title">包装：</div>
						<ul>
							<li class="sku-line selected">包装：裸装<i></i>
							</li>
							<li class="sku-line">两支手袋装（送彩带）<i></i>
							</li>
						</ul></li>
					<div class="theme-options">
						<div class="cart-title number">数量</div>
						<dd>
							<input class="min am-btn am-btn-default" name="" type="button"
								value="-" /> <input class="text_box" name="" type="text"
								value="1" style="width:30px;" /> <input
								class="add am-btn am-btn-default" name="" type="button"
								value="+" /> <span class="tb-hidden">库存<span
								class="stock">1000</span>件</span>
						</dd>

					</div>
					<div class="clear"></div>
					<div class="btn-op">
						<div class="btn am-btn am-btn-warning">确认</div>
						<div class="btn close am-btn am-btn-warning">取消</div>
					</div>

				</div>
				<div class="theme-signin-right">
					<div class="img-info">
						<img src="../images/kouhong.jpg_80x80.jpg" />
					</div>
					<div class="text-info">
						<span class="J_Price price-now">¥39.00</span> <span id="Stock"
							class="tb-hidden">库存<span class="stock">1000</span>件</span>
					</div>
				</div>

			</form>
		</div>
	</div>
	<!--引导 -->
	<div class="navCir">
		<li><a href="home2.html"><i class="am-icon-home "></i>首页</a>
		</li>
		<li><a href="sort.html"><i class="am-icon-list"></i>分类</a>
		</li>
		<li class="active"><a href="shopcart.html"><i
				class="am-icon-shopping-basket"></i>购物车</a>
		</li>
		<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a>
		</li>
	</div>
</body>

</html>