<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">

		<title>地址管理</title>

		<link href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="${pageContext.request.contextPath}/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/addstyle.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
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
						//<option value='1111'>河南省</option>
						$.each(data,function(index,content){
							$("#pro").append("<option value='"+content.provinceid+"'>"+content.province+"</option>");
						});
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
						//<option value='1111'>zhengzhoushi</option>
						$("#ci").append("<option>--请选择--</option>");
						$.each(data,function(index,content){
							$("#ci").append("<option value='"+content.cityid+"'>"+content.city+"</option>");
						});
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
						//<option value='1111'>zhengzhoushi</option>
						$("#ar").append("<option>--请选择--</option>");
						$.each(data,function(index,content){
							$("#ar").append("<option value='"+content.areaid+"'>"+content.area+"</option>");
						});
					},
					"json"
				);
			});
			
			$("#ar").change(function(){
				var ar_text = $("#ar option:selected").text();
				$("#ar_text").val(ar_text);
			});
			
			$("#sub_add").click(function(){
				$("#form1").submit();
			});
			
			//选中显示编辑和删除
			$(".user-addresslist").click(function(){
				$(".user-addresslist").find(".new-addr-btn").css("display","none");
				$(this).find(".new-addr-btn").css("display","block");
				
				//给提交订单form中赋值
				var receiver = $(this).find(".buy-user").text();
				$(".buy-footer-address").find(".buy-user").html(receiver);
				
				var phone = $(this).find(".buy-phone").text();
				$(".buy-footer-address").find(".buy-phone").html(phone);
				
				var province = $(this).find(".province").text();
				$(".buy-footer-address").find(".province").html(province);
				
				var city = $(this).find(".city").text();
				$(".buy-footer-address").find(".city").html(city);
				
				var area = $(this).find(".dist").text();
				$(".buy-footer-address").find(".dist").html(area);
				
				var street = $(this).find(".street").text();
				$(".buy-footer-address").find(".street").html(street);
				
				//获取订单id
				var id = $(this).attr("addressid");
				$("#addressid").val(id);
				
			});
			
			
			
		});
	</script>

	</head>

	<body>
		<!--头 -->
		<header>
			<article>
				<div class="mt-logo">
					<!--顶部导航条 -->
	                <!--悬浮搜索框-->
	                <%@include file="/top.jsp" %>

					</div>
				</div>
			</article>
		</header>

		<div class="nav-table">
			<div class="long-title"><span class="all-goods">全部分类</span></div>
			<div class="nav-cont">
				<ul>
					<li class="index"><a href="#">首页</a></li>
					<li class="qc"><a href="#">闪购</a></li>
					<li class="qc"><a href="#">限时抢</a></li>
					<li class="qc"><a href="#">团购</a></li>
					<li class="qc last"><a href="#">大包装</a></li>
				</ul>
				<div class="nav-extra">
					<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>我的福利
					<i class="am-icon-angle-right" style="padding-left: 10px;"></i>
				</div>
			</div>
		</div>
		<b class="line"></b>

		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-address">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> / <small>Address&nbsp;list</small></div>
						</div>
						<hr/>
						
						<ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">
						
						
							<c:forEach var="item" items="${addresslist }">
					<!-- 地址-->
					<li class="user-addresslist defaultAddr">
								<span class="new-option-r"><i class="am-icon-check-circle"></i>默认地址</span>
								<p class="new-tit new-p-re">
									<span class="new-txt">${item.receiver }</span>
									<span class="new-txt-rd2">${item.phone}</span>
								</p>
								<div class="new-mu_l2a new-p-re">
									<p class="new-mu_l2cw">
										<span class="title">地址：</span>
										<span class="province">${item.province}</span>
									<c:choose>
									<c:when test="${(item.city eq '市辖区') or (item.city eq '县')}">
									<span class="city"></span>
									</c:when>
									<c:otherwise>
									<span class="city">${item.city}</span>
									</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${(item.area eq '市辖区') }">
									<span class="dist"></span>
									</c:when>
									<c:otherwise>
									<span class="dist">${item.area}</span>
									</c:otherwise>
									</c:choose>
										<span class="street">${item.addressname }</span></p>
								</div>
								<div class="new-addr-btn">
									<a href="#"><i class="am-icon-edit"></i>编辑</a>
									<span class="new-addr-bar">|</span>
									<a href="javascript:void(0);" onclick="delClick(this);"><i class="am-icon-trash"></i>删除</a>
								</div>
							</li>
						
					<!-- 地址end -->
					</c:forEach>
					<li class="user-addresslist">
								<span class="new-option-r"><i class="am-icon-check-circle"></i>设为默认</span>
								<p class="new-tit new-p-re">
									<span class="new-txt">王一</span>
									<span class="new-txt-rd2">155****7893</span>
								</p>
								<div class="new-mu_l2a new-p-re">
									<p class="new-mu_l2cw">
										<span class="title">地址：</span>
										<span class="province">河南</span>省
										<span class="city">郑州</span>市
										<span class="dist">新郑</span>区
										<span class="street">郑州工业应用技术学院</span></p>
								</div>
								<div class="new-addr-btn">
									<a href="#"><i class="am-icon-edit"></i>编辑</a>
									<span class="new-addr-bar">|</span>
									<a href="javascript:void(0);" onclick="delClick(this);"><i class="am-icon-trash"></i>删除</a>
								</div>
							</li>
						
						
						
						
						
							

							

							
						</ul>
						<div class="clear"></div>
						<a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div class="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add&nbsp;address</small></div>
								</div>
								<hr/>

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
									<form class="am-form am-form-horizontal" id="form1" action="${pageContext.request.contextPath}/addressServlet" method="post">
										<input type="hidden" name="action" value="add"/>

										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="user-name" name="receiver" placeholder="收货人">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="user-phone" name="phone" placeholder="手机号必填" type="email">
											</div>
										</div>
										<div class="am-form-group">
					<label for="user-phone" class="am-form-label">邮编</label>
					<div class="am-form-content">
						<input id="user-email" name="postcode" placeholder="邮编必填" type="text">
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-phone" class="am-form-label">所在地</label>
					<div class="am-form-content address">
						<input type="hidden" name="province" id="pro_text"/>
						<input type="hidden" name="city" id="ci_text"/>
						<input type="hidden" name="area" id="ar_text"/>
						<select data-am-selected id="pro" size="2">
							<option>--请选择--</option>
						</select> <select data-am-selected id="ci" size="2">
							<option>--请选择--</option>
						</select> <select data-am-selected id="ar" size="2">
							<option>--请选择--</option>
						</select>
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-intro" class="am-form-label">详细地址</label>
					<div class="am-form-content">
						<textarea name="addressname" class="" rows="3" id="user-intro" placeholder="输入详细地址"></textarea>
						<small>100字以内写出你的详细地址...</small>
					</div>
				</div>

										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<a class="am-btn am-btn-danger" id="sub_add">保存</a>
												<a href="javascript: void(0)" class="am-close am-btn am-btn-danger" data-am-modal-close>取消</a>
											</div>
										</div>
									</form>
								</div>

							</div>

						</div>

					</div>

					<script type="text/javascript">
						$(document).ready(function() {							
							$(".new-option-r").click(function() {
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
							});
							
							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
							}
							
						})
					</script>

					<div class="clear"></div>

				</div>
				<!--底部-->
				<div class="footer">
					<div class="footer-hd">
						<p>
							<a href="#">恒望科技</a>
							<b>|</b>
							<a href="#">商城首页</a>
							<b>|</b>
							<a href="#">支付宝</a>
							<b>|</b>
							<a href="#">物流</a>
						</p>
					</div>
					<div class="footer-bd">
						<p>
							<a href="#">关于恒望</a>
							<a href="#">合作伙伴</a>
							<a href="#">联系我们</a>
							<a href="#">网站地图</a>
							<em>© 2015-2025 Hengwang.com 版权所有. 更多模板 <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></em>
						</p>
					</div>
				</div>
			</div>

			<!--左侧菜单menu-->
			<%@include file="/person/menu.jsp" %>
			
			
		</div>

	</body>

</html>