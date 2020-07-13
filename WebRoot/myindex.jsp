<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>首页</title>

    <link href="<c:url value='/AmazeUI-2.4.2/assets/css/amazeui.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/AmazeUI-2.4.2/assets/css/admin.css'/>" rel="stylesheet" type="text/css"/>

    <link href="<c:url value='/basic/css/demo.css'/>" rel="stylesheet" type="text/css"/>

    <link href="<c:url value='/css/hmstyle.css'/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value='/AmazeUI-2.4.2/assets/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/AmazeUI-2.4.2/assets/js/amazeui.min.js'/>"></script>

</head>
<body>
<div class="hmtop">


    <!--顶部导航条 -->
    <!--悬浮搜索框-->
    <%@include file="/top.jsp" %>


    <div class="listMain">"

        <div class="nav-table">
            <div class="long-title"><span class="all-goods">全部分类</span></div>
            <div class="nav-cont">
                <ul>
                    <li class="index"><a href="${pageContext.request.contextPath}/productServlet">首页</a></li>
                    <li class="qc"><a href="${pageContext.request.contextPath}/cartlist.jsp">购物车</a></li>
                    <li class="qc"><a href="${pageContext.request.contextPath}/orderServlet?action=findAll">我的订单</a>
                    </li>
                    <li class="qc last"><a href="${pageContext.request.contextPath}/person/index.jsp">个人中心</a></li>
                </ul>

            </div>
        </div>
    </div>


    <div class="shopMainbg">


        <!--甜点-->

        <div class="am-container ">
            <div class="shopTitle ">
                <h4>商品</h4>
                <h3>每一个商品都有一个故事</h3>

                <span class="more ">
                    <a class="more-link "
                       href="${pageContext.request.contextPath}/productServlet?action=findAll">更多商品</a>
                        </span>
            </div>
        </div>


        <div class="am-g am-g-fixed flood method3 ">
            <ul class="am-thumbnails ">
                <c:forEach var="item" items="${list}">
                    <li>
                        <a href="${pageContext.request.contextPath}/productServlet?action=findById&id=${item.productid}">
                            <div class="list ">

                                <img src="<c:url value='${item.photo}'/> " height="200px"/>
                                <div class="pro-title ">${item.pname}</div>
                                <span class="e-price ">${item.price}元</span>

                            </div>
                        </a>
                    </li>

                </c:forEach>


            </ul>

        </div>

        <div class="footer ">

            <div class="footer-bd ">
                <p>
                    <a href="# ">关于我们</a>
                    <a href="# ">合作伙伴</a>
                    <a href="# ">联系我们</a>
                    <a href="# ">网站地图</a>
                    <em>© 2015-2025 版权所有. </em>
                </p>
            </div>
        </div>

    </div>
</div>

<!--引导 -->


<script>
    window.jQuery || document.write('&lt;script src="basic/js/jquery.min.js ">&lt;\/script>');
</script>
<script type="text/javascript " src="<c:url value='/basic/js/quick_links.js'/> "></script>
</body>

</html>