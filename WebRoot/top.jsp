<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--顶部导航条 -->
<div class="am-container header">
	<ul class="message-l">
		<div class="topMessage">
			<div class="menu-hd">
				<c:choose>
					<c:when test="${user!=null}">
							${user.username}，欢迎再次回来！
							<a href="javascript:if(confirm('确定要退出吗')) window.location.href='${pageContext.request.contextPath}/userServlet?action=logout';" target="_top">退出</a>
							</c:when>
					<c:otherwise>
						<a href="<c:url value='/login.jsp'/>" target="_top" class="h"><span style="color: red">亲，请登录！</span></a>
						<a href="<c:url value='/regist.jsp'/>" target="_top">免费注册</a>
					</c:otherwise>

				</c:choose>

				
			</div>
		</div>
	</ul>
	<ul class="message-r">
		<div class="topMessage home">
			<div class="menu-hd">
				<a href="${pageContext.request.contextPath}/productServlet"
					target="_top" class="h">商城首页</a>
			</div>
		</div>
		<div class="topMessage my-shangcheng">
			<div class="menu-hd MyShangcheng">
				<a href="${pageContext.request.contextPath}/person/index.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>
			</div>
		</div>
		<div class="topMessage mini-cart">
			<div class="menu-hd">
				<a id="mc-menu-hd" href="${pageContext.request.contextPath}/cartlist.jsp" target="_top"><i
					class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong
					id="J_MiniCartNum" class="h">
					<c:choose>
					<c:when test="${empty(cart.map) }">
					</c:when>
					<c:otherwise>
					 ${cart.cartItems.size() }
					</c:otherwise>
					</c:choose>
					</strong></a>
			</div>
		</div>
		<div class="topMessage favorite">
			<div class="menu-hd">
				<a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>
			</div>
		</div>
	</ul>
</div>

<!--悬浮搜索框-->

<div class="nav white">
	<div class="logo">
		<!--<img src="<c:url value='/images/logo.png'/>" />-->
	</div>
	<div class="logoBig">
		<li><img src="<c:url value='/images/logobig.png'/>" /></li>
	</div>

	<div class="search-bar pr">
		<a name="index_none_header_sysc" href="#"></a>
		<form>
			<input id="searchInput" name="index_none_header_sysc" type="text"
				placeholder="搜索" autocomplete="off"> <input
				id="ai-topsearch" class="submit am-btn" value="搜索" index="1"
				type="submit">
		</form>
	</div>
</div>
<!--悬浮搜索框结束-->
<div class="clear"></div>
</div>
<!--顶部导航条结束 -->