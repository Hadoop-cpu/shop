<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>搜索页面</title>

		<link href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />

		<link href="${pageContext.request.contextPath}/basic/css/demo.css" rel="stylesheet" type="text/css" />

		<link href="${pageContext.request.contextPath}/css/seastyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
	<script type="text/javascript">
	$(function(){
		var sub = $("input[name='subtn']");
		sub.click(function(){
			$("#form1").submit();
		});
		
	});
	
	</script>
	
	</head>

	<body>

		<!--顶部导航条 -->
		<!--悬浮搜索框-->
		<%@include file="/top.jsp"%>

			<div class="clear"></div>
			<b class="line"></b>
           <div class="search">
			<div class="search-list">
			<div class="nav-table">
					   <div class="long-title"><span class="all-goods">全部分类</span></div>
					   <div class="nav-cont">
							<ul>
								<li class="index"><a href="${pageContext.request.contextPath}/productServlet">首页</a></li>
                                <li class="qc"><a href="#">闪购</a></li>
                                <li class="qc"><a href="#">限时抢</a></li>
                                <li class="qc"><a href="#">团购</a></li>
                                <li class="qc last"><a href="#">大包装</a></li>
							</ul>
						    
						</div>
			</div>
			
				
					<div class="am-g am-g-fixed">
						<div class="am-u-sm-12 am-u-md-12">
	                  	
							<!--<div class="search-content">-->
								<div class="sort">
									<li class="first"><a title="价格" href="${pageContext.request.contextPath}/productServlet?action=findAll&sortkey=price&sort=asc">价格升序</a></li>
									<li><a title="价格" href="${pageContext.request.contextPath}/productServlet?action=findAll&sortkey=price&sort=desc">价格降序</a></li>
									<li><a title="上架时间" href="${pageContext.request.contextPath}/productServlet?action=findAll&sortkey=time&sort=asc">上架时间升序</a></li>
									<li class="big"><a title="上架时间" href="${pageContext.request.contextPath}/productServlet?action=findAll&sortkey=time&sort=desc">上架时间降序</a></li>
								</div>
								<div class="clear"></div>

								<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">
								<c:forEach var="item" items="${page.list}">
									<li>
									<a href="${pageContext.request.contextPath}/productServlet?action=findById&id=${item.productid}">
										<div class="i-pic limit">
											<img src="${pageContext.request.contextPath}/${item.photo}" height="250px"/>											
											<p class="title fl">${item.pname }</p>
											<p class="price fl">
												<b>¥</b>
												<strong>${item.price }</strong>
											</p>
											<p class="number fl">
												销量<span>${item.hit }</span>
											</p>
										</div>
									</a>
									</li>
								</c:forEach>	
								</ul>
							<!--</div>-->
							
							<div class="clear"></div>
							<!--分页 -->
							<ul class="am-pagination am-pagination-right">
							<c:choose>
							<c:when test="${page.currentPage == 1 }">
								<li class="am-disabled"><a href="#">&laquo;</a></li>
							</c:when>
							<c:otherwise>
							<li ><a href="${pageContext.request.contextPath}/productServlet?action=findAll&current=${page.currentPage-1}&key=${key}&sortkey=${sortkey}&sort=${sort}">&laquo;</a></li>
							</c:otherwise>							
							</c:choose>
							<c:forEach var="i" begin="${page.start}" end="${page.end}" >
								<c:choose>
									<c:when test="${page.currentPage == i }">
									<li class="am-active"><a href="#">${i }</a></li>
									</c:when>
									<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/productServlet?action=findAll&current=${i}&key=${key}&sortkey=${sortkey}&sort=${sort}">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
								<c:choose>
							<c:when test="${page.currentPage == page.totalPage }">
								<li class="am-disabled"><a href="#">&raquo;</a></li>
							</c:when>
							<c:otherwise>
							<li ><a href="${pageContext.request.contextPath}/productServlet?action=findAll&current=${page.currentPage+1}&key=${key}&sortkey=${sortkey}&sort=${sort}">&raquo;</a></li>
							</c:otherwise>							
							</c:choose>
							</ul>

						</div>
					</div>
					<div class="footer">
						
						<div class="footer-bd">
							<p>
								<a href="#">关于我们</a>
								<a href="#">合作伙伴</a>
								<a href="#">联系我们</a>
								<a href="#">网站地图</a>
								<em>© 2015-2025 Hengwang.com 版权所有. </em>
							</p>
						</div>
					</div>
				</div>

			</div>

		<!--引导
		<div class="navCir">
			<li><a href="home2.html"><i class="am-icon-home "></i>首页</a></li>
			<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
			<li><a href="shopcart.html"><i class="am-icon-shopping-basket"></i>购物车</a></li>	
			<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>					
		</div> -->

		<!--菜单 -->
		<!--<div class=tip>
			<div id="sidebar">
				<div id="wrap">
					<div id="prof" class="item">
						<a href="#">
							<span class="setting"></span>
						</a>
						<div class="ibar_login_box status_login">
							<div class="avatar_box">
								<p class="avatar_imgbox"><img src="../images/no-img_mid_.jpg" /></p>
								<ul class="user_info">
									<li>用户名：sl1903</li>
									<li>级&nbsp;别：普通会员</li>
								</ul>
							</div>
							<div class="login_btnbox">
								<a href="#" class="login_order">我的订单</a>
								<a href="#" class="login_favorite">我的收藏</a>
							</div>
							<i class="icon_arrow_white"></i>
						</div>

					</div>
					<div id="shopCart" class="item">
						<a href="#">
							<span class="message"></span>
						</a>
						<p>
							购物车
						</p>
						<p class="cart_num">0</p>
					</div>
					<div id="asset" class="item">
						<a href="#">
							<span class="view"></span>
						</a>
						<div class="mp_tooltip">
							我的资产
							<i class="icon_arrow_right_black"></i>
						</div>
					</div>

					<div id="foot" class="item">
						<a href="#">
							<span class="zuji"></span>
						</a>
						<div class="mp_tooltip">
							我的足迹
							<i class="icon_arrow_right_black"></i>
						</div>
					</div>

					<div id="brand" class="item">
						<a href="#">
							<span class="wdsc"><img src="../images/wdsc.png" /></span>
						</a>
						<div class="mp_tooltip">
							我的收藏
							<i class="icon_arrow_right_black"></i>
						</div>
					</div>

					<div id="broadcast" class="item">
						<a href="#">
							<span class="chongzhi"><img src="../images/chongzhi.png" /></span>
						</a>
						<div class="mp_tooltip">
							我要充值
							<i class="icon_arrow_right_black"></i>
						</div>
					</div>

					<div class="quick_toggle">
						<li class="qtitem">
							<a href="#"><span class="kfzx"></span></a>
							<div class="mp_tooltip">客服中心<i class="icon_arrow_right_black"></i></div>
						</li>
						<!--二维码 
						<li class="qtitem">
							<a href="#none"><span class="mpbtn_qrcode"></span></a>
							<div class="mp_qrcode" style="display:none;"><img src="../images/weixin_code_145.png" /><i class="icon_arrow_white"></i></div>
						</li>
						<li class="qtitem">
							<a href="#top" class="return_top"><span class="top"></span></a>
						</li>
					</div>-->

					<!--回到顶部
					<div id="quick_links_pop" class="quick_links_pop hide"></div>

				</div>

			</div>
			<div id="prof-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					我
				</div>
			</div>
			<div id="shopCart-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					购物车
				</div>
			</div>
			<div id="asset-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					资产
				</div>

				<div class="ia-head-list">
					<a href="#" target="_blank" class="pl">
						<div class="num">0</div>
						<div class="text">优惠券</div>
					</a>
					<a href="#" target="_blank" class="pl">
						<div class="num">0</div>
						<div class="text">红包</div>
					</a>
					<a href="#" target="_blank" class="pl money">
						<div class="num">￥0</div>
						<div class="text">余额</div>
					</a>
				</div>

			</div>
			<div id="foot-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					足迹
				</div>
			</div>
			<div id="brand-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					收藏
				</div>
			</div>
			<div id="broadcast-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					充值
				</div>
			</div>
		</div>-->
		<script>
			window.jQuery || document.write('<script src="basic/js/jquery-1.9.min.js"><\/script>');
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/quick_links.js"></script>

<div class="theme-popover-mask"></div>

	</body>

</html>