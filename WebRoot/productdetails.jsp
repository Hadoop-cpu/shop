<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>商品页面</title>

		<link href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="${pageContext.request.contextPath}/css/optstyle.css" rel="stylesheet" />
		<link type="text/css" href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/quick_links.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.imagezoom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/list.js"></script>

	</head>

	<body>


		<!--顶部导航条 -->
		<!--悬浮搜索框-->
		<%@include file="/top.jsp"%>


			<div class="clear"></div>
            <b class="line"></b>
			<div class="listMain">

				<!--分类-->
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
				<ol class="am-breadcrumb am-breadcrumb-slash">
					<li><a href="${pageContext.request.contextPath}/productServlet">首页</a></li>
					<li><a href="#">分类</a></li>
					<li class="am-active">内容</li>
				</ol>
				<script type="text/javascript">
					$(function() {});
					$(window).load(function() {
						$('.flexslider').flexslider({
							animation: "slide",
							start: function(slider) {
								$('body').removeClass('loading');
							}
						});
					});
				</script>
				<!--
				<div class="scoll">
					<section class="slider">
						<div class="flexslider">
							<ul class="slides">
								<li>
									<img src="images/01.jpg" title="pic" />
								</li>
								<li>
									<img src="images/02.jpg" />
								</li>
								<li>
									<img src="images/03.jpg" />
								</li>
							</ul>
						</div>
					</section>
				</div>-->

				<!--放大镜-->

				<div class="item-inform">
					<div class="clearfixLeft" id="clearcontent">

						<div class="box">
							<script type="text/javascript">
								$(document).ready(function() {
									$(".jqzoom").imagezoom();
									$("#thumblist li a").click(function() {
										$(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
										$(".jqzoom").attr('src', $(this).find("img").attr("mid"));
										$(".jqzoom").attr('rel', $(this).find("img").attr("big"));
									});
								});
							</script>

							<div class="tb-booth tb-pic tb-s310">
								<a href="${pageContext.request.contextPath}/images/01.jpg">
								<img src="${pageContext.request.contextPath}${product.photo}" alt="细节展示放大镜特效" 
								rel="${pageContext.request.contextPath}${product.photo}" class="jqzoom" /></a>
							</div>
			
						</div>

						<div class="clear"></div>
					</div>

					<div class="clearfixRight">

					
						<div class="tb-detail-hd">
							<h1>	
				 ${product.name}
	          </h1>
	          直发，品质保障，赠原装硬底座，支持二次开发，批量采购咨询客服，免费开增值税发票。
	          直发，品质保障，赠原装硬底座，支持二次开发，批量采购咨询客服，免费开增值税发票。
	          直发，品质保障，赠原装硬底座，支持二次开发，批量采购咨询客服，免费开增值税发票。
						</div>
						<div class="tb-detail-list">
							<!--价格-->
							<div class="tb-detail-price">
								<li class="price iteminfo_price">
									<dt>促销价</dt>
									<dd><em>¥</em><b class="sys_item_price">${product.price}</b></dd>                                 
								</li>
								<li class="price iteminfo_mktprice">
									<dt>原价</dt>
									<dd><em>¥</em><b class="sys_item_mktprice">${product.markprice}</b></dd>									
								</li>
								<div class="clear"></div>
							</div>

							
							<div class="clear"></div>

							<!--销量-->
							<ul class="tm-ind-panel">
								<li class="tm-ind-item tm-ind-sellCount canClick">
									<div class="tm-indcon">
									<span class="tm-label">库存量</span>
									<span class="tm-count">${product.quality}</span></div>
								</li>	
								<li class="tm-ind-item tm-ind-sellCount canClick">
									
								</li>	
								<li class="tm-ind-item tm-ind-sellCount canClick">
									
								</li>						
							</ul>
							<div class="clear"></div>

							<!--各种规格-->
							<dl class="iteminfo_parameter sys_item_specpara">
								
								<dd>
									<!--操作页面-->

									<div class="theme-popover-mask"></div>

									<div class="theme-popover">
										
										<div class="theme-popbod dform">
											<form class="theme-signin" name="loginform" action="${pageContext.request.contextPath}/cartServlet" method="post">
												<input type="hidden" name="action" value="add"/>
												<input type="hidden" name="productid" value="${product.productid}"/>
												
												<div class="theme-signin-left">
																								
													<div class="theme-options">
														<div class="cart-title number">数量</div>
														<dd>
															<input id="min" class="am-btn am-btn-default" name="" type="button" value="-" />
															<input id="text_box" name="count" type="text" value="1" style="width:30px;" />
															<input id="add" class="am-btn am-btn-default" name="" type="button" value="+" />
															
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
														<img src="images/songzi.jpg" />
													</div>
													<div class="text-info">
														<span class="J_Price price-now">¥39.00</span>
														<span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
													</div>
												</div>

											</form>
										</div>
									</div>

								</dd>
							</dl>
							<div class="clear"></div>
							<!--活动	-->
							<div class="shopPromotion gold">
								<div class="hot">
									<dt class="tb-metatit">店铺优惠</dt>
									<div class="gold-list">
										<p>双十一买一送二啦！</p>
									</div>
								</div>
								<div class="clear"></div>
								
							</div>
						</div>

						<div class="pay">
							<div class="pay-opt">
							<a href="home.html"><span class="am-icon-home am-icon-fw">首页</span></a>
							<a><span class="am-icon-heart am-icon-fw">收藏</span></a>
							
							</div>
							<li>
								<div class="clearfix tb-btn tb-btn-buy theme-login">
									<a id="LikBuy" title="点此按钮到下一步确认购买信息" href="#">立即购买</a>
								</div>
							</li>
							<li>
								<div class="clearfix tb-btn tb-btn-basket theme-login">
									<a id="LikBasket" title="加入购物车" href="javascript:loginform.submit()"><i></i>加入购物车</a>
								</div>
							</li>
						</div>

					</div>

					<div class="clear"></div>

				</div>							
		
					</div>
					<div class="introduce">
					
					<div class="clear"></div>
				<div class="introduceMain">
						<div class="am-tabs" data-am-tabs>
							
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active">
									<a href="#">

										<span class="index-needs-dt-txt">宝贝详情</span></a>

								</li>
							</ul>
						<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active">
									${product.content}
								</div>
							</div>
						</div>
					</div>

						<div class="clear"></div>
					</div>

						<div class="footer">
							
							<div class="footer-bd">
								<p>
									<a href="#">关于我们</a>
									<a href="#">合作伙伴</a>
									<a href="#">联系我们</a>
									<a href="#">网站地图</a>
									<em>© 2015-2025  版权所有. </em>
								</p>
							</div>
						</div>
				

				</div>
			</div>
			

	</body>

</html>