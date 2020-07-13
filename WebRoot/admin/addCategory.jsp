<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/admin/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/select-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) {

		$(".select1").uedSelect({
			width : 280
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});

	});
	
	function validateName(){
		var name = $("input[name='name']");
		var result = $("#result");//用于显示提示信息
		result.html("");
		if(name.val()==null||$.trim(name.val())==""){
			result.html("分类号不能为空！");
			return false;
		}
		return true;
	}
	
	function validateSort(){
		var sort = $("input[name='sort']");
		var result = $("#result");//用于显示提示信息
		result.html("");
		//非空验证
		if(sort.val()==null||$.trim(sort.val())==""){
			result.html("分类排序号不能为空！");
			return false;
		}
		//是否是正整数
		var regix = new RegExp("^\\+?[1-9][0-9]*$");//正整数
		//test返回true或false，符合规则返回true
		if(!regix.test($.trim(sort.val()))){//如果不是正整数
			result.html("分类排序号必须是正整数！");
			return false;
		}
		return true;
	}
	//单击添加按钮，进行验证
	function validate(){
		if(validateName()&&validateSort()){
			//通过验证
			$("#form1").submit();//提交表单
		}
	}
</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a>
			</li>
			<li><a href="#">添加分类</a>
			</li>
		</ul>
	</div>
	<form action="<c:url value='/categoryManageServlet'/>" method="post" id="form1">
		<input type="hidden" name="action" value="add"/>
		<div class="formbody">
			<div class="formtitle">
				<span>添加分类</span>
			</div>

			<ul class="forminfo">
				<li><label>分类名称<b>*</b>
				</label><input name="name" type="text" class="dfinput" style="width:278px;" onblur="validateName()"/>
				</li>
				<li><label>商品分类排序<b>*</b>
				</label>
					<div class="vocation">
						<input name="sort" type="text" class="dfinput" style="width:278px;" onblur="validateSort()"/>
					</div></li>
					<li><label id="result" style="width:278px;color:red" >${msg}</label>
				</li>
				<li><label >&nbsp;</label><input name="" type="button"
					class="btn" value="添加" onclick="validate()"/>
				</li>
			</ul>

		</div>
	</form>
</body>
</html>
