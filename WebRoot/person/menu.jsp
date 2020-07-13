<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="menu">
				<ul>
					<li class="person active">
						<a href="${pageContext.request.contextPath}/person/index.jsp"><i class="am-icon-user"></i>个人中心</a>
					</li>
					<li class="person">
						<p><i class="am-icon-newspaper-o"></i>个人资料</p>
						<ul>
							<li> <a href="${pageContext.request.contextPath}/person/information.jsp">个人信息</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/safety.jsp">安全设置</a></li>
							<li> <a href="${pageContext.request.contextPath}/addressServlet?action=findAllPerson">地址管理</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/cardlist.html">快捷支付</a></li>
						</ul>
					</li>
					<li class="person">
						<p><i class="am-icon-balance-scale"></i>我的交易</p>
						<ul>
							<li><a href="${pageContext.request.contextPath}/orderServlet?action=findAll">订单管理</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/change.html">退款售后</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/comment.html">评价商品</a></li>
						</ul>
					</li>
					<li class="person">
						<p><i class="am-icon-dollar"></i>我的资产</p>
						<ul>
							<li> <a href="${pageContext.request.contextPath}/person/points.html">我的积分</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/coupon.html">优惠券 </a></li>
							<li> <a href="${pageContext.request.contextPath}/person/bonus.html">红包</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/walletlist.html">账户余额</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/bill.jsp">账单明细</a></li>
						</ul>
					</li>

					<li class="person">
						<p><i class="am-icon-tags"></i>我的收藏</p>
						<ul>
							<li> <a href="${pageContext.request.contextPath}/person/collection.html">收藏</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/foot.html">足迹</a></li>														
						</ul>
					</li>

					<li class="person">
						<p><i class="am-icon-qq"></i>在线客服</p>
						<ul>
							<li> <a href="${pageContext.request.contextPath}/person/consultation.html">商品咨询</a></li>
							<li> <a href="${pageContext.request.contextPath}/person/suggest.html">意见反馈</a></li>							
							
							<li> <a href="${pageContext.request.contextPath}/person/news.html">我的消息</a></li>
						</ul>
					</li>
				</ul>

			</aside>