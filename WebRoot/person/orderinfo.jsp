<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">

<title>订单详情</title>


<link href="<c:url value='/AmazeUI-2.4.2/assets/css/admin.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/AmazeUI-2.4.2/assets/css/amazeui.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/personal.css'/>" rel="stylesheet"
	type="text/css">

<link href="<c:url value='/css/orstyle.css'/> " rel="stylesheet"
	type="text/css">

<script src="<c:url value='/AmazeUI-2.4.2/assets/js/jquery.min.js'/> "></script>
<script src="<c:url value='/AmazeUI-2.4.2/assets/js/amazeui.js'/> "></script>

</head>

<body>
	<!--头 -->
	<header>
		<article>
			<div class="mt-logo">
				<!--顶部导航条 -->
				<!--悬浮搜索框-->
				<%@include file="/top.jsp"%>
			</div>
			</div>
		</article>
	</header>
	<div class="nav-table">
		<div class="long-title">
			<span class="all-goods">全部分类</span>
		</div>
		<div class="nav-cont">
			<ul>
				<li class="index"><a href="<c:url value='/home/index.jsp'/>">首页</a></li>
				<li class="qc"><a href="<c:url value='/home/cart.jsp'/>">购物车</a></li>
				<li class="qc"><a
					href="<c:url value='/orderServlet?action=findAll'/>">我的订单</a></li>
				<li class="qc last"><a
					href="<c:url value='/person/index.jsp'/>">个人中心</a></li>
			</ul>
			<div class="nav-extra">
				<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>我的福利 <i
					class="am-icon-angle-right" style="padding-left: 10px;"></i>
			</div>
		</div>
	</div>
	<b class="line"></b>
	<div class="center">
		<div class="col-main">
			<div class="main-wrap">

				<div class="user-orderinfo">

					<!--标题 -->
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-danger am-text-lg">订单详情</strong> / <small>Order&nbsp;details</small>
						</div>
					</div>
					<hr />
					<!--进度条-->
					<div class="m-progress">
						<div class="m-progress-list">
							<span class="step-1 step"> <em class="u-progress-stage-bg"></em>
								<i class="u-stage-icon-inner">1<em class="bg"></em></i>
								<p class="stage-name">拍下商品</p>
							</span> <span class="step-2 step"> <em
								class="u-progress-stage-bg"></em> <i class="u-stage-icon-inner">2<em
									class="bg"></em></i>
								<p class="stage-name">卖家发货</p>
							</span> <span class="step-3 step"> <em
								class="u-progress-stage-bg"></em> <i class="u-stage-icon-inner">3<em
									class="bg"></em></i>
								<p class="stage-name">确认收货</p>
							</span> <span class="step-4 step"> <em
								class="u-progress-stage-bg"></em> <i class="u-stage-icon-inner">4<em
									class="bg"></em></i>
								<p class="stage-name">交易完成</p>
							</span> <span class="u-progress-placeholder"></span>
						</div>
						<div class="u-progress-bar total-steps-2">
							<div class="u-progress-bar-inner"></div>
						</div>
					</div>
					<div class="order-infoaside">
						<div class="order-logistics">
							<a href="logistics.html">
								<div class="icon-log">
									<i><img src="${pageContext.request.contextPath}/images/receive.png"></i>
								</div>
								<div class="latest-logistics">
									<p class="text">已签收,签收人是青年城签收，感谢使用天天快递，期待再次为您服务</p>
									<div class="time-list">
										<span class="date">2015-12-19</span><span class="week">周六</span><span
											class="time">15:35:42</span>
									</div>
									<div class="inquire">
										<span class="package-detail">物流：天天快递</span> <span
											class="package-detail">快递单号: </span> <span
											class="package-number">373269427868</span> <a
											href="logistics.html">查看</a>
									</div>
								</div> <span class="am-icon-angle-right icon"></span>
							</a>
							<div class="clear"></div>
						</div>
						<div class="order-addresslist">
							<div class="order-address">
								<div class="icon-add"></div>
								<p class="new-tit new-p-re">
									<span class="new-txt">${order.address.receiver }</span> 
									<span class="new-txt-rd2">${order.address.phone }</span>
								</p>
								<div class="new-mu_l2a new-p-re">
									<p class="new-mu_l2cw">
										<span class="title">收货地址：</span> 
										 
										<span class="street">${order.address.addressname }</span>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="order-infomain">

						<div class="order-top">
							<div class="th th-item">
								<td class="td-inner">商品</td>
							</div>
							<div class="th th-price">
								<td class="td-inner">单价</td>
							</div>
							<div class="th th-number">
								<td class="td-inner">数量</td>
							</div>
							<div class="th th-operation">
								<td class="td-inner">商品操作</td>
							</div>
							<div class="th th-amount">
								<td class="td-inner">合计</td>
							</div>
							<div class="th th-status">
								<td class="td-inner">交易状态</td>
							</div>
							<div class="th th-change">
								<td class="td-inner">交易操作</td>
							</div>
						</div>

						<div class="order-main">

							<div class="order-status3">
								<div class="order-title">
									<div class="dd-num">
										订单编号：<a href="javascript:;">${order.orderid }</a>
									</div>
									<span>下单时间：
									<fmt:formatDate type="time" value="${order.ordertime }" pattern="yyyy-MM-dd hh:mm:ss" />
									</span>
									<!--    <em>店铺：小桔灯</em>-->
								</div>
								<div class="order-content">
									<div class="order-left">
									<c:forEach var="item" items="${order.orderItems }">
										<ul class="item-list">
											<li class="td td-item">
												<div class="item-pic">
													<a href="${pageContext.request.contextPath}/productServlet?action=findById&id=${item.product.productid}" class="J_MakePoint"> <img
														src="${pageContext.request.contextPath}/${item.product.photo}"
														class="itempic J_ItemImg">
													</a>
												</div>
												<div class="item-info">
													<div class="item-basic-info">
														<a href="${pageContext.request.contextPath}/productServlet?action=findById&id=${item.product.productid}">
															<p>${item.product.name}</p>
															
														</a>
													</div>
												</div>
											</li>
											<li class="td td-price">
												<div class="item-price">${item.product.price}</div>
											</li>
											<li class="td td-number">
												<div class="item-number">
													<span>×</span>${item.buycount}
												</div>
											</li>
											<li class="td td-operation">
												<div class="item-operation">退款/退货</div>
											</li>
										</ul>
									</c:forEach>

										

									</div>
									<div class="order-right">
										<li class="td td-amount">
											<div class="item-amount">
												合计：${order.totalprice}
												
											</div>
										</li>
										<div class="move-right">
											<li class="td td-status">
												<div class="item-status">

													<c:choose>
														<c:when test="${order.status==0 }">
															<p class="Mystatus">等待买家付款</p>
														</c:when>
														<c:when test="${order.status==1 }">
															<p class="Mystatus">等待卖家发货</p>
														</c:when>
														<c:when test="${order.status==2 }">
															<p class="Mystatus">卖家已发货</p>
														</c:when>
														<c:when test="${order.status==3 }">
															<p class="Mystatus">卖家已发货，未确认</p>
														</c:when>
														<c:when test="${order.status==4 }">
															<p class="Mystatus">未评价</p>
														</c:when>
													</c:choose>

													
													<p class="order-info">
														<a href="logistics.html">查看物流</a>
													</p>
													<p class="order-info">
														<a href="#">延长收货</a>
													</p>
												</div>
											</li>
											<li class="td td-change">
												
												<c:choose>
													<c:when test="${order.status==0 }">
														<a href="${pageContext.request.contextPath}/orderServlet?action=paying&id=${order.orderid}">
															<div class="am-btn am-btn-danger anniu">付款</div>
														</a>
													</c:when>
													<c:when test="${order.status==1 }">
														<div class="am-btn am-btn-danger anniu">提醒卖家发货</div>
													</c:when>
													<c:when test="${order.status==2 }">
														<div class="am-btn am-btn-danger anniu">确认收货</div>
													</c:when>
													<c:when test="${order.status==3 }">
														<div class="am-btn am-btn-danger anniu">确认收货</div>
													</c:when>
													<c:when test="${order.status==4 }">
														<div class="am-btn am-btn-danger anniu">评价</div>
													</c:when>
												</c:choose>

												
											</li>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

			</div>
			<!--底部-->
			<div class="footer">
				<div class="footer-hd">
					<p>
						<a href="#">恒望科技</a> <b>|</b> <a href="#">商城首页</a> <b>|</b> <a
							href="#">支付宝</a> <b>|</b> <a href="#">物流</a>
					</p>
				</div>
				<div class="footer-bd">
					<p>
						<a href="#">关于恒望</a> <a href="#">合作伙伴</a> <a href="#">联系我们</a> <a
							href="#">网站地图</a> <em>© 2015-2025 Hengwang.com 版权所有. 更多模板 <a
							href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
							- Collect from <a href="http://www.cssmoban.com/" title="网页模板"
							target="_blank">网页模板</a></em>
					</p>
				</div>
			</div>

		</div>
		
		<!--左侧菜单menu-->
		<%@include file="/person/menu.jsp"%>
		
		
	</div>

</body>

</html>